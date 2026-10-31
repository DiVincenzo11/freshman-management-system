<template>
	<!-- 学生详情页 -->
	<el-dialog title="学生详情" :visible="show" width="95%" @close="cancel">
		<el-card class="box-card">
			<div slot="header" class="clearfix">
				<span class="el-icon-user-solid">学号：{{list.studentNum}} |</span>
				<span class="el-icon-user-solid"> 姓名：{{list.studentName}}</span>
			</div>
			<div>
				<div style="position: relative;">
					<el-tabs v-model="activeName" @tab-click="handleClick">
						<el-tab-pane label="个人信息" name="1">
							<student-info ref="userInfos"></student-info>
						</el-tab-pane>
						<el-tab-pane v-if="checkPermi(['system:follow_up:query'])" label="跟进记录" name="2">
							<follow-list ref="followlist"></follow-list>
						</el-tab-pane>
					</el-tabs>
				</div>
			</div>
		</el-card>
		<el-card class="box-card mt20">
			<div slot="header" class="clearfix">
				<span class="el-icon-s-tools">其他操作</span>
			</div>
			<div>
				<el-row :gutter="10" class="mb8">
					<el-col :span="1.5">
						<el-button type="primary" icon="el-icon-edit" size="mini"
							@click="handleUpdate" v-hasPermi="['system:student:edit']">编辑</el-button>
					</el-col>
					<el-col :span="1.5">
						<el-button type="primary" icon="el-icon-edit" size="mini" 
							@click="handleFollowAdd" v-hasPermi="['system:follow_up:add']">添加跟进记录</el-button>
					</el-col>
					<el-col :span="1.5">
						<el-button type="primary" icon="el-icon-edit" size="mini" 
							@click="handleMarkAdd" v-hasPermi="['system:student_mark:edit']">添加新标记</el-button>
					</el-col>
				</el-row>
			</div>
		</el-card>
		<add-follow ref="follow" :show="followShow" @cancel="followCancel"></add-follow>
		<add-mark ref="mark" :show="markShow" @cancel="markCancel"></add-mark>
		<update-student ref="updatestudent" :show="updateShow" @cancel="updateCancel"></update-student>
	</el-dialog>
</template>

<script>
	import studentInfo from './selectComponents/studentInfo.vue'
	import addFollow from './selectComponents/addFollow.vue'
	import addMark from './selectComponents/addMark.vue'
	import followList from './selectComponents/followList.vue'
	import updateStudent from './selectComponents/updateStudent.vue'
	import {
		checkPermi,
		checkRole
	} from "@/utils/permission";
	export default {
		name: 'selectDetail',
		components: {
			studentInfo,
			addFollow,
			addMark,
			followList,
			updateStudent
		},
		props: {
			show: {
				type: Boolean
			}
		},
		data() {
			return {
				activeName: '1',
				list: {},
				followShow: false,
				markShow: false,
				updateShow: false,
			}
		},
		created() {
		},
		methods: {
			checkPermi,
			checkRole,
			async getData(row) {
				this.activeName = '1'
				this.list = row
				setTimeout(() => {
					this.$refs.userInfos.getData(row.studentId)
				})

			},
			cancel() {
				this.$emit("cancel")
			},
			handleClick(e) {
				if (e.label == '个人信息') {
					this.$refs.userInfos.getData(this.list.studentId)
				}
				if (e.label == '跟进记录') {
					this.$refs.followlist.getData(this.list.studentId)
				}
			},
			handleFollowAdd() {
				this.followShow = true
				this.$refs.follow.getData(this.list.studentId)
			},
			followCancel() {
				this.followShow = false
				this.$refs.followlist.getData(this.list.studentId)
			},
			updateCancel() {
				this.updateShow = false
				this.$refs.userInfos.getData(this.list.studentId)
			},
			markCancel() {
				this.markShow = false
				this.$refs.userInfos.getData(this.list.studentId)
			},
			handleMarkAdd() {
				this.markShow = true
				this.$refs.mark.getData(this.list.studentId)
			},
			handleUpdate() {
				this.updateShow = true
				this.$refs.updatestudent.getData(this.list.studentId)
			}
		}
	}
</script>

<style lang="scss">
	.clearfix {
		padding: 10px;
	}

	#my-unique-parent .el-dialog .el-dialog__body {
		padding-top: 10px !important;
	}

	.propreBtn {
		cursor: pointer;
		position: absolute;
		right: 0;
		top: 10px;
		color: #409EFF;
	}
</style>