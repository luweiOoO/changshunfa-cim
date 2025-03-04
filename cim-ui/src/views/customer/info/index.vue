<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="顾客编码" prop="customerCode">
        <el-input
          v-model="queryParams.customerCode"
          placeholder="请输入顾客编码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="顾客名称" prop="customerName">
        <el-input
          v-model="queryParams.customerName"
          placeholder="请输入顾客名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="手机号" prop="phoneNumber">
        <el-input
          v-model="queryParams.phoneNumber"
          placeholder="请输入手机号"
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
          v-hasPermi="['customer:customerInfo:add']"
        >新增</el-button>
      </el-col>
      <!-- <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['customer:customerInfo:edit']"
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
          v-hasPermi="['customer:customerInfo:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['customer:customerInfo:export']"
        >导出</el-button>
      </el-col> -->
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="customerInfoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="顾客名称" align="center" prop="customerName" />
      <el-table-column label="性别" align="center" prop="sex" >
        <template slot-scope="scope">
          <span>{{ dictChange(scope.row.sex,sexList) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="手机号" align="center" prop="phoneNumber" min-width="110px"/>
      <el-table-column label="最近一次洗护时间" align="center" prop="lastHairWashTime" min-width="110px"/>
      <el-table-column label="地址" align="center" prop="customerAddress" show-overflow-tooltip="true"/>
      <el-table-column label="头围a尺寸" align="center" prop="headSizeA" />
      <el-table-column label="头尾b尺寸" align="center" prop="headSizeB" />
      <el-table-column label="头尾c尺寸" align="center" prop="headSizeC" />
      <el-table-column label="头尾d尺寸" align="center" prop="headSizeD" />
      <el-table-column label="头尾e尺寸" align="center" prop="headSizeE" />
      <el-table-column label="头尾f尺寸" align="center" prop="headSizeF" />
      <el-table-column label="备注" align="center" prop="remark" show-overflow-tooltip="true" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleWash(scope.row)"
          >洗护</el-button>
          <!-- <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['customer:customerInfo:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['customer:customerInfo:remove']"
          >删除</el-button> -->
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


    <!-- 客户到店洗护基础信息对话框 -->
    <el-dialog title="客户洗护信息" :visible.sync="washOpen" width="500px" append-to-body>
      <el-form ref="washForm" :model="washForm" :rules="washRules" label-width="80px">
        <el-form-item label="客户到店洗护时间" prop="hairWashTime">
          <el-date-picker
            v-model="washForm.hairWashTime"
            type="date"
            placeholder="选择日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="洗护价格" prop="washPrice">
          <el-input v-model="washForm.washPrice" placeholder="请输入洗护价格" />
        </el-form-item>
        <el-form-item label="洗护人员" prop="hairWashWorker">
          <el-select
            v-model="washForm.hairWashWorker"
            single
            filterable
            remote
            reserve-keyword
            placeholder="请输入关键词"
            :remote-method="getRemoteUserListByName"
            :loading="loading">
            <el-option
              v-for="item in userOptions"
              :key="item.userName"
              :label="item.userName"
              :value="item.userName">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="washForm.remark" placeholder="请输入备注" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitWashForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 添加或修改客户基础信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="顾客名称" prop="customerName">
          <el-input v-model="form.customerName" placeholder="请输入顾客名称" />
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-select v-model="form.sex" clearable placeholder="请选择性别">
            <el-option
              v-for="item in sexList"
              :key="item.dictValue"
              :label="item.dictLabel"
              :value="item.dictValue">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="手机号" prop="phoneNumber">
          <el-input v-model="form.phoneNumber" placeholder="请输入手机号" />
        </el-form-item>
        <el-form-item label="地址" prop="customerAddress">
          <el-input v-model="form.customerAddress" placeholder="请输入地址" />
        </el-form-item>
        <el-form-item label="头围a尺寸" prop="headSizeA">
          <el-input v-model="form.headSizeA" placeholder="请输入头围a尺寸" />
        </el-form-item>
        <el-form-item label="头尾b尺寸" prop="headSizeB">
          <el-input v-model="form.headSizeB" placeholder="请输入头尾b尺寸" />
        </el-form-item>
        <el-form-item label="头尾c尺寸" prop="headSizeC">
          <el-input v-model="form.headSizeC" placeholder="请输入头尾c尺寸" />
        </el-form-item>
        <el-form-item label="头尾d尺寸" prop="headSizeD">
          <el-input v-model="form.headSizeD" placeholder="请输入头尾d尺寸" />
        </el-form-item>
        <el-form-item label="头尾e尺寸" prop="headSizeE">
          <el-input v-model="form.headSizeE" placeholder="请输入头尾e尺寸" />
        </el-form-item>
        <el-form-item label="头尾f尺寸" prop="headSizeF">
          <el-input v-model="form.headSizeF" placeholder="请输入头尾f尺寸" />
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
import { listCustomerInfo, getCustomerInfo, delCustomerInfo, addCustomerInfo, updateCustomerInfo,hairWash } from "@/api/customer/customerInfo";
import { getDicts } from "@/api/system/dict/data";
import { getUserListByUserName } from "@/api/system/user";


export default {
  name: "CustomerInfo",
  data() {
    return {
      // 字典性别列表
      sexList:[],
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
      // 客户基础信息表格数据
      customerInfoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 是否显示洗护弹出层
      washOpen: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        customerCode: null,
        customerName: null,
        sex: null,
        phoneNumber: null,
        customerAddress: null,
        headSizeA: null,
        headSizeB: null,
        headSizeC: null,
        headSizeD: null,
        headSizeE: null,
        headSizeF: null,
        remark: null
      },

      //模糊查询用户列表
      userOptions:[],
      // 表单参数
      form: {},
      // 洗护表单参数
      washForm:{},
      // 客户洗护表单校验
      washRules: {
        hairWashTime: [
          { required: true, message: "客户到店洗护时间不能为空"}
        ],
        washPrice: [
          { required: true, message: "洗护价格不能为空"}
        ],
        hairWashWorker: [
          { required: true, message: "洗护人员不能为空"}
        ]
      },
      // 表单校验
      rules: {
        customerName: [
          { required: true, message: "顾客名称不能为空", trigger: "blur" }
        ],
        sex: [
          { required: true, message: "性别不能为空", trigger: "change" }
        ],
        phoneNumber: [
          { required: true, message: "手机号不能为空", trigger: "blur" }
        ],
        delFlag: [
          { required: true, message: "删除标志不能为空", trigger: "blur" }
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
      getDicts("sys_user_sex").then(response => {
        this.sexList = response.data;
      });
    },

    //根据用户名称模糊查询用户列表
    getRemoteUserListByName(query) {
      if (query !== '') {
          this.loading = true;
          setTimeout(() => {
            this.loading = false;
            getUserListByUserName(query).then(response => {
              this.userOptions = response.data;
            }); 
          }, 200);
        } else {
          this.userOptions = [];
        }
    },

    /** 查询客户基础信息列表 */
    getList() {
      this.loading = true;
      listCustomerInfo(this.queryParams).then(response => {
        this.customerInfoList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.washOpen = fales;
      this.washReset();
      this.reset();
    },
    // 洗护表单重置
    washReset(){
      this.washForm = {
        id: null,
        hairWashTime: null,
        customerName: null,
        phoneNumber: null,
        washPrice: null,
        remark: null
      };
      this.resetForm("washForm");
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        customerCode: null,
        customerName: null,
        sex: null,
        phoneNumber: null,
        customerAddress: null,
        headSizeA: null,
        headSizeB: null,
        headSizeC: null,
        headSizeD: null,
        headSizeE: null,
        headSizeF: null,
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
      this.title = "添加客户基础信息";
    },
    /** 洗护按钮操作 */
    handleWash(row) {
      this.washReset();
      this.washForm.customerId = row.id;
      this.washForm.customerName = row.customerName;
      this.washForm.customerCode = row.customerCode;
      this.washForm.phoneNumber = row.phoneNumber;
      this.washOpen = true;
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getCustomerInfo(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改客户基础信息";
      });
    },

    /** 到店洗护提交按钮 */
    submitWashForm() {

      hairWash(this.washForm).then(response => {
        this.$modal.msgSuccess("洗护已记录");
        this.washOpen = false;
        this.getList();
      });

    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateCustomerInfo(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCustomerInfo(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除客户基础信息编号为"' + ids + '"的数据项？').then(function() {
        return delCustomerInfo(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('customer/customerInfo/export', {
        ...this.queryParams
      }, `customerInfo_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
