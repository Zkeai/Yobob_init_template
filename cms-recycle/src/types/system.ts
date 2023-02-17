//注册时的类型
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

export interface ISystemState {
  usersList: IResUser[]
  userTotalCount: number

  pageList: any[]
  pageTotalCount: number
}

export interface IAddOrUpdateInfo {
  user: {
    id?: number | ''
    userName?: string
    userAccount?: string
    userPassword?: string
    phone?: string
    email?: string
    gender?: number | ''
    isBan?: number | ''
    deptId?: number | ''
  }
  roleIds?: number[] | ''
  postIds?: number[] | ''
}

//modal prop接口类型
export interface IModalProps {
  modalConfig: IModalConfig
}
//modal Config文档接口类型
export interface IModalConfig {
  pageName: string
  header?: {
    newTitle: string
    editTitle: string
  }
  formItems: any[]
  childrenTree?: {
    rowKey: string
    treeProps: Object
    defaultExpandAll: boolean
  }
}
