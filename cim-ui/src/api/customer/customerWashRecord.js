import request from '@/utils/request'

// 查询客户产品洗护记录列表
export function listCustomerWashRecord(query) {
  return request({
    url: '/customer/washRecord/list',
    method: 'get',
    params: query
  })
}

// 查询客户产品洗护记录详细
export function getCustomerWashRecord(id) {
  return request({
    url: '/customer/washRecord/' + id,
    method: 'get'
  })
}

// 新增客户产品洗护记录
export function addCustomerWashRecord(data) {
  return request({
    url: '/customer/washRecord',
    method: 'post',
    data: data
  })
}

// 修改客户产品洗护记录
export function updateCustomerWashRecord(data) {
  return request({
    url: '/customer/washRecord',
    method: 'put',
    data: data
  })
}

// 删除客户产品洗护记录
export function delCustomerWashRecord(id) {
  return request({
    url: '/customer/washRecord/' + id,
    method: 'delete'
  })
}
