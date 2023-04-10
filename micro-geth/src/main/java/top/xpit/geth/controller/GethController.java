package top.xpit.geth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.xpit.common.core.controller.BaseController;
import top.xpit.common.core.domain.AjaxResult;
import top.xpit.geth.service.GethService;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * @Author: ptj
 * @Date: 2023/03/02/20:44
 * @Description:
 */
@RestController
@RequestMapping("geth")
public class GethController extends BaseController {

    @Autowired
    private GethService gethService;

    @GetMapping("gethVersion")
    public AjaxResult gethVersion() throws IOException {
        return success(gethService.gethVersion());
    }

    @PostMapping("receipt")
    public AjaxResult getTransactionReceipt(@RequestParam String hash) {
        return success(gethService.getTransactionReceipt(hash));
    }


    /**
     * 赠送以太币
     * @param value
     * @param userId
     * @return
     */
    @GetMapping("charge")
    public AjaxResult charge(BigDecimal value, Long userId){
        return success(gethService.charge(value, userId));
    }

    @GetMapping("balance")
    public AjaxResult balance(){
        return success(gethService.balance());
    }
}
