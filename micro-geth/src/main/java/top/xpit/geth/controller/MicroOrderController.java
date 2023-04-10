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
import top.xpit.geth.domain.MicroOrder;
import top.xpit.geth.service.IMicroOrderService;
import top.xpit.common.utils.poi.ExcelUtil;
import top.xpit.common.core.page.TableDataInfo;

/**
 * 订单信息Controller
 * 
 * @author PTJ
 * @date 2023-03-12
 */
@RestController
@RequestMapping("/geth/order")
public class MicroOrderController extends BaseController
{
    @Autowired
    private IMicroOrderService microOrderService;

    /**
     * 查询订单信息列表
     */
    @PreAuthorize("@ss.hasPermi('geth:order:list')")
    @GetMapping("/list")
    public TableDataInfo list(MicroOrder microOrder)
    {
        startPage();
        List<MicroOrder> list = microOrderService.selectMicroOrderList(microOrder);
        return getDataTable(list);
    }

    /**
     * 导出订单信息列表
     */
    @PreAuthorize("@ss.hasPermi('geth:order:export')")
    @Log(title = "订单信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MicroOrder microOrder)
    {
        List<MicroOrder> list = microOrderService.selectMicroOrderList(microOrder);
        ExcelUtil<MicroOrder> util = new ExcelUtil<MicroOrder>(MicroOrder.class);
        util.exportExcel(response, list, "订单信息数据");
    }

    /**
     * 获取订单信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('geth:order:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(microOrderService.selectMicroOrderById(id));
    }

    /**
     * 新增订单信息
     */
    @PreAuthorize("@ss.hasPermi('geth:order:add')")
    @Log(title = "订单信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MicroOrder microOrder)
    {
        return toAjax(microOrderService.insertMicroOrder(microOrder));
    }

    /**
     * 修改订单信息
     */
    @PreAuthorize("@ss.hasPermi('geth:order:edit')")
    @Log(title = "订单信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MicroOrder microOrder)
    {
        return toAjax(microOrderService.updateMicroOrder(microOrder));
    }

    /**
     * 删除订单信息
     */
    @PreAuthorize("@ss.hasPermi('geth:order:remove')")
    @Log(title = "订单信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(microOrderService.deleteMicroOrderByIds(ids));
    }
}
