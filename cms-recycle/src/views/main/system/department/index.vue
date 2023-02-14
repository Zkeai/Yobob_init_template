<template>
  <div class="department">
    <pageSearch
      @query-click="handleQueryClick"
      @reset-click="handelResetClick"
    />
    <pageContent
      ref="contentRef"
      @new-click="handleNewClick"
      @edit-click="handleEditClick"
    />
    <pageModel ref="modalRef" @referMethod="referMethod" />
  </div>
</template>

<script setup lang="ts">
import pageSearch from './components/page-search.vue'
import pageContent from './components/page-content.vue'
import pageModel from './components/page-model.vue'
import { ref } from 'vue'

//点击search
const contentRef = ref<InstanceType<typeof pageContent>>()
function handleQueryClick(queryInfo: any) {
  contentRef.value?.fetchPageListAction(queryInfo)
}
//点击重置
function handelResetClick() {
  contentRef.value?.fetchPageListAction()
}

//modal组件的操作 新增 修改
const modalRef = ref<InstanceType<typeof pageModel>>()
function handleNewClick() {
  modalRef.value?.setModalVisibel()
}
function handleEditClick(itemDate: any) {
  modalRef.value?.setModalVisibel(false, itemDate)
}
//需要传给model的方法
function referMethod() {
  return contentRef.value?.fetchPageListAction()
}
</script>
<style scoped lang="less"></style>
