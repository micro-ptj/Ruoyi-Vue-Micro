package top.xpit.geth.domain.vo;

import lombok.Data;
import top.xpit.geth.domain.MicroOrder;

/**
 * @Author: PTJ
 * @Date: 2023/04/21/21:11
 * @Description:
 */
@Data
public class MicroOrderVo extends MicroOrder {
    private String userName;
    private String phone;
    private String goodsName;
}
