import { CACHETOKEN } from '@/global/cache-constants'
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

//导航守卫
router.beforeEach((to) => {
  // 只有登录成功(token)且有效(todo), 才能真正进入到main页面
  const token = localCache.getCache(CACHETOKEN)

  if (to.path.startsWith('/main') && !token) {
    return '/login'
  }

  // 如果是进入到main
})
export default router
