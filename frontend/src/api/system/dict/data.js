import request from '@/utils/request'

// 查询字典数据列表
export function listData(query) {
  return request({
    url: '/system/dict/data/list',
    method: 'get',
    params: query
  })
}

// 查询字典数据详细
export function getData(dictCode) {
  return request({
    url: '/system/dict/data/' + dictCode,
    method: 'get'
  })
}

// 根据字典类型查询字典数据信息
export function getDicts(dictType) {
  return request({
    url: '/system/dict/data/type/' + dictType,
    method: 'get'
  })
}

export function getDictsMark(query) {
  return request({
    url: '/system/dict/data/studentMark',
    method: 'get',
	params: query
  })
}
export function getDictsGrades(query) {
  return request({
    url: '/system/dict/data/studentGrades',
    method: 'get',
	params: query
  })
}


// 新增字典数据
export function addData(data) {
  return request({
    url: '/system/dict/data',
    method: 'post',
    data: data
  })
}
export function addDataMark(data) {
  return request({
    url: '/system/dict/data/studentMark',
    method: 'post',
    data: data
  })
}
export function addDataGrades(data) {
  return request({
    url: '/system/dict/data/studentGrades',
    method: 'post',
    data: data
  })
}

// 修改字典数据
export function updateData(data) {
  return request({
    url: '/system/dict/data',
    method: 'put',
    data: data
  })
}

export function updateDataMark(data) {
  return request({
    url: '/system/dict/data/studentMark',
    method: 'put',
    data: data
  })
}
export function updateDataGrades(data) {
  return request({
    url: '/system/dict/data/studentGrades',
    method: 'put',
    data: data
  })
}

// 删除字典数据
export function delData(dictCode) {
  return request({
    url: '/system/dict/data/' + dictCode,
    method: 'delete'
  })
}
export function delDataMark(dictCode) {
  return request({
    url: '/system/dict/data/studentMark/' + dictCode,
    method: 'delete'
  })
}
export function delDataGrades(dictCode) {
  return request({
    url: '/system/dict/data/studentGrades/' + dictCode,
    method: 'delete'
  })
}