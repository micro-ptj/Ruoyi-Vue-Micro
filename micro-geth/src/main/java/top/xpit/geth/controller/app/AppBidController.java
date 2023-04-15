package top.xpit.geth.controller.app;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.xpit.common.core.controller.BaseController;
import top.xpit.common.core.domain.AjaxResult;
import top.xpit.common.core.page.TableDataInfo;
import top.xpit.common.utils.SecurityUtils;
import top.xpit.geth.domain.query.AppBidParam;
import top.xpit.geth.domain.query.AppBidQueryParam;
import top.xpit.geth.domain.vo.BidVo;
import top.xpit.geth.service.AuctionService;
import top.xpit.geth.service.IMicroBidService;

import java.util.List;

@RequestMapping("/app/bid")
@RestController
@RequiredArgsConstructor
public class AppBidController extends BaseController {

    private final IMicroBidService microBidService;

    private final AuctionService auctionService;

    @GetMapping("list")
    public TableDataInfo list(AppBidQueryParam param){
        param.setUserId(SecurityUtils.getAppUserId());
        startPage();
        List<BidVo> list = microBidService.selectAppBidList(param);
        return getDataTable(list);
    }

    @PostMapping("bids")
    public AjaxResult bids(@RequestBody AppBidParam param){
        param.setUserId(SecurityUtils.getAppUserId());
        boolean b = auctionService.bids(param);
        return success(b);
    }
}
