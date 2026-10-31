<template>
	<!-- 添加或修改学生管理对话框 -->
	<el-dialog :modal="false" title="编辑学生" :visible="show" width="1000px" @close="cancel">
		<el-descriptions class="mt10" title="基础信息" :column="2">
		</el-descriptions>
		<el-form ref="form" :model="form" :rules="rules" label-width="80px">
			<el-row>
				<el-col  v-if="isShow('studentName')"  :span="8">
					<el-form-item label="姓名" prop="studentName">
						<el-input :disabled="isUpdate('studentName')" size="medium" v-model="form.studentName" placeholder="请输入姓名" />
					</el-form-item>
				</el-col>
				<el-col v-if="isShow('studentNum')" :span="8">
					<el-form-item label="学号" prop="studentNum">
						<el-input :disabled="isUpdate('studentNum')" size="medium" v-model="form.studentNum" placeholder="请输入学号" />
					</el-form-item>
				</el-col>
				<el-col v-if="isShow('grades')" :span="8">
					<el-form-item label="专业班级" prop="grades">
						<el-select :disabled="isUpdate('grads')" size="medium" v-model="form.grades" placeholder="请选择专业班级">
							<el-option v-for="dict in dict.type.student_grades" :key="dict.value" :label="dict.label"
								:value="parseInt(dict.value)"></el-option>
						</el-select>
					</el-form-item>
				</el-col>
				<el-col v-if="isShow('duties')" :span="8">
					<el-form-item label="班级职务" prop="duties">
						<el-select :disabled="isUpdate('duties')" size="medium" v-model="form.duties" placeholder="请选择班级职务">
							<el-option v-for="dict in dict.type.student_duties" :key="dict.value" :label="dict.label"
								:value="parseInt(dict.value)"></el-option>
						</el-select>
					</el-form-item>
				</el-col>
				<el-col v-if="isShow('sex')" :span="8">
					<el-form-item label="性别" prop="sex">
						<el-select :disabled="isUpdate('sex')" size="medium" v-model="form.sex" placeholder="请选择性别">
							<el-option v-for="dict in dict.type.sys_user_sex" :key="dict.value" :label="dict.label"
								:value="parseInt(dict.value)"></el-option>
						</el-select>
					</el-form-item>
				</el-col>
				<el-col v-if="isShow('birthday')" :span="8">
					<el-form-item label="出生日期" prop="birthday">
						<el-date-picker :disabled="isUpdate('birthday')" size="medium" clearable v-model="form.birthday" type="date"
							value-format="yyyy-MM-dd" placeholder="请选择出生日期">
						</el-date-picker>
					</el-form-item>
				</el-col>
				<el-col v-if="isShow('face')" :span="8">
					<el-form-item label="政治面貌" prop="face">
						<el-select :disabled="isUpdate('face')" size="medium" v-model="form.face" placeholder="请选择性别">
							<el-option v-for="dict in dict.type.student_face" :key="dict.value" :label="dict.label"
								:value="parseInt(dict.value)"></el-option>
						</el-select>
					</el-form-item>
				</el-col>
				<el-col v-if="isShow('phone')" :span="8">
					<el-form-item label="手机号" prop="phone">
						<el-input :disabled="isUpdate('phone')" size="medium" v-model="form.phone" placeholder="请输入手机号" />
					</el-form-item>
				</el-col>
				<el-col  v-if="isShow('dormitory')"  :span="8">
					<el-form-item label="宿舍号" prop="dormitory">
						<el-input :disabled="isUpdate('dormitory')" size="medium" v-model="form.dormitory" placeholder="请输入宿舍号" />
					</el-form-item>
				</el-col>
				<el-col v-if="isShow('idCard')" :span="8">
					<el-form-item label="身份证号" prop="idCard">
						<el-input :disabled="isUpdate('idCard')" size="medium" v-model="form.idCard" placeholder="请输入身份证号" />
					</el-form-item>
				</el-col>
				<el-col v-if="isShow('nativePlace')" :span="8">
					<el-form-item label="籍贯" prop="nativePlace">
						<el-input :disabled="isUpdate('nativePlace')" size="medium" v-model="form.nativePlace" placeholder="请输入籍贯" />
					</el-form-item>
				</el-col>
				<el-col v-if="isShow('nation')" :span="8">
					<el-form-item label="民族" prop="nation">
						<el-input :disabled="isUpdate('nation')" size="medium" v-model="form.nation" placeholder="请输入民族" />
					</el-form-item>
				</el-col>
				<el-col v-if="isShow('religion')" :span="8">
					<el-form-item label="宗教信仰" prop="religion">
						<el-input :disabled="isUpdate('religion')" size="medium" v-model="form.religion" placeholder="请输入宗教信仰" />
					</el-form-item>
				</el-col>
				<el-col v-if="isShow('oicq')" :span="8">
					<el-form-item label="QQ" prop="oicq">
						<el-input :disabled="isUpdate('oicq')" size="medium" v-model="form.oicq" placeholder="请输入QQ" />
					</el-form-item>
				</el-col>
				<el-col  v-if="isShow('weChat')" :span="8">
					<el-form-item label="微信号" prop="weChat">
						<el-input :disabled="isUpdate('weChat')" size="medium" v-model="form.weChat" placeholder="请输入微信号" />
					</el-form-item>
				</el-col>
				<el-col v-if="isShow('address')" :span="24">
					<el-form-item label="家庭住址" prop="address">
						<el-input :disabled="isUpdate('address')" size="medium" v-model="form.address" type="textarea" placeholder="请输入内容" />
					</el-form-item>
				</el-col>
			</el-row>
			<el-descriptions class="mt10" title="紧急联系人信息1" :column="2">
			</el-descriptions>
			<el-row>
				<el-col v-if="isShow('emergencyName1')" :span="12">
					<el-form-item label="姓名" prop="emergencyName1">
						<el-input :disabled="isUpdate('emergencyName1')" size="medium" v-model="form.emergencyName1" placeholder="请输入姓名" />
					</el-form-item>
				</el-col>
				<el-col v-if="isShow('emergencyRelation1')" :span="12">
					<el-form-item label="与生关系" prop="emergencyRelation1">
						<el-input :disabled="isUpdate('emergencyRelation1')" size="medium" v-model="form.emergencyRelation1" placeholder="请输入与生关系" />
					</el-form-item>
				</el-col>
				<el-col v-if="isShow('emergencyWork1')" :span="12">
					<el-form-item label="工作或就读单位" prop="emergencyWork1" label-width="120px">
						<el-input :disabled="isUpdate('emergencyWork1')" size="medium" v-model="form.emergencyWork1" placeholder="请输入工作或就读单位" />
					</el-form-item>
				</el-col>
				<el-col v-if="isShow('emergencyPhone1')" :span="12">
					<el-form-item label="手机号" prop="emergencyPhone1">
						<el-input :disabled="isUpdate('emergencyPhone1')" size="medium" v-model="form.emergencyPhone1" placeholder="请输入手机号" />
					</el-form-item>
				</el-col>
			</el-row>
			<el-descriptions class="mt10" title="紧急联系人信息2" :column="2">
			</el-descriptions>
			<el-row>
				<el-col v-if="isShow('emergencyName2')" :span="12">
					<el-form-item label="姓名" prop="emergencyName2">
						<el-input :disabled="isUpdate('emergencyName2')" size="medium" v-model="form.emergencyName2" placeholder="请输入姓名" />
					</el-form-item>
				</el-col>
				<el-col v-if="isShow('emergencyRelation2')" :span="12">
					<el-form-item label="与生关系" prop="emergencyRelation2">
						<el-input :disabled="isUpdate('emergencyRelation2')" size="medium" v-model="form.emergencyRelation2" placeholder="请输入与生关系" />
					</el-form-item>
				</el-col>
				<el-col v-if="isShow('emergencyWork2')" :span="12">
					<el-form-item label="工作或就读单位" prop="emergencyWork2" label-width="120px">
						<el-input :disabled="isUpdate('emergencyWork2')" size="medium" v-model="form.emergencyWork2" placeholder="请输入工作或就读单位" />
					</el-form-item>
				</el-col>
				<el-col v-if="isShow('emergencyPhone2')" :span="12">
					<el-form-item label="手机号" prop="emergencyPhone2">
						<el-input :disabled="isUpdate('emergencyPhone2')" size="medium" v-model="form.emergencyPhone2" placeholder="请输入手机号" />
					</el-form-item>
				</el-col>
			</el-row>
			<el-descriptions class="mt10" title="附件" :column="2">
			</el-descriptions>
			<el-row>
				<el-col v-if="isShow('photo')" :span="12">
					<el-form-item label="1寸照片" prop="photo">
						<image-upload :disabled="isUpdate('photo')" v-model="form.photo" />
					</el-form-item>
				</el-col>
				<el-col v-if="isShow('lifePhoto')" :span="12">
					<el-form-item label="生活照" prop="lifePhoto">
						<image-upload :disabled="isUpdate('lifePhoto')" v-model="form.lifePhoto" />
					</el-form-item>
				</el-col>
			</el-row>
		</el-form>

		<div slot="footer" class="dialog-footer">
			<el-button type="primary" @click="submitForm">确 定</el-button>
			<el-button @click="cancel">取 消</el-button>
		</div>
	</el-dialog>
</template>

<script>
	import { listStudent, getStudent, delStudent, addStudent, updateStudent } from "@/api/system/student";
	import formMixins from '_mixins'
	import store from '@/store'
	export default {
		mixins:[formMixins],
		dicts: ['student_grades', 'sys_user_sex', 'student_duties', 'student_face'],
		name: 'updateStudent',
		props: {
			show: {
				type: Boolean
			},
		},
		mounted() {
		},
		data() {
			return {
				// 表单参数
				form: {},
				// 表单校验
				rules: {
					studentName: [{
						required: true,
						message: "姓名不能为空",
						trigger: "blur"
					}],
					studentNum: [{
						required: true,
						message: "学号不能为空",
						trigger: "blur"
					}],
					grades: [{
						required: true,
						message: "专业班级不能为空",
						trigger: "change"
					}],
					duties: [{
						required: true,
						message: "班级职务不能为空",
						trigger: "change"
					}],
					sex: [{
						required: true,
						message: "性别不能为空",
						trigger: "change"
					}],
					idCard: [{
						required: true,
						message: "身份证号不能为空",
						trigger: "blur"
					}],
					nativePlace: [{
						required: true,
						message: "籍贯不能为空",
						trigger: "blur"
					}],
					nation: [{
						required: true,
						message: "民族不能为空",
						trigger: "blur"
					}],
					face: [{
						required: true,
						message: "政治面貌不能为空",
						trigger: "blur"
					}],
					phone: [{
						required: true,
						message: "手机号不能为空",
						trigger: "blur"
					}],
					address: [{
						required: true,
						message: "家庭住址不能为空",
						trigger: "blur"
					}],
				}
			}
		},
		methods: {
			//根据字段判断是否能编辑
			isUpdate(value){
				if(this.mixEditchildRoleList.includes(value)){
					return  !store.state.user.edit.includes(value)
				}else{
					return false
				}
			},
			isShow(value){
				if(this.mixSelectchildRoleList.includes(value)){
					return  store.state.user.select.includes(value)
				}else{
					return true
				}
			},
			getData(studentId){
				getStudent(studentId).then(response => {
				  this.form = response.data;
				  this.title = "修改学生管理";
				});
			},
			// 表单重置
			reset() {
				this.form = {
					studentId: null,
					studentName: null,
					studentNum: null,
					grades: null,
					duties: null,
					sex: null,
					birthday: null,
					idCard: null,
					nativePlace: null,
					nation: null,
					dormitory: null,
					face: null,
					phone: null,
					oicq: null,
					weChat: null,
					address: null,
					emergencyName1: null,
					emergencyRelation1: null,
					emergencyWork1: null,
					emergencyPhone1: null,
					emergencyName2: null,
					emergencyRelation2: null,
					emergencyWork2: null,
					emergencyPhone2: null,
					photo: null,
					lifePhoto: null,
					createTime: null,
					updateTime: null
				};
				this.resetForm("form");
			},
			cancel() {
				this.reset()
				this.$emit("cancel")
			},
			submitForm() {
			  this.$refs["form"].validate(valid => {
			    if (valid) {
			        updateStudent(this.form).then(response => {
			          this.$modal.msgSuccess("修改成功");
			          this.open = false;
			         this.cancel()
			        });
			    }
			  });
			},
		}
	}
</script>

<style>
</style>