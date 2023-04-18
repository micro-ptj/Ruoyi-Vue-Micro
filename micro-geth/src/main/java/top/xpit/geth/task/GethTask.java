package top.xpit.geth.task;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.xpit.geth.domain.MicroGoods;
import top.xpit.geth.mapper.MicroGoodsMapper;
import top.xpit.geth.service.AuctionService;
import top.xpit.geth.service.GethService;
import top.xpit.geth.service.IMicroGoodsService;

import java.math.BigInteger;
import java.util.List;

/**
 * @Author: PTJ
 * @Date: 2023/04/16/17:30
 * @Description:
 */
@Slf4j
@Component("gethTask")
@RequiredArgsConstructor
public class GethTask {
    private final IMicroGoodsService microGoodsService;

    private final AuctionService auctionService;

    private final GethService gethService;

    public void test() {
        System.out.println("------------------------------>task is start");
    }

    /**
     * 查询用户余额
     */
    public void queryBalance() {
        gethService.balance();
    }

    /**
     * 查询是否有拍卖结束的商品
     * 对其进行资金释放
     */
    public void queryGoodsEnd() {
        List<Long> goods = microGoodsService.queryGoodsEnd();
        try {
            Thread.sleep(500L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (goods.size() != 0){
            log.info(String.format("当前时间结束拍卖的商品数量为%s", goods.size()));
            goods.stream().forEach(e -> {
                try {
                    Thread.sleep(200L);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                auctionService.auctionEnd(BigInteger.valueOf(e));
            });
        }else {
//            log.info(String.format("当前时间没有需要结束拍卖的商品", goods.size()));
        }
    }
}
