package top.xpit.geth.controller.app;

import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.xpit.common.core.controller.BaseController;
import top.xpit.common.core.page.TableDataInfo;
import top.xpit.common.utils.SecurityUtils;
import top.xpit.geth.domain.MicroBid;
import top.xpit.geth.domain.MicroOrder;
import top.xpit.geth.domain.query.AppOrderParam;
import top.xpit.geth.domain.vo.OrderVo;
import top.xpit.geth.service.IMicroBidService;
import top.xpit.geth.service.IMicroOrderService;

import java.util.List;

@RequestMapping("/app/order")
@RestController
@RequiredArgsConstructor
public class AppOrderController extends BaseController {

    private final IMicroOrderService microOrderService;

    @GetMapping("list")
    public TableDataInfo list(AppOrderParam param){
        param.setUserId(SecurityUtils.getAppUserId());
        startPage();
        List<OrderVo> list = microOrderService.selectAppOrderList(param);
        return getDataTable(list);
    }
}