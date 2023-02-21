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

export let firstMenu: any = null
/**
 *根据路径匹配要显示的菜单
 * @param path 需要匹配的路径
 * @param menuList  所有的菜单
 */
export function mapPathToMenu(path: string, menuList: any[]) {
  for (const menu of menuList) {
    for (const submenu of menu.children) {
      if (submenu.path === path) {
        return submenu
      }
    }
  }
  if (path === '/main/index') {
    let obj = {}
    obj = { path: '/index' }
    return obj
  }
}
/**
 *路由匹配
 * @param menuList 菜单列表
 * @returns 动态菜单的路径数组
 */
export function mapMenuToRoutes(menuList: any[]) {
  //加载本地路由
  const localRoutes = loadLocalRoutes()
  //根据菜单匹配路由
  const routes: RouteRecordRaw[] = []
  for (const menu of menuList) {
    for (const subMenu of menu.children) {
      const route = localRoutes.find((item) => item.path === subMenu.path)

      if (route) {
        //1.顶层菜单加重定向(只加一次)
        if (!routes.find((item) => item.path === menu.path)) {
          routes.push({ path: menu.path, redirect: route.path })
        }
        //2.二级菜单加入到路由
        routes.push(route)
      }
      //第一个匹配的
      if (!firstMenu && route) firstMenu = subMenu
    }
  }
  //添加首页路由
  const indexRouter = localRoutes.find((item) => item.path === '/main/index')
  if (indexRouter) routes.push(indexRouter)

  return routes
}
/**
 *面包屑匹配
 * @param path 需要匹配的路径
 * @param menuList 菜单列表
 * @returns
 */
interface IbreadCrum {
  name: string
  path?: string
}
export function mapPathToBreadcrumbs(path: string, menuList: any[]) {
  //定义面包屑的数组
  const breadCrums: IbreadCrum[] = []
  //遍历获取面包屑层级
  for (const menu of menuList) {
    for (const subMenu of menu.children) {
      if (subMenu.path === path) {
        breadCrums.push({ name: menu.name, path: menu.path })
        breadCrums.push({ name: subMenu.name, path: subMenu.path })
      }
    }
  }
  return breadCrums
}
/**
 * 菜单映射到id列表 递归
 * @param menuList
 */
export function mapMenuListToIds(menuList: any[]) {
  const ids: number[] = []
  function recurseGetId(menus: any[]) {
    for (const item of menus) {
      if (item.children.length > 0) {
        recurseGetId(item.children)
      } else {
        ids.push(item.id)
      }
    }
  }
  recurseGetId(menuList)
  return ids
}

/**
 * 从菜单列表映射权限数组
 * @param menuList 菜单列表
 * @returns 权限字符串数组
 */
export function mapMenuListToPermissions(menuList: any[]) {
  const permissions: string[] = []

  function recurseGetPermission(menus: any[]) {
    for (const item of menus) {
      if (item.menu_type === 'F') {
        permissions.push(item.code)
      } else {
        recurseGetPermission(item.children)
      }
    }
  }

  recurseGetPermission(menuList)
  return permissions
}
