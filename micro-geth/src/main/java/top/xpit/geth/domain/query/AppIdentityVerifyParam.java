package top.xpit.geth.domain.query;

import lombok.Data;

/**
 * @Author: ptj
 * @Date: 2023/04/18/10:12
 * @Description:
 */
@Data
public class AppIdentityVerifyParam {
    private Long userId;
    private String name;
    private String idCode;
    private String phone;
}
