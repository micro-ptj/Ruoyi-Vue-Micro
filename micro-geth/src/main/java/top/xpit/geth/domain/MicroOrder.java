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
 * 订单信息对象 micro_order
 *
 * @author PTJ
 * @date 2023-04-11
 */
@Data
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

    @Excel(name = "用户地址id")
    private Long address;

    /** 快递单号 */
    @Excel(name = "快递单号")
    private String trackingNum;

    /**  */
    private Long delFlag;

}
