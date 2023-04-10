import request from '@/utils/request'

// 查询区块链交易数据列表
export function listTransaction(query) {
  return request({
    url: '/geth/transaction/list',
    method: 'get',
    params: query
  })
}

// 查询区块链交易数据详细
export function getTransaction(id) {
  return request({
    url: '/geth/transaction/' + id,
    method: 'get'
  })
}

// 新增区块链交易数据
export function addTransaction(data) {
  return request({
    url: '/geth/transaction',
    method: 'post',
    data: data
  })
}

// 修改区块链交易数据
export function updateTransaction(data) {
  return request({
    url: '/geth/transaction',
    method: 'put',
    data: data
  })
}

// 删除区块链交易数据
export function delTransaction(id) {
  return request({
    url: '/geth/transaction/' + id,
    method: 'delete'
  })
}
