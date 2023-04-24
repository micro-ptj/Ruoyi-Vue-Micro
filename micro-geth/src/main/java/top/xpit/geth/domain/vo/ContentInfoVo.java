package top.xpit.geth.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: PTJ
 * @Date: 2023/04/24/15:28
 * @Description:
 */
@Data
public class ContentInfoVo implements Serializable {
    private Long id;
    private BigDecimal amount;
    private Integer status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date time;
    private String name;
    private Integer category;
    private String description;
    private BigDecimal startPrice;
    private String trackingNum;
}
