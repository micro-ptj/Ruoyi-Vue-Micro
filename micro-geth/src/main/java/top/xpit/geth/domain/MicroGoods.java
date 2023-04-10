package top.xpit.geth.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import top.xpit.common.annotation.Excel;
import top.xpit.common.core.domain.BaseEntity;

/**
 * 商品信息对象 micro_goods
 * 
 * @author PTJ
 * @date 2023-04-02
 */
public class MicroGoods extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 分类 */
    @Excel(name = "分类")
    private Long category;

    /** 图片 */
    @Excel(name = "图片")
    private String imageLink;

    /** 描述 */
    @Excel(name = "描述")
    private String description;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date auctionStartTime;

    /** 间隔时间 */
    @Excel(name = "间隔时间")
    private Long intervalTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date auctionEndTime;

    /** 起拍价 */
    @Excel(name = "起拍价")
    private BigDecimal startPrice;

    /** 是否上架 */
    @Excel(name = "是否上架")
    private Long status;

    /** 商品的状态（新品或二手） */
    @Excel(name = "商品的状态", readConverterExp = "新=品或二手")
    private Long conditions;

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
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setCategory(Long category) 
    {
        this.category = category;
    }

    public Long getCategory() 
    {
        return category;
    }
    public void setImageLink(String imageLink) 
    {
        this.imageLink = imageLink;
    }

    public String getImageLink() 
    {
        return imageLink;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setAuctionStartTime(Date auctionStartTime) 
    {
        this.auctionStartTime = auctionStartTime;
    }

    public Date getAuctionStartTime() 
    {
        return auctionStartTime;
    }
    public void setIntervalTime(Long intervalTime) 
    {
        this.intervalTime = intervalTime;
    }

    public Long getIntervalTime() 
    {
        return intervalTime;
    }
    public void setAuctionEndTime(Date auctionEndTime) 
    {
        this.auctionEndTime = auctionEndTime;
    }

    public Date getAuctionEndTime() 
    {
        return auctionEndTime;
    }
    public void setStartPrice(BigDecimal startPrice) 
    {
        this.startPrice = startPrice;
    }

    public BigDecimal getStartPrice() 
    {
        return startPrice;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }
    public void setConditions(Long conditions) 
    {
        this.conditions = conditions;
    }

    public Long getConditions() 
    {
        return conditions;
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
            .append("name", getName())
            .append("category", getCategory())
            .append("imageLink", getImageLink())
            .append("description", getDescription())
            .append("auctionStartTime", getAuctionStartTime())
            .append("intervalTime", getIntervalTime())
            .append("auctionEndTime", getAuctionEndTime())
            .append("startPrice", getStartPrice())
            .append("status", getStatus())
            .append("conditions", getConditions())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
