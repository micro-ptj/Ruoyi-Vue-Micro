package top.xpit.geth.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import top.xpit.geth.util.PhoneCheckUtils;
import top.xpit.sms.util.SendSms;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Objects;

/**
 * @Author: PTJ
 * @Date: 2023/03/12/17:30
 * @Description:
 */
@Slf4j
@Service("loginService")
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final MicroAppUserMapper microAppUserMapper;

    private final MicroUserInfoMapper microUserInfoMapper;

    private final AppLoginService appLoginService;

    private final GethService gethService;

    @Override
    public String login(AppLoginUserParam param, HttpServletRequest request) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (Objects.isNull(param)){
            throw new NullPointerException("用户名密码不能为空");
        }else {
            MicroAppUser appUser = microAppUserMapper.selectByPhone(param.getPhone());
            if (Objects.isNull(appUser)){
                throw new UserNotExistsException();
            }
            if (param.getCode() == null && param.getPassword() != null){
                if (passwordEncoder.matches(param.getPassword(), appUser.getPassword())){
                    return loginToken(appUser);
                }else {
                    throw new UserPasswordNotMatchException();
                }
            }else {
                HttpSession session = request.getSession();
                String code = (String) session.getAttribute("code.login");
                if (param.getCode().equals(code)){
                    return loginToken(appUser);
                }else {
                    throw new RuntimeException("验证码错误");
                }
            }
        }
    }

    private String loginToken(MicroAppUser appUser) {
        MicroUserInfo userInfo = microUserInfoMapper.selectByUserId(appUser.getId());
        AppLoginUser appLoginUser = new AppLoginUser();
        appLoginUser.setPhone(appUser.getPhone());
        appLoginUser.setUserAddress(userInfo.getAddress());
        appLoginUser.setUserId(appUser.getId());
        appLoginUser.setPrivateKey(userInfo.getPrivateKey());
        String token = appLoginService.createToken(appLoginUser);
        return "app " + token;
    }

    /**
     * 用户注册
     * @param param
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int register(AppRegisterUserParam param, HttpServletRequest request) {
        MicroAppUser appUser1 = microAppUserMapper.selectByPhone(param.getPhone());
        if (Objects.nonNull(appUser1)){
            throw new RuntimeException("手机号已经存在");
        }
        HttpSession session = request.getSession();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if (Objects.isNull(param)){
            throw new RuntimeException("请输入内容");
        }else if (param.getPassword().trim().length() < 5){
            throw new RuntimeException("密码长度不能小于五位");
        } else if (param.getCode() == null) {
            throw new RuntimeException("请输入验证码");
        }else {
            String code = (String) session.getAttribute("code.register");
            if (code == null){
                throw new RuntimeException("验证码失效");
            } else if (!code.equals(param.getCode())){
                throw new RuntimeException("验证码错误");
            }else {
                MicroAppUser appUser = new MicroAppUser();
                appUser.setPhone(param.getPhone());
                appUser.setPassword(passwordEncoder.encode(param.getPassword().trim()));
                appUser.setUsername("micro");
                if (param.getAvatar() != null){
                    appUser.setAvatar(param.getAvatar());
                }
                microAppUserMapper.insertMicroAppUser(appUser);
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
                return microUserInfoMapper.insertMicroUserInfo(account);

            }
        }
    }

    @Override
    public boolean code(Long phone, String type, HttpServletRequest request) {
        //校验数据
        boolean phoneLegal = PhoneCheckUtils.isPhoneLegal(phone);
        if (!phoneLegal){
            throw new RuntimeException("手机号码格式错误");
        }
        //检测手机号是否存在
        MicroAppUser appUser = microAppUserMapper.selectByPhone(phone);
        if (Objects.isNull(appUser)){
            throw new RuntimeException("手机号不存在");
        }else {
            String code = String.valueOf(Math.round((Math.random() + 1) * 1000));
            HttpSession session = request.getSession();
            session.setAttribute("code." + type, code);
            String send = SendSms.send(phone, code);
            log.debug(send);
            return true;
        }
    }
}
