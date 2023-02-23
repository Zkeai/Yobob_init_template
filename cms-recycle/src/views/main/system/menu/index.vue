<template>
  <div class="menu">
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
      <template #menu>
        <el-tree-select
          ref="treeRef"
          :data="Menus"
          v-model="menuChecked"
          check-on-click-node
          check-strictly
          show-checkbox
          clearable
          node-key="id"
          :render-after-expand="false"
          :props="{ children: 'children', label: 'name' }"
          @check="handleElMenuTreeCheck"
        />
      </template>
    </PageModal>
  </div>
</template>

<script setup lang="ts">
import pageSearch from '@/components/page/page-search/page-search.vue'
import pageContent from '@/components/page/page-content/page-content.vue'
import PageModal from '@/components/page/page-modal/page-modal.vue'
import usePageContent from '@/hooks/usePageContent'
import contentConfig from './config/content.config'
import searchConfig from './config/search.config'
import modalConfig from './config/modal.config'
import usePageModal from '@/hooks/usePageModal'
import useOtherStore from '@/store/other'
import { nextTick, reactive, ref } from 'vue'
import { storeToRefs } from 'pinia'
import type { ElTree } from 'element-plus'

const menuChecked = ref()
const otherStore = useOtherStore()
const { Menus } = storeToRefs(otherStore)

//点击 搜索 重置 hooks
const { contentRef, handleQueryClick, handleResetClick } = usePageContent()
//modal组件的操作 新增 修改
const { modalRef, handleNewClick, handleEditClick } = usePageModal(editCallback)
// 需要传给model的方法
function referMethod() {
  return contentRef.value?.fetchPageListAction()
}

//callback

interface IotherInfo {
  menuIds: number[]
}
let otherInfo: IotherInfo = reactive({
  menuIds: []
})
function handleElMenuTreeCheck(_: any) {
  let menuId = _.id
  otherInfo.menuIds = [menuId]
}

const treeRef = ref<InstanceType<typeof ElTree>>()

function editCallback(itemData: any) {
  nextTick(() => {
    menuChecked.value = itemData?.parent_id
    treeRef.value?.setCheckedKeys([itemData?.parent_id])
  })
}
</script>
<style scoped lang="less"></style>
