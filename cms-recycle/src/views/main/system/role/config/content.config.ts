const contentConfig = {
  pageName: 'role',
  header: {
    title: '角色列表',
    btnTitle: '新建角色 '
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
      label: '角色名称',
      prop: 'name',
      width: '120px',
      vIf: true
    },
    {
      type: 'normal',
      label: '权限标识',
      prop: 'code',
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
      type: 'normal',
      prop: 'remark',
      label: '备注',
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
