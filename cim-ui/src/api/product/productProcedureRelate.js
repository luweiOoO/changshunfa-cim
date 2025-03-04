import request from '@/utils/request'

// 查询产品流程关联列表
export function listRelate(query) {
  return request({
    url: '/product/procedureRelate/list',
    method: 'get',
    params: query
  })
}

// 查询产品流程关联详细
export function getRelate(id) {
  return request({
    url: '/product/procedureRelate/' + id,
    method: 'get'
  })
}

// 新增产品流程关联
export function addRelate(data) {
  return request({
    url: '/product/procedureRelate',
    method: 'post',
    data: data
  })
}

// 修改产品流程关联
export function updateRelate(data) {
  return request({
    url: '/product/procedureRelate',
    method: 'put',
    data: data
  })
}

// 删除产品流程关联
export function delRelate(id) {
  return request({
    url: '/product/procedureRelate/' + id,
    method: 'delete'
  })
}
