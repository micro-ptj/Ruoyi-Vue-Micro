package top.xpit.geth.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import top.xpit.common.annotation.Excel;
import top.xpit.common.core.domain.BaseEntity;

import java.math.BigInteger;

/**
 * 区块链交易数据对象 micro_transaction
 * 
 * @author PTJ
 * @date 2023-03-12
 */
@Data
public class MicroTransaction extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** source_type */
    @Excel(name = "source_type")
    private String sourceType;

    /** source_id */
    @Excel(name = "source_id")
    private Long sourceId;

    /** transaction_hash */
    @Excel(name = "transaction_hash")
    private String transactionHash;

    /** transaction_index */
    @Excel(name = "transaction_index")
    private String transactionIndex;

    /** block_hash */
    @Excel(name = "block_hash")
    private String blockHash;

    /** block_number */
    @Excel(name = "block_number")
    private String blockNumber;

    /** cumulative_gas_used */
    @Excel(name = "cumulative_gas_used")
    private String cumulativeGasUsed;

    /** gas_used */
    @Excel(name = "gas_used")
    private String gasUsed;

    /** contract_address */
    @Excel(name = "contract_address")
    private String contractAddress;

    /** root */
    @Excel(name = "root")
    private String root;

    /** status */
    @Excel(name = "status")
    private String status;

    /** from */
    @Excel(name = "from")
    private String from;

    /** to */
    @Excel(name = "to")
    private String to;

    /** logs */
    @Excel(name = "logs")
    private String logs;

    /** logs_bloom */
    @Excel(name = "logs_bloom")
    private String logsBloom;

    /** $column.columnComment */
    private Long delFlag;

}
