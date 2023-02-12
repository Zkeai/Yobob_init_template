<template>
  <div class="search">
    <el-form label-width="80px" :model="searchForm" ref="formRef">
      <el-row :gutter="20">
        <el-col :span="6">
          <el-form-item label="用户名" prop="userName">
            <el-input
              v-model="searchForm.userName"
              placeholder="请输入用户名"
            /> </el-form-item
        ></el-col>
        <el-col :span="6">
          <el-form-item label="性别" prop="gender">
            <el-select
              v-model="searchForm.gender"
              placeholder="请选择性别"
              style="width: 100%"
            >
              <el-option label="全部" :value="1000" />
              <el-option label="男" :value="0" />
              <el-option label="女" :value="1" /> </el-select></el-form-item
        ></el-col>
        <el-col :span="6">
          <el-form-item label="手机号" prop="phone">
            <el-input
              v-model="searchForm.phone"
              placeholder="请输入手机号"
            /> </el-form-item
        ></el-col>
        <el-col :span="6">
          <el-form-item label="邮箱" prop="email">
            <el-input
              placeholder="请输入邮箱"
              v-model="searchForm.email"
            /> </el-form-item
        ></el-col>

        <el-col :span="5">
          <el-form-item label="状态" prop="isBan">
            <el-select
              v-model="searchForm.isBan"
              placeholder="请选择状态"
              style="width: 100%"
            >
              <el-option label="全部" :value="1000" />
              <el-option label="正常" :value="0" />
              <el-option label="封禁" :value="1" /> </el-select></el-form-item
        ></el-col>
        <el-col :span="5">
          <el-form-item label="角色" prop="userRole">
            <el-select
              placeholder="请选择状态"
              style="width: 100%"
              v-model="searchForm.userRole"
            >
              <el-option label="全部" value="all" />
              <el-option label="管理员" value="admin" />
              <el-option label="普通" value="user" /> </el-select></el-form-item
        ></el-col>
        <el-col :span="7">
          <el-form-item label="创建时间" prop="createTime">
            <el-date-picker
              v-model="searchForm.createTime"
              type="daterange"
              range-separator="-"
              start-placeholder="开始时间"
              end-placeholder="结束时间"
            /> </el-form-item
        ></el-col>
        <el-col :span="7">
          <el-form-item label="更新时间" prop="updateTime">
            <el-date-picker
              v-model="searchForm.updateTime"
              type="daterange"
              range-separator="-"
              start-placeholder="开始时间"
              end-placeholder="结束时间"
            /> </el-form-item
        ></el-col>
      </el-row>
    </el-form>
    <!-- 重置 搜索的按钮 -->
    <div class="btns">
      <el-button icon="Refresh" @click="handleResetClick">重置</el-button>
      <el-button type="primary" icon="Search" @click="searchHandelClick"
        >查询</el-button
      >
    </div>
  </div>
</template>

<script setup lang="ts">
import { getTime } from '@/utils/time-format'
import type ElForm from 'element-plus/es/components/form'

import { reactive, ref } from 'vue'

const searchForm = reactive({
  userName: '',
  gender: 1000,
  email: '',
  phone: '',
  status: 1000,
  isBan: 1000,
  userRole: 'all',
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

  .btns {
    text-align: right;
    padding: 0 15px 0 0;
  }
}
</style>
