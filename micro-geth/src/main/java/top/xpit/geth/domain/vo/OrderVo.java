package top.xpit.geth.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import top.xpit.common.core.domain.BaseEntity;

import java.util.Date;

@ToString
@Data
public class OrderVo {

    /**
     * id
     */
    private Long id;

    /**
     * 标题
     */
    private String name;

    /**
     * 商品分类
     */
    private Integer category;
    /**
     * 价格
     */
    private String amount;
    /**
     * 订单生成时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "GMT+8")
    private Date orderTime;
    /**
     * 订单状态
     */
    private Integer status;

    /**
     * 快递单号
     */
    private String trackingNum;
}
