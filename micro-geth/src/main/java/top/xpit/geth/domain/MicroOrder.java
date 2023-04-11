package top.xpit.geth.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import top.xpit.common.annotation.Excel;
import top.xpit.common.core.domain.BaseEntity;

/**
 * 订单信息对象 micro_order
 *
 * @author PTJ
 * @date 2023-04-11
 */
public class MicroOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一标识 */
    private Long id;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 商品id */
    @Excel(name = "商品id")
    private Long goodsId;

    /** 支付价格 */
    @Excel(name = "支付价格")
    private BigDecimal amount;

    /** 订单状态 */
    @Excel(name = "订单状态")
    private Long status;

    /** 订单生成时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "订单生成时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date orderTime;

    /** 快递单号 */
    @Excel(name = "快递单号")
    private String trackingNum;

    /**  */
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
    public void setGoodsId(Long goodsId)
    {
        this.goodsId = goodsId;
    }

    public Long getGoodsId()
    {
        return goodsId;
    }
    public void setAmount(BigDecimal amount)
    {
        this.amount = amount;
    }

    public BigDecimal getAmount()
    {
        return amount;
    }
    public void setStatus(Long status)
    {
        this.status = status;
    }

    public Long getStatus()
    {
        return status;
    }
    public void setOrderTime(Date orderTime)
    {
        this.orderTime = orderTime;
    }

    public Date getOrderTime()
    {
        return orderTime;
    }
    public void setTrackingNum(String trackingNum)
    {
        this.trackingNum = trackingNum;
    }

    public String getTrackingNum()
    {
        return trackingNum;
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
                .append("goodsId", getGoodsId())
                .append("amount", getAmount())
                .append("status", getStatus())
                .append("orderTime", getOrderTime())
                .append("trackingNum", getTrackingNum())
                .append("createTime", getCreateTime())
                .append("createBy", getCreateBy())
                .append("updateTime", getUpdateTime())
                .append("updateBy", getUpdateBy())
                .append("delFlag", getDelFlag())
                .toString();
    }
}
