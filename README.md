### 安装geth

```bash
yum install -y golang git
git clone https://github.com/ethereum/go-ethereum.git
cd go-ethereum
export GOPROXY=https://proxy.golang.com.cn,direct
make all
```

### geth dev

```bash
geth --datadir ./devdata/ --dev --http --http.addr 0.0.0.0 --http.api web3,eth,debug,personal,net --rpc.allow-unprotected-txs console
```

### solidity compile

+ solidity into the abi and bin

    + idea config

      ```bash
      --abi --bin $FileName$ -o ./
      ```

+ abi and bin Into the java

    + web3j-cli download

        + windows

      ```bash
      Set-ExecutionPolicy Bypass -Scope Process -Force; iex ((New-Object System.Net.WebClient).DownloadString('https://raw.githubusercontent.com/web3j/web3j-installer/master/installer.ps1'))
      ```

        + linux

      ```bash
      curl -L get.web3j.io | sh
      source $HOME/.web3j/source.sh
      ```

    + Into the java

  ```bash
  web3j generate solidity --abiFile Dapp_sol_Dapp.abi --binFile Dapp_sol_Dapp.bin -o .\src\main\java -p xpit.top.action.contract
  ```

  ```bash
  web3j generate solidity --abiFile GoodsStore.abi --binFile GoodsStore.bin -o .\src\main\java -p top.xpit.action.contract
  ```
  
```text
Kwei(Babbage) = 10^3 Wei
Mwei(Lovelace) = 10^6 Wei
Gwei(Shannon) = 10^9 Wei
Microether(Szabo) = 10^12 Wei
Milliether(Finney) = 10^15 Wei
Ether = 10^18 Wei
```


```text
eth.sendTransaction({from: eth.accounts[0], to: '0x62E04b39B03c731697c71c9b9A09Ab06f1A0D5c2', value: web3.toWei(1000, 'ether')})
```

```text
"test","1","/profile/upload/2023/03/22/1_20230322194759A001.jpg","<p>test</p>","1679414400","1679673600","5","0"
```

```text
keccak256(abi.encode("15", "test"))
0x7ff1ee2fbf7d5831d8bdf7c2714eef4c4a3f3e225f7e4b80a76b81c3a9f858a7
```

```text
这份智能合约实现了一个简单的密封拍卖，竞拍者可以在规定时间内进行出价，最终以第二高价支付。合约在构造函数中传入了商品唯一标识，最终在拍卖结束时将其写入合约日志中。

该合约具有以下基本特性：

构造函数中传入拍卖时间、受益人地址和商品唯一标识。
竞拍者可以在规定时间内进行出价，如果出价高于当前最高出价，则将之前
```