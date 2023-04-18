package top.xpit.sms.util;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import com.tencentcloudapi.sms.v20210111.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20210111.models.SendSmsResponse;
import top.xpit.sms.contracts.SmsContracts;

/**
 * @Author: ptj
 * @Date: 2023/03/07/0:18
 * @Description:
 */
public class SendSms {
    public static String send(Long phone, String code) {
        try {
            Credential cred = new Credential(SmsContracts.SMS_SECRET_ID, SmsContracts.SMS_SECRET_KEY);

            HttpProfile httpProfile = new HttpProfile();
            // 设置代理（无需要直接忽略）
            // httpProfile.setProxyHost("真实代理ip");
            // httpProfile.setProxyPort(真实代理端口);
            /* SDK默认使用POST方法。
             * 如果你一定要使用GET方法，可以在这里设置。GET方法无法处理一些较大的请求 */
            httpProfile.setReqMethod("POST");
            httpProfile.setConnTimeout(60);
            httpProfile.setEndpoint(SmsContracts.SMS_ENDPOINT);
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setSignMethod("HmacSHA256");
            clientProfile.setHttpProfile(httpProfile);
            SmsClient client = new SmsClient(cred, SmsContracts.SMS_REGION,clientProfile);
            SendSmsRequest req = new SendSmsRequest();
            req.setSmsSdkAppId(SmsContracts.SMS_SDK_APP_ID);
            req.setSignName(SmsContracts.SMS_SIGN_NAME);
            req.setTemplateId(SmsContracts.SMS_TEMPLATE_ID);
            String[] templateParamSet = {code};
            req.setTemplateParamSet(templateParamSet);
            String[] phoneNumberSet = {"+86"+ phone};
            req.setPhoneNumberSet(phoneNumberSet);
            String sessionContext = "";
            req.setSessionContext(sessionContext);
            req.setExtendCode("");
            /* 国际/港澳台短信 SenderId（无需要可忽略）: 国内短信填空，默认未开通，如需开通请联系 [腾讯云短信小助手] */
            req.setSenderId("");
            SendSmsResponse res = client.SendSms(req);
            return SendSmsResponse.toJsonString(res);
        } catch (TencentCloudSDKException e) {
            return e.getMessage();
        }
    }
}
