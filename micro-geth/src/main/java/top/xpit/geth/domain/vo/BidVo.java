package top.xpit.geth.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Author: ptj
 * @Date: 2023/04/12/17:57
 * @Description:
 */
@Data
public class BidVo {
    private static final long serialVersionUID = 1L;

    /** 唯一标识 */
    private Long id;


    /** 商品标题 */
    private String name;

    private Integer category;

    /** 出价 */
    private String amount;

    /** 竞拍时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date bidTime;

    /** 竞拍状态 */
    private Long status;
}
