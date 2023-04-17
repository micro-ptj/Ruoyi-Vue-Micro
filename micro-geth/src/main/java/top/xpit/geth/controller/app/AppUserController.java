package top.xpit.geth.controller.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.xpit.common.core.controller.BaseController;
import top.xpit.common.core.domain.AjaxResult;
import top.xpit.common.core.domain.model.AppLoginUser;
import top.xpit.common.utils.SecurityUtils;
import top.xpit.geth.service.IMicroAppUserService;

/**
 * @Author: PTJ
 * @Date: 2023/03/12/21:40
 * @Description:
 */
@RequestMapping("/app")
@RestController
public class AppUserController extends BaseController {

    @Autowired
    private IMicroAppUserService microAppUserService;

    @GetMapping("/userInfo")
    public AjaxResult userInfo() {
        return success(microAppUserService.selectUserInfo());
    }
}
