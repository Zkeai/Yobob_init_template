export interface IUser {
  userNmae?: string
  gender?: number
  phone?: string
  email?: string
  isBan?: number
  userRole?: string
  createTime?: string
  updateTime?: string
  pageNum: number
  pageSize: number
}

export interface IResUser {
  userNmae?: string
  gender?: number
  phone?: string
  email?: string
  userRole?: string
  createTime?: string
  updateTime?: string
  deptId?: number
  age?: number
  userAccount?: string
  userAvatar?: string
  isBan?: number
  status?: number
}

export interface IqueryPage {
  pageNum: number
  pageSize: number
}

export interface IUserState {
  usersList: IResUser[]
  userTotalCount: number
}

export interface IPageInfo {
  pageNum: number
  pageSize: number
  isBan?: number
  gender?: number
}
