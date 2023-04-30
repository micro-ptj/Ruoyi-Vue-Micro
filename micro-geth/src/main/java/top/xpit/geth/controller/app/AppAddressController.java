package top.xpit.geth.controller.app;

import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import top.xpit.common.annotation.Log;
import top.xpit.common.core.controller.BaseController;
import top.xpit.common.core.domain.AjaxResult;
import top.xpit.common.core.page.TableDataInfo;
import top.xpit.common.enums.BusinessType;
import top.xpit.common.utils.SecurityUtils;
import top.xpit.common.utils.poi.ExcelUtil;
import top.xpit.geth.domain.MicroUserAddress;
import top.xpit.geth.service.IMicroUserAddressService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author: PTJ
 * @Date: 2023/04/30/10:49
 * @Description:
 */
@RequestMapping("/app/address")
@RestController
@RequiredArgsConstructor
public class AppAddressController extends BaseController {

    @Autowired
    private IMicroUserAddressService microUserAddressService;

    /**
     * 查询地址管理列表
     */
    @GetMapping("/list")
    public TableDataInfo list(MicroUserAddress microUserAddress)
    {
        microUserAddress.setUserId(SecurityUtils.getAppUserId());
        microUserAddress.setDelFlag(0L);
        startPage();
        List<MicroUserAddress> list = microUserAddressService.selectMicroUserAddressList(microUserAddress);
        return getDataTable(list);
    }


    /**
     * 新增地址管理
     */
    @PostMapping
    public AjaxResult add(@RequestBody MicroUserAddress microUserAddress)
    {
        microUserAddress.setUserId(SecurityUtils.getAppUserId());
        return toAjax(microUserAddressService.insertMicroUserAddress(microUserAddress));
    }

    /**
     * 修改地址管理
     */
    @PutMapping
    public AjaxResult edit(@RequestBody MicroUserAddress microUserAddress)
    {
        return toAjax(microUserAddressService.updateMicroUserAddress(microUserAddress));
    }

}
