package top.xpit.geth.domain.vo;

import lombok.Data;
import top.xpit.common.annotation.Excel;
import top.xpit.common.core.domain.BaseEntity;

/**
 * 区块链交易数据对象 micro_transaction
 * 
 * @author PTJ
 * @date 2023-03-12
 */
@Data
public class TransactionVo
{
    private static final long serialVersionUID = 1L;

    /** transaction_hash */
    private String transactionHash;

    /** transaction_index */
    private String transactionIndex;

    /** block_hash */
    private String blockHash;

    /** block_number */
    private String blockNumber;

    /** cumulative_gas_used */
    private String cumulativeGasUsed;

    /** gas_used */
    private String gasUsed;

    /** contract_address */
    private String contractAddress;

    /** root */
    private String root;

    /** status */
    private String status;

    /** from */
    private String from;

    /** to */
    private String to;


}
