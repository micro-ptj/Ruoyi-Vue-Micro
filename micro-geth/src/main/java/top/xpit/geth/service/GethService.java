package top.xpit.geth.service;

import org.web3j.crypto.CipherException;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import top.xpit.common.core.domain.AjaxResult;
import top.xpit.geth.domain.MicroBid;
import top.xpit.geth.domain.MicroUserInfo;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 * @Author: ptj
 * @Date: 2023/03/02/20:29
 * @Description:
 */
public interface GethService {

    String gethVersion() throws IOException;

    MicroUserInfo createAccount() throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException, CipherException, IOException;


    TransactionReceipt charge(BigDecimal value, Long userId);

    BigDecimal balance();

    Request<?, EthGetTransactionReceipt> getTransactionReceipt(String hash);
}
