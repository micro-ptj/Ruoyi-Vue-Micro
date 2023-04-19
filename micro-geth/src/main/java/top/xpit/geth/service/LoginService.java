package top.xpit.geth.service;

import top.xpit.geth.domain.query.AppLoginUserParam;
import top.xpit.geth.domain.query.AppRegisterUserParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: PTJ
 * @Date: 2023/03/12/17:29
 * @Description:
 */
public interface LoginService {

    String login(AppLoginUserParam param);

    int register(AppRegisterUserParam param);

    boolean code(Long phone, String type);
}
