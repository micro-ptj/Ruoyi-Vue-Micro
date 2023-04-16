package top.xpit.geth.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.utils.Convert;
import top.xpit.common.constant.GethConstants;
import top.xpit.geth.constants.GethConstantsEnum;
import top.xpit.geth.contract.Escrow;
import top.xpit.geth.domain.MicroBid;
import top.xpit.geth.mapper.MicroUserInfoMapper;
import top.xpit.geth.service.EscrowService;
import top.xpit.geth.service.GoodsStoreService;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @Author: ptj
 * @Date: 2023/04/16/16:52
 * @Description:
 */
@Service("escrowService")
@RequiredArgsConstructor
@Slf4j
public class EscrowServiceImpl implements EscrowService {
    @Autowired
    private Web3j web3j;

    private final GoodsStoreService goodsStoreService;

    private final static String contactAddress = GethConstants.CONTRACT_ADDRESS;


    private ContractGasProvider gasProvider = new DefaultGasProvider();


    private final MicroUserInfoMapper microUserInfoMapper;

    private Escrow appEscrow(BigInteger id, Long userId) {
        String privateKey = microUserInfoMapper.selectPrivateKeyByUserId(userId);
        log.debug(privateKey);
        Credentials credentials = Credentials.create(privateKey);
        return Escrow.load(this.escrowAddress(id), web3j, credentials, gasProvider);
    }

    private Escrow systemEscrow(BigInteger id) {
        Credentials credentials = Credentials.create(GethConstants.PRIVATE_KEY);
        return Escrow.load(this.escrowAddress(id), web3j, credentials, gasProvider);
    }

    @Override
    public String escrowAddress(BigInteger id) {
        try {
            return goodsStoreService.systemGoodsStore().escrowAddress(id).send();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean bid(BigInteger id, BigDecimal weiValue, MicroBid microBid) {
        BigInteger amount = Convert.toWei(weiValue, Convert.Unit.ETHER).toBigInteger();
        TransactionReceipt send = null;
        try {
            send = systemEscrow(id).bid(amount).send();
            log.debug(send.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (send.isStatusOK()){
            goodsStoreService.saveTransaction(send, GethConstantsEnum.MICRO_BID.getSourceType(), microBid.getId(), this.escrowAddress(id));
        }
        return send.isStatusOK();
    }

    /**
     * 释放资金需要每个用户自己调用
     * 为了方便操作 此处采用后端进行
     * @param id
     * @param orderId
     * @return
     */
    @Override
    public boolean withdraw(BigInteger id, Long orderId, Long userId) {
        TransactionReceipt send = null;
        try {
            send = appEscrow(id, userId).withdraw().send();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        goodsStoreService.saveTransaction(send, GethConstantsEnum.MICRO_ORDER.getSourceType(), orderId, this.escrowAddress(id));

        return send.isStatusOK();
    }



    @Override
    public void auctionEnd(BigInteger id) {
        try {
            systemEscrow(id).auctionEnd().send();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean ended(BigInteger goodsId) {
        try {
            return systemEscrow(goodsId).ended().send();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
