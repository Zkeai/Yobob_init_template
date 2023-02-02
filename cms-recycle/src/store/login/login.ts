import { CACHETOKEN } from '@/global/cache-constants'
import router from '@/router'
import { loginRequest, registerRequest } from '@/service/login/login'
import type { IAccount, IRegAccount } from '@/types'
import { localCache } from '@/utils/localCache'
import { defineStore } from 'pinia'
import { Names } from '../store-name'
const useLoginStore = defineStore(Names.LOGIN, {
  state: () => ({
    token: localCache.getCache(CACHETOKEN) ?? '',
    account: ''
  }),
  getters: {},
  actions: {
    async loginAction(data: IAccount) {
      const loginResult = await loginRequest(data)
      if (loginResult.code === 0) {
        //存储jwtToken
        localCache.setCache(CACHETOKEN, loginResult.data)
        // 跳转页面 todo
        router.push('/main')

        return 'success'
      } else {
        return loginResult.message
      }
    },

    async registerAction(data: IRegAccount) {
      const registerResult = await registerRequest(data)
      if (registerResult.code === 0) {
        return 'success'
      } else {
        return registerResult.message
      }
    }
  }
})

export default useLoginStore
