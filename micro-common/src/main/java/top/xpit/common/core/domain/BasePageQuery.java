package top.xpit.common.core.domain;


import lombok.Data;

import java.io.Serializable;

@Data
public class BasePageQuery implements Serializable {

    private Integer pageNum = 1;

    private Integer pageSize = 10;

}
