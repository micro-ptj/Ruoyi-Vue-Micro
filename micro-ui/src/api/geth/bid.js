import request from '@/utils/request'

// 查询交易信息列表
export function listBid(query) {
  return request({
    url: '/geth/bid/list',
    method: 'get',
    params: query
  })
}

// 查询交易信息详细
export function getBid(userId) {
  return request({
    url: '/geth/bid/' + userId,
    method: 'get'
  })
}

// 新增交易信息
export function addBid(data) {
  return request({
    url: '/geth/bid',
    method: 'post',
    data: data
  })
}

// 修改交易信息
export function updateBid(data) {
  return request({
    url: '/geth/bid',
    method: 'put',
    data: data
  })
}

// 删除交易信息
export function delBid(userId) {
  return request({
    url: '/geth/bid/' + userId,
    method: 'delete'
  })
}
