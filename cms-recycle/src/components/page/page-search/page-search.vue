<template>
  <div class="search">
    <el-form label-width="80px" :model="searchForm" ref="formRef">
      <el-row :gutter="20">
        <template v-for="item in searchConfig.formItems" :key="item.prop">
          <el-col :span="6">
            <el-form-item :label="item.label" :prop="item.prop">
              <template v-if="item.type === 'input'">
                <el-input
                  v-model="searchForm[item.prop]"
                  :placeholder="item.placeholder"
                ></el-input>
              </template>

              <template v-if="item.type === 'select'">
                <el-select
                  v-model="searchForm[item.prop]"
                  :placeholder="item.placeholder"
                  style="width: 100%"
                >
                  <el-option
                    v-for="(val, index) in item.selectValue"
                    :key="index"
                    :label="val.label"
                    :value="val.value"
                  />
                </el-select>
              </template>

              <template v-if="item.type === 'date-picker'">
                <el-date-picker
                  v-model="searchForm[item.prop]"
                  :type="item.dateType"
                  :range-separator="item.rangeSeparator"
                  :start-placeholder="item.startPlaceholder"
                  :end-placeholder="item.endPlaceholder"
                />
              </template>
            </el-form-item>
          </el-col>
        </template>
        <el-col :span="24" class="btn">
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
interface Iprops {
  searchConfig: {
    formItems: any[]
  }
}
const props = defineProps<Iprops>()
const initialForm: any = {}
for (const item of props.searchConfig.formItems) {
  initialForm[item.prop] = item.initialValue ?? ''
}
const searchForm = reactive(initialForm)
//自定义事件/接收的属性

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
    margin-top: 5px;
    justify-content: flex-end;
  }
}
</style>
