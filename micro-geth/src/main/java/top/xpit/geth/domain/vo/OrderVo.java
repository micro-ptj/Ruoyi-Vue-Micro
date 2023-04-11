package top.xpit.geth.domain.vo;

import lombok.Data;

import java.util.Date;

@Data
public class OrderVo {

    /**
     * id
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

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
