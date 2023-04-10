package top.xpit.geth.contract;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.4.1.
 */
@SuppressWarnings("rawtypes")
public class GoodsStore extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b506118f4806100206000396000f3fe6080604052600436106200009e5760003560e01c8063850a05571162000061578063850a055714620001b9578063b901906614620001fd578063c6dfb2ef1462000241578063c93b01901462000261578063e2e07458146200028f576200009e565b806305d6307014620000a357806338af3eed14620000e75780634bfca768146200011757806364c0187914620001475780636bf78da7146200018b575b600080fd5b348015620000b057600080fd5b50620000cf6004803603810190620000c99190620008af565b620002d3565b604051620000de919062000a0a565b60405180910390f35b348015620000f457600080fd5b50620000ff62000327565b6040516200010e9190620009d0565b60405180910390f35b3480156200012457600080fd5b506200012f6200034b565b6040516200013e919062000a0a565b60405180910390f35b3480156200015457600080fd5b506200017360048036038101906200016d9190620008af565b62000351565b604051620001829190620009ed565b60405180910390f35b3480156200019857600080fd5b50620001b76004803603810190620001b1919062000913565b62000411565b005b348015620001c657600080fd5b50620001e56004803603810190620001df9190620008af565b62000529565b604051620001f4919062000a0a565b60405180910390f35b3480156200020a57600080fd5b50620002296004803603810190620002239190620008af565b620005e7565b604051620002389190620009b3565b60405180910390f35b6200025f6004803603810190620002599190620008af565b62000624565b005b3480156200026e57600080fd5b506200028d6004803603810190620002879190620008af565b620006bd565b005b3480156200029c57600080fd5b50620002bb6004803603810190620002b59190620008af565b62000755565b604051620002ca919062000a0a565b60405180910390f35b60006002600083815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16319050919050565b60008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60035481565b60006002600083815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16633ccfd60b6040518163ffffffff1660e01b8152600401602060405180830381600087803b158015620003cf57600080fd5b505af1158015620003e4573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906200040a91906200087d565b9050919050565b81426200041f919062000a64565b60018190555082600381905550806000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550600060015460008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1685604051620004a19062000813565b620004af9392919062000a27565b604051809103906000f080158015620004cc573d6000803e3d6000fd5b5090508060026000600354815260200190815260200160002060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050505050565b60006002600083815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16634bfca7686040518163ffffffff1660e01b815260040160206040518083038186803b158015620005a557600080fd5b505afa158015620005ba573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250810190620005e09190620008e1565b9050919050565b60006002600083815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050919050565b6002600082815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16631998aeef346040518263ffffffff1660e01b81526004016000604051808303818588803b158015620006a057600080fd5b505af1158015620006b5573d6000803e3d6000fd5b505050505050565b6002600082815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16632a24f46c6040518163ffffffff1660e01b8152600401600060405180830381600087803b1580156200073957600080fd5b505af11580156200074e573d6000803e3d6000fd5b5050505050565b60006002600083815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663787c0a6c6040518163ffffffff1660e01b815260040160206040518083038186803b158015620007d157600080fd5b505afa158015620007e6573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906200080c9190620008e1565b9050919050565b610d1d8062000ba283390190565b600081359050620008328162000b53565b92915050565b600081519050620008498162000b6d565b92915050565b600081359050620008608162000b87565b92915050565b600081519050620008778162000b87565b92915050565b60006020828403121562000896576200089562000b4e565b5b6000620008a68482850162000838565b91505092915050565b600060208284031215620008c857620008c762000b4e565b5b6000620008d8848285016200084f565b91505092915050565b600060208284031215620008fa57620008f962000b4e565b5b60006200090a8482850162000866565b91505092915050565b6000806000606084860312156200092f576200092e62000b4e565b5b60006200093f868287016200084f565b935050602062000952868287016200084f565b9250506040620009658682870162000821565b9150509250925092565b6200097a8162000ad5565b82525050565b6200098b8162000ac1565b82525050565b6200099c8162000ae9565b82525050565b620009ad8162000b15565b82525050565b6000602082019050620009ca600083018462000980565b92915050565b6000602082019050620009e760008301846200096f565b92915050565b600060208201905062000a04600083018462000991565b92915050565b600060208201905062000a216000830184620009a2565b92915050565b600060608201905062000a3e6000830186620009a2565b62000a4d60208301856200096f565b62000a5c6040830184620009a2565b949350505050565b600062000a718262000b15565b915062000a7e8362000b15565b9250827fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff0382111562000ab65762000ab562000b1f565b5b828201905092915050565b600062000ace8262000af5565b9050919050565b600062000ae28262000af5565b9050919050565b60008115159050919050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000819050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b600080fd5b62000b5e8162000ad5565b811462000b6a57600080fd5b50565b62000b788162000ae9565b811462000b8457600080fd5b50565b62000b928162000b15565b811462000b9e57600080fd5b5056fe60806040523480156200001157600080fd5b5060405162000d1d38038062000d1d8339818101604052810190620000379190620000c9565b816000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550824262000085919062000125565b6001819055508060068190555050505062000228565b600081519050620000ac81620001f4565b92915050565b600081519050620000c3816200020e565b92915050565b600080600060608486031215620000e557620000e4620001ef565b5b6000620000f586828701620000b2565b935050602062000108868287016200009b565b92505060406200011b86828701620000b2565b9150509250925092565b60006200013282620001b6565b91506200013f83620001b6565b9250827fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff03821115620001775762000176620001c0565b5b828201905092915050565b60006200018f8262000196565b9050919050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000819050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b600080fd5b620001ff8162000182565b81146200020b57600080fd5b50565b6200021981620001b6565b81146200022557600080fd5b50565b610ae580620002386000396000f3fe60806040526004361061004a5760003560e01c80631998aeef1461004f5780632a24f46c146100595780633ccfd60b146100705780634bfca7681461009b578063787c0a6c146100c6575b600080fd5b6100576100f1565b005b34801561006557600080fd5b5061006e610282565b005b34801561007c57600080fd5b506100856105c9565b604051610092919061081b565b60405180910390f35b3480156100a757600080fd5b506100b06106ed565b6040516100bd91906108b6565b60405180910390f35b3480156100d257600080fd5b506100db6106f7565b6040516100e891906108b6565b60405180910390f35b600154421115610136576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161012d90610836565b60405180910390fd5b600354341161017a576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161017190610856565b60405180910390fd5b6000600354146101ff5760035460046000600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008282546101f791906108e2565b925050819055505b33600260006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550346003819055507ff4757a49b326036464bec6fe419a4ae38c8a02ce3e68bf0809674f6aab8ad30033346040516102789291906107f2565b60405180910390a1565b6001544210156102c7576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016102be90610876565b60405180910390fd5b600560009054906101000a900460ff1615610317576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161030e90610896565b60405180910390fd5b6001600560006101000a81548160ff0219169083151502179055507fdaec4582d5d9595688c8c98545fdd1c696d41c6aeaeb636737e84ed2f5c00eda600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff166003546040516103879291906107f2565b60405180910390a160008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc6003549081150290604051600060405180830381858888f193505050501580156103f7573d6000803e3d6000fd5b50600060046000600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205411156105c7577fdaec4582d5d9595688c8c98545fdd1c696d41c6aeaeb636737e84ed2f5c00eda3060046000600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020546040516104f49291906107c9565b60405180910390a1600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc60046000600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020549081150290604051600060405180830381858888f193505050501580156105c5573d6000803e3d6000fd5b505b565b600080600460003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054905060008111156106e4576000600460003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055503373ffffffffffffffffffffffffffffffffffffffff166108fc829081150290604051600060405180830381858888f193505050506106e35780600460003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000208190555060009150506106ea565b5b60019150505b90565b6000600654905090565b6000600154905090565b61070a81610980565b82525050565b61071981610938565b82525050565b6107288161094a565b82525050565b600061073b6016836108d1565b9150610746826109e5565b602082019050919050565b600061075e601e836108d1565b915061076982610a0e565b602082019050919050565b60006107816016836108d1565b915061078c82610a37565b602082019050919050565b60006107a46023836108d1565b91506107af82610a60565b604082019050919050565b6107c381610976565b82525050565b60006040820190506107de6000830185610701565b6107eb60208301846107ba565b9392505050565b60006040820190506108076000830185610710565b61081460208301846107ba565b9392505050565b6000602082019050610830600083018461071f565b92915050565b6000602082019050818103600083015261084f8161072e565b9050919050565b6000602082019050818103600083015261086f81610751565b9050919050565b6000602082019050818103600083015261088f81610774565b9050919050565b600060208201905081810360008301526108af81610797565b9050919050565b60006020820190506108cb60008301846107ba565b92915050565b600082825260208201905092915050565b60006108ed82610976565b91506108f883610976565b9250827fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff0382111561092d5761092c6109b6565b5b828201905092915050565b600061094382610956565b9050919050565b60008115159050919050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000819050919050565b600061098b82610992565b9050919050565b600061099d826109a4565b9050919050565b60006109af82610956565b9050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b7f41756374696f6e20616c726561647920656e6465642e00000000000000000000600082015250565b7f546865726520616c7265616479206973206120686967686572206269642e0000600082015250565b7f41756374696f6e206e6f742079657420656e6465642e00000000000000000000600082015250565b7f61756374696f6e456e642068617320616c7265616479206265656e2063616c6c60008201527f65642e000000000000000000000000000000000000000000000000000000000060208201525056fea2646970667358221220007e7aaaf913a46ff3786bf57c6ef831c6c973b05994646ce2049de13eb22aa864736f6c63430008070033a2646970667358221220f774f8e620d73be47a243bb3df930b675655ae950bacc840a274499218fa6a5464736f6c63430008070033";

    public static final String FUNC_BENEFICIARY = "beneficiary";

    public static final String FUNC_CREATEGOODS = "createGoods";

    public static final String FUNC_ESCROWADDRESS = "escrowAddress";

    public static final String FUNC_ESCROWAUCTIONEND = "escrowAuctionEnd";

    public static final String FUNC_ESCROWAUCTIONENDTIME = "escrowAuctionEndTime";

    public static final String FUNC_ESCROWBID = "escrowBid";

    public static final String FUNC_ESCROWGOODSID = "escrowGoodsId";

    public static final String FUNC_ESCROWWITHDRAW = "escrowWithdraw";

    public static final String FUNC_GETCONTRACTBALANCE = "getContractBalance";

    public static final String FUNC_GOODSID = "goodsId";

    @Deprecated
    protected GoodsStore(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected GoodsStore(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected GoodsStore(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected GoodsStore(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<String> beneficiary() {
        final Function function = new Function(FUNC_BENEFICIARY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> createGoods(BigInteger _goodsId, BigInteger _biddingTime, String _beneficiary) {
        final Function function = new Function(
                FUNC_CREATEGOODS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_goodsId), 
                new org.web3j.abi.datatypes.generated.Uint256(_biddingTime), 
                new org.web3j.abi.datatypes.Address(160, _beneficiary)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> escrowAddress(BigInteger _goodsId) {
        final Function function = new Function(FUNC_ESCROWADDRESS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_goodsId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> escrowAuctionEnd(BigInteger _goodsId) {
        final Function function = new Function(
                FUNC_ESCROWAUCTIONEND, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_goodsId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> escrowAuctionEndTime(BigInteger _goodsId) {
        final Function function = new Function(FUNC_ESCROWAUCTIONENDTIME, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_goodsId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> escrowBid(BigInteger _goodsId, BigInteger webValue) {
        final Function function = new Function(
                FUNC_ESCROWBID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_goodsId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, webValue);
    }

    public RemoteFunctionCall<BigInteger> escrowGoodsId(BigInteger _goodsId) {
        final Function function = new Function(FUNC_ESCROWGOODSID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_goodsId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> escrowWithdraw(BigInteger _goodsId) {
        final Function function = new Function(
                FUNC_ESCROWWITHDRAW, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_goodsId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> getContractBalance(BigInteger _goodsId) {
        final Function function = new Function(FUNC_GETCONTRACTBALANCE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_goodsId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> goodsId() {
        final Function function = new Function(FUNC_GOODSID, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    @Deprecated
    public static GoodsStore load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new GoodsStore(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static GoodsStore load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new GoodsStore(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static GoodsStore load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new GoodsStore(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static GoodsStore load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new GoodsStore(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<GoodsStore> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(GoodsStore.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<GoodsStore> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(GoodsStore.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<GoodsStore> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(GoodsStore.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<GoodsStore> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(GoodsStore.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }
}
