<template>
	<!-- 新增跟进记录 -->
	<el-dialog :modal="false" title="新增跟进记录" :visible="show" width="500px" @close="cancel">
		<el-form ref="form" :model="form" :rules="rules" label-width="80px">
			<el-form-item label="内容" prop="followContent">
				<el-input type="textarea"   :autosize="{ minRows: 9, maxRows: 15}"  v-model="form.followContent" placeholder="请输入内容" />
			</el-form-item>
			<el-form-item label="附件" prop="followImage">
				<el-upload
				  class="upload-demo"
				  drag
				  :action="uploadImgUrl"
				   :headers="headers"
				    :on-success="handleUploadSuccess"
					:on-remove="handleDelete"
				  multiple>
				  <i class="el-icon-upload"></i>
				  <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
				  <div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过500kb</div>
				</el-upload>
			</el-form-item>
		</el-form>
		<div slot="footer" class="dialog-footer">
			<el-button type="primary" @click="submitForm">确 定</el-button>
			<el-button @click="cancel">取 消</el-button>
		</div>
	</el-dialog>
</template>

<script>
	import {
		listFollow_up,
		getFollow_up,
		delFollow_up,
		addFollow_up,
		updateFollow_up
	} from "@/api/system/follow_up";
	import { getToken } from "@/utils/auth";
	export default {
		name: 'addFollow',
		props: {
			show: {
				type: Boolean,
			}
		},
		data() {
			return {
				form: {},
				// 表单校验
				rules: {
					followContent: [{
						required: true,
						message: "内容不能为空",
						trigger: "blur"
					}],
				},
				uploadImgUrl: process.env.VUE_APP_BASE_API + "/common/upload",
				headers: {
				  Authorization: "Bearer " + getToken(),
				},
				fileList:[],
				delList:[],
			}
		},
		methods: {
			handleDelete(file,fileList) {
				const findex = this.delList.map(f => f.name).indexOf(file.name);
				if (findex > -1) {
				  this.fileList.splice(findex, 1);
				  this.delList.splice(findex, 1);
				}
				console.log(this.fileList)
			},
			handleUploadSuccess(e){
				console.log(e)
				this.fileList.push(e.fileName)
				this.delList.push({name:e.originalFilename})
			},
			getData(studentId) {
				this.form.studentId = studentId
			},
			submitForm() {
				this.form.followImage=this.fileList.join()
				console.log(this.form)
				this.$refs["form"].validate(valid => {
					if (valid) {
						addFollow_up(this.form).then(response => {
							this.$modal.msgSuccess("新增成功");
							this.open = false;
							this.cancel()
						});
					}
				});
			},
			reset(){
				this.form.followContent=''
				this.form.followImage=''
				this.fileList=[]
				this.delList=[]
			},
			cancel(){
				this.reset()
				this.$emit("cancel")
			}
		}
	}
</script>

<style>
</style>