package top.xpit.geth.service;

import java.util.List;
import top.xpit.geth.domain.MicroBid;
import top.xpit.geth.domain.query.AppBidParam;
import top.xpit.geth.domain.query.AppBidQueryParam;
import top.xpit.geth.domain.vo.BidVo;

/**
 * 交易信息Service接口
 * 
 * @author PTJ
 * @date 2023-03-19
 */
public interface IMicroBidService 
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
     * 批量删除交易信息
     *
     * @param ids 需要删除的交易信息主键集合
     * @return 结果
     */
    public int deleteMicroBidByIds(Long[] ids);

    /**
     * 删除交易信息信息
     *
     * @param id 交易信息主键
     * @return 结果
     */
    public int deleteMicroBidById(Long id);

    List<BidVo> selectAppBidList(AppBidQueryParam param);

    MicroBid bids(AppBidParam param);
}
