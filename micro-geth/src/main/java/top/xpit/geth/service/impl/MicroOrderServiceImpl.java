package top.xpit.geth.service.impl;

import java.util.List;
import top.xpit.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.xpit.geth.domain.query.AppOrderParam;
import top.xpit.geth.domain.vo.OrderVo;
import top.xpit.geth.mapper.MicroOrderMapper;
import top.xpit.geth.domain.MicroOrder;
import top.xpit.geth.service.IMicroOrderService;

/**
 * 订单信息Service业务层处理
 * 
 * @author PTJ
 * @date 2023-03-12
 */
@Service
public class MicroOrderServiceImpl implements IMicroOrderService 
{
    @Autowired
    private MicroOrderMapper microOrderMapper;

    /**
     * 查询订单信息
     * 
     * @param id 订单信息主键
     * @return 订单信息
     */
    @Override
    public MicroOrder selectMicroOrderById(Long id)
    {
        return microOrderMapper.selectMicroOrderById(id);
    }

    /**
     * 查询订单信息列表
     * 
     * @param microOrder 订单信息
     * @return 订单信息
     */
    @Override
    public List<MicroOrder> selectMicroOrderList(MicroOrder microOrder)
    {
        return microOrderMapper.selectMicroOrderList(microOrder);
    }

    /**
     * 新增订单信息
     * 
     * @param microOrder 订单信息
     * @return 结果
     */
    @Override
    public int insertMicroOrder(MicroOrder microOrder)
    {
        microOrder.setCreateTime(DateUtils.getNowDate());
        return microOrderMapper.insertMicroOrder(microOrder);
    }

    /**
     * 修改订单信息
     * 
     * @param microOrder 订单信息
     * @return 结果
     */
    @Override
    public int updateMicroOrder(MicroOrder microOrder)
    {
        microOrder.setUpdateTime(DateUtils.getNowDate());
        return microOrderMapper.updateMicroOrder(microOrder);
    }

    /**
     * 批量删除订单信息
     * 
     * @param ids 需要删除的订单信息主键
     * @return 结果
     */
    @Override
    public int deleteMicroOrderByIds(Long[] ids)
    {
        return microOrderMapper.deleteMicroOrderByIds(ids);
    }

    /**
     * 删除订单信息信息
     * 
     * @param id 订单信息主键
     * @return 结果
     */
    @Override
    public int deleteMicroOrderById(Long id)
    {
        return microOrderMapper.deleteMicroOrderById(id);
    }

    @Override
    public List<OrderVo> selectAppOrderList(AppOrderParam param) {
        return microOrderMapper.selectAppOrderList(param);
    }
}
