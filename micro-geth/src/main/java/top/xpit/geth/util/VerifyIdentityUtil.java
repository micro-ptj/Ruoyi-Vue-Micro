package top.xpit.geth.util;

import lombok.extern.slf4j.Slf4j;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import top.xpit.common.constant.GethConstants;
import top.xpit.geth.domain.query.AppIdentityVerifyParam;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Author: ptj
 * @Date: 2023/04/18/12:01
 * @Description:
 */
@Slf4j
public class VerifyIdentityUtil {

    public static String verify(AppIdentityVerifyParam param) throws IOException {
        String url = GethConstants.VERIFY_URL;
        String appCode = GethConstants.VERIFY_APP_CODE;

        Map<String, String> params = new HashMap<>();
        params.put("idcard", param.getIdCard());
        params.put("name", param.getName());
        params.put("mobile", param.getPhone());

        String result = postForm(appCode, url, params);
        return result;
    }

    /**
     * 用到的HTTP工具包：okhttp 3.13.1
     * <dependency>
     * <groupId>com.squareup.okhttp3</groupId>
     * <artifactId>okhttp</artifactId>
     * <version>3.13.1</version>
     * </dependency>
     */
    public static String postForm(String appCode, String url, Map<String, String> params) throws IOException {
        OkHttpClient client = new OkHttpClient.Builder().build();
        FormBody.Builder formbuilder = new FormBody.Builder();
        Iterator<String> it = params.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();
            formbuilder.add(key, params.get(key));
        }
        FormBody body = formbuilder.build();
        Request request = new Request.Builder().url(url).addHeader("Authorization", "APPCODE " + appCode).post(body).build();
        Response response = client.newCall(request).execute();
        log.debug("返回状态码" + response.code() + ",message:" + response.message());
        if (response.code() == 400){
            throw new RuntimeException("参数错误");
        } else if (response.code() == 404) {
            throw new RuntimeException("请求资源不存在");
        } else if (response.code() == 500) {
            throw new RuntimeException("系统内部错误，请联系服务商");
        } else if (response.code() == 501) {
            throw new RuntimeException("第三方服务异常");
        } else if (response.code() == 604) {
            throw new RuntimeException("接口停用");
        }else if (response.code() == 1001){
            throw new RemoteException("其他，以实际返回为准");
        }else {
            String result = response.body().string();
            return result;
        }
    }
}
