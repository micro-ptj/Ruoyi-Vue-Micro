package top.xpit.geth.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.web3j.crypto.CipherException;
import top.xpit.common.core.domain.model.AppLoginUser;
import top.xpit.common.exception.user.UserNotExistsException;
import top.xpit.common.exception.user.UserPasswordNotMatchException;
import top.xpit.framework.web.service.AppLoginService;
import top.xpit.geth.domain.MicroUserInfo;
import top.xpit.geth.domain.query.AppLoginUserParam;
import top.xpit.geth.domain.MicroAppUser;
import top.xpit.geth.domain.query.AppRegisterUserParam;
import top.xpit.geth.mapper.MicroAppUserMapper;
import top.xpit.geth.mapper.MicroUserInfoMapper;
import top.xpit.geth.service.LoginService;
import top.xpit.geth.service.GethService;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Objects;

/**
 * @Author: ptj
 * @Date: 2023/03/12/17:30
 * @Description:
 */
@Service("loginService")
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final MicroAppUserMapper microAppUserMapper;

    private final MicroUserInfoMapper microUserInfoMapper;

    private final AppLoginService appLoginService;

    private final GethService gethService;

    @Override
    public String login(AppLoginUserParam param) {
        if (Objects.isNull(param)){
            throw new NullPointerException("用户名密码不能为空");
        }else {
            MicroAppUser appUser = microAppUserMapper.selectByPhone(param.getPhone());
            if (Objects.isNull(appUser)){
                throw new UserNotExistsException();
            }else {
                if (appUser.getPassword().equals(param.getPassword())){
                    MicroUserInfo userInfo = microUserInfoMapper.selectByUserId(appUser.getId());


                    AppLoginUser appLoginUser = new AppLoginUser();
                    appLoginUser.setPhone(appUser.getPhone());
                    appLoginUser.setUserAddress(userInfo.getAddress());
                    appLoginUser.setUserId(appUser.getId());
                    appLoginUser.setPrivateKey(userInfo.getPrivateKey());
                    String token = appLoginService.createToken(appLoginUser);
                    return "app " + token;
                }else {
                    throw new UserPasswordNotMatchException();
                }
            }
        }
    }

    /**
     * 用户注册
     * @param param
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean register(AppRegisterUserParam param) {
        if (Objects.isNull(param)){
            throw new RuntimeException("请输入内容");
        }else {
            MicroAppUser appUser1 = microAppUserMapper.selectByPhone(param.getPhone());
            if (Objects.nonNull(appUser1)){
                throw new RuntimeException("手机号已经存在");
            }
            MicroAppUser appUser = new MicroAppUser();
            appUser.setPhone(param.getPhone());
            appUser.setPassword(param.getPassword());
            int i = microAppUserMapper.insertMicroAppUser(appUser);
            MicroUserInfo account = new MicroUserInfo();
            try {
                account = gethService.createAccount();
            } catch (InvalidAlgorithmParameterException e) {
                throw new RuntimeException(e);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            } catch (NoSuchProviderException e) {
                throw new RuntimeException(e);
            } catch (CipherException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            int j = microUserInfoMapper.insertMicroUserInfo(account);
            if (j != 1){
                throw new RuntimeException("创建失败");
            }else {
                return true;
            }
        }
    }
}
