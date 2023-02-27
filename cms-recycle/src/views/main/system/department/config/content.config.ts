const contentConfig = {
  pageName: 'department',
  hasPagination: false,
  treeVisible: false,
  header: {
    title: '部门列表',
    btnTitle: '新建部门 '
  },
  propsList: [
    {
      type: 'index',
      label: '序号',
      prop: 'id',
      width: '80px',
      vIf: false
    },

    {
      type: 'normal',
      label: '部门名称',
      prop: 'name',
      width: '120px',
      vIf: true
    },
    {
      type: 'normal',
      label: '上级部门',
      prop: 'parentId',
      width: '120px',
      vIf: false
    },
    {
      type: 'normal',
      label: '部门领导',
      prop: 'leader',
      width: '100px',
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
  ],
  childrenTree: {
    rowKey: 'id',
    treeProps: { children: 'children', hasChildren: 'hasChildren' },
    defaultExpandAll: true
  }
}

export default contentConfig
