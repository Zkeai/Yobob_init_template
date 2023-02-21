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
          v-model="MenuChecked"
          ref="treeRef"
          :data="Menus"
          :props="{ children: 'children', label: 'name' }"
          node-key="id"
          highlight-current
          multiple
          show-checkbox
          check-on-click-node
          render-after-expand="false"
          @check="handleElMenuTreeCheck"
        >
        </el-tree-select>
      </template>
    </PageModal>
  </div>
</template>

<script setup lang="ts">
import { reactive, ref, nextTick } from 'vue'
import type { ElTree } from 'element-plus'
import { storeToRefs } from 'pinia'

import pageSearch from '@/components/page/page-search/page-search.vue'
import pageContent from '@/components/page/page-content/page-content.vue'
import PageModal from '@/components/page/page-modal/page-modal.vue'

import searchConfig from './config/search.config'
import contentConfig from './config/content.config'
import modalConfig from './config/modal.config'

import usePageContent from '@/hooks/usePageContent'
import usePageModal from '@/hooks/usePageModal'

import useOtherStore from '@/store/other/index'
import { mapMenuListToIds } from '@/utils/map-menus'

const MenuChecked = ref()
//点击 搜索 重置 hooks
const { contentRef, handleQueryClick, handleResetClick } = usePageContent()
//modal组件的操作 新增 修改
const { modalRef, handleNewClick, handleEditClick } = usePageModal(
  newCallback,
  editCallback
)

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
const { Menus } = storeToRefs(otherStore)
let otherInfo: IotherInfo = reactive({
  menuIds: [],
  deptIds: []
})
function handleElMenuTreeCheck(_: any, data: any) {
  const MenuList = [...data.checkedKeys, ...data.halfCheckedKeys]
  otherInfo.menuIds = MenuList
}

const treeRef = ref<InstanceType<typeof ElTree>>()

function editCallback(itemData: any) {
  nextTick(() => {
    const menuIds = mapMenuListToIds(itemData.menuList)
    MenuChecked.value = menuIds
    treeRef.value?.setCheckedKeys(menuIds)
  })
}

function newCallback() {
  nextTick(() => {
    MenuChecked.value = []
    treeRef.value?.setCheckedKeys([])
  })
}
</script>
<style scoped lang="less"></style>
