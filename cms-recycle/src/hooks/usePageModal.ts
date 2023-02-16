import { ref } from 'vue'
import type pageModal from '@/components/page-modal/page-modal.vue'
function usePageModal() {
  const modalRef = ref<InstanceType<typeof pageModal>>()
  function handleNewClick() {
    modalRef.value?.setModalVisibel()
  }
  function handleEditClick(itemDate: any) {
    modalRef.value?.setModalVisibel(false, itemDate)
  }

  return {
    modalRef,
    handleNewClick,
    handleEditClick
  }
}

export default usePageModal
