package top.xpit.geth.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
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
@Data
@ToString
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date auctionStartTime;

    /** 间隔时间 */
    @Excel(name = "间隔时间")
    private Long intervalTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
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

    private String address;
}
