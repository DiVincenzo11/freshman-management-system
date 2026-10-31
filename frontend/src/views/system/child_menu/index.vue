<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="实体" prop="childMenuClass">
        <el-select v-model="queryParams.childMenuClass" placeholder="请选择实体" clearable>
          <el-option
            v-for="dict in dict.type.sys_child_menu"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="字段名称" prop="childMenuName">
        <el-input
          v-model="queryParams.childMenuName"
          placeholder="请输入字段名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="类型" prop="childMenuType">
        <el-select v-model="queryParams.childMenuType" placeholder="请选择类型" clearable>
          <el-option
            v-for="dict in dict.type.sys_child_menu_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
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
          v-hasPermi="['system:child_menu:add']"
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
          v-hasPermi="['system:child_menu:edit']"
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
          v-hasPermi="['system:child_menu:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:child_menu:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="child_menuList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键ID" align="center" prop="childMenuId" />
      <el-table-column label="实体" align="center" prop="childMenuClass">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_child_menu" :value="scope.row.childMenuClass"/>
        </template>
      </el-table-column>
	   <el-table-column label="名称" align="center" prop="childMenuLabel" />
      <el-table-column label="字段标识" align="center" prop="childMenuName" />
	  <el-table-column label="类型" align="center" prop="childMenuType">
	    <template slot-scope="scope">
	      <dict-tag :options="dict.type.sys_child_menu_type" :value="scope.row.childMenuType"/>
	    </template>
	  </el-table-column>
      <el-table-column label="显示顺序" align="center" prop="orderNum" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:child_menu:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:child_menu:remove']"
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

    <!-- 添加或修改子菜单管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="实体" prop="childMenuClass">
          <el-select v-model="form.childMenuClass" placeholder="请选择实体">
            <el-option
              v-for="dict in dict.type.sys_child_menu"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
		<el-form-item label="名称" prop="childMenuLabel">
		  <el-input v-model="form.childMenuLabel" placeholder="请输入名称" />
		</el-form-item>
        <el-form-item label="字段标识" prop="childMenuName">
          <el-input v-model="form.childMenuName" placeholder="请输入字段标识" />
        </el-form-item>
		<el-form-item label="类型" prop="childMenuType">
		  <el-select v-model="form.childMenuType" placeholder="请选择类型">
		    <el-option
		      v-for="dict in dict.type.sys_child_menu_type"
		      :key="dict.value"
		      :label="dict.label"
		      :value="parseInt(dict.value)"
		    ></el-option>
		  </el-select>
		</el-form-item>
        <el-form-item label="显示顺序" prop="orderNum">
          <el-input v-model="form.orderNum" placeholder="请输入显示顺序" />
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
import { listChild_menu, getChild_menu, delChild_menu, addChild_menu, updateChild_menu } from "@/api/system/child_menu";
export default {
  name: "Child_menu",
  dicts: ['sys_child_menu', 'sys_child_menu_type'],
  
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
      // 子菜单管理表格数据
      child_menuList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        childMenuClass: null,
        childMenuName: null,
        orderNum: null,
        childMenuType: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        childMenuClass: [
          { required: true, message: "实体不能为空", trigger: "change" }
        ],
        childMenuName: [
          { required: true, message: "字段标识不能为空", trigger: "blur" }
        ],
		childMenuLabel: [
		  { required: true, message: "名称不能为空", trigger: "blur" }
		],
        childMenuType: [
          { required: true, message: "类型不能为空", trigger: "change" }
        ]
      }
    };
  },
  created() {
    this.getList();

  },
  methods: {
    /** 查询子菜单管理列表 */
    getList() {
      this.loading = true;
      listChild_menu(this.queryParams).then(response => {
        this.child_menuList = response.rows;
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
        childMenuId: null,
        childMenuClass: null,
        childMenuName: null,
        orderNum: null,
        childMenuType: null
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
      this.ids = selection.map(item => item.childMenuId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加子菜单管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const childMenuId = row.childMenuId || this.ids
      getChild_menu(childMenuId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改子菜单管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.childMenuId != null) {
            updateChild_menu(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addChild_menu(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const childMenuIds = row.childMenuId || this.ids;
      this.$modal.confirm('是否确认删除子菜单管理编号为"' + childMenuIds + '"的数据项？').then(function() {
        return delChild_menu(childMenuIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/child_menu/export', {
        ...this.queryParams
      }, `child_menu_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
