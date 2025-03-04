import request from '@/utils/request'

// 查询客户基础信息列表
export function listCustomerInfo(query) {
  return request({
    url: '/customer/info/list',
    method: 'get',
    params: query
  })
}

// 查询客户基础信息详细
export function getCustomerInfo(id) {
  return request({
    url: '/customer/info/' + id,
    method: 'get'
  })
}

// 新增客户基础信息
export function addCustomerInfo(data) {
  return request({
    url: '/customer/info',
    method: 'post',
    data: data
  })
}

// 修改客户基础信息
export function updateCustomerInfo(data) {
  return request({
    url: '/customer/info',
    method: 'put',
    data: data
  })
}

// 删除客户基础信息
export function delCustomerInfo(id) {
  return request({
    url: '/customer/info/' + id,
    method: 'delete'
  })
}


// 根据客户名称模糊查询客户基础信息列表
export function getCustomerListByName(customerName) {
  return request({
    url: '/customer/info/getCustomerListByName/' + customerName,
    method: 'get'
  })
}

// 客户到店洗护
export function hairWash(data) {
  return request({
    url: '/customer/info/hairWash',
    method: 'post',
    data: data
  })
}