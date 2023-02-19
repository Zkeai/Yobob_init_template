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
      @referMethod="referMethod"
    >
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

//点击 搜索 重置 hooks
const { contentRef, handleQueryClick, handleResetClick } = usePageContent()
//modal组件的操作 新增 修改
const { modalRef, handleNewClick, handleEditClick } = usePageModal()
// //需要传给model的方法
function referMethod() {
  return contentRef.value?.fetchPageListAction()
}
</script>
<style scoped lang="less"></style>
