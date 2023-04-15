package top.xpit.common.utils;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间戳转化为简单时间格式
 * @author Pu Tongjiao
 * @date 2022/9/27 15:03
 */
public class GethDateUtils {
    public static Date timestampToDate(BigInteger date) {

        return new Date(Long.parseLong(String.valueOf(date) + "000"));
    }

    public static BigInteger dateToTimestamp(Date date) throws ParseException {
        long time = date.getTime() / 1000;
        return BigInteger.valueOf(time);
    }
}
