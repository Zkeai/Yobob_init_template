<template>
  <div class="user">
    <pageSearch
      :search-config="searchConfig"
      @query-click="handleQueryClick"
      @reset-click="handleResetClick"
    />
    <pageContent
      ref="contentRef"
      :content-config="contentConfig"
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

    <UserModal
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
          filterable
          allow-create
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
          :data="Departments"
          v-model="DeptChecked"
          check-strictly
          check-on-click-node
          multiple
          node-key="id"
          :render-after-expand="false"
          show-checkbox
          :props="{ children: 'children', label: 'name' }"
          @check="handleElDeptTreeCheck"
        />
      </template>
      <!-- 岗位 -->
      <template #post="formData">
        <el-select
          v-model="formData.formData['postIds']"
          multiple
          filterable
          allow-create
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
    </UserModal>
  </div>
</template>

<script setup lang="ts">
import pageSearch from '@/components/page/page-search/page-search.vue'
import pageContent from '@/components/page/page-content/page-content.vue'
import UserModal from '@/components/page/page-modal/page-modal.vue'
import searchConfig from './config/search.config'
import contentConfig from './config/content.config'
import modalConfig from './config/modal.config'
import usePageModal from '@/hooks/usePageModal'
import usePageContent from '@/hooks/usePageContent'
import useOtherStore from '@/store/other'
import { storeToRefs } from 'pinia'
import { ref } from 'vue'
const DeptChecked = ref()
const otherStore = useOtherStore()
const { Roles, Departments, Posts } = storeToRefs(otherStore)
//点击 搜索 重置 hooks
const { contentRef, handleQueryClick, handleResetClick } = usePageContent()

//modal组件的操作 新增 修改
const { modalRef, handleNewClick, handleEditClick } = usePageModal()
//需要传给model的方法
function referMethod() {
  return contentRef.value?.fetchPageListAction()
}

const otherInfo = ref({})
function handleElDeptTreeCheck(_: any, data: any) {
  const DeptList = [...data.checkedKeys, ...data.halfCheckedKeys]
  otherInfo.value = DeptList
}
</script>
<style scoped lang="less"></style>
