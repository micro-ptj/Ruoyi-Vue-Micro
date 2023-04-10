package top.xpit.geth.domain.query;

import lombok.Data;

/**
 * @Author: ptj
 * @Date: 2023/03/12/16:07
 * @Description:
 */
@Data
public class AppLoginUserParam {

    private Long phone;

    private String password;

    private Long code;
}
