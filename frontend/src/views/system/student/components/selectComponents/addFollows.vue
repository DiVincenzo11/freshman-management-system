<template>
	<!-- 新增跟进记录 -->
	<el-dialog :modal="false" title="新增跟进记录" :visible="show" width="500px" @close="cancel">
		<el-form ref="followform" :model="form" :rules="rules" label-width="80px">
			<el-form-item label="内容" prop="followContent">
				<el-input type="textarea" v-model="form.followContent" placeholder="请输入内容" />
			</el-form-item>
			<el-form-item  label="附件" prop="followImage">
				<image-upload v-model="form.followImage" />
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
	} from "@/api/system/follow_up";
	export default {
		name: 'addFollowss',
		props: {
			show: {
				type: Boolean,
			}
		},

		data() {
			return {
				form: {
					followImage: ''
				},
				// 表单校验
				rules: {
					followContent: [{
						required: true,
						message: "内容不能为空",
						trigger: "blur"
					}],
				}
			}
		},
		methods: {
			getData(studentId) {
				this.form.studentId = studentId
			},
			submitForm() {
				this.$refs["followform"].validate(valid => {
					if (valid) {
						addFollow_up(this.form).then(response => {
							this.$modal.msgSuccess("新增成功");
							this.open = false;
							this.cancel()
						});
					}
				});
			},
			reset() {
				this.form.followContent = ''
				this.form.followImage = ''
			},
			cancel() {
				this.reset()
				this.$emit("cancel")
			}
		}
	}
</script>

<style>
</style>