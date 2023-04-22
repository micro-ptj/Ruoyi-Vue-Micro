package top.xpit.geth.service;

import java.util.List;
import top.xpit.geth.domain.MicroTransaction;
import top.xpit.geth.domain.query.TransactionQueryParam;
import top.xpit.geth.domain.vo.TransactionVo;

/**
 * 区块链交易数据Service接口
 * 
 * @author PTJ
 * @date 2023-03-12
 */
public interface IMicroTransactionService 
{
    /**
     * 查询区块链交易数据
     * 
     * @param id 区块链交易数据主键
     * @return 区块链交易数据
     */
    public MicroTransaction selectMicroTransactionById(Long id);

    /**
     * 查询区块链交易数据列表
     * 
     * @param microTransaction 区块链交易数据
     * @return 区块链交易数据集合
     */
    public List<MicroTransaction> selectMicroTransactionList(MicroTransaction microTransaction);

    /**
     * 新增区块链交易数据
     * 
     * @param microTransaction 区块链交易数据
     * @return 结果
     */
    public int insertMicroTransaction(MicroTransaction microTransaction);

    /**
     * 修改区块链交易数据
     * 
     * @param microTransaction 区块链交易数据
     * @return 结果
     */
    public int updateMicroTransaction(MicroTransaction microTransaction);

    /**
     * 批量删除区块链交易数据
     * 
     * @param ids 需要删除的区块链交易数据主键集合
     * @return 结果
     */
    public int deleteMicroTransactionByIds(Long[] ids);

    /**
     * 删除区块链交易数据信息
     * 
     * @param id 区块链交易数据主键
     * @return 结果
     */
    public int deleteMicroTransactionById(Long id);

    TransactionVo selectBySourceId(TransactionQueryParam param);
}
