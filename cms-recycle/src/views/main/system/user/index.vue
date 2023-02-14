<template>
  <div class="user">
    <pageSearch
      @query-click="handelQueryClick"
      @reset-click="handelResetClick"
    />
    <pageContent
      ref="contentRef"
      @new-click="handleNewClick"
      @edit-click="handleEditClick"
    />
    <UserModel ref="modalRef" @referMethod="referMethod" />
  </div>
</template>

<script setup lang="ts">
import pageSearch from './components/page-search.vue'
import pageContent from './components/page-content.vue'
import UserModel from './components/user-model.vue'
import { ref } from 'vue'

const contentRef = ref<InstanceType<typeof pageContent>>()
function handelQueryClick(formData: any) {
  contentRef.value?.fetchPageListAction(formData)
}
function handelResetClick() {
  contentRef.value?.fetchPageListAction()
}

//modal组件的操作 新增 修改
const modalRef = ref<InstanceType<typeof UserModel>>()
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
