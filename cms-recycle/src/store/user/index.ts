import { userListRequest } from '@/service/main/user'
import type { IUser, IUserState } from '@/types/user'
import { defineStore } from 'pinia'
import { Names } from '../store-name'

const UserStore = defineStore(Names.USER, {
  state: (): IUserState => ({
    usersList: [],
    userTotalCount: 0
  }),
  getters: {},
  actions: {
    //分页获取首次加载数据
    async getUserlistAction(queryPage: IUser) {
      const loginResult = await userListRequest(queryPage)
      const { totalCount, list } = loginResult.data
      this.userTotalCount = totalCount
      this.usersList = list
    }
  }
})
export default UserStore
