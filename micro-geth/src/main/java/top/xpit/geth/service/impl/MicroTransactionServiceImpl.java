package top.xpit.geth.service.impl;

import java.util.List;
import top.xpit.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.xpit.geth.domain.query.TransactionQueryParam;
import top.xpit.geth.domain.vo.TransactionVo;
import top.xpit.geth.mapper.MicroTransactionMapper;
import top.xpit.geth.domain.MicroTransaction;
import top.xpit.geth.service.IMicroTransactionService;

/**
 * 区块链交易数据Service业务层处理
 * 
 * @author PTJ
 * @date 2023-03-12
 */
@Service
public class MicroTransactionServiceImpl implements IMicroTransactionService 
{
    @Autowired
    private MicroTransactionMapper microTransactionMapper;

    /**
     * 查询区块链交易数据
     * 
     * @param id 区块链交易数据主键
     * @return 区块链交易数据
     */
    @Override
    public MicroTransaction selectMicroTransactionById(Long id)
    {
        return microTransactionMapper.selectMicroTransactionById(id);
    }

    /**
     * 查询区块链交易数据列表
     * 
     * @param microTransaction 区块链交易数据
     * @return 区块链交易数据
     */
    @Override
    public List<MicroTransaction> selectMicroTransactionList(MicroTransaction microTransaction)
    {
        return microTransactionMapper.selectMicroTransactionList(microTransaction);
    }

    /**
     * 新增区块链交易数据
     * 
     * @param microTransaction 区块链交易数据
     * @return 结果
     */
    @Override
    public int insertMicroTransaction(MicroTransaction microTransaction)
    {
        microTransaction.setCreateTime(DateUtils.getNowDate());
        return microTransactionMapper.insertMicroTransaction(microTransaction);
    }

    /**
     * 修改区块链交易数据
     * 
     * @param microTransaction 区块链交易数据
     * @return 结果
     */
    @Override
    public int updateMicroTransaction(MicroTransaction microTransaction)
    {
        microTransaction.setUpdateTime(DateUtils.getNowDate());
        return microTransactionMapper.updateMicroTransaction(microTransaction);
    }

    /**
     * 批量删除区块链交易数据
     * 
     * @param ids 需要删除的区块链交易数据主键
     * @return 结果
     */
    @Override
    public int deleteMicroTransactionByIds(Long[] ids)
    {
        return microTransactionMapper.deleteMicroTransactionByIds(ids);
    }

    /**
     * 删除区块链交易数据信息
     * 
     * @param id 区块链交易数据主键
     * @return 结果
     */
    @Override
    public int deleteMicroTransactionById(Long id)
    {
        return microTransactionMapper.deleteMicroTransactionById(id);
    }

    @Override
    public TransactionVo selectBySourceId(TransactionQueryParam param) {
        TransactionVo transactionVo = microTransactionMapper.selectBySourceId(param);
        return transactionVo;
    }
}
