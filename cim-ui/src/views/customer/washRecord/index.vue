<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="客户名称" prop="customerName">
        <el-input
          v-model="queryParams.customerName"
          placeholder="请输入客户名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="洗护产品店员" prop="hairWashWorker">
        <el-input
          v-model="queryParams.hairWashWorker"
          placeholder="请输入洗护产品店员"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['customer:customerWashRecord:add']"
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
          v-hasPermi="['customer:customerWashRecord:edit']"
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
          v-hasPermi="['customer:customerWashRecord:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['customer:customerWashRecord:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row> -->

    <el-table v-loading="loading" :data="customerWashRecordList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="客户名称" align="center" prop="customerName" />
      <el-table-column label="客户手机号" align="center" prop="phoneNumber" />

      <!-- <el-table-column label="产品类别编码" align="center" prop="productCategoryCode" /> -->
      <!-- <el-table-column label="产品类别名称" align="center" prop="productCategoryName" /> -->
      <el-table-column label="产品洗护日期" align="center" prop="hairWashTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.hairWashTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="洗护产品店员" align="center" prop="hairWashWorker" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['customer:customerWashRecord:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['customer:customerWashRecord:remove']"
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

    <!-- 添加或修改客户产品洗护记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="客户编码" prop="customerCode">
          <el-input v-model="form.customerCode" placeholder="请输入客户编码" />
        </el-form-item>
        <el-form-item label="客户名称" prop="customerName">
          <el-input v-model="form.customerName" placeholder="请输入客户名称" />
        </el-form-item>
        <el-form-item label="产品洗护日期" prop="hairWashTime">
          <el-date-picker clearable
            v-model="form.hairWashTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择产品洗护日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="洗护产品店员" prop="hairWashWorker">
          <el-input v-model="form.hairWashWorker" placeholder="请输入洗护产品店员" />
        </el-form-item>
        <el-form-item label="产品详情编码" prop="productDetailCode">
          <el-input v-model="form.productDetailCode" placeholder="请输入产品详情编码" />
        </el-form-item>
        <el-form-item label="删除标志" prop="delFlag">
          <el-input v-model="form.delFlag" placeholder="请输入删除标志" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" />
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
import { listCustomerWashRecord, getCustomerWashRecord, delCustomerWashRecord, addCustomerWashRecord, updateCustomerWashRecord } from "@/api/customer/customerWashRecord";

export default {
  name: "CustomerWashRecord",
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
      // 客户产品洗护记录表格数据
      customerWashRecordList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        customerCode: null,
        customerName: null,
        hairWashTime: null,
        hairWashWorker: null,
        productDetailCode: null,
        remark: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        customerCode: [
          { required: true, message: "客户编码不能为空", trigger: "blur" }
        ],
        hairWashTime: [
          { required: true, message: "产品洗护日期不能为空", trigger: "blur" }
        ],
        hairWashWorker: [
          { required: true, message: "洗护产品店员不能为空", trigger: "blur" }
        ],
        productDetailCode: [
          { required: true, message: "产品详情编码不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询客户产品洗护记录列表 */
    getList() {
      this.loading = true;
      listCustomerWashRecord(this.queryParams).then(response => {
        this.customerWashRecordList = response.rows;
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
        id: null,
        customerCode: null,
        customerName: null,
        hairWashTime: null,
        hairWashWorker: null,
        productDetailCode: null,
        createTime: null,
        createBy: null,
        updateBy: null,
        updateTime: null,
        delFlag: null,
        remark: null
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
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加客户产品洗护记录";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getCustomerWashRecord(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改客户产品洗护记录";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateCustomerWashRecord(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCustomerWashRecord(this.form).then(response => {
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
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除客户产品洗护记录编号为"' + ids + '"的数据项？').then(function() {
        return delCustomerWashRecord(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('customer/customerWashRecord/export', {
        ...this.queryParams
      }, `customerWashRecord_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
