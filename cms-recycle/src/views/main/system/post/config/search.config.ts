const searchConfig = {
  pageName: 'post',
  formItems: [
    {
      type: 'input',
      prop: 'name',
      label: '岗位名称',
      placeholder: '请输入岗位名称',
      initalValue: ''
    },
    {
      type: 'input',
      prop: 'code',
      label: '岗位标识',
      placeholder: '请输入岗位标识',
      initalValue: ''
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
