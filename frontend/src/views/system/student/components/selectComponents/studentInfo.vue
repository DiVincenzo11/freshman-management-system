<template>
	<div>
		<el-descriptions title="基础信息" :column="4">
			<el-descriptions-item v-if="isShow('studentName')"
				label="学生姓名">{{studentForm.studentName}}</el-descriptions-item>
			<el-descriptions-item v-if="isShow('studentNum')"
				label="学号">{{studentForm.studentNum}}</el-descriptions-item>
			<el-descriptions-item v-if="isShow('phone')" label="手机号">{{studentForm.phone}}</el-descriptions-item>
			<el-descriptions-item v-if="isShow('dormitory')"
				label="宿舍号">{{studentForm.dormitory}}</el-descriptions-item>
			<el-descriptions-item v-if="isShow('grades')" label="专业班级">
				<dict-tag :options="dict.type.student_grades" :value="studentForm.grades" />
			</el-descriptions-item>
			<el-descriptions-item v-if="isShow('duties')" label="班级职务">
				<dict-tag :options="dict.type.student_duties" :value="studentForm.duties" />
			</el-descriptions-item>
			<el-descriptions-item v-if="isShow('sex')" label="性别">
				<dict-tag :options="dict.type.sys_user_sex" :value="studentForm.sex" />
			</el-descriptions-item>
			<el-descriptions-item v-if="isShow('face')" label="政治面貌">
				<dict-tag :options="dict.type.student_face" :value="studentForm.face" />
			</el-descriptions-item>
			<el-descriptions-item v-if="isShow('birthday')" label="出生日期">{{studentForm.birthday}}</el-descriptions-item>
			<el-descriptions-item v-if="isShow('idCard')" label="身份证号">{{studentForm.idCard}}</el-descriptions-item>
			<el-descriptions-item v-if="isShow('nativePlace')"
				label="籍贯">{{studentForm.nativePlace}}</el-descriptions-item>
			<el-descriptions-item v-if="isShow('nation')" label="民族">{{studentForm.nation}}</el-descriptions-item>
			<el-descriptions-item v-if="isShow('religion')" label="宗教信仰">{{studentForm.religion}}</el-descriptions-item>
			<el-descriptions-item v-if="isShow('oicq')" label="QQ">{{studentForm.oicq}}</el-descriptions-item>\
			<el-descriptions-item v-if="isShow('weChat')" label="微信号">{{studentForm.weChat}}</el-descriptions-item>
			<el-descriptions-item v-if="isShow('address')" label="家庭住址"
			>{{studentForm.address}}</el-descriptions-item>
		</el-descriptions>
		<el-descriptions title="紧急联系人信息1" :column="4">
			<el-descriptions-item v-if="isShow('emergencyName1')"
				label="姓名">{{studentForm.emergencyName1}}</el-descriptions-item>
			<el-descriptions-item v-if="isShow('emergencyRelation1')"
				label="与生关系">{{studentForm.emergencyRelation1}}</el-descriptions-item>
			<el-descriptions-item v-if="isShow('emergencyWork1')"
				label="工作或就读单位">{{studentForm.emergencyWork1}}</el-descriptions-item>
			<el-descriptions-item v-if="isShow('emergencyPhone1')"
				label="手机号">{{studentForm.emergencyPhone1}}</el-descriptions-item>
		</el-descriptions>
		<el-descriptions title="紧急联系人信息2" :column="4">
			<el-descriptions-item v-if="isShow('emergencyName2')"
				label="姓名">{{studentForm.emergencyName2}}</el-descriptions-item>
			<el-descriptions-item v-if="isShow('emergencyRelation2')"
				label="与生关系">{{studentForm.emergencyRelation2}}</el-descriptions-item>
			<el-descriptions-item v-if="isShow('emergencyWork2')"
				label="工作或就读单位">{{studentForm.emergencyWork2}}</el-descriptions-item>
			<el-descriptions-item v-if="isShow('emergencyPhone2')"
				label="手机号">{{studentForm.emergencyPhone2}}</el-descriptions-item>
		</el-descriptions>
		<el-descriptions v-if="isShow('photo')||isShow('lifePhoto')" title="附件" :column="4">
			<el-descriptions-item v-if="isShow('photo')" :span="2" label="1寸照">
				<image-preview :src="studentForm.photo" :width="50" :height="50" />
			</el-descriptions-item>
			<el-descriptions-item v-if="isShow('lifePhoto')" :span="2" label="生活照">
				<image-preview :src="studentForm.lifePhoto" :width="50" :height="50" />
			</el-descriptions-item>
		</el-descriptions>
		<el-descriptions v-hasPermi="['system:mark:query']"  title="标记" :column="4">
		</el-descriptions>
		<div v-hasPermi="['system:mark:query']" style="margin-left: 30px;" v-if="studentForm.markList.length!=0">
			<el-tag class="ml10" v-for="tag in studentForm.markList" :key="tag.markId" closable type=""
				@close="close(tag.markId)">
				{{getDict(tag.markContent)}}
			</el-tag>
		</div>
	</div>
</template>

<script>
	import {
		listStudent,
		getStudent,
		delStudent,
		addStudent,
		updateStudent
	} from "@/api/system/student";
	import {
		listMark,
		getMark,
		delMark,
		addMark,
		updateMark
	} from "@/api/system/mark";
	import formMixins from '_mixins'
	import store from '@/store'
	export default {
		mixins: [formMixins],
		name: 'studentInfo',
		dicts: ['student_grades', 'sys_user_sex', 'student_duties', 'student_face', 'student_mark'],
		data() {
			return {
				studentForm: {},
				studentId: null,
				loading: null
			}
		},
		methods: {
			isShow(value) {
				// console.log(value)
				if (this.mixSelectchildRoleList.includes(value)) {
					return  store.state.user.select.includes(value)
				} else {
					return true
				}
			},
			getDict(value) {
				if(value!=null||value!=''){
					for (var i = 0; i < this.dict.type.student_mark.length; i++) {
						if (parseInt(this.dict.type.student_mark[i].value) === value) {
							return this.dict.type.student_mark[i].label
						}
					}
				}
	
			},
			async getData(studentId) {
				
				this.studentId = studentId
				const loading = this.$loading({
					lock: true,
					text: 'Loading',
					spinner: 'el-icon-loading',
					background: 'rgba(0, 0, 0, 0.7)'
				});
				const res = await getStudent(studentId)
				loading.close()
				this.studentForm = res.data
			},
			close(markId) {
				this.$modal.confirm('是否确认删除标记？').then(function() {
					return delMark(markId);
				}).then(() => {
					this.getData(this.studentId)
					this.$modal.msgSuccess("删除成功");
				}).catch(() => {});
			}
		}

	}
</script>

<style>
</style>