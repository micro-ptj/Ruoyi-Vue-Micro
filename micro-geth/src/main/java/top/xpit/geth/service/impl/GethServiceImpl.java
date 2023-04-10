package top.xpit.geth.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.web3j.crypto.*;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;
import top.xpit.common.constant.GethConstants;
import top.xpit.common.core.domain.AjaxResult;
import top.xpit.common.utils.SecurityUtils;
import top.xpit.geth.domain.MicroBid;
import top.xpit.geth.domain.MicroUserInfo;
import top.xpit.geth.mapper.MicroBidMapper;
import top.xpit.geth.mapper.MicroUserInfoMapper;
import top.xpit.geth.service.GethService;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Objects;

/**
 * @Author: ptj
 * @Date: 2023/03/02/20:30
 * @Description:
 */
@Slf4j
@Service("web3jService")
@RequiredArgsConstructor
public class GethServiceImpl implements GethService {

    private final MicroUserInfoMapper microUserInfoMapper;


    private final MicroBidMapper microBidMapper;

    @Autowired
    private Web3j web3j;

    /**
     * 获取geth版本信息
     * @throws IOException
     */
    @Override
    public String gethVersion() throws IOException {
        Web3ClientVersion web3ClientVersion = web3j.web3ClientVersion().send();
        String version = web3ClientVersion.getWeb3ClientVersion();
        log.info(version);
        return version;
    }

    /**
     * 生成区块链账户
     * @throws InvalidAlgorithmParameterException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     * @throws CipherException
     * @throws IOException
     */
    @Override
    public MicroUserInfo createAccount() throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException, CipherException, IOException {
        ECKeyPair ecKeyPair = Keys.createEcKeyPair();//调用Keys的静态方法创建密钥对
        String privateKey = ecKeyPair.getPrivateKey().toString(16);//获取私钥
        String publicKey = ecKeyPair.getPublicKey().toString(16);//获取公钥
        String address = Keys.getAddress(publicKey);//获取地址值
        MicroUserInfo microUserInfo = new MicroUserInfo();
        microUserInfo.setAddress("0x" + address);
        microUserInfo.setPrivateKey(privateKey);
        microUserInfo.setPublicKey(publicKey);
       return microUserInfo;
    }

    @Override
    public TransactionReceipt charge(BigDecimal value, Long userId) {
        Credentials credentials = Credentials.create(GethConstants.PRIVATE_KEY);
        MicroUserInfo userInfo = microUserInfoMapper.selectByUserId(userId);
        if (Objects.isNull(userInfo)){
            throw new RuntimeException("用户不存在");
        }
        //发送交易
        TransactionReceipt transactionReceipt = null;
        try {
            transactionReceipt = Transfer.sendFunds(web3j, credentials, userInfo.getAddress(), value, Convert.Unit.ETHER).send();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return transactionReceipt;
    }

    /**
     * 获取用户余额 更新到user Info中
     * @return
     */
    @Override
    public BigDecimal balance() {
        Long userId = SecurityUtils.getAppUserId();
        MicroUserInfo userInfo = microUserInfoMapper.selectByUserId(userId);
        EthGetBalance ethGetBalance = null;
        try {
            ethGetBalance = web3j.ethGetBalance(userInfo.getAddress(), DefaultBlockParameterName.LATEST).send();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BigInteger balance = ethGetBalance.getBalance();
        BigDecimal bigDecimal = Convert.fromWei(balance.toString(), Convert.Unit.ETHER);
        userInfo.setBalance(bigDecimal);
        int i = microUserInfoMapper.updateMicroUserInfo(userInfo);
        return bigDecimal;
    }

    @Override
    public Request<?, EthGetTransactionReceipt> getTransactionReceipt(String hash) {
        Request<?, EthGetTransactionReceipt> ethGetTransactionReceiptRequest = web3j.ethGetTransactionReceipt(hash);
        return ethGetTransactionReceiptRequest;
    }

}
