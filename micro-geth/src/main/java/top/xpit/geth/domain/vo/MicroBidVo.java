package top.xpit.geth.domain.vo;

import lombok.Data;
import top.xpit.geth.domain.MicroBid;

/**
 * @Author: PTJ
 * @Date: 2023/04/21/21:22
 * @Description:
 */
@Data
public class MicroBidVo extends MicroBid {
    private String userName;
    private String phone;
    private String goodsName;
}
