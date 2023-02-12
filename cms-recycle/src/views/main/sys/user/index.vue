<template>
  <div class="user">
    <pageSearch
      @query-click="handelQueryClick"
      @reset-click="handelResetClick"
    />
    <pageContent ref="contentRef" />
  </div>
</template>

<script setup lang="ts">
import pageSearch from './components/page-search.vue'
import pageContent from './components/page-content.vue'
import { ref } from 'vue'

const contentRef = ref<InstanceType<typeof pageContent>>()
function handelQueryClick(formData: any) {
  contentRef.value?.fetchUserListAction(formData)
}
function handelResetClick() {
  contentRef.value?.fetchUserListAction()
}
</script>
<style scoped lang="less"></style>
