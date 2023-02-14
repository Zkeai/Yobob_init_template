import {
  getDeparmentsRequest,
  getPostsRequest,
  getRolesRequest
} from '@/service/other'
import { defineStore } from 'pinia'
import { Names } from '../store-name'

interface IOtherState {
  Roles: any[]
  Departments: any[]
  Posts: any[]
}
const useOtherStore = defineStore(Names.OTHER, {
  state: (): IOtherState => ({
    Roles: [],
    Departments: [],
    Posts: []
  }),
  getters: {},
  actions: {
    async fetchDataAction() {
      const rolesResult = await getRolesRequest()
      const departmentsResult = await getDeparmentsRequest()
      const postsResult = await getPostsRequest()
      //保存数据
      this.Roles = rolesResult.data
      this.Departments = departmentsResult.data
      this.Posts = postsResult.data
    }
  }
})
export default useOtherStore
