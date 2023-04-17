package top.xpit.framework.security.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import top.xpit.common.core.domain.model.AppLoginUser;
import top.xpit.common.core.domain.model.LoginUser;
import top.xpit.common.utils.SecurityUtils;
import top.xpit.common.utils.StringUtils;
import top.xpit.framework.web.service.AppLoginService;
import top.xpit.framework.web.service.TokenService;

/**
 * token过滤器 验证token有效性
 * 
 * @author PTJ
 */
@Slf4j
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter
{
    @Autowired
    private TokenService tokenService;

    @Autowired
    private AppLoginService appLoginService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException
    {
//        log.info(String.valueOf(request));
        //如果请求头中含有app
        String token = appLoginService.getToken(request);
        if (StringUtils.isNotNull(token)){
            //app
//            log.info("---------->App");
            AppLoginUser appLoginUser = appLoginService.getAppLoginUser(request);
//            log.info(appLoginUser.toString());
            if (StringUtils.isNotNull(appLoginUser)){
                appLoginService.verifyToken(appLoginUser);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(appLoginUser, null, null);
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }else {
            //管理后台
//            log.info("---------->Manager");
            LoginUser loginUser = tokenService.getLoginUser(request);
            if (StringUtils.isNotNull(loginUser) && StringUtils.isNull(SecurityUtils.getAuthentication()))
            {
                tokenService.verifyToken(loginUser);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        chain.doFilter(request, response);
    }
}
