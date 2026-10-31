import request from '@/utils/request'

// 查询跟进记录列表
export function listFollow_up(query) {
  return request({
    url: '/system/follow_up/list',
    method: 'get',
    params: query
  })
}

// 查询跟进记录详细
export function getFollow_up(followId) {
  return request({
    url: '/system/follow_up/' + followId,
    method: 'get'
  })
}

// 新增跟进记录
export function addFollow_up(data) {
  return request({
    url: '/system/follow_up',
    method: 'post',
    data: data
  })
}

// 修改跟进记录
export function updateFollow_up(data) {
  return request({
    url: '/system/follow_up',
    method: 'put',
    data: data
  })
}

// 删除跟进记录
export function delFollow_up(followId) {
  return request({
    url: '/system/follow_up/' + followId,
    method: 'delete'
  })
}
