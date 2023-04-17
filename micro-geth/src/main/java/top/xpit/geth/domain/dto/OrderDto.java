package top.xpit.geth.domain.dto;

import lombok.Data;

import java.math.BigInteger;

/**
 * @Author: PTJ
 * @Date: 2023/04/16/12:50
 * @Description:
 */
@Data
public class OrderDto {

    private Long userId;
    private Long goodsId;
    private String address;
    private BigInteger amount;
}
