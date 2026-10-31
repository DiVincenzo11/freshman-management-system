import request from '@/utils/request'

// 查询标记管理列表
export function listMark(query) {
  return request({
    url: '/system/mark/list',
    method: 'get',
    params: query
  })
}

// 查询标记管理详细
export function getMark(markId) {
  return request({
    url: '/system/mark/' + markId,
    method: 'get'
  })
}

// 新增标记管理
export function addMark(data) {
  return request({
    url: '/system/mark',
    method: 'post',
    data: data
  })
}

// 修改标记管理
export function updateMark(data) {
  return request({
    url: '/system/mark',
    method: 'put',
    data: data
  })
}

// 删除标记管理
export function delMark(markId) {
  return request({
    url: '/system/mark/' + markId,
    method: 'delete'
  })
}
