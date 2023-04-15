package top.xpit.geth.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import top.xpit.common.annotation.Excel;
import top.xpit.common.core.domain.BaseEntity;

/**
 * 交易信息对象 micro_bid
 *
 * @author PTJ
 * @date 2023-04-12
 */
@Data
public class MicroBid extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一标识 */
    private Long id;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 商品ID */
    @Excel(name = "商品ID")
    private Long goodsId;

    /** 出价 */
    @Excel(name = "出价")
    private BigDecimal amount;

    /** 竞拍状态 */
    @Excel(name = "竞拍状态")
    private Long status;

    /** 竞拍时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "竞拍时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date bidTime;

    /**  */
    private Long delFlag;
}
