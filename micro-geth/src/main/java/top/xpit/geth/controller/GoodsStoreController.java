package top.xpit.geth.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import top.xpit.common.core.controller.BaseController;
import top.xpit.common.core.domain.AjaxResult;
import top.xpit.geth.domain.query.CreateGoodsParam;
import top.xpit.geth.service.GoodsStoreService;

import java.math.BigInteger;

/**
 * @Author: ptj
 * @Date: 2023/04/02/22:40
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping("geth")
@RequiredArgsConstructor
public class GoodsStoreController extends BaseController {

    private final GoodsStoreService goodsStoreService;

//    @GetMapping("bid")
//    public AjaxResult bid(BigInteger id, BigInteger weiValue){
//        return goodsStoreService.bid(id, weiValue);
//    }


    /**
     * 释放资金
     */
    @GetMapping("withdraw/{id}")
    public AjaxResult withdraw(@PathVariable("id") BigInteger id){
        return goodsStoreService.withdraw(id);
    }

    /**
     * 结束拍卖
     */
    @GetMapping("auctionEnd/{id}")
    public AjaxResult auctionEnd(@PathVariable("id") BigInteger id){
        return goodsStoreService.auctionEnd(id);
    }
}
