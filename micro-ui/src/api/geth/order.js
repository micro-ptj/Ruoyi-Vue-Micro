import request from '@/utils/request'

// 查询订单信息列表
export function listOrder(query) {
  return request({
    url: '/geth/order/list',
    method: 'get',
    params: query
  })
}

// 查询订单信息详细
export function getOrder(id) {
  return request({
    url: '/geth/order/' + id,
    method: 'get'
  })
}

// 新增订单信息
export function addOrder(data) {
  return request({
    url: '/geth/order',
    method: 'post',
    data: data
  })
}

// 修改订单信息
export function updateOrder(data) {
  return request({
    url: '/geth/order',
    method: 'put',
    data: data
  })
}

// 删除订单信息
export function delOrder(id) {
  return request({
    url: '/geth/order/' + id,
    method: 'delete'
  })
}
