<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="标记内容" prop="markContent">
        <el-select v-model="queryParams.markContent" placeholder="请选择标记内容" clearable>
          <el-option
            v-for="dict in dict.type.student_mark"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="被标记的学生ID" prop="studentId">
        <el-input
          v-model="queryParams.studentId"
          placeholder="请输入被标记的学生ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:mark:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:mark:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:mark:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:mark:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="markList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键ID" align="center" prop="markId" />
      <el-table-column label="标记内容" align="center" prop="markContent">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.student_mark" :value="scope.row.markContent"/>
        </template>
      </el-table-column>
      <el-table-column label="被标记的学生ID" align="center" prop="studentId" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:mark:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:mark:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改标记管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
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
        <el-form-item label="被标记的学生ID" prop="studentId">
          <el-input v-model="form.studentId" placeholder="请输入被标记的学生ID" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listMark, getMark, delMark, addMark, updateMark } from "@/api/system/mark";
import Dict from '@/utils/dict/Dict'
export default {
  name: "Mark",
  dicts: ['student_mark'],
  data() {
    return {
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
      // 标记管理表格数据
      markList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        markContent: null,
        studentId: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询标记管理列表 */
    getList() {
      this.loading = true;
      listMark(this.queryParams).then(response => {
        this.markList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        markId: null,
        markContent: null,
        studentId: null,
        createTime: null,
        updateTime: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.markId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加标记管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const markId = row.markId || this.ids
      getMark(markId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改标记管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.markId != null) {
            updateMark(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
				reloadDict("student_mark")
            });
          } else {
            addMark(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
							reloadDict("student_mark")
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const markIds = row.markId || this.ids;
      this.$modal.confirm('是否确认删除标记管理编号为"' + markIds + '"的数据项？').then(function() {
        return delMark(markIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
				reloadDict("student_mark")
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/mark/export', {
        ...this.queryParams
      }, `mark_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
