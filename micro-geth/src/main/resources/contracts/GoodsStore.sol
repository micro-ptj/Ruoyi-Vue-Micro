// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

import "contracts/Escrow.sol";

contract GoodsStore {

    address payable public beneficiary; // 受益人地址
    uint biddingTime; // 拍卖持续时间
    mapping (uint => address) goodsEscrow;

    uint public goodsId; // 商品唯一标识

    function createGoods(uint _goodsId, uint _biddingTime, address payable _beneficiary) public {
        biddingTime = block.timestamp + _biddingTime;
        goodsId = _goodsId;
        beneficiary = _beneficiary;
        Escrow escrow = new Escrow(biddingTime, beneficiary, _goodsId);
        goodsEscrow[goodsId] = address(escrow);
    }

    function escrowBid(uint _goodsId) public payable{
        Escrow(goodsEscrow[_goodsId]).bid{value: msg.value}();
    }

    function escrowAddress(uint _goodsId)public view returns(address){
        return address(goodsEscrow[_goodsId]);
    }

    function escrowGoodsId(uint _goodsId)public view returns(uint256){
        return Escrow(goodsEscrow[_goodsId]).goodsId();
    }

    function getContractBalance(uint _goodsId) public view returns (uint256) {
        return address(goodsEscrow[_goodsId]).balance;
    }

    function escrowWithdraw(uint _goodsId) public returns (bool) {
        return Escrow(goodsEscrow[_goodsId]).withdraw();
    }

    function escrowAuctionEnd(uint _goodsId) public {
        return Escrow(goodsEscrow[_goodsId]).auctionEnd();
    }

    function escrowAuctionEndTime(uint _goodsId) public view returns(uint256) {
        return Escrow(goodsEscrow[_goodsId]).getAuctionEndTime();
    }

}