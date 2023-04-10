package top.xpit.geth.mapper;

import java.util.List;
import top.xpit.geth.domain.MicroBid;

/**
 * 交易信息Mapper接口
 * 
 * @author PTJ
 * @date 2023-03-19
 */
public interface MicroBidMapper 
{
    /**
     * 查询交易信息
     * 
     * @param userId 交易信息主键
     * @return 交易信息
     */
    public MicroBid selectMicroBidByUserId(Long userId);

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
     * @param userId 交易信息主键
     * @return 结果
     */
    public int deleteMicroBidByUserId(Long userId);

    /**
     * 批量删除交易信息
     * 
     * @param userIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMicroBidByUserIds(Long[] userIds);
}
