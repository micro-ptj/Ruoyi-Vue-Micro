package top.xpit.geth.controller.app;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import top.xpit.common.core.controller.BaseController;
import top.xpit.common.core.domain.AjaxResult;
import top.xpit.geth.domain.query.TransactionQueryParam;
import top.xpit.geth.domain.vo.TransactionVo;
import top.xpit.geth.service.IMicroTransactionService;

import javax.validation.Valid;

/**
 * @Author: PTJ
 * @Date: 2023/04/22/21:44
 * @Description:
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/app/transaction")
public class TransactionController extends BaseController {

    private final IMicroTransactionService transactionService;

    @PostMapping("/info")
    public AjaxResult info(@Valid @RequestBody TransactionQueryParam param) {
        TransactionVo transactionVo = transactionService.selectBySourceId(param);
        return success(transactionVo);
    }
}
