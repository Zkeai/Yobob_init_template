import { ref } from 'vue'
import type pageSearch from '@/components/page/page-search/page-search.vue'
function usePageSearch() {
  const searchRef = ref<InstanceType<typeof pageSearch>>()
  return {
    searchRef
  }
}
export default usePageSearch
