package top.xpit.geth.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.utils.Convert;
import top.xpit.common.constant.GethConstants;
import top.xpit.common.core.domain.AjaxResult;
import top.xpit.common.utils.SecurityUtils;
import top.xpit.common.utils.geth.BeanCopyUtils;
import top.xpit.geth.constants.GethConstantsEnum;
import top.xpit.geth.contract.GoodsStore;
import top.xpit.geth.domain.MicroAppUser;
import top.xpit.geth.domain.MicroBid;
import top.xpit.geth.domain.MicroTransaction;
import top.xpit.geth.domain.MicroUserInfo;
import top.xpit.geth.domain.dto.OrderDto;
import top.xpit.geth.domain.query.CreateGoodsParam;
import top.xpit.geth.mapper.MicroUserInfoMapper;
import top.xpit.geth.service.GoodsStoreService;
import top.xpit.geth.service.IMicroAppUserService;
import top.xpit.geth.service.IMicroTransactionService;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @Author: ptj
 * @Date: 2023/04/02/22:39
 * @Description:
 */
@Service("goodsStoreService")
@RequiredArgsConstructor
@Slf4j
public class GoodsStoreServiceImpl implements GoodsStoreService {

    @Autowired
    private Web3j web3j;

    private final static String contactAddress = GethConstants.CONTRACT_ADDRESS;

    private ContractGasProvider gasProvider = new DefaultGasProvider();

    private final IMicroTransactionService transactionService;

    private final MicroUserInfoMapper microUserInfoMapper;

    private GoodsStore appGoodsStore() {
        String privateKey = microUserInfoMapper.selectPrivateKeyByUserId(SecurityUtils.getAppUserId());
        log.debug(privateKey);
        Credentials credentials = Credentials.create(privateKey);
        return GoodsStore.load(contactAddress, web3j, credentials, gasProvider);
    }

    @Override
    public GoodsStore systemGoodsStore() {
        Credentials credentials = Credentials.create(GethConstants.PRIVATE_KEY);
        return GoodsStore.load(contactAddress, web3j, credentials, gasProvider);
    }

    /**
     * 竞拍
     * @param id
     * @param weiValue
     * @param microBid
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean bid(BigInteger id, BigDecimal weiValue, MicroBid microBid) {
        BigInteger amount = Convert.toWei(weiValue, Convert.Unit.ETHER).toBigInteger();
        TransactionReceipt send = null;
        try {
            send = appGoodsStore().escrowBid(id, amount).send();
            log.debug(send.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (send.isStatusOK()){
            saveTransaction(send, GethConstantsEnum.MICRO_BID.getSourceType(), microBid.getId(), GethConstants.CONTRACT_ADDRESS);
        }
        return send.isStatusOK();
    }

    /**
     * 保存信息到区块链
     * @param send
     * @param sourceTye
     * @param sourceId
     */
    public void saveTransaction(TransactionReceipt send, String sourceTye, Long sourceId, String contactAddress) {
        MicroTransaction microTransaction = BeanCopyUtils.copyBean(send, MicroTransaction.class);
//        MicroTransaction microTransaction = new MicroTransaction();
//        microTransaction.setTransactionHash(send.getTransactionHash());
        microTransaction.setTransactionIndex(send.getTransactionIndexRaw());
//        microTransaction.setBlockHash(send.getBlockHash());
        microTransaction.setBlockNumber(send.getBlockNumberRaw());
        microTransaction.setCumulativeGasUsed(send.getCumulativeGasUsedRaw());
        microTransaction.setGasUsed(send.getGasUsedRaw());
//        microTransaction.setStatus(send.getStatus());
//        microTransaction.setFrom(send.getFrom());
//        microTransaction.setTo(send.getTo());
        microTransaction.setLogs(send.getLogs().toString());
//        microTransaction.setLogsBloom(send.getLogsBloom());
        microTransaction.setContractAddress(contactAddress);
        microTransaction.setSourceType(sourceTye);
        microTransaction.setSourceId(sourceId);
        transactionService.insertMicroTransaction(microTransaction);
    }

    /**
     * 上传商品
     * @param param
     * @return
     */
    @Override
    public boolean createGoods(CreateGoodsParam param) {
        TransactionReceipt send = null;
        try {
            log.debug(param.toString());
            send = systemGoodsStore().createGoods(param.getStartTimeStamp(), param.getGoodsId(), param.getInterval(), param.getAddress()).send();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        saveTransaction(send, GethConstantsEnum.MICRO_GOODS.getSourceType(), param.getGoodsId().longValue(), GethConstants.CONTRACT_ADDRESS);
        return send.isStatusOK();
    }

    @Override
    public boolean withdraw(BigInteger id, Long orderId) {
        TransactionReceipt send = null;
        try {
            send = systemGoodsStore().escrowWithdraw(id).send();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        saveTransaction(send, GethConstantsEnum.MICRO_ORDER.getSourceType(), orderId, GethConstants.CONTRACT_ADDRESS);

        return send.isStatusOK();
    }



    @Override
    public void auctionEnd(BigInteger id) {
        try {
            systemGoodsStore().escrowAuctionEnd(id).send();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public OrderDto winnerBidder(BigInteger id) {
        Tuple2<String, BigInteger> send = null;
        try {
            send = systemGoodsStore().escrowWinnerBidder(id).send();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        OrderDto orderDto = new OrderDto();
        orderDto.setAddress(send.component1());
        int i = (int)Math.pow(10, 9);
        BigInteger divide = send.component2().divide(BigInteger.valueOf(i)).divide(BigInteger.valueOf(i));
        orderDto.setAmount(divide);
        return orderDto;
    }


}
