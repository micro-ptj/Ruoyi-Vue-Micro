package top.xpit.geth.controller.app;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.xpit.common.core.controller.BaseController;
import top.xpit.common.core.domain.AjaxResult;
import top.xpit.common.core.page.TableDataInfo;
import top.xpit.geth.domain.MicroBid;
import top.xpit.geth.domain.MicroGoods;
import top.xpit.geth.service.IMicroBidService;

import java.util.List;

@RequestMapping("/app/bid")
@RestController
@RequiredArgsConstructor
public class AppBidController extends BaseController {

    private final IMicroBidService microBidService;

    @GetMapping("list")
    public TableDataInfo list(MicroBid microBid){
        startPage();
        List<MicroBid> list = microBidService.selectMicroBidList(microBid);
        return getDataTable(list);
    }
}