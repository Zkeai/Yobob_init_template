import { loginRequest, registerRequest } from '@/service/login/login'
import type { IAccount, IRegAccount } from '@/types'
import { defineStore } from 'pinia'
import { Names } from '../store-name'
const useLoginStore = defineStore(Names.LOGIN, {
  state: () => ({
    token: '',
    account: ''
  }),
  getters: {},
  actions: {
<<<<<<< HEAD
    async loginAction(data: IAccount) {
      const loginResult = await loginRequest(data)
      if (loginResult.code === 0) {
        //存储jwtToken todo
        localStorage.setItem('token', loginResult.data)
        // 跳转页面
        return 'success'
      } else {
        return loginResult.message
      }
    },

    async registerAction(data: IRegAccount) {
      const registerResult = await registerRequest(data)
      console.log(registerResult)
=======
    async loginAction(data: any) {
      const loginResult = await loginRequest(data)
      console.log(loginResult)
>>>>>>> 3dea64886e711b48d0959e1fe7f354f01ad245b5
    }
  }
})

export default useLoginStore
