package top.xpit.geth.service.impl;

import java.math.BigInteger;
import java.text.ParseException;
import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import top.xpit.common.constant.CacheConstants;
import top.xpit.common.constant.GethConstants;
import top.xpit.common.core.domain.AjaxResult;
import top.xpit.common.core.redis.RedisCache;
import top.xpit.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.xpit.common.utils.SecurityUtils;
import top.xpit.geth.domain.query.CreateGoodsParam;
import top.xpit.geth.mapper.MicroGoodsMapper;
import top.xpit.geth.domain.MicroGoods;
import top.xpit.geth.service.EscrowService;
import top.xpit.geth.service.GoodsStoreService;
import top.xpit.geth.service.IMicroGoodsService;
import top.xpit.system.service.ISysConfigService;

/**
 * 商品信息Service业务层处理
 * 
 * @author PTJ
 * @date 2023-04-02
 */
@Service("microGoodsService")
@Slf4j
@RequiredArgsConstructor
public class MicroGoodsServiceImpl implements IMicroGoodsService 
{
    private final MicroGoodsMapper microGoodsMapper;
    private final EscrowService escrowService;
    private final RedisCache redisCache;
    private final GoodsStoreService goodsStoreService;
    /**
     * 查询商品信息
     * 
     * @param id 商品信息主键
     * @return 商品信息
     */
    @Override
    public MicroGoods selectMicroGoodsById(Long id)
    {
        return microGoodsMapper.selectMicroGoodsById(id);
    }

    /**
     * 查询商品信息列表
     * 
     * @param microGoods 商品信息
     * @return 商品信息
     */
    @Override
    public List<MicroGoods> selectMicroGoodsList(MicroGoods microGoods)
    {
        return microGoodsMapper.selectMicroGoodsList(microGoods);
    }

    /**
     * 新增商品信息
     * 
     * @param microGoods 商品信息
     * @return 结果
     */
    @Override
    public int insertMicroGoods(MicroGoods microGoods)
    {
        microGoods.setCreateTime(DateUtils.getNowDate());
        return microGoodsMapper.insertMicroGoods(microGoods);
    }

    /**
     * 修改商品信息
     * 
     * @param microGoods 商品信息
     * @return 结果
     */
    @Override
    public int updateMicroGoods(MicroGoods microGoods)
    {
        microGoods.setUpdateTime(DateUtils.getNowDate());
        return microGoodsMapper.updateMicroGoods(microGoods);
    }

    /**
     * 批量删除商品信息
     * 
     * @param ids 需要删除的商品信息主键
     * @return 结果
     */
    @Override
    public int deleteMicroGoodsByIds(Long[] ids)
    {
        return microGoodsMapper.deleteMicroGoodsByIds(ids);
    }

    /**
     * 删除商品信息信息
     * 
     * @param id 商品信息主键
     * @return 结果
     */
    @Override
    public int deleteMicroGoodsById(Long id)
    {
        return microGoodsMapper.deleteMicroGoodsById(id);
    }

    /**
     * 商品上架
     * @param param
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int grounding(CreateGoodsParam param){
        //根据id进行查询商品
        MicroGoods goods = microGoodsMapper.selectMicroGoodsById(param.getGoodsId().longValue());
        param.setAddress((String) redisCache.getCacheObject(CacheConstants.SYS_CONFIG_KEY + "micro.geth.goods.address"));
        param.setStartTime(goods.getAuctionStartTime());
        param.setUserId(SecurityUtils.getUserId());
        param.setInterval(BigInteger.valueOf(goods.getIntervalTime()));
        try {
            param.setStartTimeStamp(goods.getAuctionStartTime());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        goodsStoreService.createGoods(param);
        String escrowAddress = escrowService.escrowAddress(param.getGoodsId());
        param.setEscrowAddress(escrowAddress);
        int rows = microGoodsMapper.grounding(param);

        return rows;
    }

    @Override
    public List<Long> queryGoodsEnd() {
        return microGoodsMapper.queryGoodsEnd();
    }

    @Override
    public int remove(Long id) {
        return microGoodsMapper.remove(id);
    }
}
