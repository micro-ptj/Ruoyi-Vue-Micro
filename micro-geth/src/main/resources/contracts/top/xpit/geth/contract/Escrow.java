package top.xpit.geth.contract;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
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
public class Escrow extends Contract {
    public static final String BINARY = "60806040523480156200001157600080fd5b5060405162000d7b38038062000d7b833981810160405281019062000037919062000141565b816000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055508284620000859190620001e2565b60018190555080600681905550505050506200021d565b600080fd5b6000819050919050565b620000b681620000a1565b8114620000c257600080fd5b50565b600081519050620000d681620000ab565b92915050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b60006200010982620000dc565b9050919050565b6200011b81620000fc565b81146200012757600080fd5b50565b6000815190506200013b8162000110565b92915050565b600080600080608085870312156200015e576200015d6200009c565b5b60006200016e87828801620000c5565b94505060206200018187828801620000c5565b935050604062000194878288016200012a565b9250506060620001a787828801620000c5565b91505092959194509250565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b6000620001ef82620000a1565b9150620001fc83620000a1565b9250828201905080821115620002175762000216620001b3565b5b92915050565b610b4e806200022d6000396000f3fe6080604052600436106100555760003560e01c806312fa6feb1461005a5780631998aeef146100855780632a24f46c1461008f5780633ccfd60b146100a6578063787c0a6c146100d1578063b5e221c1146100fc575b600080fd5b34801561006657600080fd5b5061006f610128565b60405161007c919061078d565b60405180910390f35b61008d61013b565b005b34801561009b57600080fd5b506100a46102cc565b005b3480156100b257600080fd5b506100bb610613565b6040516100c8919061078d565b60405180910390f35b3480156100dd57600080fd5b506100e6610737565b6040516100f391906107c1565b60405180910390f35b34801561010857600080fd5b50610111610741565b60405161011f92919061081d565b60405180910390f35b600560009054906101000a900460ff1681565b600154421115610180576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610177906108a3565b60405180910390fd5b60035434116101c4576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016101bb9061090f565b60405180910390fd5b6000600354146102495760035460046000600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000828254610241919061095e565b925050819055505b33600260006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550346003819055507ff4757a49b326036464bec6fe419a4ae38c8a02ce3e68bf0809674f6aab8ad30033346040516102c292919061081d565b60405180910390a1565b600154421015610311576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610308906109de565b60405180910390fd5b600560009054906101000a900460ff1615610361576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161035890610a70565b60405180910390fd5b6001600560006101000a81548160ff0219169083151502179055507fdaec4582d5d9595688c8c98545fdd1c696d41c6aeaeb636737e84ed2f5c00eda600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff166003546040516103d192919061081d565b60405180910390a160008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc6003549081150290604051600060405180830381858888f19350505050158015610441573d6000803e3d6000fd5b50600060046000600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020541115610611577fdaec4582d5d9595688c8c98545fdd1c696d41c6aeaeb636737e84ed2f5c00eda3060046000600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205460405161053e929190610aef565b60405180910390a1600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc60046000600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020549081150290604051600060405180830381858888f1935050505015801561060f573d6000803e3d6000fd5b505b565b600080600460003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020549050600081111561072e576000600460003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055503373ffffffffffffffffffffffffffffffffffffffff166108fc829081150290604051600060405180830381858888f1935050505061072d5780600460003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055506000915050610734565b5b60019150505b90565b6000600154905090565b600080600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16600354915091509091565b60008115159050919050565b61078781610772565b82525050565b60006020820190506107a2600083018461077e565b92915050565b6000819050919050565b6107bb816107a8565b82525050565b60006020820190506107d660008301846107b2565b92915050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000610807826107dc565b9050919050565b610817816107fc565b82525050565b6000604082019050610832600083018561080e565b61083f60208301846107b2565b9392505050565b600082825260208201905092915050565b7f41756374696f6e20616c726561647920656e6465642e00000000000000000000600082015250565b600061088d601683610846565b915061089882610857565b602082019050919050565b600060208201905081810360008301526108bc81610880565b9050919050565b7f546865726520616c7265616479206973206120686967686572206269642e0000600082015250565b60006108f9601e83610846565b9150610904826108c3565b602082019050919050565b60006020820190508181036000830152610928816108ec565b9050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b6000610969826107a8565b9150610974836107a8565b925082820190508082111561098c5761098b61092f565b5b92915050565b7f41756374696f6e206e6f742079657420656e6465642e00000000000000000000600082015250565b60006109c8601683610846565b91506109d382610992565b602082019050919050565b600060208201905081810360008301526109f7816109bb565b9050919050565b7f61756374696f6e456e642068617320616c7265616479206265656e2063616c6c60008201527f65642e0000000000000000000000000000000000000000000000000000000000602082015250565b6000610a5a602383610846565b9150610a65826109fe565b604082019050919050565b60006020820190508181036000830152610a8981610a4d565b9050919050565b6000819050919050565b6000610ab5610ab0610aab846107dc565b610a90565b6107dc565b9050919050565b6000610ac782610a9a565b9050919050565b6000610ad982610abc565b9050919050565b610ae981610ace565b82525050565b6000604082019050610b046000830185610ae0565b610b1160208301846107b2565b939250505056fea2646970667358221220b8cf343bcea0fad2a44baca3e2899ff725c654dea5ca5a024670c5d3d6d686ae64736f6c63430008130033";

    public static final String FUNC_AUCTIONEND = "auctionEnd";

    public static final String FUNC_BID = "bid";

    public static final String FUNC_ENDED = "ended";

    public static final String FUNC_GETAUCTIONENDTIME = "getAuctionEndTime";

    public static final String FUNC_WINNERBIDDER = "winnerBidder";

    public static final String FUNC_WITHDRAW = "withdraw";

    public static final Event AUCTIONENDED_EVENT = new Event("AuctionEnded", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event HIGHESTBIDINCREASED_EVENT = new Event("HighestBidIncreased", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected Escrow(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Escrow(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Escrow(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Escrow(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<AuctionEndedEventResponse> getAuctionEndedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(AUCTIONENDED_EVENT, transactionReceipt);
        ArrayList<AuctionEndedEventResponse> responses = new ArrayList<AuctionEndedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            AuctionEndedEventResponse typedResponse = new AuctionEndedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.winner = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<AuctionEndedEventResponse> auctionEndedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, AuctionEndedEventResponse>() {
            @Override
            public AuctionEndedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(AUCTIONENDED_EVENT, log);
                AuctionEndedEventResponse typedResponse = new AuctionEndedEventResponse();
                typedResponse.log = log;
                typedResponse.winner = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<AuctionEndedEventResponse> auctionEndedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(AUCTIONENDED_EVENT));
        return auctionEndedEventFlowable(filter);
    }

    public List<HighestBidIncreasedEventResponse> getHighestBidIncreasedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(HIGHESTBIDINCREASED_EVENT, transactionReceipt);
        ArrayList<HighestBidIncreasedEventResponse> responses = new ArrayList<HighestBidIncreasedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            HighestBidIncreasedEventResponse typedResponse = new HighestBidIncreasedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.bidder = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<HighestBidIncreasedEventResponse> highestBidIncreasedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, HighestBidIncreasedEventResponse>() {
            @Override
            public HighestBidIncreasedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(HIGHESTBIDINCREASED_EVENT, log);
                HighestBidIncreasedEventResponse typedResponse = new HighestBidIncreasedEventResponse();
                typedResponse.log = log;
                typedResponse.bidder = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<HighestBidIncreasedEventResponse> highestBidIncreasedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(HIGHESTBIDINCREASED_EVENT));
        return highestBidIncreasedEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> auctionEnd() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_AUCTIONEND, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> bid() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_BID, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> ended() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ENDED, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<BigInteger> getAuctionEndTime() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETAUCTIONENDTIME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Tuple2<String, BigInteger>> winnerBidder() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_WINNERBIDDER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple2<String, BigInteger>>(function,
                new Callable<Tuple2<String, BigInteger>>() {
                    @Override
                    public Tuple2<String, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<String, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue());
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> withdraw() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_WITHDRAW, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static Escrow load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Escrow(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Escrow load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Escrow(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Escrow load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Escrow(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Escrow load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Escrow(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Escrow> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, BigInteger _startTime, BigInteger _biddingTime, String _beneficiary, BigInteger _uniqueItemId) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_startTime), 
                new org.web3j.abi.datatypes.generated.Uint256(_biddingTime), 
                new org.web3j.abi.datatypes.Address(160, _beneficiary), 
                new org.web3j.abi.datatypes.generated.Uint256(_uniqueItemId)));
        return deployRemoteCall(Escrow.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<Escrow> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, BigInteger _startTime, BigInteger _biddingTime, String _beneficiary, BigInteger _uniqueItemId) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_startTime), 
                new org.web3j.abi.datatypes.generated.Uint256(_biddingTime), 
                new org.web3j.abi.datatypes.Address(160, _beneficiary), 
                new org.web3j.abi.datatypes.generated.Uint256(_uniqueItemId)));
        return deployRemoteCall(Escrow.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Escrow> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger _startTime, BigInteger _biddingTime, String _beneficiary, BigInteger _uniqueItemId) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_startTime), 
                new org.web3j.abi.datatypes.generated.Uint256(_biddingTime), 
                new org.web3j.abi.datatypes.Address(160, _beneficiary), 
                new org.web3j.abi.datatypes.generated.Uint256(_uniqueItemId)));
        return deployRemoteCall(Escrow.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Escrow> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger _startTime, BigInteger _biddingTime, String _beneficiary, BigInteger _uniqueItemId) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_startTime), 
                new org.web3j.abi.datatypes.generated.Uint256(_biddingTime), 
                new org.web3j.abi.datatypes.Address(160, _beneficiary), 
                new org.web3j.abi.datatypes.generated.Uint256(_uniqueItemId)));
        return deployRemoteCall(Escrow.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class AuctionEndedEventResponse extends BaseEventResponse {
        public String winner;

        public BigInteger amount;
    }

    public static class HighestBidIncreasedEventResponse extends BaseEventResponse {
        public String bidder;

        public BigInteger amount;
    }
}
