package top.xpit.geth.domain.query;

import lombok.Data;
import top.xpit.common.core.domain.BasePageQuery;

@Data
public class AppBidQueryParam extends BasePageQuery {
    private Long userId;
}
