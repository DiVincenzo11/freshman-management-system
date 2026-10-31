<template>
	<div class="app-container">
		<el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch"
			label-width="68px">
			<el-form-item label="姓名" prop="studentName" min-width="140">
				<el-input v-model="queryParams.studentName" placeholder="请输入姓名" clearable
					@keyup.enter.native="handleQuery" />
			</el-form-item>
			<el-form-item label="学号" prop="studentNum" min-width="170">
				<el-input v-model="queryParams.studentNum" placeholder="请输入学号" clearable
					@keyup.enter.native="handleQuery" />
			</el-form-item>
			<el-form-item label="专业班级" prop="grades" min-width="210">
				<el-select v-model="queryParams.grades" placeholder="请选择专业班级" clearable>
					<el-option v-for="dict in dict.type.student_grades" :key="dict.value" :label="dict.label"
						:value="dict.value" />
				</el-select>
			</el-form-item>
			<el-form-item label="班级职务" prop="duties" min-width="115">
				<el-select v-model="queryParams.duties" placeholder="请选择班级职务" clearable>
					<el-option v-for="dict in dict.type.student_duties" :key="dict.value" :label="dict.label"
						:value="dict.value" />
				</el-select>
			</el-form-item>
			<el-form-item label="性别" prop="sex" min-width="70">
				<el-select v-model="queryParams.sex" placeholder="请选择性别" clearable>
					<el-option v-for="dict in dict.type.sys_user_sex" :key="dict.value" :label="dict.label"
						:value="dict.value" />
				</el-select>
			</el-form-item>
			<el-form-item label="宿舍号" prop="dormitory" min-width="150">
				<el-input v-model="queryParams.dormitory" placeholder="请输入宿舍号" clearable
					@keyup.enter.native="handleQuery" />
			</el-form-item>
			<el-form-item v-hasPermi="['system:follow_up:query']" label="跟进" prop="follow">
				<el-select v-model="queryParams.follow" placeholder="请选择是否跟进" clearable>
					<el-option :key="1" label="是" :value="1" />
					<el-option :key="2" label="否" :value="2" />
				</el-select>
			</el-form-item>
			<el-form-item v-hasPermi="['system:mark:query']" label="标记" prop="ismark">
				<el-select v-model="queryParams.ismark" placeholder="请选择专业班级" clearable>
					<el-option v-for="dict in dict.type.student_mark" :key="dict.value" :label="dict.label"
						:value="dict.value" />
				</el-select>
			</el-form-item>
			<el-form-item>
				<el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
				<el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
			</el-form-item>
		</el-form>

		<el-row :gutter="10" class="mb8">
			<el-col :span="1.5">
				<el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
					v-hasPermi="['system:student:add']">新增</el-button>
			</el-col>
			<el-col :span="1.5">
				<el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
					v-hasPermi="['system:student:edit']">修改</el-button>
			</el-col>
			<el-col :span="1.5">
				<el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple"
					@click="handleDelete" v-hasPermi="['system:student:remove']">删除</el-button>
			</el-col>
			<el-col :span="1.5">
				<el-button type="warning" plain icon="el-icon-upload2" size="mini" @click="handleImport"
					v-hasPermi="['system:student:import']">导入</el-button>
			</el-col>
			<el-col :span="1.5">
				<el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
					v-hasPermi="['system:student:export']">导出</el-button>
			</el-col>
			<right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
		</el-row>

		<el-dialog :title="upload.title" :visible.sync="upload.open" width="400px">
			<el-upload ref="upload" :limit="1" accept=".xlsx, .xls" :headers="upload.headers"
				:action="upload.url + '?updateSupport=' + upload.updateSupport" :disabled="upload.isUploading"
				:on-progress="handleFileUploadProgress" :on-success="handleFileSuccess" :auto-upload="false" drag>
				<i class="el-icon-upload"></i>
				<div class="el-upload__text">
					将文件拖到此处，或
					<em>点击上传</em>
				</div>
				<div class="el-upload__tip" slot="tip">
					<el-link type="info" style="font-size:12px" @click="importTemplate">下载模板</el-link>
				</div>
				<div class="el-upload__tip" style="color:red" slot="tip">提示：仅允许导入“xls”或“xlsx”格式文件！</div>
			</el-upload>
			<div slot="footer" class="dialog-footer">
				<el-button type="primary" @click="submitFileForm">确 定</el-button>
				<el-button @click="upload.open = false">取 消</el-button>
			</div>
		</el-dialog>

		<el-table v-loading="loading" :data="studentList" @selection-change="handleSelectionChange">
			<el-table-column type="selection" width="55" align="center" />
			<el-table-column  v-if="isShow('studentName')"  label="姓名" align="center" prop="studentName" min-width="140" />
			<el-table-column  v-if="isShow('studentNum')"  label="学号" align="center" prop="studentNum" min-width="170" />
			<el-table-column  v-if="isShow('grades')"  label="专业班级" align="center" prop="grades" min-width="210">
				<template slot-scope="scope">
					<dict-tag :options="dict.type.student_grades" :value="scope.row.grades" />
				</template>
			</el-table-column>
			<el-table-column  v-if="isShow('duties')"  label="班级职务" align="center" prop="duties" min-width="115">
				<template slot-scope="scope">
					<dict-tag :options="dict.type.student_duties" :value="scope.row.duties" />
				</template>
			</el-table-column>
			<el-table-column  v-if="isShow('sex')"  label="性别" align="center" prop="sex" min-width="70">
				<template slot-scope="scope">
					<dict-tag :options="dict.type.sys_user_sex" :value="scope.row.sex" />
				</template>
			</el-table-column>
            <el-table-column label="关注标记" prop="ismark" min-width="145"><template slot-scope="scope"><el-tag v-if="scope.row.ismark" type="warning" size="small">{{scope.row.ismark}}</el-tag><span v-else>—</span></template></el-table-column>
            <el-table-column label="跟进状态" prop="follow" min-width="110"><template slot-scope="scope"><el-tag :type="scope.row.follow === 1 ? 'success' : 'info'" size="small">{{scope.row.follow === 1 ? '已跟进' : '待跟进'}}</el-tag></template></el-table-column>

			<el-table-column  v-if="isShow('birthday')"  label="出生日期" align="center" prop="birthday" width="180">
				<template slot-scope="scope">
					<span>{{ parseTime(scope.row.birthday, '{y}-{m}-{d}') }}</span>
				</template>
			</el-table-column>
			<el-table-column  v-if="isShow('idCard')"  label="身份证号" align="center" prop="idCard" width="200px" />
			<el-table-column  v-if="isShow('nativePlace')"  label="籍贯" align="center" prop="nativePlace" min-width="110" />
			<el-table-column  v-if="isShow('nation')"  label="民族" align="center" prop="nation" min-width="100" />
			<el-table-column  v-if="isShow('dormitory')"  label="宿舍号" align="center" prop="dormitory" min-width="150" />
			<el-table-column  v-if="isShow('face')"  label="政治面貌" align="center" prop="face" min-width="100" />
			<el-table-column  v-if="isShow('phone')"  label="手机号" align="center" prop="phone" min-width="150" />
			<el-table-column  v-if="isShow('oicq')"  label="QQ" align="center" prop="oicq" min-width="100" />
			<el-table-column  v-if="isShow('weChat')"  label="微信号" align="center" prop="weChat" min-width="120" />
			<el-table-column  v-if="isShow('address')"  label="家庭住址" align="center" prop="address" min-width="150" />
			<el-table-column label="操作" align="center" width="250px"  fixed="right">
				<template slot-scope="scope">
					<el-button size="mini" type="primary" @click="handleInfo(scope.row)"
						v-hasPermi="['system:student:query']">详情</el-button>
						<el-button size="mini" type="primary" @click="handleUpdate(scope.row.studentId)"
							v-hasPermi="['system:student:edit']">编辑</el-button>
					<el-button size="mini" type="danger" @click="handleDelete(scope.row)"
						v-hasPermi="['system:student:remove']">删除</el-button>
				</template>
			</el-table-column>
		</el-table>

		<pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
			@pagination="getList" />

		<!-- 新增学生 -->
		<add-students :open="open" @cancel="cancel"></add-students>
		<!-- 学生详情 -->
		<select-detail :show="detailOpen" ref="studentInfo" @cancel="detailCancel"></select-detail>
		<update-students ref="updatestudents" :show="updateShow" @cancel="updateCancel"></update-students>
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
	import addStudents from './components/addStudent.vue'
	import selectDetail from './components/selectDetail.vue'
	import updateStudents from './components/selectComponents/updateStudent.vue'
	import Dict from '@/utils/dict/Dict'
	import {
		getToken
	} from "@/utils/auth";
	import formMixins from '_mixins'
	import store from '@/store'
	export default {
		mixins: [formMixins],
		name: "Student",
		dicts: ['student_grades', 'sys_user_sex', 'student_duties', 'student_mark'],
		components: {
			addStudents,
			selectDetail,
			updateStudents
		},
		data() {
			return {
				//用户导入参数
				upload: {
					// 是否显示弹出层（用户导入）
					open: false,
					// 弹出层标题（用户导入）
					title: "",
					// 是否禁用上传
					isUploading: false,
					// 是否更新已经存在的用户数据
					updateSupport: 0,
					// 设置上传的请求头部
					headers: {
						Authorization: "Bearer " + getToken()
					},
					// 上传的地址
					url: process.env.VUE_APP_BASE_API + "/system/student/importData"
				},
				// 遮罩层
				loading: true,
				// 选中数组
				ids: [],
				// 非单个禁用
				single: true,
				// 非多个禁用
				multiple: true,
				// 显示搜索条件
				showSearch: true,
				// 总条数
				total: 0,
				// 学生管理表格数据
				studentList: [],
				// 弹出层标题
				title: "",
				// 新增学生
				open: false,
				//学生详情弹出层
				detailOpen: false,
				// 查询参数
				queryParams: {
					pageNum: 1,
					pageSize: 10,
					studentName: null,
					studentNum: null,
					grades: null,
					duties: null,
					sex: null,
					dormitory: null,
					follow: null,
					ismark: '',
					params: {
						mark: 0
					}
				},
				updateShow: false,
			};
		},
		created() {
			this.getList();
		},
		watch:{
			$route(){
				this.reloadDict()
			}
		},
		methods: {
			reloadDict(){
				location.reload();
			},
			detailCancel(){
				this.detailOpen=false
				this.getList()
			},
			handleUpdate(studentId) {
				this.updateShow = true
				this.$refs.updatestudents.getData(studentId)
			},
			updateCancel() {
				this.updateShow = false
				this.getList()
			},
			isShow(value) {
				if (this.mixSelectchildRoleList.includes(value)) {
					return store.state.user.select.includes(value)
				} else {
					return true
				}
			},
			handleImport() {
				this.upload.title = "学生导入";
				this.upload.open = true;
			},
			importTemplate() {
				this.download("system/student/importTemplate", {}, '学生数据导入模板.xlsx')
			},
			// 文件上传中处理
			handleFileUploadProgress(event, file, fileList) {
				this.upload.isUploading = true;
			},
			// 文件上传成功处理
			handleFileSuccess(response, file, fileList) {
				this.upload.open = false;
				this.upload.isUploading = false;
				this.$refs.upload.clearFiles();
				this.$alert(response.msg, "导入结果", {
					dangerouslyUseHTMLString: true
				});
				this.getList();
			},
			// 提交上传文件
			submitFileForm() {
				this.$refs.upload.submit();
			},
			/** 查询学生管理列表 */
			getList() {
				this.loading = true;
				// console.log(store.state.user)
				this.queryParams.params.roleId=store.state.user.roleId
				listStudent(this.queryParams).then(response => {
					this.studentList = response.rows;
					this.total = response.total;
					this.loading = false;
				});
			},
			// 取消按钮
			cancel() {
				this.open = false;
				this.getList()
			},

			/** 搜索按钮操作 */
			handleQuery() {
				this.queryParams.pageNum = 1;
				this.getList();
			},
			/** 重置按钮操作 */
			resetQuery() {
				this.queryParams = {
					pageNum: 1,
					pageSize: 10,
					studentName: null,
					studentNum: null,
					grades: null,
					duties: null,
					sex: null,
					dormitory: null,
					follow: null,
					ismark: '',
					params: {
						mark: 0
					}
				}
				// this.resetForm("queryForm");
				this.handleQuery();
			},
			// 多选框选中数据
			handleSelectionChange(selection) {
				this.ids = selection.map(item => item.studentId)
				this.single = selection.length !== 1
				this.multiple = !selection.length
			},
			/** 新增按钮操作 */
			handleAdd() {
				// this.reset();
				this.open = true;
				// this.title = "添加学生管理";
			},
			/** 修改按钮操作 */

			handleInfo(row) {
				this.detailOpen = true
				this.$refs.studentInfo.getData(row)

			},
			/** 提交按钮 */
			// submitForm() {
			//   this.$refs["form"].validate(valid => {
			//     if (valid) {
			//       if (this.form.studentId != null) {
			//         updateStudent(this.form).then(response => {
			//           this.$modal.msgSuccess("修改成功");
			//           this.open = false;
			//           this.getList();
			//         });
			//       } else {
			//         addStudent(this.form).then(response => {
			//           this.$modal.msgSuccess("新增成功");
			//           this.open = false;
			//           this.getList();
			//         });
			//       }
			//     }
			//   });
			// },
			/** 删除按钮操作 */
			handleDelete(row) {
				const studentIds = row.studentId || this.ids;
				this.$modal.confirm('是否确认删除学生管理编号为"' + studentIds + '"的数据项？').then(function() {
					return delStudent(studentIds);
				}).then(() => {
					this.getList();
					this.$modal.msgSuccess("删除成功");
				}).catch(() => {});
			},
			/** 导出按钮操作 */
			handleExport() {
				this.download('system/student/export', {
					...this.queryParams
				}, `student_${new Date().getTime()}.xlsx`)
			}
		}
	};
</script>