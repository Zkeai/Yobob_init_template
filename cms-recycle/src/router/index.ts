import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(),
  //映射关系: path => component
  routes: [
    {
      path: '/',
      redirect: '/login'
    },
    {
      path: '/login',
      component: () => import('../views/login/index.vue')
    },
    {
      path: '/main',
      name: 'main',
      component: () => import('../views/main/Main.vue')
    },
    {
      path: '/:pathMatch(.*)',
      component: () => import('../views/not-found/NotFound.vue')
    }
  ]
})

//导航守卫
router.beforeEach((to) => {
  // 只有登录成功(token), 才能真正进入到main页面
  const token = localStorage.getItem('token')
  if (to.path.startsWith('/main') && !token) {
    return '/login'
  }

  // 如果是进入到main
})
export default router
