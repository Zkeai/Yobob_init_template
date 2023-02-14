import { CACHETOKEN } from '@/global/cache-constants'
import { authenticationRequest } from '@/service/other'
import { localCache } from '@/utils/localCache'
import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(),
  //映射关系: path => component
  routes: [
    {
      path: '/',
      redirect: '/main'
    },
    {
      path: '/main',
      name: 'main',
      component: () => import('../layout/index.vue')
    },
    {
      path: '/login',
      component: () => import('../views/login/index.vue')
    },

    {
      path: '/:pathMatch(.*)',
      component: () => import('../views/not-found/NotFound.vue')
    }
  ]
})

//动态路由

//导航守卫
router.beforeEach(async (to) => {
  // 只有登录成功(token)且有效(todo), 才能真正进入到main页面
  const token = localCache.getCache(CACHETOKEN)
  //token校验
  const res = await authenticationRequest().then((res) => {
    return res.success
  })
  if (to.path.startsWith('/main') && (!token || res !== true)) {
    return '/login'
  }

  // 如果是进入到main
  if (to.path === '/main') {
    //如果没有首页 就 firstMenu.url
    return '/main/index'
  }
})
export default router
