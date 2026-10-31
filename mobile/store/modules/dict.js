import {listType} from '@/api/system/dict/type.js'
import {listData} from '@/api/system/dict/data.js'
import storage from '@/utils/storage'

const dict={
	state:{
		type:storage.get('dict')
	},
	
	mutations: {
	  SET_DICT: (state, dict) => {
	    state.dcit = dcit
	  },
	},
	actions: {
	  // 获取全部字典
	  getDictList({commit,state}){
		return new Promise((resolve, reject) => {
			listData().then(res=>{
				reject(res)
			}).catch(error=>{
				reject(error)
			})
		})
	  }
	  Login({ commit }, userInfo) {
	    const username = userInfo.username.trim()
	    const password = userInfo.password
	    const code = userInfo.code
	    const uuid = userInfo.uuid
	    return new Promise((resolve, reject) => {
	      login(username, password, code, uuid).then(res => {
	        setToken(res.token)
	        commit('SET_TOKEN', res.token)
	        resolve()
	      }).catch(error => {
	        reject(error)
	      })
	    })
	  },
	
	}
}