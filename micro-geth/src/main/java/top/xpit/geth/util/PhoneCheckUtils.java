package top.xpit.geth.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * @Author: ptj
 * @Date: 2023/04/18/20:01
 * @Description:
 */
public class PhoneCheckUtils {
 
    /**
     * 号码长度校验
     * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数
     */
    public static boolean isPhoneLegal(Long phone) throws PatternSyntaxException {
        if (phone.toString().length() != 11){
            return false;
        }
        String regex = "^((13[0-9])|(14[05679])|(15([0-3,5-9]))|(16[2567])|(17[01235678])|(18[0-9]|19[135689]))\\d{8}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(phone.toString());
        boolean isMatch = m.matches();
        return isMatch;
    }
}
