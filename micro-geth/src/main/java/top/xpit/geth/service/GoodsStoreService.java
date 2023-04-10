package top.xpit.geth.service;

import top.xpit.common.core.domain.AjaxResult;
import top.xpit.geth.domain.query.CreateGoodsParam;

import java.math.BigInteger;

/**
 * @Author: ptj
 * @Date: 2023/04/02/22:38
 * @Description:
 */
public interface GoodsStoreService {
    AjaxResult bid(BigInteger id, BigInteger weiValue);

    AjaxResult createGoods(CreateGoodsParam param);

    AjaxResult withdraw(BigInteger id);

    AjaxResult auctionEnd(BigInteger id);
}
