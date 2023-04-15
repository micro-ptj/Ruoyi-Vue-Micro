// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

contract Escrow {

    address payable beneficiary; // 受益人地址
    uint auctionEndTime; // 拍卖结束时间
    address highestBidder; // 最高出价者地址
    uint highestBid;
    mapping(address => uint) pendingReturns; // 出价者需要取回的金额
    bool ended; // 拍卖是否已结束

    uint uniqueItemId; // 商品唯一标识

    event HighestBidIncreased(address bidder, uint amount); // 最高出价增加事件
    event AuctionEnded(address winner, uint amount); // 拍卖结束事件

    constructor(uint _startTime, uint _biddingTime, address payable _beneficiary, uint _uniqueItemId) {
        beneficiary = _beneficiary;
        auctionEndTime = _startTime + _biddingTime;
        uniqueItemId = _uniqueItemId;
    }

    function bid() public payable {
        require(block.timestamp <= auctionEndTime, "Auction already ended.");
        require(msg.value > highestBid, "There already is a higher bid.");

        if (highestBid != 0) {
            pendingReturns[highestBidder] += highestBid; // 将之前最高出价者的出价金额加入取回列表
        }

        highestBidder = msg.sender;
        highestBid = msg.value;
        emit HighestBidIncreased(msg.sender, msg.value);
    }

    function withdraw() public returns (bool) {
        uint amount = pendingReturns[msg.sender];
        if (amount > 0) {
            pendingReturns[msg.sender] = 0;
            if (!payable(msg.sender).send(amount)) {
                pendingReturns[msg.sender] = amount;
                return false;
            }
        }
        return true;
    }

    function auctionEnd() public {
        require(block.timestamp >= auctionEndTime, "Auction not yet ended.");
        require(!ended, "auctionEnd has already been called.");

        ended = true;
        emit AuctionEnded(highestBidder, highestBid);

        beneficiary.transfer(highestBid); // 将最高出价转给受益人

        // 如果存在第二高出价者，则将第二高出价转给其
        if (pendingReturns[highestBidder] > 0) {
            emit AuctionEnded(payable(address(this)), pendingReturns[highestBidder]);
            payable(highestBidder).transfer(pendingReturns[highestBidder]);
        }
    }

    function goodsId() public view returns(uint) {
        return uniqueItemId;
    }

    function getAuctionEndTime() public view returns(uint){
        return auctionEndTime;
    }


}

