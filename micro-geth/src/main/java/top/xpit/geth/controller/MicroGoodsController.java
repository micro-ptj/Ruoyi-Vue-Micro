package top.xpit.geth.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.xpit.common.annotation.Log;
import top.xpit.common.core.controller.BaseController;
import top.xpit.common.core.domain.AjaxResult;
import top.xpit.common.enums.BusinessType;
import top.xpit.geth.domain.MicroGoods;
import top.xpit.geth.domain.query.CreateGoodsParam;
import top.xpit.geth.service.GoodsStoreService;
import top.xpit.geth.service.IMicroGoodsService;
import top.xpit.common.utils.poi.ExcelUtil;
import top.xpit.common.core.page.TableDataInfo;

/**
 * 商品信息Controller
 * 
 * @author PTJ
 * @date 2023-04-02
 */
@RestController
@RequestMapping("/geth/goods")
public class MicroGoodsController extends BaseController
{
    @Autowired
    private IMicroGoodsService microGoodsService;

    /**
     * 查询商品信息列表
     */
    @PreAuthorize("@ss.hasPermi('geth:goods:list')")
    @GetMapping("/list")
    public TableDataInfo list(MicroGoods microGoods)
    {
        startPage();
        List<MicroGoods> list = microGoodsService.selectMicroGoodsList(microGoods);
        return getDataTable(list);
    }

    /**
     * 导出商品信息列表
     */
    @PreAuthorize("@ss.hasPermi('geth:goods:export')")
    @Log(title = "商品信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MicroGoods microGoods)
    {
        List<MicroGoods> list = microGoodsService.selectMicroGoodsList(microGoods);
        ExcelUtil<MicroGoods> util = new ExcelUtil<MicroGoods>(MicroGoods.class);
        util.exportExcel(response, list, "商品信息数据");
    }

    /**
     * 获取商品信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('geth:goods:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(microGoodsService.selectMicroGoodsById(id));
    }

    /**
     * 新增商品信息
     */
    @PreAuthorize("@ss.hasPermi('geth:goods:add')")
    @Log(title = "商品信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MicroGoods microGoods)
    {
        return toAjax(microGoodsService.insertMicroGoods(microGoods));
    }

    /**
     * 修改商品信息
     */
    @PreAuthorize("@ss.hasPermi('geth:goods:edit')")
    @Log(title = "商品信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MicroGoods microGoods)
    {
        return toAjax(microGoodsService.updateMicroGoods(microGoods));
    }

    /**
     * 删除商品信息
     */
    @PreAuthorize("@ss.hasPermi('geth:goods:remove')")
    @Log(title = "商品信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(microGoodsService.deleteMicroGoodsByIds(ids));
    }

    @Log(title = "商品信息", businessType = BusinessType.UPDATE)
    @PostMapping(value = "grounding", name = "上架")
    public AjaxResult grounding(@RequestBody CreateGoodsParam param){
        return toAjax(microGoodsService.grounding(param));
    }
}
