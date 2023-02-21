import { ref } from 'vue'
import type PageModal from '@/components/page/page-modal/page-modal.vue'

type CallbackFnType = (data?: any) => void

function usePageModal(
  newCallback?: CallbackFnType,
  editCallback?: CallbackFnType
) {
  const modalRef = ref<InstanceType<typeof PageModal>>()

  function handleNewClick() {
    modalRef.value?.setModalVisibel()

    if (newCallback) newCallback()
  }
  function handleEditClick(itemDate: any) {
    modalRef.value?.setModalVisibel(false, itemDate)

    if (editCallback) editCallback(itemDate)
  }

  return {
    modalRef,
    handleNewClick,
    handleEditClick
  }
}

export default usePageModal
