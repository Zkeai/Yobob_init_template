const contentConfig = {
  pageName: 'menu',
  hasPagination: false,
  header: {
    title: '菜单列表',
    btnTitle: '新建菜单'
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
      label: '菜单名称',
      prop: 'name',
      width: '120px',
      vIf: true
    },
    {
      type: 'normal',
      label: '上级菜单',
      prop: 'parent_id',
      width: '80px',
      vIf: false
    },
    {
      type: 'normal',
      label: '菜单图标',
      prop: 'icon',
      width: '120px',
      vIf: true
    },
    {
      type: 'normal',
      label: '排序',
      prop: 'order_num',
      width: '120px',
      vIf: true
    },
    {
      type: 'normal',
      label: '菜单标识',
      prop: 'code',
      vIf: true
    },
    {
      type: 'normal',
      label: '组件路径',
      prop: 'component',
      vIf: true
    },
    {
      type: 'normal',
      label: '菜单类型',
      prop: 'menu_type',
      width: '120px',
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
      prop: 'create_time',
      vIf: true
    },
    {
      type: 'time',
      label: '更新时间',
      prop: 'update_time',
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
    defaultExpandAll: false
  }
}

export default contentConfig
