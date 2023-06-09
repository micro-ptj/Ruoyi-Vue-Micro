package top.xpit.geth.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import top.xpit.geth.domain.MicroBid;
import top.xpit.geth.domain.dto.OrderDto;
import top.xpit.geth.domain.query.AppBidParam;
import top.xpit.geth.domain.query.AppBidQueryParam;
import top.xpit.geth.domain.vo.BidVo;
import top.xpit.geth.domain.vo.ContentInfoVo;
import top.xpit.geth.domain.vo.MicroBidVo;

/**
 * 交易信息Mapper接口
 *
 * @author PTJ
 * @date 2023-04-12
 */
public interface MicroBidMapper
{
    /**
     * 查询交易信息
     *
     * @param id 交易信息主键
     * @return 交易信息
     */
    public MicroBid selectMicroBidById(Long id);

    /**
     * 查询交易信息列表
     *
     * @param microBid 交易信息
     * @return 交易信息集合
     */
    public List<MicroBid> selectMicroBidList(MicroBid microBid);

    /**
     * 新增交易信息
     *
     * @param microBid 交易信息
     * @return 结果
     */
    public int insertMicroBid(MicroBid microBid);

    /**
     * 修改交易信息
     *
     * @param microBid 交易信息
     * @return 结果
     */
    public int updateMicroBid(MicroBid microBid);

    /**
     * 删除交易信息
     *
     * @param id 交易信息主键
     * @return 结果
     */
    public int deleteMicroBidById(Long id);

    /**
     * 批量删除交易信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMicroBidByIds(Long[] ids);

    List<BidVo> selectAppBidList(AppBidQueryParam param);

    int updateBidStatus(OrderDto orderDto);

    int updateByFail(OrderDto orderDto);

    List<Long> selectWithdraw(long goodsId);

    BigDecimal selectHighestPrice(AppBidParam param);

    List<MicroBidVo> selectMicroBidVoList(MicroBid microBid);

    ContentInfoVo selectAppBidById(@Param("id") Long id, @Param("userId") Long userId);
}
