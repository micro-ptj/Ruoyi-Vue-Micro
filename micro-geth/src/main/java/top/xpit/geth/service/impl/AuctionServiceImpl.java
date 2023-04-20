package top.xpit.geth.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.xpit.common.utils.DateUtils;
import top.xpit.geth.domain.MicroBid;
import top.xpit.geth.domain.MicroGoods;
import top.xpit.geth.domain.MicroOrder;
import top.xpit.geth.domain.dto.OrderDto;
import top.xpit.geth.domain.query.AppBidParam;
import top.xpit.geth.mapper.MicroBidMapper;
import top.xpit.geth.mapper.MicroGoodsMapper;
import top.xpit.geth.mapper.MicroOrderMapper;
import top.xpit.geth.mapper.MicroUserInfoMapper;
import top.xpit.geth.service.AuctionService;
import top.xpit.geth.service.EscrowService;
import top.xpit.geth.service.GoodsStoreService;
import top.xpit.geth.service.IMicroBidService;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/**
 * @Author: PTJ
 * @Date: 2023/04/15/14:42
 * @Description:
 */
@Slf4j
@Service(value = "auctionService")
@RequiredArgsConstructor
public class AuctionServiceImpl implements AuctionService {

    private final IMicroBidService microBidService;
    private final MicroUserInfoMapper microUserInfoMapper;
    private final GoodsStoreService goodsStoreService;
    private final MicroGoodsMapper microGoodsMapper;
    private final EscrowService escrowService;
    private final MicroOrderMapper microOrderMapper;
    private final MicroBidMapper microBidMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean bids(AppBidParam param) {
        //竞拍价不能小于之前出价 或者商品价格
        BigDecimal amount = param.getAmount();
        MicroGoods goods = microGoodsMapper.selectMicroGoodsById(param.getGoodsId());
        //查询用户对于该商品的最高价
        BigDecimal bigDecimal = microBidMapper.selectHighestPrice(param);
        if (amount.compareTo(goods.getStartPrice()) <= 0){
            throw new RuntimeException("出价不能小于拍品价格");
        } else if (bigDecimal != null) {
            if (amount.compareTo(bigDecimal) <= 0) {
                throw new RuntimeException("出价不能小于上次出价");
            }else {
                log.debug("出价有效");
            }
        }
        MicroBid bids = microBidService.bids(param);
        escrowService.bid(BigInteger.valueOf(param.getGoodsId()), param.getAmount(), bids);
        return true;
    }

    /**
     * 结束竞拍
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean auctionEnd(BigInteger goodsId) {
        // 结束竞拍
        //判断当前商品区块链上是否结束
        boolean ended = escrowService.ended(goodsId);
        if (!ended){
            escrowService.auctionEnd(goodsId);
        }
        //生成订单
        /*-------------start------------------*/
        //获取成功的用户
        OrderDto orderDto = goodsStoreService.winnerBidder(goodsId);
        //根据地址查询用户id
        Long userId = microUserInfoMapper.selectByAddress(orderDto.getAddress());
        log.debug("用户id为----------------------->" + userId.toString());
        //查询订单是否添加过 每个用户对应一个商品订单
        int i = microOrderMapper.selectByTask(userId, goodsId.longValue());
        log.debug(i == 0 ? "未生成订单" : "已生成订单");
        if (i == 0){
            orderDto.setUserId(userId);
            orderDto.setGoodsId(goodsId.longValue());
            //修改竞拍状态为成功
            microBidMapper.updateBidStatus(orderDto);
            //修改竞拍其他竞拍为失败
            microBidMapper.updateByFail(orderDto);
            //添加订单
            MicroOrder microOrder = new MicroOrder();
            microOrder.setUserId(userId);
            microOrder.setAmount(BigDecimal.valueOf(orderDto.getAmount().longValue()));
            microOrder.setOrderTime(DateUtils.getNowDate());
            microOrder.setGoodsId(goodsId.longValue());
            microOrder.setStatus(0L);
            microOrder.setDelFlag(0L);
            microOrderMapper.insertMicroOrder(microOrder);
            /*-------------end------------------*/
            //对所有用户返还资金
            List<Long> userIds = microBidMapper.selectWithdraw(goodsId.longValue());
            userIds.stream().forEach(e -> {
                escrowService.withdraw(goodsId, microOrder.getId(), e);
            });
        }else {
            log.info("该笔订单已经添加");
        }
        return true;
    }
}
