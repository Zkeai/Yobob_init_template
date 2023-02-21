import {
  postPageListRequest,
  deletePageRequest,
  addOrSavePageRequest,
  updateIsBanPageRequest
} from '@/service/main/system/system'
import type { ISystemState } from '@/types/system'
import { defineStore } from 'pinia'
import useOtherStore from '../other'
import { Names } from '../store-name'

const SystemStore = defineStore(Names.USER, {
  state: (): ISystemState => ({
    usersList: [],
    userTotalCount: 0,

    pageList: [],
    pageTotalCount: 0
  }),
  getters: {},
  actions: {
    // 通用获取
    async postPageListAction(pageName: string, queryInfo: any) {
      const pageListResult = await postPageListRequest(pageName, queryInfo)
      const { totalCount, list } = pageListResult.data
      this.pageTotalCount = totalCount
      this.pageList = list
    },
    //通用删除
    async deletePageAction(pageName: string, id: number) {
      const Result = await deletePageRequest(pageName, id)

      //重新获取数据
      const otherStore = useOtherStore()
      otherStore.fetchDataAction()

      return Result
    },
    //通用新增或修改
    async addOrSavePagelistAction(pageName: string, userInfo: any) {
      const Result = await addOrSavePageRequest(pageName, userInfo)
      //重新获取数据
      const otherStore = useOtherStore()
      otherStore.fetchDataAction()
      return Result
    },
    //通用根据id修改IsBan状态
    async updateIsBanAction(pageName: string, userInfo: any) {
      const Result = await updateIsBanPageRequest(pageName, userInfo)
      //重新获取数据
      const otherStore = useOtherStore()
      otherStore.fetchDataAction()
      return Result
    }
  }
})
export default SystemStore
