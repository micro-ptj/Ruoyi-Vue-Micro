package top.xpit.geth.domain.query;

import lombok.Data;

/**
 * @Author: PTJ
 * @Date: 2023/03/12/16:07
 * @Description:
 */
@Data
public class AppRegisterUserParam {

    private Long phone;

    private String password;

    private String avatar;

    private String code;
}
