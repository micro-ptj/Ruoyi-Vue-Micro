package top.xpit.framework.web.service;

import eu.bitwalker.useragentutils.UserAgent;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import top.xpit.common.core.domain.model.AppLoginUser;
import top.xpit.common.core.domain.model.LoginUser;
import top.xpit.common.core.redis.RedisCache;
import top.xpit.common.utils.ServletUtils;
import top.xpit.common.utils.StringUtils;
import top.xpit.common.utils.ip.AddressUtils;
import top.xpit.common.utils.ip.IpUtils;
import top.xpit.common.utils.uuid.IdUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * token验证处理
 *
 * @author PTJ
 */
@Component
public class AppLoginService
{
    // 令牌自定义标识
    @Value("${app.token.header}")
    private String header;

    // 令牌秘钥
    @Value("${app.token.secret}")
    private String secret;

    // 令牌有效期（默认30分钟）
    @Value("${app.token.expireTime}")
    private int expireTime;

    protected static final long MILLIS_SECOND = 1000;

    protected static final long MILLIS_MINUTE = 60 * MILLIS_SECOND;

    private static final Long MILLIS_MINUTE_TEN = 20 * 60 * 1000L;

    private static final String APP_PREFIX = "app";

    private static final String TOKEN_KEY = "app_token:";

    @Autowired
    private RedisCache redisCache;

    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
    public AppLoginUser getAppLoginUser(HttpServletRequest request)
    {
        // 获取请求携带的令牌
        String token = getToken(request);
        if (StringUtils.isNotEmpty(token))
        {
            try
            {
                Claims claims = parseToken(token);
                // 解析对应的权限以及用户信息
                String uuid = (String) claims.get(TOKEN_KEY);
                String userKey = getTokenKey(uuid);
                AppLoginUser user = redisCache.getCacheObject(userKey);
                return user;
            }
            catch (Exception e)
            {
            }
        }
        return null;
    }

    /**
     * 设置用户身份信息
     */
    public void setAppLoginUser(AppLoginUser appLoginUser)
    {
        if (StringUtils.isNotNull(appLoginUser) && StringUtils.isNotEmpty(appLoginUser.getUuid()))
        {
            refreshToken(appLoginUser);
        }
    }

    /**
     * 删除用户身份信息
     */
    public void delAppLoginUser(String token)
    {
        if (StringUtils.isNotEmpty(token))
        {
            Claims claims = parseToken(token);
            String uuid = (String) claims.get(APP_PREFIX);
            String userKey = getTokenKey(uuid);  //拼接 app_token:xxxx-xx-xxxx
            redisCache.deleteObject(userKey);
        }
    }

    /**
     * 创建令牌
     *
     * @param appLoginUser 用户信息
     * @return 令牌
     */
    public String createToken(AppLoginUser appLoginUser)
    {
        String uuid = IdUtils.fastUUID();
        appLoginUser.setUuid(uuid);
        setUserAgent(appLoginUser);
        refreshToken(appLoginUser);

        Map<String, Object> claims = new HashMap<>();
        claims.put(TOKEN_KEY, uuid);
        return createToken(claims);
    }

    /**
     * 验证令牌有效期，相差不足20分钟，自动刷新缓存
     *
     * @param appLoginUser
     * @return 令牌
     */
    public void verifyToken(AppLoginUser appLoginUser)
    {
        long expireTime = appLoginUser.getExpireTime();
        long currentTime = System.currentTimeMillis();
        if (expireTime - currentTime <= MILLIS_MINUTE_TEN)
        {
            refreshToken(appLoginUser);
        }
    }

    /**
     * 刷新令牌有效期
     *
     * @param appLoginUser 登录信息
     */
    public void refreshToken(AppLoginUser appLoginUser)
    {
        appLoginUser.setLoginTime(System.currentTimeMillis());
        appLoginUser.setExpireTime(appLoginUser.getLoginTime() + expireTime * MILLIS_MINUTE);
        // 根据uuid将loginUser缓存
        String userKey = getTokenKey(appLoginUser.getUuid());
        redisCache.setCacheObject(userKey, appLoginUser, expireTime, TimeUnit.MINUTES);
    }

    /**
     * 设置用户代理信息
     *
     * @param appLoginUser 登录信息
     */
    public void setUserAgent(AppLoginUser appLoginUser)
    {
        UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        String ip = IpUtils.getIpAddr();
        appLoginUser.setIpaddr(ip);
        appLoginUser.setLoginLocation(AddressUtils.getRealAddressByIP(ip));
        appLoginUser.setBrowser(userAgent.getBrowser().getName());
        appLoginUser.setOs(userAgent.getOperatingSystem().getName());
    }

    /**
     * 从数据声明生成令牌
     *
     * @param claims 数据声明
     * @return 令牌
     */
    private String createToken(Map<String, Object> claims)
    {
        String token = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret).compact();
        return token;
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    private Claims parseToken(String token)
    {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }


    /**
     * 获取请求token
     *
     * @param request
     * @return token
     */
    public String getToken(HttpServletRequest request)
    {
        String token = request.getHeader(header);
        if (StringUtils.isNotEmpty(token) && token.startsWith(APP_PREFIX))
        {
            token = token.replace(APP_PREFIX, "");
            return token;
        }else {
            return null;
        }
    }

    private String getTokenKey(String uuid)
    {
        return TOKEN_KEY + uuid;
    }

}
