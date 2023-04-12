package top.xpit.geth.service.impl;

import java.util.List;
import top.xpit.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.xpit.geth.domain.query.AppBidParam;
import top.xpit.geth.domain.vo.BidVo;
import top.xpit.geth.mapper.MicroBidMapper;
import top.xpit.geth.domain.MicroBid;
import top.xpit.geth.service.IMicroBidService;

/**
 * 交易信息Service业务层处理
 * 
 * @author PTJ
 * @date 2023-03-19
 */
@Service
public class MicroBidServiceImpl implements IMicroBidService 
{
    @Autowired
    private MicroBidMapper microBidMapper;

    /**
     * 查询交易信息
     *
     * @param id 交易信息主键
     * @return 交易信息
     */
    @Override
    public MicroBid selectMicroBidById(Long id)
    {
        return microBidMapper.selectMicroBidById(id);
    }

    /**
     * 查询交易信息列表
     *
     * @param microBid 交易信息
     * @return 交易信息
     */
    @Override
    public List<MicroBid> selectMicroBidList(MicroBid microBid)
    {
        return microBidMapper.selectMicroBidList(microBid);
    }

    /**
     * 新增交易信息
     *
     * @param microBid 交易信息
     * @return 结果
     */
    @Override
    public int insertMicroBid(MicroBid microBid)
    {
        microBid.setCreateTime(DateUtils.getNowDate());
        return microBidMapper.insertMicroBid(microBid);
    }

    /**
     * 修改交易信息
     *
     * @param microBid 交易信息
     * @return 结果
     */
    @Override
    public int updateMicroBid(MicroBid microBid)
    {
        microBid.setUpdateTime(DateUtils.getNowDate());
        return microBidMapper.updateMicroBid(microBid);
    }

    /**
     * 批量删除交易信息
     *
     * @param ids 需要删除的交易信息主键
     * @return 结果
     */
    @Override
    public int deleteMicroBidByIds(Long[] ids)
    {
        return microBidMapper.deleteMicroBidByIds(ids);
    }

    /**
     * 删除交易信息信息
     *
     * @param id 交易信息主键
     * @return 结果
     */
    @Override
    public int deleteMicroBidById(Long id)
    {
        return microBidMapper.deleteMicroBidById(id);
    }

    @Override
    public List<BidVo> selectAppBidList(AppBidParam param) {
        return microBidMapper.selectAppBidList(param);
    }
}
