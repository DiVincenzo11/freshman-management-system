<template>
	<!-- 新增学生标记 -->
	<el-dialog :modal="false" title="新增学生标记" :visible="show" width="500px" @close="cancel">
		<el-form ref="form" :model="form" :rules="rules" label-width="80px">
			<el-form-item label="标记内容" prop="markContent">
			  <el-select v-model="form.markContent" placeholder="请选择标记内容">
			    <el-option
			      v-for="dict in dict.type.student_mark"
			      :key="dict.value"
			      :label="dict.label"
			      :value="parseInt(dict.value)"
			    ></el-option>
			  </el-select>
			</el-form-item>
		</el-form>
		<div slot="footer" class="dialog-footer">
			<el-button type="primary" @click="submitForm">确 定</el-button>
			<el-button @click="cancel">取 消</el-button>
		</div>
	</el-dialog>
</template>

<script>
import { listMark, getMark, delMark, addMark, updateMark } from "@/api/system/mark";

	export default {
		dicts: ['student_mark'],
		name: 'addMark',
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
					markContent: [{
						required: true,
						message: "标记不能为空",
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
				this.$refs["form"].validate(valid => {
					if (valid) {
						addMark(this.form).then(response => {
							console.log("res1",response)
							this.$modal.msgSuccess("新增成功");
							this.open = false;
							this.cancel()
						});
					}
				});
			},
			reset(){
				this.form.markContent=''
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