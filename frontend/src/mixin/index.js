/**
 * 表头   区域+项目
 */
import {
	listChild_menu,
	getChild_menu,
	delChild_menu,
	addChild_menu,
	updateChild_menu
} from "@/api/system/child_menu";
export default {
	data() {
		return {
			mixSelectchildRoleList: [], //区域列表
			mixEditchildRoleList:[]
		};
	},
	created() {
		this.mixingetChildRoleList();
	},
	// mounted() { },
	methods: {
		async mixingetChildRoleList() {
			this.mixchildRoleList=[]
			const query={
				pageSize:100
			}
			const res = await listChild_menu(query)
			res.rows.forEach(item=>{
				if(item.childMenuType==1){
					this.mixSelectchildRoleList.push(item.childMenuName)
				}else{
					this.mixEditchildRoleList.push(item.childMenuName)
				}
				
			})
		}
	},
};