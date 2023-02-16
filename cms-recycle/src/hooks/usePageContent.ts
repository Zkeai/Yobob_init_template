import { ref } from 'vue'
import type pageContent from '@/components/page/page-content/page-content.vue'
function usePageContent() {
  const contentRef = ref<InstanceType<typeof pageContent>>()
  function handleQueryClick(queryInfo: any) {
    contentRef.value?.fetchPageListAction(queryInfo)
  }
  function handleResetClick() {
    contentRef.value?.fetchPageListAction()
  }
  return {
    contentRef,
    handleQueryClick,
    handleResetClick
  }
}
export default usePageContent
