import {
  getDeparmentsRequest,
  getMenuRequest,
  getPostsRequest,
  getRolesRequest
} from '@/service/other'
import { defineStore } from 'pinia'
import { Names } from '../store-name'

interface IOtherState {
  Roles: any[]
  Departments: any[]
  Posts: any[]
  Menus: any[]
}
const useOtherStore = defineStore(Names.OTHER, {
  state: (): IOtherState => ({
    Roles: [],
    Departments: [],
    Posts: [],
    Menus: []
  }),
  getters: {},
  actions: {
    async fetchDataAction() {
      const rolesResult = await getRolesRequest()
      const departmentsResult = await getDeparmentsRequest()
      const postsResult = await getPostsRequest()
      const menuResult = await getMenuRequest()
      //保存数据
      this.Roles = rolesResult.data
      this.Departments = departmentsResult.data
      this.Posts = postsResult.data
      this.Menus = menuResult.data
    }
  }
})
export default useOtherStore
