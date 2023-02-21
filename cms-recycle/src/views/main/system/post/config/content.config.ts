const contentConfig = {
  pageName: 'post',
  hasPagination: true,
  header: {
    title: '岗位列表',
    btnTitle: '新建岗位 '
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
      label: '岗位名称',
      prop: 'name',
      width: '120px',
      vIf: true
    },
    {
      type: 'normal',
      label: '岗位标识',
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
