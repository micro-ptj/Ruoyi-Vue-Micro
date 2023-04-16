package top.xpit.geth.service;

import org.web3j.protocol.core.methods.response.TransactionReceipt;
import top.xpit.common.core.domain.AjaxResult;
import top.xpit.geth.contract.GoodsStore;
import top.xpit.geth.domain.MicroBid;
import top.xpit.geth.domain.dto.OrderDto;
import top.xpit.geth.domain.query.CreateGoodsParam;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @Author: ptj
 * @Date: 2023/04/02/22:38
 * @Description:
 */
public interface GoodsStoreService {
    GoodsStore systemGoodsStore();
    boolean bid(BigInteger id, BigDecimal amount, MicroBid microBid);

    boolean createGoods(CreateGoodsParam param);

    boolean withdraw(BigInteger id, Long orderId);

    void auctionEnd(BigInteger id);

    public OrderDto winnerBidder(BigInteger id);

    public void saveTransaction(TransactionReceipt send, String sourceTye, Long sourceId, String contractAddress);
}
