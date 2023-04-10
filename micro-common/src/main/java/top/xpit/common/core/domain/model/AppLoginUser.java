package top.xpit.common.core.domain.model;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import top.xpit.common.core.domain.entity.SysUser;

import java.util.Collection;
import java.util.Set;

/**
 * 登录用户身份权限
 * 
 * @author ruoyi
 */
@ToString
@Data
public class AppLoginUser
{
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long userId;

    private String privateKey;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 用户唯一标识
     */
    private String uuid;

    /**
     * 登录时间
     */
    private Long loginTime;

    /**
     * 过期时间
     */
    private Long expireTime;

    /**
     * 登录IP地址
     */
    private String ipaddr;

    /**
     * 登录地点
     */
    private String loginLocation;

    /**
     * 浏览器类型
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    private String userAddress;

    private Long phone;

}
