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
import top.xpit.geth.domain.MicroTransaction;
import top.xpit.geth.service.IMicroTransactionService;
import top.xpit.common.utils.poi.ExcelUtil;
import top.xpit.common.core.page.TableDataInfo;

/**
 * 区块链交易数据Controller
 * 
 * @author PTJ
 * @date 2023-03-12
 */
@RestController
@RequestMapping("/geth/transaction")
public class MicroTransactionController extends BaseController
{
    @Autowired
    private IMicroTransactionService microTransactionService;

    /**
     * 查询区块链交易数据列表
     */
    @PreAuthorize("@ss.hasPermi('geth:transaction:list')")
    @GetMapping("/list")
    public TableDataInfo list(MicroTransaction microTransaction)
    {
        startPage();
        List<MicroTransaction> list = microTransactionService.selectMicroTransactionList(microTransaction);
        return getDataTable(list);
    }

    /**
     * 导出区块链交易数据列表
     */
    @PreAuthorize("@ss.hasPermi('geth:transaction:export')")
    @Log(title = "区块链交易数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MicroTransaction microTransaction)
    {
        List<MicroTransaction> list = microTransactionService.selectMicroTransactionList(microTransaction);
        ExcelUtil<MicroTransaction> util = new ExcelUtil<MicroTransaction>(MicroTransaction.class);
        util.exportExcel(response, list, "区块链交易数据数据");
    }

    /**
     * 获取区块链交易数据详细信息
     */
    @PreAuthorize("@ss.hasPermi('geth:transaction:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(microTransactionService.selectMicroTransactionById(id));
    }

    /**
     * 新增区块链交易数据
     */
    @PreAuthorize("@ss.hasPermi('geth:transaction:add')")
    @Log(title = "区块链交易数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MicroTransaction microTransaction)
    {
        return toAjax(microTransactionService.insertMicroTransaction(microTransaction));
    }

    /**
     * 修改区块链交易数据
     */
    @PreAuthorize("@ss.hasPermi('geth:transaction:edit')")
    @Log(title = "区块链交易数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MicroTransaction microTransaction)
    {
        return toAjax(microTransactionService.updateMicroTransaction(microTransaction));
    }

    /**
     * 删除区块链交易数据
     */
    @PreAuthorize("@ss.hasPermi('geth:transaction:remove')")
    @Log(title = "区块链交易数据", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(microTransactionService.deleteMicroTransactionByIds(ids));
    }
}
