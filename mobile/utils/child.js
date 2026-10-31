import {
	listChild_menu,
	getChild_menu,
	delChild_menu,
	addChild_menu,
	updateChild_menu
} from "@/api/system/child_menu";
import store from '@/store'
var mixSelectchildRoleList = []
var mixEditchildRoleList = []

function mixingetSelectChildRoleList() {
	const query = {
		pageSize: 100
	}
	listChild_menu(query).then(res=>{
		res.rows.forEach(item => {
			if (item.childMenuType == 1) {
				mixSelectchildRoleList.push(item.childMenuName)
			} else {
				mixEditchildRoleList.push(item.childMenuName)
			}
		
		})
	})
}

mixingetSelectChildRoleList()



export function isShow(value) {
	
	if (mixSelectchildRoleList.includes(value)) {
		
		return store.state.user.select.includes(value)
	} else {
		return true
	}
}
export function isUpdate(value,id) {
	if(id==null){
		return false
	}
	if (mixEditchildRoleList.includes(value)) {
		return !store.state.user.edit.includes(value)
	} else {
		return false
	}
}