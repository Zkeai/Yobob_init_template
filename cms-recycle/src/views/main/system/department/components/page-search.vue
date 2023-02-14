<template>
  <div class="search">
    <el-form label-width="80px" :model="searchForm" ref="formRef">
      <el-row :gutter="20">
        <el-col :span="4">
          <el-form-item label="部门名称" prop="name">
            <el-input
              v-model="searchForm.name"
              placeholder="请输入部门名称"
            /> </el-form-item
        ></el-col>

        <el-col :span="4">
          <el-form-item label="状态" prop="status">
            <el-select
              v-model="searchForm.status"
              placeholder="请选择状态"
              style="width: 100%"
            >
              <el-option label="全部" :value="1000" />
              <el-option label="正常" :value="0" />
              <el-option label="封禁" :value="1" /> </el-select></el-form-item
        ></el-col>

        <el-col :span="6">
          <el-form-item label="创建时间" prop="createTime">
            <el-date-picker
              v-model="searchForm.createTime"
              type="daterange"
              range-separator="-"
              start-placeholder="开始时间"
              end-placeholder="结束时间"
            /> </el-form-item
        ></el-col>
        <el-col :span="6">
          <el-form-item label="更新时间" prop="updateTime">
            <el-date-picker
              v-model="searchForm.updateTime"
              type="daterange"
              range-separator="-"
              start-placeholder="开始时间"
              end-placeholder="结束时间"
            /> </el-form-item
        ></el-col>
        <el-col :span="4" class="btn">
          <!-- 重置 搜索的按钮 -->
          <el-button icon="Refresh" @click="handleResetClick">重置</el-button>
          <el-button type="primary" icon="Search" @click="searchHandelClick"
            >查询</el-button
          >
        </el-col>
      </el-row>
    </el-form>
  </div>
</template>

<script setup lang="ts">
import { getTime } from '@/utils/time-format'
import type ElForm from 'element-plus/es/components/form'

import { reactive, ref } from 'vue'

const searchForm = reactive({
  name: '',
  status: 1000,
  createTime: '',
  updateTime: '',
  pageSize: 10,
  current: 1
})
//自定义事件
const emit = defineEmits(['queryClick', 'resetClick'])
//重置表单 通过ref
const formRef = ref<InstanceType<typeof ElForm>>()
function handleResetClick() {
  formRef.value?.resetFields()

  //重新发送网络请求
  emit('resetClick')
}

//查询
function searchHandelClick() {
  if (searchForm.createTime !== null && searchForm.createTime !== '') {
    let createTime = getTime(searchForm.createTime)
    searchForm.createTime = createTime
  }
  if (searchForm.updateTime !== null && searchForm.updateTime !== '') {
    let updateTime = getTime(searchForm.updateTime)
    searchForm.updateTime = updateTime
  }
  emit('queryClick', searchForm)
}
</script>
<style scoped lang="less">
.search {
  padding: 20px;
  background-color: white;
  .el-form-item {
    padding: 20px 16px;
    margin-bottom: 0;
  }
  .btn {
    display: flex;
    margin-top: 20px;
    flex-direction: row;
  }
}
</style>
