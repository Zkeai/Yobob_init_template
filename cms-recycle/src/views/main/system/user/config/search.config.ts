const searchConfig = {
  pageName: 'user',
  formItems: [
    {
      type: 'input',
      prop: 'userName',
      label: '用户名',
      placeholder: '请输入用户名',
      initalValue: ''
    },
    {
      type: 'select',
      prop: 'gender',
      label: '性别',
      placeholder: '请选择性别',
      selectValue: [
        { label: '全部', value: 1000 },
        { label: '男', value: 0 },
        { label: '女', value: 1 }
      ]
    },
    {
      type: 'input',
      prop: 'phone',
      label: '手机号',
      placeholder: '请输入手机号',
      initalValue: ''
    },
    {
      type: 'input',
      prop: 'email',
      label: '邮箱',
      placeholder: '请输入邮箱',
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
