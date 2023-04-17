package top.xpit.geth.domain.query;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: PTJ
 * @Date: 2023/04/15/14:08
 * @Description:
 */
@Data
public class AppBidParam {
    private Long userId;
    private Long goodsId;
    private BigDecimal amount;
}
