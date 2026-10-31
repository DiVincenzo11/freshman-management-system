import request from '@/utils/request'

// 查询子菜单管理列表
export function listChild_menu(query) {
  return request({
    url: '/system/child_menu/list',
    method: 'get',
    params: query
  })
}

// 查询子菜单管理详细
export function getChild_menu(childMenuId) {
  return request({
    url: '/system/child_menu/' + childMenuId,
    method: 'get'
  })
}

// 新增子菜单管理
export function addChild_menu(data) {
  return request({
    url: '/system/child_menu',
    method: 'post',
    data: data
  })
}

// 修改子菜单管理
export function updateChild_menu(data) {
  return request({
    url: '/system/child_menu',
    method: 'put',
    data: data
  })
}

// 删除子菜单管理
export function delChild_menu(childMenuId) {
  return request({
    url: '/system/child_menu/' + childMenuId,
    method: 'delete'
  })
}
