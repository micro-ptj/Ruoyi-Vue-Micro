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
import top.xpit.common.utils.PageUtils;
import top.xpit.geth.domain.MicroBid;
import top.xpit.geth.domain.vo.MicroBidVo;
import top.xpit.geth.service.IMicroBidService;
import top.xpit.common.utils.poi.ExcelUtil;
import top.xpit.common.core.page.TableDataInfo;

/**
 * 交易信息Controller
 *
 * @author PTJ
 * @date 2023-04-12
 */
@RestController
@RequestMapping("/geth/bid")
public class MicroBidController extends BaseController
{
    @Autowired
    private IMicroBidService microBidService;

    /**
     * 查询交易信息列表
     */
    @PreAuthorize("@ss.hasPermi('geth:bid:list')")
    @GetMapping("/list")
    public TableDataInfo list(MicroBid microBid)
    {
        startPage();
        List<MicroBidVo> list = microBidService.selectMicroBidVoList(microBid);
        return getDataTable(list);
    }

    /**
     * 导出交易信息列表
     */
    @PreAuthorize("@ss.hasPermi('geth:bid:export')")
    @Log(title = "交易信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MicroBid microBid)
    {
        List<MicroBid> list = microBidService.selectMicroBidList(microBid);
        ExcelUtil<MicroBid> util = new ExcelUtil<MicroBid>(MicroBid.class);
        util.exportExcel(response, list, "交易信息数据");
    }

    /**
     * 获取交易信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('geth:bid:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(microBidService.selectMicroBidById(id));
    }

    /**
     * 新增交易信息
     */
    @PreAuthorize("@ss.hasPermi('geth:bid:add')")
    @Log(title = "交易信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MicroBid microBid)
    {
        return toAjax(microBidService.insertMicroBid(microBid));
    }

    /**
     * 修改交易信息
     */
    @PreAuthorize("@ss.hasPermi('geth:bid:edit')")
    @Log(title = "交易信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MicroBid microBid)
    {
        return toAjax(microBidService.updateMicroBid(microBid));
    }

    /**
     * 删除交易信息
     */
    @PreAuthorize("@ss.hasPermi('geth:bid:remove')")
    @Log(title = "交易信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(microBidService.deleteMicroBidByIds(ids));
    }
}
