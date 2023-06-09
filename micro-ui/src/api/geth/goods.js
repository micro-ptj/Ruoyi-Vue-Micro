import request from '@/utils/request'

// 查询商品信息列表
export function listGoods(query) {
  return request({
    url: '/geth/goods/list',
    method: 'get',
    params: query
  })
}

// 查询商品信息详细
export function getGoods(id) {
  return request({
    url: '/geth/goods/' + id,
    method: 'get'
  })
}

// 新增商品信息
export function addGoods(data) {
  return request({
    url: '/geth/goods',
    method: 'post',
    data: data
  })
}

// 修改商品信息
export function updateGoods(data) {
  return request({
    url: '/geth/goods',
    method: 'put',
    data: data
  })
}

//上架
export function groundingGoods(data) {
  return request({
    url: '/geth/goods/grounding',
    method: 'post',
    data: data
  })
}

// 删除商品信息
export function delGoods(id) {
  return request({
    url: '/geth/goods/' + id,
    method: 'delete'
  })
}

// 下架
export function removeGoods(id) {
  return request({
    url: '/geth/goods/remove/' + id,
    method: 'get'
  })
}
