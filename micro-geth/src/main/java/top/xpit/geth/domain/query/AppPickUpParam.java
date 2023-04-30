package top.xpit.geth.domain.query;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: PTJ
 * @Date: 2023/04/30/10:42
 * @Description:
 */
@Data
public class AppPickUpParam implements Serializable {

    private Long userId;
    private Long orderId;

    private Long addressId;
}
