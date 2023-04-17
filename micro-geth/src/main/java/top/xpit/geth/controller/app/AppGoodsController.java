package top.xpit.geth.controller.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.xpit.common.core.controller.BaseController;
import top.xpit.common.core.domain.AjaxResult;
import top.xpit.common.core.page.TableDataInfo;
import top.xpit.geth.domain.MicroGoods;
import top.xpit.geth.service.IMicroGoodsService;

import java.util.List;

/**
 * @Author: PTJ
 * @Date: 2023/03/13/0:11
 * @Description:
 */
@RequestMapping("/app/goods")
@RestController
public class AppGoodsController extends BaseController {

    @Autowired
    private IMicroGoodsService microGoodsService;

    @GetMapping("/list")
    public TableDataInfo list(MicroGoods microGoods)
    {
        microGoods.setStatus(1L);
        microGoods.setDelFlag(0L);
        startPage();
        List<MicroGoods> list = microGoodsService.selectMicroGoodsList(microGoods);
        return getDataTable(list);
    }

    @GetMapping("/byId/{id}")
    public AjaxResult goodsById(@PathVariable("id") Long id) {
        MicroGoods microGoods = microGoodsService.selectMicroGoodsById(id);
        return success(microGoods);
    }
}
