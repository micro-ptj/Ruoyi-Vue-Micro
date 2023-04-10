package top.xpit.geth.domain.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: ptj
 * @Date: 2023/03/12/21:43
 * @Description:
 */
@Data
public class UserInfoVo {

    private Long userId;

    private String username;

    private Long phone;
    private String name;
    private Integer sex;
    private String avatar;
    private String address;
    private BigDecimal balance;
}
