package top.xpit.geth.domain.query;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Author: PTJ
 * @Date: 2023/04/22/21:54
 * @Description:
 */
@Data
public class TransactionQueryParam {

    @NotNull(message = "sourceId不能为空")
    private Long sourceId;

    @NotNull(message = "sourceType不能为空")
    private String sourceType;

}
