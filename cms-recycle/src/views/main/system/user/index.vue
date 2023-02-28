<template>
  <div class="user">
    <pageSearch
      ref="searchRef"
      :dept="treeDeptId"
      :search-config="searchConfig"
      @search-val="searchVal"
      @query-click="handleQueryClick"
      @reset-click="handleResetClick"
    />
    <pageContent
      ref="contentRef"
      :msg="formVal"
      :content-config="contentConfig"
      @tree-dept-id="getTreeDeptId"
      @new-click="handleNewClick"
      @edit-click="handleEditClick"
    >
      <template #status="scope">
        <el-switch
          :model-value="scope.row.isBan"
          active-color="#5352ed"
          inactive-color="#2ed573"
          inline-prompt
          active-text="封禁"
          inactive-text="正常"
          :active-value="1"
          :inactive-value="0"
        />
      </template>
    </pageContent>

    <pageModal
      :modal-config="modalConfig"
      ref="modalRef"
      :other-info="otherInfo"
      @referMethod="referMethod"
    >
      <!-- 角色 -->
      <template #role="formData">
        <el-select
          v-model="formData.formData['roleIds']"
          multiple
          default-first-option
          :reserve-keyword="false"
          placeholder="请选择角色"
        >
          <el-option
            v-for="item in Roles"
            :key="item.value"
            :label="item.name"
            :value="item.id"
          />
        </el-select>
      </template>
      <!-- 部门 -->
      <template #dept>
        <el-tree-select
          ref="treeRef"
          :data="Departments"
          v-model="DeptChecked"
          check-on-click-node
          check-strictly
          show-checkbox
          clearable
          node-key="id"
          :render-after-expand="false"
          :props="{ children: 'children', label: 'name' }"
          @check="handleElDeptTreeCheck"
        />
      </template>
      <!-- 岗位 -->
      <template #post="formData">
        <el-select
          v-model="formData.formData['postIds']"
          multiple
          default-first-option
          :reserve-keyword="false"
          placeholder="请选择岗位"
        >
          <el-option
            v-for="item in Posts"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          />
        </el-select>
      </template>
    </pageModal>
  </div>
</template>

<script setup lang="ts">
import { storeToRefs } from 'pinia'
import { nextTick, reactive, ref } from 'vue'

import pageSearch from '@/components/page/page-search/page-search.vue'
import pageContent from '@/components/page/page-content/page-content.vue'
import pageModal from '@/components/page/page-modal/page-modal.vue'

import searchConfig from './config/search.config'
import contentConfig from './config/content.config'
import modalConfig from './config/modal.config'

import usePageModal from '@/hooks/usePageModal'
import usePageContent from '@/hooks/usePageContent'

import useOtherStore from '@/store/other'
import type { ElSelect, ElTree } from 'element-plus'
import usePageSearch from '@/hooks/usePageSearch'

const DeptChecked = ref()
const otherStore = useOtherStore()
const { Roles, Departments, Posts } = storeToRefs(otherStore)

const { searchRef } = usePageSearch()

let formVal = ref()
const searchVal = (val: any) => {
  formVal.value = val
}
let treeDeptId = ref()

const getTreeDeptId = (val: any) => {
  treeDeptId.value = val
}
//点击 搜索 重置 hooks
const { contentRef, handleQueryClick, handleResetClick } = usePageContent()
//modal组件的操作 新增 修改
const { modalRef, handleNewClick, handleEditClick } = usePageModal(editCallback)
//需要传给modal的方法
function referMethod() {
  return contentRef.value?.fetchPageListAction()
}
//callback
interface IotherInfo {
  deptIds: number[]
}

let otherInfo: IotherInfo = reactive({
  deptIds: []
})

function handleElDeptTreeCheck(_: any) {
  let deptId = _.id
  otherInfo.deptIds = [deptId]
}

const treeRef = ref<InstanceType<typeof ElTree>>()

function editCallback(itemData: any) {
  nextTick(() => {
    DeptChecked.value = itemData?.deptId
    treeRef.value?.setCheckedKeys([itemData?.deptId])
  })
}
</script>
<style scoped lang="less"></style>
