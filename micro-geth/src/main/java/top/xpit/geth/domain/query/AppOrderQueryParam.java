package top.xpit.geth.domain.query;

import lombok.Data;
import top.xpit.common.core.domain.BasePageQuery;

@Data
public class AppOrderQueryParam extends BasePageQuery {

    private Long userId;

}
