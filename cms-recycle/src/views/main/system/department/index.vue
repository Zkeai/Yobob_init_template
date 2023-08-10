<template>
  <div class="department">
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
    <pageModal
      ref="modalRef"
      @referMethod="referMethod"
      :modal-config="modalConfigRef"
    />
  </div>
</template>

<script setup lang="ts">
import pageSearch from '@/components/page/page-search/page-search.vue'
import pageContent from '@/components/page/page-content/page-content.vue'
import pageModal from '@/components/page/page-modal/page-modal.vue'
import searchConfig from './config/search.config'
import contentConfig from './config/content.config'
import modalConfig from './config/modal.config'
import { computed } from 'vue'
import useOtherStore from '@/store/other'
import usePageContent from '@/hooks/usePageContent'
import usePageModal from '@/hooks/usePageModal'
//对modalConfig 进行操作
const modalConfigRef = computed(() => {
  const otherStore = useOtherStore()
  type idsType = {
    label: string
    value: number
  }

  //添加上级网站  递归
  const departments = mapMenuListToIds(otherStore.Departments)
  function mapMenuListToIds(deptList: any[]) {
    const ids: idsType[] = []
    function recurseGetId(depts: any[]) {
      for (const item of depts) {
        if (item.children.length > 0) {
          ids.push({ label: item.name, value: item.id })
          recurseGetId(item.children)
        }
      }
    }
    recurseGetId(deptList)
    return ids
  }
  modalConfig.formItems.forEach((item) => {
    if (item.prop === 'parentId') {
      item.selectValue.push(...departments)
    }
  })

  return modalConfig
})
//点击 搜索 重置 hooks
const { contentRef, handleQueryClick, handleResetClick } = usePageContent()

//modal组件的操作 新增 修改
const { modalRef, handleNewClick, handleEditClick } = usePageModal()

//需要传给model的方法
function referMethod() {
  return contentRef.value?.fetchPageListAction()
}
</script>
<style scoped lang="less"></style>
