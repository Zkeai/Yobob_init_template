import { getDepartmentPageRequest } from '@/service/main/departMent'
import type { IPageParam } from '@/types'
import { defineStore } from 'pinia'
import { Names } from '../store-name'
const useLoginStore = defineStore(Names.DEPARTMENT, {
  state: () => ({}),
  getters: {},
  actions: {
    async getDeparmentPageAction(data: IPageParam) {
      const deparmentPageResult = await getDepartmentPageRequest(data)
      if (deparmentPageResult.code === 200) {
        return deparmentPageResult
      } else {
        return deparmentPageResult.message
      }
    }
  }
})

export default useLoginStore
