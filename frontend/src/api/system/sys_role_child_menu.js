import request from '@/utils/request'

// 查询中间表列表
export function listSys_role_child_menu(query) {
  return request({
    url: '/system/sys_role_child_menu/list',
    method: 'get',
    params: query
  })
}

// 查询中间表详细
export function getSys_role_child_menu(roleId) {
  return request({
    url: '/system/sys_role_child_menu/' + roleId,
    method: 'get'
  })
}

// 新增中间表
export function addSys_role_child_menu(data) {
  return request({
    url: '/system/sys_role_child_menu',
    method: 'post',
    data: data
  })
}

// 修改中间表
export function updateSys_role_child_menu(data) {
  return request({
    url: '/system/sys_role_child_menu',
    method: 'put',
    data: data
  })
}

// 删除中间表
export function delSys_role_child_menu(roleId) {
  return request({
    url: '/system/sys_role_child_menu/' + roleId,
    method: 'delete'
  })
}
