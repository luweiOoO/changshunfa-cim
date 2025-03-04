import request from '@/utils/request'

// 查询产品基础信息列表
export function listInfo(query) {
  return request({
    url: '/product/info/list',
    method: 'get',
    params: query
  })
}

// 查询产品基础信息详细
export function getInfo(id) {
  return request({
    url: '/product/info/' + id,
    method: 'get'
  })
}

// 新增产品基础信息
export function addInfo(data) {
  return request({
    url: '/product/info',
    method: 'post',
    data: data
  })
}

// 修改产品基础信息
export function updateInfo(data) {
  return request({
    url: '/product/info',
    method: 'put',
    data: data
  })
}

// 删除产品基础信息
export function delInfo(id) {
  return request({
    url: '/product/info/' + id,
    method: 'delete'
  })
}
