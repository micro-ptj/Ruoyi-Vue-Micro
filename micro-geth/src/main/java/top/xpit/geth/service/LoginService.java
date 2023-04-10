package top.xpit.geth.service;

import top.xpit.geth.domain.query.AppLoginUserParam;
import top.xpit.geth.domain.query.AppRegisterUserParam;

/**
 * @Author: ptj
 * @Date: 2023/03/12/17:29
 * @Description:
 */
public interface LoginService {

    String login(AppLoginUserParam param);

    boolean register(AppRegisterUserParam param);
}
