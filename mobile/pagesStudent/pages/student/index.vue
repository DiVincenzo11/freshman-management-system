<template>
	<view class="container">
		<view class="student_input">
			<view style="width: 90%;"><uni-search-bar placeholder="请输入学生姓名" @confirm="search" @cancel="cancel"
					@clear="cancel" v-model="queryParams.studentName"></uni-search-bar></view>
			<view @click="open" class="student_font">筛选</view>
		</view>
		<view class="mt10" v-for="(item,index) in studentList">
			<uni-card title="基础卡片" type="line" :is-shadow="false">
				<template v-slot:title>
					<view class="card_header">
						<image style="width: 25px;height: 25px;" @click="change(item.studentId,index)"
							v-if="checkedList[index]" src="../../../static/images/radio2.png"></image>
						<image style="width: 25px;height: 25px;" @click="clearChecked(item.studentId,index)"
							v-if="checkedList[index]==false" src="../../../static/images/radio1.png"></image>
						<view v-if="isShow('studentName')" class="card-text">学生姓名：{{item.studentName}}</view>
					</view>
				</template>

				<view v-if="isShow('studentNum')" class="card-content">学生学号：{{item.studentNum}}</view>
				<view v-if="isShow('grades')" class="card-content">学生班级：{{getDictDataName(gradsList,item.grades)}}</view>
				<view v-if="checkPermi(['system:mark:query'])" class="card-content">
					<span>学生标记：</span>
					<uni-tag @click="delMark(child.markId,item,index,childindex)" v-if="item.markList!=0" class="ml3" size="small"
						v-for="(child,childindex) in item.markList" :text="getDictDataName(markList,child.markContent)"
						type="primary" />
					<uni-tag v-if="item.markList==0" class="ml3" size="small" text="无标记" type="success" />

				</view>
				<view v-if="checkPermi(['system:follow_up:query'])" class="card-content"><span>是否跟进：</span><uni-tag class="ml3" size="small" :inverted="true"
						:text="item.follow==1?'是':'否'" :type="item.follow==1?'primary':'error'" /></view>
				<view slot="actions" class="card-actions no-border">
					<view v-for="(item1,index1) in cardFooterList" class="card-actions-item"
						@click="actionsClick(item1.name,item.studentId,index)">
						<uni-icons :type="item1.type" size="18" color="#999"></uni-icons>
						<text class="card-actions-item-text">{{item1.name}}</text>
					</view>
				</view>
			</uni-card>
		</view>
		<view style="padding-bottom: 60px;"></view>
		<view class="footer">
			<view class="pt10" style="padding-left: 10px;">
				<radio class="radio_style" :checked="allchecked" @click="changeAll" />
				<span>全选</span>
			</view>
			<view style="margin-left: 50px;" v-if="checkPermi(['system:student:add'])"  @click="goAdd">
				<view><uni-icons type="plus" size="25"></uni-icons></view>
				<view v-if="checkPermi(['system:student:add'])"  class="font-11" style="text-align: center;">新增</view>
			</view>
			<view  v-if="checkPermi(['system:student:remove'])"  @click="handleDelete()" style="margin-left: 50px;">
				<view><uni-icons type="trash" size="25"></uni-icons></view>
				<view v-if="checkPermi(['system:student:remove'])" class="font-11" style="text-align: center;">删除</view>
			</view>
		</view>
		<!-- 筛选 -->
		<uv-popup ref="popup" mode="top">
			<view class="p10">
				<uni-forms ref="stuform" v-model="queryParams" label-width="50">
					<uni-forms-item label="学生学号" required name="studentNum">
						<uni-easyinput v-model="queryParams.studentNum" placeholder="请输入学号" />
					</uni-forms-item>
					<uni-forms-item label="专业班级" required name="grades">
						<uni-data-select v-model="queryParams.grades" :localdata="gradesRadioList"></uni-data-select>
					</uni-forms-item>
					<uni-forms-item label="学生标记" required name="ismark">
						<uni-data-select v-model="queryParams.ismark" :localdata="markRadioList"></uni-data-select>
					</uni-forms-item>
					<uni-forms-item label="是否跟进" required name="follw">
						<uni-data-checkbox class="pt5" v-model="queryParams.follow"
							:localdata="followData"></uni-data-checkbox>
					</uni-forms-item>
					<view class="flex">
						<button size="mini" type="" @click="close">取消</button>
						<button size="mini" type="" @click="cancel">重置</button>
						<button size="mini" type="primary" @click="search()">筛选</button>
					</view>
				</uni-forms>
			</view>
		</uv-popup>
		<!-- 更多操作 -->
		<uv-popup round="10" ref="popup1" mode="bottom">
			<view style="text-align: center;">
				<view  v-if="checkPermi(['system:follow_up:query'])"  style="padding: 15px;" @click="goFollow">跟进记录</view>
				<view style="border: 1px solid #eee;width: 100%;"></view>
				<view v-if="checkPermi(['system:mark:add'])" style="padding: 15px;"  @click="goMark">新增标记</view>
			</view>
		</uv-popup>

		<uv-modal style="width: 100%;" :showCancelButton="true" @close="markClose" ref="modal" title="新增标记" @confirm="confirm">
			<view class="slot-content">
				<uni-forms ref="stuform" v-model="markParams" label-width="50">
					<uni-forms-item label="学生标记" required name="ismark">
						<uni-data-select v-model="markParams.markContent" :localdata="markRadioList"></uni-data-select>
					</uni-forms-item>
				</uni-forms>
			</view>
		</uv-modal>
	</view>
</template>

<script>
	import {
		listStudent,
		delStudent
	} from '@/api/system/student.js'
	import {delMark} from '@/api/system/mark.js'
	import {addMark} from "@/api/system/mark.js"
	// 获取系统信息
	const systemInfo = uni.getSystemInfoSync();

	// 计算状态栏高度
	const statusBarHeight = systemInfo.statusBarHeight;
	import {
		listData
	} from '@/api/system/dict/data.js'
	import {
		formatWithOptions
	} from 'util';
	import { checkPermi, checkRole } from "@/utils/permission";
	import {isShow} from "@/utils/child.js"
import store from '../../../store';
import { pushScopeId } from "vue";
	export default {
		data() {
			return {
				queryParams: {
					studentName: '',
					studentNum: '',
					ismark: '',
					follow: null,
					pageSize: 10,
					pageNum: 1,
					params:{}
				},
				followData: [{
						text: '是',
						value: 1
					},
					{
						text: '否',
						value: 2
					}
				],
				markParams:{
					markContent:null,
				},
				studentList: [],
				checkedList: [],
				allchecked: false,
				cardFooterList: [{
						name: '详情',
						type: 'list'
					},
					{
						name: '编辑',
						type: 'gear'
					},
					{
						name: '删除',
						type: 'trash'
					},
					{
						name: '更多',
						type: 'more-filled'
					}
				],
				gradsList: [],
				markList: [],
				total: 0,
				statusBarHeight: 0, //状态栏高度，单位：px
				titleBarHeight: 0, //标题栏高度，单位：px
				headerHeight: 0, //页面头部高度，单位：px
				ids: [],
				markRadioList: [],
				gradesRadioList: [],
				studentId: null,
				markIndex:null,
			}
		},
		onLoad() {
			this.getDict()
			this.getList()
			

		},
		onReachBottom(e) {
			if (this.total > this.studentList.length) {
				this.queryParams.pageNum++
				this.getList()
			} else {
				// this.$modal.showToast("暂无更多信息")
			}
		},

		methods: {
			checkPermi,
			checkRole,
			isShow,
			confirm(){
				this.markParams.studentId=this.studentId
				addMark(this.markParams).then(res=>{
					console.log(res)
					this.$modal.showToast(res.msg)
					this.$refs.modal.close()
					this.studentList[this.markIndex].markList.push(res.data)
				})
			},
			markClose(){
				this.$refs.modal.close()
			},
			goMark() {
				this.$refs.popup1.close()
				this.$refs.modal.open()
				
			},
			goFollow() {
				this.$refs.popup1.close()
				uni.navigateTo({
					url: '/pagesStudent/pages/follow/index?studentId=' + this.studentId
				})
			},
			close() {
				this.$refs.popup.close()
			},
			open() {
				this.$refs.popup.open();
			},
			radioChange(e) {
				console.log(e)
			},
			delMark(id,item,index,cindex){
				var that=this
				uni.showModal({
					title: '提示',
					content: '是否确认删除',
					success: function(res) {
						if (res.confirm) {
							delMark(id).then(res => {
								that.$modal.showToast(res.msg)
								that.studentList[index].markList.splice(cindex,1)
							})
						}
						if (res.cancel) {
							that.$modal.showToast(res.msg)
						}
					}
				})
			},
			handleDelete() {
				var that = this
				uni.showModal({
					title: '提示',
					content: '是否确认删除',
					success: function(res) {
						if (res.confirm) {
							delStudent(that.ids).then(res => {
								that.$modal.showToast(res.msg)
								that.queryParams.pageNum = 1
								that.getClearList()
							})
						}
						if (res.cancel) {
							that.$modal.showToast(res.msg)
						}
					}
				})
			},

			clearChecked(studentId, index) {
				this.checkedList[index] = true
				this.ids.push(studentId)
				this.$forceUpdate();
			},
			change(studentId, num) {
				console.log("change", num)
				this.checkedList[num] = false
				let index = this.ids.indexOf(studentId)
				if (index !== -1) {
					this.ids.splice(index, 1);
				} else {
					this.ids.push(studentId)
				}
				this.$forceUpdate();

			},
			changeAll() {
				this.ids = []
				if (this.allchecked == false) {
					this.allchecked = true
					for (var i = 0; i < this.checkedList.length; i++) {
						this.checkedList[i] = true
						this.ids.push(this.studentList[i].studentId)
					}
				} else {
					this.allchecked = false
					for (var i = 0; i < this.checkedList.length; i++) {
						this.checkedList[i] = false
						this.ids = []
					}
				}
			},
			goAdd() {
				uni.navigateTo({
					url: '/pagesStudent/pages/studentAddUpdate/index'
				})
			},
			actionsClick(e, id,index) {
				var that = this
				if (e == '详情') {
					uni.navigateTo({
						url: '/pagesStudent/pages/studentDetail/index?studentId=' + id
					})
				}
				if (e == '编辑') {
					uni.navigateTo({
						url: '/pagesStudent/pages/studentAddUpdate/index?studentId=' + id
					})
				}
				if (e == '删除') {
					uni.showModal({
						title: '提示',
						content: '是否确认删除',
						success: function(res) {
							if (res.confirm) {
								delStudent(id).then(res => {
									that.$modal.showToast(res.msg)
									that.studentList.splice(index,1)
								})
							}
							if (res.cancel) {
								that.$modal.showToast(res.msg)
							}
						}
					})
				}
				if (e == '更多') {
					this.$refs.popup1.open()
					this.studentId = id
					this.markIndex=index
				}
			},
			getClearList() {
				var that = this
				this.queryParams.params.roleId=store.state.user.roleId
				listStudent(that.queryParams).then(res => {
					that.checkedList = []
					that.allchecked = false
					that.total = res.total
					that.studentList = res.rows
					for (var i = 0; i < that.studentList.length; i++) {
						that.checkedList.push(false)
					}
				})
			},
			search() {
				this.getClearList()
				this.close()
			},
			cancel() {
				this.queryParams = {
						studentName: '',
						pageSize: 10,
						pageNum: 1,
						studentNum: '',
						ismark: '',
						follow: null,
					},
					this.getClearList()
				this.close()
			},
			getDictDataName(options, value) {
				for (var i = 0; i < options.length; i++) {
					if (parseInt(options[i].dictValue) === value) {		
						return options[i].dictLabel
					}
				}
			},
			getList() {
				this.queryParams.params.roleId=store.state.user.roleId
				listStudent(this.queryParams).then(res => {
					this.checkedList = []
					this.allchecked = false
					this.total = res.total
					this.studentList = [...this.studentList, ...res.rows]
					for (var i = 0; i < this.studentList.length; i++) {
						this.checkedList.push(false)
					}
				})
			},
			getDict() {
				listData({
					dictType: 'student_grades',
					pageSize:100,
				}).then(res => {
					this.gradsList = res.rows
					this.gradsList.forEach(item => {
						this.gradesRadioList.push({
							text: item.dictLabel,
							value: parseInt(item.dictValue)
						})
					})
				})
				listData({
					dictType: 'student_mark'
				}).then(res => {
					this.markList = res.rows
					this.markList.forEach(item => {
						this.markRadioList.push({
							text: item.dictLabel,
							value: parseInt(item.dictValue)
						})
					})
				})
			},
		}
	}
</script>

<style lang="scss">
	::v-deep .uni-forms-item{
		width: 280px;
	}
	.radio_style {
		transform: scale(0.7);
		color: #007aff;
	}

	.footer {
		position: fixed;
		height: 50px;
		background: #fff;
		width: 100%;
		bottom: 0;
		display: flex;
		padding-top: 5px;
	}

	::v-deep .uni-select__selector {
		max-height: 100px;
		overflow: auto;
	}

	::v-deep .uni-card {
		margin: 0px !important;
	}

	::v-deep .uni-card--boder {
		margin: none;
	}

	.uni-card .uni-card--border {
		margin: 0px !important;
	}

	.card-text {
		color: #000;
		font-size: 12px;
		padding-top: 4px;
		padding-left: 5px;
	}

	.card-content {
		font-size: 12px;
		color: #000;
	}

	.card-actions {
		display: flex;
		flex-direction: row;
		justify-content: space-around;
		align-items: center;
		height: 45px;
		border-top: 1px #eee solid;
	}

	.card-actions-item {
		display: flex;
		flex-direction: row;
		align-items: center;
	}

	.student_input {
		background: #fff;
		display: flex;
		justify-content: space-between;
	}

	.student_font {
		margin-top: 17px;
		margin-right: 8px;
	}

	.card_header {
		display: flex;
		padding-top: 10px;
		padding-bottom: 5px;
		border-bottom: 1px solid #eee;
	}

	// .no-border {
	// 	border-width: 0;
	// }
</style>