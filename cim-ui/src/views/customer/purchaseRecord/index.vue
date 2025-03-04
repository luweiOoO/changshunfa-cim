<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="客户编码" prop="customerCode">
        <el-input
          v-model="queryParams.customerCode"
          placeholder="请输入客户编码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="客户名称" prop="customerName">
        <el-input
          v-model="queryParams.customerName"
          placeholder="请输入客户名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="产品详情编码" prop="productDetailCode">
        <el-input
          v-model="queryParams.productDetailCode"
          placeholder="请输入产品详情编码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="客户下单时间" prop="customerOrderTime">
        <el-date-picker clearable
          v-model="queryParams.customerOrderTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择客户下单时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="订单完成时间" prop="finishTime">
        <el-date-picker clearable
          v-model="queryParams.finishTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择订单完成时间">
        </el-date-picker>
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
          v-hasPermi="['customer:customerPurchaseRecord:add']"
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
          v-hasPermi="['customer:customerPurchaseRecord:edit']"
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
          v-hasPermi="['customer:customerPurchaseRecord:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['customer:customerPurchaseRecord:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="customerPurchaseRecordList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="客户编码" align="center" prop="customerCode" />
      <el-table-column label="客户名称" align="center" prop="customerName" />
      <el-table-column label="产品详情编码" align="center" prop="productDetailCode" />
      <el-table-column label="客户下单时间" align="center" prop="customerOrderTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.customerOrderTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="订单状态" align="center" prop="orderStatus" >
        <template slot-scope="scope">
          <span>{{ dictChange(scope.row.orderStatus,orderStatusList) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="订单完成时间" align="center" prop="finishTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.finishTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="销售金额" align="center" prop="saleAmount" />
      <el-table-column label="到账金额" align="center" prop="receiveAmount" />
      <el-table-column label="支付方式" align="center" prop="paymentMethod" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['customer:customerPurchaseRecord:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['customer:customerPurchaseRecord:remove']"
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

    <!-- 添加或修改客户购买记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="客户编码" prop="customerCode">
          <el-input v-model="form.customerCode" placeholder="请输入客户编码" />
        </el-form-item>
        <el-form-item label="客户名称" prop="customerName">
          <el-input v-model="form.customerName" placeholder="请输入客户名称" />
        </el-form-item>
        <el-form-item label="产品详情编码" prop="productDetailCode">
          <el-input v-model="form.productDetailCode" placeholder="请输入产品详情编码" />
        </el-form-item>
        <el-form-item label="客户下单时间" prop="customerOrderTime">
          <el-date-picker clearable
            v-model="form.customerOrderTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择客户下单时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="订单完成时间" prop="finishTime">
          <el-date-picker clearable
            v-model="form.finishTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择订单完成时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="销售金额" prop="saleAmount">
          <el-input v-model="form.saleAmount" placeholder="请输入销售金额" />
        </el-form-item>
        <el-form-item label="到账金额" prop="receiveAmount">
          <el-input v-model="form.receiveAmount" placeholder="请输入到账金额" />
        </el-form-item>
        <el-form-item label="支付方式" prop="paymentMethod">
          <el-input v-model="form.paymentMethod" placeholder="请输入支付方式" />
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
import { listCustomerPurchaseRecord, getCustomerPurchaseRecord, delCustomerPurchaseRecord, addCustomerPurchaseRecord, updateCustomerPurchaseRecord } from "@/api/customer/customerPurchaseRecord";
import { getDicts } from "@/api/system/dict/data";

export default {
  name: "CustomerPurchaseRecord",
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
      // 客户购买记录表格数据
      customerPurchaseRecordList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 产品订单状态字典列表
      orderStatusList:[],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        customerCode: null,
        customerName: null,
        productDetailCode: null,
        customerOrderTime: null,
        orderStatus: null,
        finishTime: null,
        saleAmount: null,
        receiveAmount: null,
        paymentMethod: null,
        remark: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        customerCode: [
          { required: true, message: "客户编码不能为空", trigger: "blur" }
        ],
        productDetailCode: [
          { required: true, message: "产品详情编码不能为空", trigger: "blur" }
        ],
        customerOrderTime: [
          { required: true, message: "客户下单时间不能为空", trigger: "blur" }
        ],
        orderStatus: [
          { required: true, message: "订单状态不能为空", trigger: "change" }
        ],
        saleAmount: [
          { required: true, message: "销售金额不能为空", trigger: "blur" }
        ],
        receiveAmount: [
          { required: true, message: "到账金额不能为空", trigger: "blur" }
        ],
        paymentMethod: [
          { required: true, message: "支付方式不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getDictDataList();
  },
  methods: {

    //根据字典类型查询页面字典列表
    getDictDataList() {
      getDicts("order_status").then(response => {
        this.orderStatusList = response.data;
      });
    },

    /** 查询客户购买记录列表 */
    getList() {
      this.loading = true;
      listCustomerPurchaseRecord(this.queryParams).then(response => {
        this.customerPurchaseRecordList = response.rows;
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
        productDetailCode: null,
        customerOrderTime: null,
        orderStatus: null,
        finishTime: null,
        saleAmount: null,
        receiveAmount: null,
        paymentMethod: null,
        createBy: null,
        createTime: null,
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
      this.title = "添加客户购买记录";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getCustomerPurchaseRecord(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改客户购买记录";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateCustomerPurchaseRecord(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCustomerPurchaseRecord(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除客户购买记录编号为"' + ids + '"的数据项？').then(function() {
        return delCustomerPurchaseRecord(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('customer/customerPurchaseRecord/export', {
        ...this.queryParams
      }, `customerPurchaseRecord_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
