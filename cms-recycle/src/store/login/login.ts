import { CACHETOKEN, MENULIST } from '@/global/cache-constants'
import router from '@/router'
import { loginRequest, registerRequest } from '@/service/login/login'
import type { IAccount, IRegAccount } from '@/types'
import { localCache, sessionCache } from '@/utils/localCache'
import { defineStore } from 'pinia'
import { Names } from '../store-name'
const useLoginStore = defineStore(Names.LOGIN, {
  state: () => ({
    token: localCache.getCache(CACHETOKEN) ?? '',
    account: ''
  }),
  getters: {
    GET_MENULIST: () => {
      return JSON.parse(sessionCache.getCache(MENULIST) as string)
    }
  },
  actions: {
    async loginAction(data: IAccount) {
      const loginResult = await loginRequest(data)
      if (loginResult.code === 200) {
        //存储jwtToken menu
        localCache.setCache(CACHETOKEN, loginResult.data?.token)
        sessionCache.setCache(
          MENULIST,
          JSON.stringify(loginResult.data?.menuList)
        )
        // 跳转页面 todo
        router.push('/main')
        return 'success'
      } else {
        return loginResult.message
      }
    },

    async registerAction(data: IRegAccount) {
      const registerResult = await registerRequest(data)
      if (registerResult.code === 200) {
        return 'success'
      } else {
        return registerResult.message
      }
    }
  }
})

export default useLoginStore
