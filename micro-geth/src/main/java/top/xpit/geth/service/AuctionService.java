package top.xpit.geth.service;

import top.xpit.geth.domain.query.AppBidParam;

/**
 * @Author: ptj
 * @Date: 2023/04/15/14:41
 * @Description:
 */
public interface AuctionService {
    boolean bids(AppBidParam param);
}
