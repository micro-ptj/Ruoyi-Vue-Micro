package top.xpit.geth.service;

import top.xpit.geth.domain.MicroBid;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @Author: PTJ
 * @Date: 2023/04/16/16:51
 * @Description:
 */
public interface EscrowService {

    String escrowAddress(BigInteger id);
    boolean bid(BigInteger id, BigDecimal weiValue, MicroBid microBid);
    boolean withdraw(BigInteger id, Long orderId, Long userId);
    void auctionEnd(BigInteger id);

    boolean ended(BigInteger goodsId);
}
