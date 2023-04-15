package top.xpit.geth.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.xpit.geth.domain.MicroBid;
import top.xpit.geth.domain.query.AppBidParam;
import top.xpit.geth.service.AuctionService;
import top.xpit.geth.service.GoodsStoreService;
import top.xpit.geth.service.IMicroBidService;

import java.math.BigInteger;

/**
 * @Author: ptj
 * @Date: 2023/04/15/14:42
 * @Description:
 */
@Service(value = "auctionService")
@RequiredArgsConstructor
public class AuctionServiceImpl implements AuctionService {

    private final IMicroBidService microBidService;

    private final GoodsStoreService goodsStoreService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean bids(AppBidParam param) {
        MicroBid bids = microBidService.bids(param);
        goodsStoreService.bid(BigInteger.valueOf(param.getGoodsId()), param.getAmount(), bids);
        return true;
    }
}
