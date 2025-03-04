import request from '@/utils/request'

// 查询客户购买记录列表
export function listCustomerPurchaseRecord(query) {
  return request({
    url: '/customer/purchase/list',
    method: 'get',
    params: query
  })
}

// 查询客户购买记录详细
export function getCustomerPurchaseRecord(id) {
  return request({
    url: '/customer/purchase/' + id,
    method: 'get'
  })
}

// 新增客户购买记录
export function addCustomerPurchaseRecord(data) {
  return request({
    url: '/customer/purchase',
    method: 'post',
    data: data
  })
}

// 修改客户购买记录
export function updateCustomerPurchaseRecord(data) {
  return request({
    url: '/customer/purchase',
    method: 'put',
    data: data
  })
}

// 删除客户购买记录
export function delCustomerPurchaseRecord(id) {
  return request({
    url: '/customer/purchase/' + id,
    method: 'delete'
  })
}
