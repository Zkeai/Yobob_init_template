import { userListByPageRequest } from '@/service/main/user'
import type { IUser } from '@/types/user'
import { defineStore } from 'pinia'
import { Names } from '../store-name'

const UserStore = defineStore(Names.USER, {
  state: () => ({}),
  getters: {},
  actions: {
    //登录方法
    async getUserlistBypageAction(data: IUser) {
      const loginResult = await userListByPageRequest(data)
      console.log(loginResult)
      if (loginResult.code === 200) {
        return 'success'
      } else {
        return loginResult.message
      }
    }
  }
})
export default UserStore
