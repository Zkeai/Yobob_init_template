const searchConfig = {
  pageName: 'menu',
  formItems: [
    {
      type: 'input',
      prop: 'name',
      label: '菜单名称',
      placeholder: '请输入菜单名称',
      initalValue: ''
    },
    {
      type: 'select',
      prop: 'status',
      label: '状态',
      placeholder: '请选择状态',
      selectValue: [
        { label: '全部', value: 1000 },
        { label: '正常', value: 0 },
        { label: '封禁', value: 1 }
      ]
    },
    {
      type: 'date-picker',
      prop: 'createTime',
      label: '创建时间',
      placeholder: '请输入创建时间',
      dateType: 'daterange',
      rangeSeparator: '-',
      startPlaceholder: '开始时间',
      endPlaceholder: '结束时间'
    },
    {
      type: 'date-picker',
      prop: 'updateTime',
      label: '更新时间',
      dateType: 'daterange',
      rangeSeparator: '-',
      placeholder: '请输入更新时间',
      startPlaceholder: '开始时间',
      endPlaceholder: '结束时间'
    }
  ]
}

export default searchConfig
