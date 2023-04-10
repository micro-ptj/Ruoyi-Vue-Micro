<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="source_id" prop="sourceId">
        <el-input
          v-model="queryParams.sourceId"
          placeholder="请输入source_id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="transaction_hash" prop="transactionHash">
        <el-input
          v-model="queryParams.transactionHash"
          placeholder="请输入transaction_hash"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="transaction_index" prop="transactionIndex">
        <el-input
          v-model="queryParams.transactionIndex"
          placeholder="请输入transaction_index"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="block_hash" prop="blockHash">
        <el-input
          v-model="queryParams.blockHash"
          placeholder="请输入block_hash"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="block_number" prop="blockNumber">
        <el-input
          v-model="queryParams.blockNumber"
          placeholder="请输入block_number"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="cumulative_gas_used" prop="cumulativeGasUsed">
        <el-input
          v-model="queryParams.cumulativeGasUsed"
          placeholder="请输入cumulative_gas_used"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="gas_used" prop="gasUsed">
        <el-input
          v-model="queryParams.gasUsed"
          placeholder="请输入gas_used"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="contract_address" prop="contractAddress">
        <el-input
          v-model="queryParams.contractAddress"
          placeholder="请输入contract_address"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="root" prop="root">
        <el-input
          v-model="queryParams.root"
          placeholder="请输入root"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="from" prop="from">
        <el-input
          v-model="queryParams.from"
          placeholder="请输入from"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="to" prop="to">
        <el-input
          v-model="queryParams.to"
          placeholder="请输入to"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="logs" prop="logs">
        <el-input
          v-model="queryParams.logs"
          placeholder="请输入logs"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="logs_bloom" prop="logsBloom">
        <el-input
          v-model="queryParams.logsBloom"
          placeholder="请输入logs_bloom"
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
          v-hasPermi="['geth:transaction:add']"
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
          v-hasPermi="['geth:transaction:edit']"
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
          v-hasPermi="['geth:transaction:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['geth:transaction:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="transactionList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="source_type" align="center" prop="sourceType" />
      <el-table-column label="source_id" align="center" prop="sourceId" />
      <el-table-column label="transaction_hash" align="center" prop="transactionHash" />
      <el-table-column label="transaction_index" align="center" prop="transactionIndex" />
      <el-table-column label="block_hash" align="center" prop="blockHash" />
      <el-table-column label="block_number" align="center" prop="blockNumber" />
      <el-table-column label="cumulative_gas_used" align="center" prop="cumulativeGasUsed" />
      <el-table-column label="gas_used" align="center" prop="gasUsed" />
      <el-table-column label="contract_address" align="center" prop="contractAddress" />
      <el-table-column label="root" align="center" prop="root" />
      <el-table-column label="status" align="center" prop="status" />
      <el-table-column label="from" align="center" prop="from" />
      <el-table-column label="to" align="center" prop="to" />
      <el-table-column label="logs" align="center" prop="logs" />
      <el-table-column label="logs_bloom" align="center" prop="logsBloom" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['geth:transaction:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['geth:transaction:remove']"
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

    <!-- 添加或修改区块链交易数据对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="source_id" prop="sourceId">
          <el-input v-model="form.sourceId" placeholder="请输入source_id" />
        </el-form-item>
        <el-form-item label="transaction_hash" prop="transactionHash">
          <el-input v-model="form.transactionHash" placeholder="请输入transaction_hash" />
        </el-form-item>
        <el-form-item label="transaction_index" prop="transactionIndex">
          <el-input v-model="form.transactionIndex" placeholder="请输入transaction_index" />
        </el-form-item>
        <el-form-item label="block_hash" prop="blockHash">
          <el-input v-model="form.blockHash" placeholder="请输入block_hash" />
        </el-form-item>
        <el-form-item label="block_number" prop="blockNumber">
          <el-input v-model="form.blockNumber" placeholder="请输入block_number" />
        </el-form-item>
        <el-form-item label="cumulative_gas_used" prop="cumulativeGasUsed">
          <el-input v-model="form.cumulativeGasUsed" placeholder="请输入cumulative_gas_used" />
        </el-form-item>
        <el-form-item label="gas_used" prop="gasUsed">
          <el-input v-model="form.gasUsed" placeholder="请输入gas_used" />
        </el-form-item>
        <el-form-item label="contract_address" prop="contractAddress">
          <el-input v-model="form.contractAddress" placeholder="请输入contract_address" />
        </el-form-item>
        <el-form-item label="root" prop="root">
          <el-input v-model="form.root" placeholder="请输入root" />
        </el-form-item>
        <el-form-item label="from" prop="from">
          <el-input v-model="form.from" placeholder="请输入from" />
        </el-form-item>
        <el-form-item label="to" prop="to">
          <el-input v-model="form.to" placeholder="请输入to" />
        </el-form-item>
        <el-form-item label="logs" prop="logs">
          <el-input v-model="form.logs" placeholder="请输入logs" />
        </el-form-item>
        <el-form-item label="logs_bloom" prop="logsBloom">
          <el-input v-model="form.logsBloom" placeholder="请输入logs_bloom" />
        </el-form-item>
        <el-form-item label="${comment}" prop="delFlag">
          <el-input v-model="form.delFlag" placeholder="请输入${comment}" />
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
import { listTransaction, getTransaction, delTransaction, addTransaction, updateTransaction } from "@/api/geth/transaction";

export default {
  name: "Transaction",
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
      // 区块链交易数据表格数据
      transactionList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        sourceType: null,
        sourceId: null,
        transactionHash: null,
        transactionIndex: null,
        blockHash: null,
        blockNumber: null,
        cumulativeGasUsed: null,
        gasUsed: null,
        contractAddress: null,
        root: null,
        status: null,
        from: null,
        to: null,
        logs: null,
        logsBloom: null,
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
    /** 查询区块链交易数据列表 */
    getList() {
      this.loading = true;
      listTransaction(this.queryParams).then(response => {
        this.transactionList = response.rows;
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
        sourceType: null,
        sourceId: null,
        transactionHash: null,
        transactionIndex: null,
        blockHash: null,
        blockNumber: null,
        cumulativeGasUsed: null,
        gasUsed: null,
        contractAddress: null,
        root: null,
        status: null,
        from: null,
        to: null,
        logs: null,
        logsBloom: null,
        createTime: null,
        createBy: null,
        updateTime: null,
        updateBy: null,
        delFlag: null
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
      this.title = "添加区块链交易数据";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getTransaction(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改区块链交易数据";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateTransaction(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addTransaction(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除区块链交易数据编号为"' + ids + '"的数据项？').then(function() {
        return delTransaction(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('geth/transaction/export', {
        ...this.queryParams
      }, `transaction_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
