package top.xpit.geth.controller.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.xpit.common.core.controller.BaseController;
import top.xpit.common.core.domain.AjaxResult;
import top.xpit.common.core.domain.model.AppLoginUser;
import top.xpit.common.core.redis.RedisCache;
import top.xpit.common.utils.SecurityUtils;
import top.xpit.framework.web.service.AppLoginService;
import top.xpit.geth.domain.query.AppLoginUserParam;
import top.xpit.geth.domain.query.AppRegisterUserParam;
import top.xpit.geth.service.LoginService;

import java.util.HashMap;

/**
 * @Author: PTJ
 * @Date: 2023/03/12/16:06
 * @Description:
 */
@RestController
@RequestMapping("/app")
public class LoginController extends BaseController {

    @Autowired
    private LoginService appLoginService;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private AppLoginService loginService;

    @PostMapping("/login")
    public AjaxResult login(@RequestBody AppLoginUserParam param) {
        String token = appLoginService.login(param);
        HashMap<String, Object> map = new HashMap<>();
        map.put("token", token);
        return success(map);
    }

    @GetMapping("/logout")
    public AjaxResult logout() {
        redisCache.deleteObject("app_token");
        return AjaxResult.success("logout");
    }

    /**
     * 刷新token
     * @return
     */
    @GetMapping("/verify")
    public AjaxResult verify() {
        AppLoginUser appUserInfo = SecurityUtils.getAppUserInfo();
        loginService.refreshToken(appUserInfo);
        return success();
    }

    @PostMapping("/register")
    public AjaxResult register(@RequestBody AppRegisterUserParam param){
        int i = appLoginService.register(param);
        return toAjax(i);
    }

    @GetMapping(value = "code", name = "验证码发送")
    public AjaxResult code(Long phone, String type){
        return success(appLoginService.code(phone, type));
    }
}
