package top.xpit.geth.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import top.xpit.common.annotation.Excel;
import top.xpit.common.core.domain.BaseEntity;

/**
 * 区块链交易数据对象 micro_transaction
 * 
 * @author PTJ
 * @date 2023-03-12
 */
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

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setSourceType(String sourceType) 
    {
        this.sourceType = sourceType;
    }

    public String getSourceType() 
    {
        return sourceType;
    }
    public void setSourceId(Long sourceId) 
    {
        this.sourceId = sourceId;
    }

    public Long getSourceId() 
    {
        return sourceId;
    }
    public void setTransactionHash(String transactionHash) 
    {
        this.transactionHash = transactionHash;
    }

    public String getTransactionHash() 
    {
        return transactionHash;
    }
    public void setTransactionIndex(String transactionIndex) 
    {
        this.transactionIndex = transactionIndex;
    }

    public String getTransactionIndex() 
    {
        return transactionIndex;
    }
    public void setBlockHash(String blockHash) 
    {
        this.blockHash = blockHash;
    }

    public String getBlockHash() 
    {
        return blockHash;
    }
    public void setBlockNumber(String blockNumber) 
    {
        this.blockNumber = blockNumber;
    }

    public String getBlockNumber() 
    {
        return blockNumber;
    }
    public void setCumulativeGasUsed(String cumulativeGasUsed) 
    {
        this.cumulativeGasUsed = cumulativeGasUsed;
    }

    public String getCumulativeGasUsed() 
    {
        return cumulativeGasUsed;
    }
    public void setGasUsed(String gasUsed) 
    {
        this.gasUsed = gasUsed;
    }

    public String getGasUsed() 
    {
        return gasUsed;
    }
    public void setContractAddress(String contractAddress) 
    {
        this.contractAddress = contractAddress;
    }

    public String getContractAddress() 
    {
        return contractAddress;
    }
    public void setRoot(String root) 
    {
        this.root = root;
    }

    public String getRoot() 
    {
        return root;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setFrom(String from) 
    {
        this.from = from;
    }

    public String getFrom() 
    {
        return from;
    }
    public void setTo(String to) 
    {
        this.to = to;
    }

    public String getTo() 
    {
        return to;
    }
    public void setLogs(String logs) 
    {
        this.logs = logs;
    }

    public String getLogs() 
    {
        return logs;
    }
    public void setLogsBloom(String logsBloom) 
    {
        this.logsBloom = logsBloom;
    }

    public String getLogsBloom() 
    {
        return logsBloom;
    }
    public void setDelFlag(Long delFlag) 
    {
        this.delFlag = delFlag;
    }

    public Long getDelFlag() 
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("sourceType", getSourceType())
            .append("sourceId", getSourceId())
            .append("transactionHash", getTransactionHash())
            .append("transactionIndex", getTransactionIndex())
            .append("blockHash", getBlockHash())
            .append("blockNumber", getBlockNumber())
            .append("cumulativeGasUsed", getCumulativeGasUsed())
            .append("gasUsed", getGasUsed())
            .append("contractAddress", getContractAddress())
            .append("root", getRoot())
            .append("status", getStatus())
            .append("from", getFrom())
            .append("to", getTo())
            .append("logs", getLogs())
            .append("logsBloom", getLogsBloom())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
