import type { IModalConfig } from '@/types/system'

const modalConfig: IModalConfig = {
  pageName: 'menu',
  header: {
    newTitle: '新建菜单',
    editTitle: '编辑菜单'
  },
  formItems: [
    {
      type: 'input',
      prop: 'name',
      label: '菜单名称',
      initialVal: '',
      placeholder: '请输入菜单名称',
      vIf: true
    },
    {
      type: 'input',
      prop: 'id',
      label: 'id',
      placeholder: '请输入id',
      vIf: false
    },
    {
      type: 'input',
      prop: 'icon',
      label: '菜单图标',
      initialVal: '',
      placeholder: '请输入菜单图标',
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
