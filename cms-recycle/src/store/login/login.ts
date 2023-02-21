import { CACHETOKEN, MENULIST, USERINFO } from '@/global/cache-constants'
import router from '@/router'
import { loginRequest, registerRequest } from '@/service/login/login'
import type { IAccount, IRegAccount } from '@/types'
import { localCache } from '@/utils/localCache'
import { mapMenuListToPermissions, mapMenuToRoutes } from '@/utils/map-menus'
import { defineStore } from 'pinia'
import useOtherStore from '../other'
import { Names } from '../store-name'
const useLoginStore = defineStore(Names.LOGIN, {
  state: () => ({
    token: '',
    userInfo: '',
    menuList: <any>[],
    permissions: <any>[]
  }),
  getters: {},
  actions: {
    //登录方法
    async loginAction(data: IAccount) {
      const loginResult = await loginRequest(data)
      const token = loginResult.data?.token
      const menuList = loginResult.data?.menuList
      const userInfo = loginResult.data?.currentUser
      if (loginResult.code === 200) {
        //本地缓存jwtToken menuList userInfo
        localCache.setCache(CACHETOKEN, token)
        localCache.setCache(MENULIST, JSON.stringify(menuList))
        localCache.setCache(USERINFO, JSON.stringify(userInfo))

        //获取所有departments/roles数据
        const otherStore = useOtherStore()
        otherStore.fetchDataAction()

        //获取登录用户按钮权限
        const permissions = mapMenuListToPermissions(menuList)
        this.permissions = permissions

        //动态理由
        const routes = mapMenuToRoutes(menuList)
        routes.forEach((route) => router.addRoute('main', route))

        // 跳转页面 todo
        router.push('/main')
        return 'success'
      } else {
        return loginResult.message
      }
    },
    //注册方法
    async registerAction(data: IRegAccount) {
      const registerResult = await registerRequest(data)
      if (registerResult.code === 200) {
        return 'success'
      } else {
        return registerResult.message
      }
    },
    //用户进行刷新时 获取本地缓存
    loadLocalCatcheAction() {
      const token = localCache.getCache(CACHETOKEN)
      let menuList = localCache.getCache(MENULIST)
      let userInfo = localCache.getCache(USERINFO)
      if (menuList && userInfo) {
        menuList = JSON.parse(menuList)
        userInfo = JSON.parse(userInfo)
      }
      if (token && userInfo && menuList) {
        this.token = token
        this.userInfo = userInfo
        this.menuList = menuList

        //获取最新的所有roles/departments数据
        const otherStore = useOtherStore()
        otherStore.fetchDataAction()

        //动态添加路由
        const routes = mapMenuToRoutes(menuList as any)
        routes.forEach((route) => router.addRoute('main', route))

        //获取登录用户按钮权限
        const permissions = mapMenuListToPermissions(menuList as any)
        this.permissions = permissions
      }
    }
  }
})

export default useLoginStore
