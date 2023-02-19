<template>
  <div class="role">
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
    </pageContent>
    <PageModal
      :modal-config="modalConfig"
      ref="modalRef"
      :other-info="otherInfo"
      @referMethod="referMethod"
    >
      <template #menuList>
        <el-tree-select
          :data="Menus"
          v-model="MenuChecked"
          check-strictly
          check-on-click-node
          multiple
          :render-after-expand="false"
          show-checkbox
          node-key="id"
          :props="{ children: 'children', label: 'name' }"
          @check="handleElMenuTreeCheck"
        />
      </template>
      <template #deptList>
        <el-tree-select
          :data="Departments"
          v-model="DeptChecked"
          check-strictly
          check-on-click-node
          multiple
          :render-after-expand="false"
          show-checkbox
          node-key="id"
          :props="{ children: 'children', label: 'name' }"
          @check="handleElDeptTreeCheck"
        />
      </template>
    </PageModal>
  </div>
</template>

<script setup lang="ts">
import pageSearch from '@/components/page/page-search/page-search.vue'
import pageContent from '@/components/page/page-content/page-content.vue'
import PageModal from '@/components/page/page-modal/page-modal.vue'
import searchConfig from './config/search.config'
import contentConfig from './config/content.config'
import modalConfig from './config/modal.config'
import usePageContent from '@/hooks/usePageContent'
import usePageModal from '@/hooks/usePageModal'
import { storeToRefs } from 'pinia'
import useOtherStore from '@/store/other/index'
import { reactive, ref } from 'vue'
const DeptChecked = ref()
const MenuChecked = ref()
//点击 搜索 重置 hooks
const { contentRef, handleQueryClick, handleResetClick } = usePageContent()
//modal组件的操作 新增 修改
const { modalRef, handleNewClick, handleEditClick } = usePageModal()

//需要传给model的方法
function referMethod() {
  return contentRef.value?.fetchPageListAction()
}
//获取完整菜单
interface IotherInfo {
  menuIds: number[]
  deptIds: number[]
}
const otherStore = useOtherStore()
const { Menus, Departments } = storeToRefs(otherStore)
let otherInfo: IotherInfo = reactive({
  menuIds: [],
  deptIds: []
})
function handleElMenuTreeCheck(_: any, data: any) {
  const MenuList = [...data.checkedKeys, ...data.halfCheckedKeys]
  otherInfo.menuIds = MenuList
}
function handleElDeptTreeCheck(_: any, data: any) {
  const DeptList = [...data.checkedKeys, ...data.halfCheckedKeys]
  otherInfo.deptIds = DeptList
}
</script>
<style scoped lang="less"></style>
