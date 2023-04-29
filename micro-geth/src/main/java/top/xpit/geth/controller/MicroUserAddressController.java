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
import top.xpit.geth.domain.MicroUserAddress;
import top.xpit.geth.service.IMicroUserAddressService;
import top.xpit.common.utils.poi.ExcelUtil;
import top.xpit.common.core.page.TableDataInfo;

/**
 * 地址管理Controller
 * 
 * @author PTJ
 * @date 2023-04-29
 */
@RestController
@RequestMapping("/geth/address")
public class MicroUserAddressController extends BaseController
{
    @Autowired
    private IMicroUserAddressService microUserAddressService;

    /**
     * 查询地址管理列表
     */
    @PreAuthorize("@ss.hasPermi('geth:address:list')")
    @GetMapping("/list")
    public TableDataInfo list(MicroUserAddress microUserAddress)
    {
        startPage();
        List<MicroUserAddress> list = microUserAddressService.selectMicroUserAddressList(microUserAddress);
        return getDataTable(list);
    }

    /**
     * 导出地址管理列表
     */
    @PreAuthorize("@ss.hasPermi('geth:address:export')")
    @Log(title = "地址管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MicroUserAddress microUserAddress)
    {
        List<MicroUserAddress> list = microUserAddressService.selectMicroUserAddressList(microUserAddress);
        ExcelUtil<MicroUserAddress> util = new ExcelUtil<MicroUserAddress>(MicroUserAddress.class);
        util.exportExcel(response, list, "地址管理数据");
    }

    /**
     * 获取地址管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('geth:address:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(microUserAddressService.selectMicroUserAddressById(id));
    }

    /**
     * 新增地址管理
     */
    @PreAuthorize("@ss.hasPermi('geth:address:add')")
    @Log(title = "地址管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MicroUserAddress microUserAddress)
    {
        return toAjax(microUserAddressService.insertMicroUserAddress(microUserAddress));
    }

    /**
     * 修改地址管理
     */
    @PreAuthorize("@ss.hasPermi('geth:address:edit')")
    @Log(title = "地址管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MicroUserAddress microUserAddress)
    {
        return toAjax(microUserAddressService.updateMicroUserAddress(microUserAddress));
    }

    /**
     * 删除地址管理
     */
    @PreAuthorize("@ss.hasPermi('geth:address:remove')")
    @Log(title = "地址管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(microUserAddressService.deleteMicroUserAddressByIds(ids));
    }
}
