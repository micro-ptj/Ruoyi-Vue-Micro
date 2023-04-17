package top.xpit.geth.domain.query;

import lombok.Data;
import lombok.ToString;
import org.apache.commons.collections4.Get;
import top.xpit.common.utils.DateUtils;
import top.xpit.common.utils.GethDateUtils;

import java.math.BigInteger;
import java.text.ParseException;
import java.util.Date;

/**
 * @Author: PTJ
 * @Date: 2023/04/02/23:48
 * @Description:
 */
@Data
@ToString
public class CreateGoodsParam {
    private BigInteger goodsId;
    private Long userId;

    private BigInteger interval;

    private String address;
    private String escrowAddress;
    private BigInteger startTimeStamp;
    private Date startTime;
    private Date endTime;


    public void setStartTimeStamp(Date startTime) throws ParseException {
        this.startTimeStamp = GethDateUtils.dateToTimestamp(startTime);
        BigInteger timestamp = this.startTimeStamp.add(this.interval);
        this.endTime = GethDateUtils.timestampToDate(timestamp);
    }

}
