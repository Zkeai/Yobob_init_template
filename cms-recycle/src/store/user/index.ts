import { userListByPageRequest, userListRequest } from '@/service/main/user'
import type { IUser } from '@/types/user'
import { defineStore } from 'pinia'
import { Names } from '../store-name'

interface IUserState {
  usersList: any[]
  userTotalPages: number
}

const UserStore = defineStore(Names.USER, {
  state: (): IUserState => ({
    usersList: [],
    userTotalPages: 0
  }),
  getters: {},
  actions: {
    //分页获取列表
    async getUserlistBypageAction(data: IUser) {
      const loginResult = await userListByPageRequest(data)
      console.log(loginResult)
      if (loginResult.code === 200) {
        return 'success'
      } else {
        return loginResult.message
      }
    },
    //分页获取首次加载数据
    async getUserlistAction() {
      const loginResult = await userListRequest()
      const { totalPages, list } = loginResult.data
      this.userTotalPages = totalPages
      this.usersList = list
    }
  }
})
export default UserStore
