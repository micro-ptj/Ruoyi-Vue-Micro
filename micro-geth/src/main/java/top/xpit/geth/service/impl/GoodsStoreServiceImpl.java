package top.xpit.geth.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import top.xpit.common.constant.GethConstants;
import top.xpit.common.core.domain.AjaxResult;
import top.xpit.common.utils.SecurityUtils;
import top.xpit.common.utils.geth.BeanCopyUtils;
import top.xpit.geth.contract.GoodsStore;
import top.xpit.geth.domain.MicroTransaction;
import top.xpit.geth.domain.query.CreateGoodsParam;
import top.xpit.geth.service.GoodsStoreService;
import top.xpit.geth.service.IMicroTransactionService;

import java.math.BigInteger;

/**
 * @Author: ptj
 * @Date: 2023/04/02/22:39
 * @Description:
 */
@Service("goodsStoreService")
@RequiredArgsConstructor
public class GoodsStoreServiceImpl implements GoodsStoreService {

    @Autowired
    private Web3j web3j;

    private final static String contactAddress = GethConstants.CONTRACT_ADDRESS;

    private ContractGasProvider gasProvider = new DefaultGasProvider();

    private final IMicroTransactionService transactionService;

    private GoodsStore appGoodsStore() {
        Credentials credentials = Credentials.create(SecurityUtils.getPrivateKey());
        return GoodsStore.load(contactAddress, web3j, credentials, gasProvider);
    }

    private GoodsStore systemGoodsStore() {
        Credentials credentials = Credentials.create(GethConstants.PRIVATE_KEY);
        return GoodsStore.load(contactAddress, web3j, credentials, gasProvider);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult bid(BigInteger id, BigInteger weiValue) {
        TransactionReceipt send = null;
        try {
            send = appGoodsStore().escrowBid(id, weiValue).send();
            saveTransaction(send);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return AjaxResult.success(send.isStatusOK());
    }

    private void saveTransaction(TransactionReceipt send) {
        MicroTransaction microTransaction = BeanCopyUtils.copyBean(send, MicroTransaction.class);
        transactionService.insertMicroTransaction(microTransaction);
    }

    @Override
    public AjaxResult createGoods(CreateGoodsParam param) {
        TransactionReceipt send = null;
        try {
            send = systemGoodsStore().createGoods(param.getGoodsId(), param.getInterval(), GethConstants.GETH_ADDRESS).send();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        saveTransaction(send);
        return AjaxResult.success(send.isStatusOK());
    }

    @Override
    public AjaxResult withdraw(BigInteger id) {
        TransactionReceipt send = null;
        try {
            send = appGoodsStore().escrowWithdraw(id).send();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        saveTransaction(send);
        return AjaxResult.success(send.isStatusOK());
    }

    @Override
    public AjaxResult auctionEnd(BigInteger id) {
        //TODO 未开始
//        BigInteger send = systemGoodsStore().escrowAuctionEnd(id).send();
        return AjaxResult.success();
    }


}
