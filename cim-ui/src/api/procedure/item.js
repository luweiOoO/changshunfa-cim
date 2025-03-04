import request from '@/utils/request'

// 查询流程信息列表
export function listItem(query) {
  return request({
    url: '/procedure/item/list',
    method: 'get',
    params: query
  })
}

// 查询流程信息详细
export function getItem(id) {
  return request({
    url: '/procedure/item/' + id,
    method: 'get'
  })
}

// 新增流程信息
export function addItem(data) {
  return request({
    url: '/procedure/item',
    method: 'post',
    data: data
  })
}

// 修改流程信息
export function updateItem(data) {
  return request({
    url: '/procedure/item',
    method: 'put',
    data: data
  })
}

// 删除流程信息
export function delItem(id) {
  return request({
    url: '/procedure/item/' + id,
    method: 'delete'
  })
}
