package top.xpit.geth.controller.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.xpit.common.core.controller.BaseController;
import top.xpit.common.core.domain.AjaxResult;
import top.xpit.geth.domain.query.AppIdentityVerifyParam;
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

    @PostMapping(value = "/identityVerify", name = "实名认证")
    public AjaxResult identityVerify(@RequestBody AppIdentityVerifyParam param) {
        return success(microAppUserService.identityVerify(param));
    }
}
