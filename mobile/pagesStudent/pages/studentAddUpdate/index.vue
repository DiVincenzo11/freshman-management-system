<template>
	<view>

		<view class="example">

			<uni-forms ref="stuform" :rules="rules" v-model="studentForm" label-width="50">
				<uni-section title="基础信息" type="line" />
				<uni-forms-item  v-if="isShow('studentName')"  label="学生姓名" required name="studentName">
					<uni-easyinput :disabled="isUpdate('studentName',studentId)"  v-model="studentForm.studentName" placeholder="请输入姓名" />
				</uni-forms-item>
				<uni-forms-item  v-if="isShow('studentNum')"  label="学号" required name="studentNum">
					<uni-easyinput :disabled="isUpdate('studentNum',studentId)"  v-model="studentForm.studentNum" placeholder="请输入姓名" />
				</uni-forms-item>
				<uni-forms-item  v-if="isShow('phone')"  label="手机号码" required name="phone">
					<uni-easyinput :disabled="isUpdate('phone',studentId)"    v-model="studentForm.phone" placeholder="请输入手机号" />
				</uni-forms-item>
				<uni-forms-item  v-if="isShow('sex')"  label="性别" required name="sex">
					<uni-data-select :disabled="isUpdate('sex',studentId)"   v-model="studentForm.sex" :localdata="dict.sys_user_sex"></uni-data-select>
				</uni-forms-item>
				<uni-forms-item  v-if="isShow('grades')"  label="专业班级" required name="grades">
					<uni-data-select :disabled="isUpdate('grades',studentId)"   v-model="studentForm.grades" :localdata="dict.student_grades"></uni-data-select>
				</uni-forms-item>
				<uni-forms-item  v-if="isShow('duties')"  label="班级职务" required name="duties">
					<uni-data-select :disabled="isUpdate('duties',studentId)"   v-model="studentForm.duties" :localdata="dict.student_duties"></uni-data-select>
				</uni-forms-item>
				<uni-forms-item  v-if="isShow('face')"  label="政治面貌" required name="face">
					<uni-data-select :disabled="isUpdate('face',studentId)"   v-model="studentForm.face" :localdata="dict.student_face"></uni-data-select>
				</uni-forms-item>
				<uni-forms-item  v-if="isShow('idCard')"  label="身份证号" required name="idCard">
					<uni-easyinput :disabled="isUpdate('idCard',studentId)"   v-model="studentForm.idCard" placeholder="请输入身份证号" />
				</uni-forms-item>
				<uni-forms-item  v-if="isShow('nativePlace')"  label="籍贯" required name="nativePlace">
					<uni-easyinput :disabled="isUpdate('nativePlace',studentId)"   v-model="studentForm.nativePlace" placeholder="请输入籍贯" />
				</uni-forms-item>
				<uni-forms-item  v-if="isShow('nation')"  label="民族" required name="nation">
					<uni-easyinput :disabled="isUpdate('nation',studentId)"   v-model="studentForm.nation" placeholder="请输入民族" />
				</uni-forms-item>
				<uni-forms-item  v-if="isShow('religion')"  label="宗教信仰" required name="religion">
					<uni-easyinput :disabled="isUpdate('religion',studentId)"   v-model="studentForm.religion" placeholder="请输入宗教信仰" />
				</uni-forms-item>
				<uni-forms-item  v-if="isShow('birthday')"  label="出生日期" name="birthday">
					<uni-datetime-picker :disabled="isUpdate('birthday',studentId)"   type="datetime" return-type="timestamp" v-model="studentForm.birthday"
						placeholder="请选择出生日期" />
				</uni-forms-item>
				<uni-forms-item  v-if="isShow('dormitory')"  label="宿舍号">
					<uni-easyinput :disabled="isUpdate('dormitory',studentId)"   v-model="studentForm.dormitory" placeholder="请输入宿舍号" />
				</uni-forms-item>
				<uni-forms-item  v-if="isShow('oicq')"  label="oicq">
					<uni-easyinput :disabled="isUpdate('oicq',studentId)"   v-model="studentForm.oicq" placeholder="请输入QQ" />
				</uni-forms-item>
				<uni-forms-item  v-if="isShow('weChat')" label="微信号">
					<uni-easyinput :disabled="isUpdate('weChat',studentId)"   v-model="studentForm.weChat" placeholder="请输入微信号" />
				</uni-forms-item>
				<uni-forms-item  v-if="isShow('address')" label="家庭住址" name="address">
					<uni-easyinput :disabled="isUpdate('address',studentId)"   type="textarea" v-model="studentForm.address" placeholder="请输入家庭住址" />
				</uni-forms-item>
				<uni-section  title="紧急联系人信息1" type="line" />
				<uni-forms-item  v-if="isShow('emergencyName1')" label="姓名">
					<uni-easyinput :disabled="isUpdate('emergencyName1',studentId)"   v-model="studentForm.emergencyName1" placeholder="请输入姓名" />
				</uni-forms-item>
				<uni-forms-item  v-if="isShow('emergencyRelation1')" label="与生关系">
					<uni-easyinput :disabled="isUpdate('emergencyRelation1',studentId)"   v-model="studentForm.emergencyRelation1" placeholder="请输入与生关系" />
				</uni-forms-item>
				<uni-forms-item  v-if="isShow('emergencyWork1')" label="工作/就读单位">
					<uni-easyinput :disabled="isUpdate('emergencyWork1',studentId)"   v-model="studentForm.emergencyWork1" placeholder="请输入微工作/就读单位" />
				</uni-forms-item>
				<uni-forms-item  v-if="isShow('emergencyPhone1')" label="手机号">
					<uni-easyinput :disabled="isUpdate('emergencyPhone1',studentId)"   v-model="studentForm.emergencyPhone1" placeholder="请输入手机号" />
				</uni-forms-item>
				<uni-section title="紧急联系人信息2" type="line" />
				<uni-forms-item  v-if="isShow('emergencyName2')" label="姓名">
					<uni-easyinput :disabled="isUpdate('emergencyName2',studentId)"   v-model="studentForm.emergencyName2" placeholder="请输入姓名" />
				</uni-forms-item>
				<uni-forms-item  v-if="isShow('emergencyRelation2')" label="与生关系">
					<uni-easyinput :disabled="isUpdate('emergencyRelation2',studentId)"   v-model="studentForm.emergencyRelation2" placeholder="请输入与生关系" />
				</uni-forms-item>
				<uni-forms-item  v-if="isShow('emergencyWork2')"   label="工作/就读单位">
					<uni-easyinput :disabled="isUpdate('emergencyWork2',studentId)"   v-model="studentForm.emergencyWork2" placeholder="请输入微工作/就读单位" />
				</uni-forms-item>
				<uni-forms-item  v-if="isShow('emergencyPhone2')" label="手机号">
					<uni-easyinput :disabled="isUpdate('emergencyPhone2',studentId)"   v-model="studentForm.emergencyPhone2" placeholder="请输入手机号" />
				</uni-forms-item>
				<uni-section  v-if="isShow('photo')" title="一寸照片" type="line" />
				<uv-upload  v-if="isShow('photo')" :disabled="isUpdate('photo',studentId)"   :fileList="photoList" name="photo" :maxCount="5" @afterRead="afterRead" @delete="deletePic"
					width="100" height="100">
				</uv-upload>
				<uni-section  v-if="isShow('lifePhoto')"  title="生活照" type="line" />
				<uv-upload  v-if="isShow('lifePhoto')" :disabled="isUpdate('lifePhoto',studentId)"   :fileList="lifeList" name="life" :maxCount="5" @afterRead="afterRead" @delete="deletePic"
					width="100" height="100">
				</uv-upload>
				<button type="primary" @click="submit()">提交</button>
			</uni-forms>
		</view>
	</view>
</template>

<script>
	import {
		getDicts,
	} from '@/api/system/dict/data.js'
	import {
		baseUrl
	} from '../../../config'
	import {
		getStudent,
		updateStudent,
		addStudent
	} from '@/api/system/student.js'
	import {
		getToken,
		setToken,
		removeToken
	} from '@/utils/auth'
	import {
		isUpdate,isShow
	} from "@/utils/child.js"
	export default {
		data() {
			return {
				rules: {
					studentName: {
						rules: [{
							required: true,
							errorMessage: '姓名不能为空'
						}]
					},
					studentNum: {
						rules: [{
							required: true,
							errorMessage: '学号不能为空'
						}]
					},
					phone: {
						rules: [{
							required: true,
							errorMessage: '手机号不能为空'
						}]
					},
					grades: {
						rules: [{
							required: true,
							errorMessage: '专业班级不能为空'
						}]
					},
					duties: {
						rules: [{
							required: true,
							errorMessage: '班级职务不能为空'
						}]
					},
					idCard: {
						rules: [{
							required: true,
							errorMessage: '身份证号不能为空'
						}]
					},
					sex: {
						rules: [{
							required: true,
							errorMessage: '性别不能为空'
						}]
					},
					face: {
						rules: [{
							required: true,
							errorMessage: '政治面貌不能为空'
						}]
					},
					nativePlace: {
						rules: [{
							required: true,
							errorMessage: '籍贯不能为空'
						}]
					},
					nation: {
						rules: [{
							required: true,
							errorMessage: '民族不能为空'
						}]
					},
					address: {
						rules: [{
							required: true,
							errorMessage: '地址不能为空'
						}]
					},
				},
				studentForm: {
					studentName: '',
					studentNum: '',
					phone: '',
					grades: '',
					duties: '',
					sex: '',
					idCard: '',
					nativePlace: '',
					nation: '',
					face: '',
					address: '',
				},
				configParams: {
					labelKey: "dictLabel",
					valueKey: 'dictValue'
				},
				//需要查询的字典集合
				selectDict: ['sys_user_sex', 'student_grades', 'student_duties', 'student_face'],
				dict: {
					sys_user_sex: [],
					student_grades: [],
					student_duties: [],
					student_face: []
				},
				uploadList: baseUrl + '/common/upload',
				photoList: [],
				lifeList: [],
				fileList: [],
				baseUrl: baseUrl,
				studentId: null,
			}
		},
		onReady() {
			// 设置自定义表单校验规则，必须在节点渲染完毕后执行
			this.$refs.stuform.setRules(this.rules)
		},
		onLoad(e) {
			console.log(e)
			if (e.studentId) {
				uni.setNavigationBarTitle({
					title: '编辑学生'
				})
				this.studentId = e.studentId
				this.getStudent()
			} else {
				uni.setNavigationBarTitle({
					title: '新增学生'
				})
			}
			this.getDictList()
		},
		methods: {
			isUpdate,
			isShow,
			getStudent() {
				getStudent(this.studentId).then(res => {
					console.log(res)
					this.studentForm = res.data
					if (this.studentForm.photo != '' && this.studentForm.phone != null) {
						const photo = res.data.photo.split(",")
						photo.forEach(item => {
							this.photoList.push({
								url: baseUrl + item
							})
						})
					}
					if (this.studentForm.lifePhoto != '' && this.studentForm.lifePhoto != null) {
						const life = res.data.lifePhoto.split(",")
						life.forEach(item => {
							this.lifeList.push({
								url: baseUrl + item
							})
						})
					}

				})
			},
			clearPhoto() {
				uni.showLoading({

				})
				return new Promise(res => {
					let resultObject = {
						urls: this.photoList.map(item => item.url)
					};
					this.studentForm.photo = resultObject.urls.join()
					this.studentForm.photo = this.studentForm.photo.replace(baseUrl, "")
					let resultObject1 = {
						urls: this.lifeList.map(item => item.url)
					};
					this.studentForm.lifePhoto = resultObject1.urls.join()
					this.studentForm.lifePhoto = this.studentForm.lifePhoto.replace(baseUrl, "")
					setTimeout(() => {
						res()
					}, 500)
				})
			},
			async submit() {
				await this.clearPhoto()
				uni.hideLoading()
				this.$refs.stuform.validate().then(res => {
					if (this.studentId) {
						updateStudent(this.studentForm).then(res => {
							this.$modal.showToast(res.msg)
							setTimeout(() => {
								uni.redirectTo({
									url: '/pagesStudent/pages/student/index'
								})
							}, 500)

						})
					} else {
						addStudent(this.studentForm).then(res => {
							this.$modal.showToast(res.msg)
							setTimeout(() => {
								uni.redirectTo({
									url: '/pagesStudent/pages/student/index'
								})
							}, 500)
						})
					}
				}).catch(err => {
					console.log(err)
					this.$modal.msgError("请将信息补充完整")
				})
			},
			reset() {

			},
			deletePic(event) {
				console.log(event)
				this.photoList.splice(event.index, 1)
			},
			deletePic1(event) {
				console.log(event)
				this.lifeList.splice(event.index, 1)
			},
			// 新增图片
			async afterRead(event) {
				console.log(event)
				if (event.name == 'photo') {
					const photo = await this.uploadFilePromise(event.file.url)
					this.photoList.push({
						url: baseUrl + photo
					})
				} else {
					const photo = await this.uploadFilePromise(event.file.url)
					this.lifeList.push({
						url: baseUrl + photo
					})
				}

			},
			uploadFilePromise(url) {
				console.log(getToken())
				return new Promise((resolve, reject) => {
					let a = uni.uploadFile({
						url: baseUrl + '/common/upload', // 仅为示例，非真实的接口地址
						filePath: url,
						name: 'file',
						formData: {
							user: 'test'
						},
						header: {
							Authorization: "Bearer " + getToken(),
						},
						success: (res) => {
							console.log(res)
							let jsonObject = JSON.parse(res.data)
							setTimeout(() => {
								resolve(jsonObject.fileName)
							}, 1000)
						}
					});
				})
			},
			async getDictList() {
				for (let i = 0; i < this.selectDict.length; i++) {
					const dictKey = this.selectDict[i];
					console.log(dictKey); // 输出当前正在处理的字典键  
					try {
						const res = await getDicts(dictKey);
						if (res && res.data) {
							const data = res.data;
							data.forEach(item => {
								if (item.dictValue !== undefined && item.dictLabel !== undefined) {
									this.dict[dictKey].push({
										value: parseInt(item.dictValue, 10),
										text: item.dictLabel
									});
								} else {}
							});
						} else {
							console.error(dictKey);
						}
					} catch (error) {
						console.error(error);
					}
				}
			}
		}
	}
</script>

<style lang="scss">
	.addImg {
		width: 100px;
		height: 100px;
		background: #eee;
		color: #808080;
		text-align: center;
		padding-top: 35px
	}

	.example {
		background-color: #fff;

		.uni-forms-item {
			padding-left: 15px;
		}

		.uv-upload {
			padding-left: 15px;
		}
	}

	.segmented-control {
		margin-bottom: 15px;
	}

	.button-group {
		margin-top: 15px;
		display: flex;
		justify-content: space-around;
	}

	.form-item {
		display: flex;
		align-items: center;
	}

	.button {
		display: flex;
		align-items: center;
		height: 35px;
		margin-left: 10px;
	}
</style>