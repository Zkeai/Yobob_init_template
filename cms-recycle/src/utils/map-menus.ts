import type { RouteRecordRaw } from 'vue-router'

function loadLocalRoutes() {
  const localRoutes: RouteRecordRaw[] = []
  //1.1读取素有router/main下的所有ts文件
  const files: Record<string, any> = import.meta.glob(
    '../router/main/**/*.ts',
    {
      eager: true
    }
  )
  //1.2 将加载的对象放进localRoutes数组
  for (const key in files) {
    const module = files[key]
    localRoutes.push(module.default)
  }

  return localRoutes
}

export function mapMenuToRoutes(menuList: any[]) {
  //加载本地路由
  const localRoutes = loadLocalRoutes()
  //根据菜单匹配路由
  const routes: RouteRecordRaw[] = []
  for (const menu of menuList) {
    for (const subMenu of menu.children) {
      const route = localRoutes.find((item) => item.path === subMenu.path)
      if (route) routes.push(route)
    }
  }
  //添加首页路由
  const indexRouter = localRoutes.find((item) => item.path === '/main/index')
  if (indexRouter) routes.push(indexRouter)

  return routes
}
