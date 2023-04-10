package top.xpit.geth.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import top.xpit.common.annotation.Excel;
import top.xpit.common.core.domain.BaseEntity;

import java.math.BigDecimal;

/**
 * ${subTable.functionName}对象 micro_user_info
 * 
 * @author PTJ
 * @date 2023-03-12
 */
public class MicroUserInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 区块链用户地址（16进制） */
    @Excel(name = "区块链用户地址", readConverterExp = "1=6进制")
    private String address;

    /** 私钥 */
    @Excel(name = "私钥")
    private String privateKey;

    /** 公钥 */
    @Excel(name = "公钥")
    private String publicKey;

    /** 用户余额 */
    @Excel(name = "用户余额")
    private BigDecimal balance;

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
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setPrivateKey(String privateKey) 
    {
        this.privateKey = privateKey;
    }

    public String getPrivateKey() 
    {
        return privateKey;
    }
    public void setPublicKey(String publicKey) 
    {
        this.publicKey = publicKey;
    }

    public String getPublicKey() 
    {
        return publicKey;
    }
    public void setBalance(BigDecimal balance)
    {
        this.balance = balance;
    }

    public BigDecimal getBalance()
    {
        return balance;
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
            .append("userId", getUserId())
            .append("address", getAddress())
            .append("privateKey", getPrivateKey())
            .append("publicKey", getPublicKey())
            .append("balance", getBalance())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
