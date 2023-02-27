const contentConfig = {
  pageName: 'user',
  hasPagination: true,
  treeVisible: true,
  header: {
    title: '用户列表',
    btnTitle: '新建用户'
  },
  propsList: [
    {
      type: 'index',
      label: '序号',
      prop: 'id',
      width: '80px',
      vIf: true
    },
    {
      type: 'normal',
      label: 'postIds',
      prop: 'postIds',
      vIf: false
    },
    {
      type: 'normal',
      label: 'roleIds',
      prop: 'roleIds',
      vIf: false
    },
    {
      type: 'normal',
      label: '用户名',
      prop: 'userName',
      width: '120px',
      vIf: true
    },
    {
      type: 'normal',
      label: '账号',
      prop: 'userAccount',
      width: '150px',
      vIf: true
    },
    {
      type: 'avatar',
      label: '头像',
      width: '80px',
      vIf: true
    },
    {
      type: 'normal',
      label: '年龄',
      prop: 'age',
      width: '80px',
      vIf: true
    },
    {
      type: 'tag',
      label: '性别',
      prop: 'gender',
      width: '80px',
      tagList: [
        { type: 'success', value: '男' },
        { type: 'warning', value: '女' }
      ],
      vIf: true
    },
    {
      type: 'normal',
      label: '邮箱',
      prop: 'email',
      width: '180px',
      vIf: true
    },
    {
      type: 'normal',
      label: '手机',
      prop: 'phone',
      width: '180px',
      vIf: true
    },
    {
      type: 'isBan',
      prop: 'isBan',
      label: '状态',
      width: '80px',
      vIf: true
    },

    {
      type: 'time',
      label: '创建时间',
      prop: 'createTime',
      vIf: true
    },
    {
      type: 'time',
      label: '更新时间',
      prop: 'updateTime',
      vIf: true
    },
    {
      type: 'handle',
      label: '操作',
      width: '185px',
      vIf: true
    }
  ]
}

export default contentConfig
