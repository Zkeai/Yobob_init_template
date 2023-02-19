import type { IModalConfig } from '@/types/system'

const modalConfig: IModalConfig = {
  pageName: 'post',
  header: {
    newTitle: '新建岗位',
    editTitle: '编辑岗位'
  },
  formItems: [
    {
      type: 'input',
      prop: 'name',
      label: '岗位名称',
      initialVal: '',
      placeholder: '请输入岗位名称',
      vIf: true
    },
    {
      type: 'input',
      prop: 'code',
      label: '岗位标识',
      placeholder: '请输入岗位标识',
      vIf: true
    },
    {
      type: 'radio',
      label: '状态',
      prop: 'isBan',
      initialVal: '',
      width: '80px',
      radioList: [
        { label: '正常', value: 0 },
        { label: '封禁', value: 1 }
      ],
      size: 'large',
      vIf: true
    }
  ]
}

export default modalConfig
