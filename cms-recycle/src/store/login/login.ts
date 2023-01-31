import { loginRequest } from '@/service/login/login'
import { defineStore } from 'pinia'
import { Names } from '../store-name'
const useLoginStore = defineStore(Names.LOGIN, {
  state: () => ({
    token: '',
    account: ''
  }),
  getters: {},
  actions: {
    async loginAction(data: any) {
      const loginResult = await loginRequest(data)
      console.log(loginResult)
    }
  }
})

export default useLoginStore
