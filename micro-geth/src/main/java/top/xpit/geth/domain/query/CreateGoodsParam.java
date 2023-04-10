package top.xpit.geth.domain.query;

import lombok.Data;

import java.math.BigInteger;

/**
 * @Author: ptj
 * @Date: 2023/04/02/23:48
 * @Description:
 */
@Data
public class CreateGoodsParam {
    private BigInteger goodsId;

    private BigInteger interval;

    private String address;

}
