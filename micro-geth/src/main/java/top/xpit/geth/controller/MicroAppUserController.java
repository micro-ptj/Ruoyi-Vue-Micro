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
import top.xpit.geth.domain.MicroAppUser;
import top.xpit.geth.service.IMicroAppUserService;
import top.xpit.common.utils.poi.ExcelUtil;
import top.xpit.common.core.page.TableDataInfo;

/**
 * 用户管理Controller
 * 
 * @author PTJ
 * @date 2023-03-12
 */
@RestController
@RequestMapping("/geth/user")
public class MicroAppUserController extends BaseController
{
    @Autowired
    private IMicroAppUserService microAppUserService;

    /**
     * 查询用户管理列表
     */
    @PreAuthorize("@ss.hasPermi('geth:user:list')")
    @GetMapping("/list")
    public TableDataInfo list(MicroAppUser microAppUser)
    {
        startPage();
        List<MicroAppUser> list = microAppUserService.selectMicroAppUserList(microAppUser);
        return getDataTable(list);
    }

    /**
     * 导出用户管理列表
     */
    @PreAuthorize("@ss.hasPermi('geth:user:export')")
    @Log(title = "用户管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MicroAppUser microAppUser)
    {
        List<MicroAppUser> list = microAppUserService.selectMicroAppUserList(microAppUser);
        ExcelUtil<MicroAppUser> util = new ExcelUtil<MicroAppUser>(MicroAppUser.class);
        util.exportExcel(response, list, "用户管理数据");
    }

    /**
     * 获取用户管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('geth:user:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(microAppUserService.selectMicroAppUserById(id));
    }

    /**
     * 新增用户管理
     */
    @PreAuthorize("@ss.hasPermi('geth:user:add')")
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MicroAppUser microAppUser)
    {
        return toAjax(microAppUserService.insertMicroAppUser(microAppUser));
    }

    /**
     * 修改用户管理
     */
    @PreAuthorize("@ss.hasPermi('geth:user:edit')")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MicroAppUser microAppUser)
    {
        return toAjax(microAppUserService.updateMicroAppUser(microAppUser));
    }

    /**
     * 删除用户管理
     */
    @PreAuthorize("@ss.hasPermi('geth:user:remove')")
    @Log(title = "用户管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(microAppUserService.deleteMicroAppUserByIds(ids));
    }

}
