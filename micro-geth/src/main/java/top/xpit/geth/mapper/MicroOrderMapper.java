package top.xpit.geth.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import top.xpit.geth.domain.MicroOrder;
import top.xpit.geth.domain.query.AppOrderQueryParam;
import top.xpit.geth.domain.vo.OrderVo;

/**
 * 订单信息Mapper接口
 * 
 * @author PTJ
 * @date 2023-03-12
 */
@Mapper
public interface MicroOrderMapper 
{
    /**
     * 查询订单信息
     * 
     * @param id 订单信息主键
     * @return 订单信息
     */
    public MicroOrder selectMicroOrderById(Long id);

    /**
     * 查询订单信息列表
     * 
     * @param microOrder 订单信息
     * @return 订单信息集合
     */
    public List<MicroOrder> selectMicroOrderList(MicroOrder microOrder);

    /**
     * 新增订单信息
     * 
     * @param microOrder 订单信息
     * @return 结果
     */
    public int insertMicroOrder(MicroOrder microOrder);

    /**
     * 修改订单信息
     * 
     * @param microOrder 订单信息
     * @return 结果
     */
    public int updateMicroOrder(MicroOrder microOrder);

    /**
     * 删除订单信息
     * 
     * @param id 订单信息主键
     * @return 结果
     */
    public int deleteMicroOrderById(Long id);

    /**
     * 批量删除订单信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMicroOrderByIds(Long[] ids);

    List<OrderVo> selectAppOrderList(AppOrderQueryParam param);

    int selectByTask(long userId, long longValue);
}
