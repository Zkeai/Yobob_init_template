<template>
  <div class="user">
    <pageSearch
      @query-click="handelQueryClick"
      @reset-click="handelResetClick"
    />
    <pageContent ref="contentRef" @new-click="handelNewBtnClick" />
    <UserModel ref="modalRef" />
  </div>
</template>

<script setup lang="ts">
import pageSearch from './components/page-search.vue'
import pageContent from './components/page-content.vue'
import UserModel from './components/user-model.vue'
import { ref } from 'vue'

const contentRef = ref<InstanceType<typeof pageContent>>()
function handelQueryClick(formData: any) {
  contentRef.value?.fetchUserListAction(formData)
}
function handelResetClick() {
  contentRef.value?.fetchUserListAction()
}

//modal组件的操作
const modalRef = ref<InstanceType<typeof UserModel>>()
function handelNewBtnClick() {
  modalRef.value?.setModalVisibel()
}
</script>
<style scoped lang="less"></style>
