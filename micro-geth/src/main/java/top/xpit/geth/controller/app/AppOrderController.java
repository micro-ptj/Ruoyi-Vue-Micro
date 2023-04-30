package top.xpit.geth.controller.app;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.xpit.common.core.controller.BaseController;
import top.xpit.common.core.domain.AjaxResult;
import top.xpit.common.core.page.TableDataInfo;
import top.xpit.common.utils.SecurityUtils;
import top.xpit.geth.domain.query.AppOrderQueryParam;
import top.xpit.geth.domain.query.AppPickUpParam;
import top.xpit.geth.domain.vo.OrderVo;
import top.xpit.geth.service.IMicroOrderService;

import java.util.List;

@RequestMapping("/app/order")
@RestController
@RequiredArgsConstructor
public class AppOrderController extends BaseController {

    private final IMicroOrderService microOrderService;

    @GetMapping("list")
    public TableDataInfo list(AppOrderQueryParam param){
        param.setUserId(SecurityUtils.getAppUserId());
        startPage();
        List<OrderVo> list = microOrderService.selectAppOrderList(param);
        return getDataTable(list);
    }

    @GetMapping("info/{id}")
    public AjaxResult info(@PathVariable Long id){
        return success(microOrderService.selectAppOrderById(id));
    }

    @GetMapping("pickup")
    public AjaxResult pickup(@RequestBody AppPickUpParam param) {
        param.setUserId(SecurityUtils.getAppUserId());
        return toAjax(microOrderService.pickup(param));
    }
}
