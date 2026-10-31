<template>
	<view>
		<uni-section class="mb-10" title="基础信息" type="line"></uni-section>
		<uni-list>
			<uni-list-item v-if="isShow('studentName')" showExtraIcon="true" title="学生姓名" :rightText="student.studentName" />
			<uni-list-item v-if="isShow('phone')" showExtraIcon="true" title="手机号码" :rightText="student.phone" />
			<uni-list-item v-if="isShow('sex')" showExtraIcon="true" title="学生性别" :rightText="getValue('sys_user_sex',student.sex)" />
			<uni-list-item v-if="isShow('idCard')" showExtraIcon="true" title="身份证号" :rightText="student.idCard" />
			<uni-list-item v-if="isShow('studentNum')" showExtraIcon="true" title="学生学号" :rightText="student.studentNum" />
			<uni-list-item v-if="isShow('dormitory')" showExtraIcon="true" title="学生宿舍" :rightText="student.dormitory" />
			<uni-list-item v-if="isShow('grades')" showExtraIcon="true" title="专业班级" :rightText="getValue('student_grades',student.grades)" />
			<uni-list-item v-if="isShow('duties')" showExtraIcon="true" title="班级职务" :rightText="getValue('student_duties',student.duties)" />
			<uni-list-item v-if="isShow('face')" showExtraIcon="true" title="政治面貌" :rightText="getValue('student_face',student.face)" />
			<uni-list-item v-if="isShow('birthday')" showExtraIcon="true" title="出生日期" :rightText="student.birthday" />
			<uni-list-item v-if="isShow('nativePlace')" showExtraIcon="true" title="学生籍贯" :rightText="student.nativePlace" />
			<uni-list-item v-if="isShow('nation')" showExtraIcon="true" title="学生民族" :rightText="student.nation" />
			<uni-list-item v-if="isShow('religion')" showExtraIcon="true" title="宗教信仰" :rightText="student.religion" />
			<uni-list-item v-if="isShow('address')" showExtraIcon="true" title="家庭住址" :rightText="student.address" />
			<uni-list-item v-if="isShow('weChat')" showExtraIcon="true" title="微信号" :rightText="student.weChat" />
			<uni-list-item v-if="isShow('oicq')" showExtraIcon="true" title="QQ" :rightText="student.oicq" />
		</uni-list>
		<uni-section class="mb-10 mt10" title="紧急联系人信息1" type="line"></uni-section>
		<uni-list>
			<uni-list-item v-if="isShow('emergencyName1')" showExtraIcon="true" title="姓名" :rightText="student.emergencyName1" />
			<uni-list-item v-if="isShow('emergencyRelation1')" showExtraIcon="true" title="与生关系" :rightText="student.emergencyRelation1" />
			<uni-list-item v-if="isShow('emergencyWork1')" showExtraIcon="true" title="工作或就读单位" :rightText="student.emergencyWork1" />
			<uni-list-item v-if="isShow('emergencyPhone1')" showExtraIcon="true" title="手机号码" :rightText="student.emergencyPhone1" />
		</uni-list>
		<uni-section class="mb-10 mt10" title="紧急联系人信息2" type="line"></uni-section>
		<uni-list>
			<uni-list-item v-if="isShow('emergencyName2')" showExtraIcon="true" title="姓名" :rightText="student.emergencyName2" />
			<uni-list-item v-if="isShow('emergencyRelation2')" showExtraIcon="true" title="与生关系" :rightText="student.emergencyRelation2" />
			<uni-list-item v-if="isShow('emergencyWork2')" showExtraIcon="true" title="工作或就读单位" :rightText="student.emergencyWork2" />
			<uni-list-item v-if="isShow('emergencyPhone2')" showExtraIcon="true" title="手机号码" :rightText="student.emergencyPhone2" />
		</uni-list>
		<uni-section v-if="isShow('photo')" class="mb-10 mt10" title="一寸照" type="line"></uni-section>
		<view v-if="isShow('photo')" class="bg-white p10 flex">
			<uv-image  width="80px" height="80px"v-for="item in photoList" :src="baseUrl+item" ></uv-image>
		</view>
		<uni-section v-if="isShow('lifePhoto')" class="mb-10 mt10" title="生活照" type="line"></uni-section>
		<view v-if="isShow('lifePhoto')" class="bg-white p10 flex">
			<uv-image  width="80px" height="80px"v-for="item in lifeList" :src="baseUrl+item" ></uv-image>
		</view>
	</view>
</template>

<script>
	import {
		getStudent
	} from '@/api/system/student.js'
	import {
		listData
	} from '@/api/system/dict/data.js'
	import {
		baseUrl
	} from '../../../config'
	import {isShow} from "@/utils/child.js"
	export default {
		data() {
			return {
				studentId: null,
				student: {},
				dictList: [],
				baseUrl: baseUrl,
				photoList: [],
				lifeList:[]
			}
		},
		onLoad(e) {
			this.studentId = e.studentId
			this.getList()
			this.getDict()
		},
		methods: {
			isShow,
			getList() {
				getStudent(this.studentId).then(res => {
					this.student = res.data
					this.photoList = res.data.photo.split(",")
					this.lifeList = res.data.lifePhoto.split(",")
					console.log(this.photoList)
				})
			},
			getDict() {
				listData({
					pageSize: 1000
				}).then(res => {

					this.dictList = res.rows
					console.log(this.baseUrl)
				})
			},
			//过滤,先简单用，后期业务量大可以写个hooks
			getValue(type, value) {
				for (var i = 0; i < this.dictList.length; i++) {
					if (type == this.dictList[i].dictType && parseInt(this.dictList[i].dictValue) == value) {
						return this.dictList[i].dictLabel
					}
				}
			}

		}
	}
</script>

<style>
	.content {
		background: #fff;
	}
	::v-deep .detatilImg{
		width: 65px;
		height: 65px;
	}
</style>