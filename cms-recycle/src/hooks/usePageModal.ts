import { ref } from 'vue'
import type PageModal from '@/components/page/page-modal/page-modal.vue'

type CallbackFnType = (data?: any) => void

function usePageModal(newCallback?: CallbackFnType) {
  const modalRef = ref<InstanceType<typeof PageModal>>()

  function handleNewClick() {
    modalRef.value?.setModalVisibel()

    if (newCallback) {
      newCallback()
    }
  }
  function handleEditClick(itemDate: any) {
    modalRef.value?.setModalVisibel(false, itemDate)
    if (newCallback) {
      newCallback(itemDate)
    }
  }

  return {
    modalRef,
    handleNewClick,
    handleEditClick
  }
}

export default usePageModal
