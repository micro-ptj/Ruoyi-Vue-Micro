package top.xpit.geth.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import top.xpit.common.annotation.Excel;
import top.xpit.common.core.domain.BaseEntity;

/**
 * 交易信息对象 micro_bid
 *
 * @author PTJ
 * @date 2023-04-09
 */
public class MicroBid extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /**  */
    @Excel(name = "")
    private Long userId;

    /** 商品ID */
    @Excel(name = "商品ID")
    private Long goodsId;

    /** 出价 */
    @Excel(name = "出价")
    private String weiValue;

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
    public void setWeiValue(String weiValue)
    {
        this.weiValue = weiValue;
    }

    public String getWeiValue()
    {
        return weiValue;
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
                .append("weiValue", getWeiValue())
                .append("createTime", getCreateTime())
                .append("createBy", getCreateBy())
                .append("updateTime", getUpdateTime())
                .append("updateBy", getUpdateBy())
                .append("delFlag", getDelFlag())
                .toString();
    }
}
