import type { IModalConfig } from '@/types/system'

const modalConfig: IModalConfig = {
  pageName: 'department',
  header: {
    newTitle: '新建网站',
    editTitle: '编辑网站'
  },
  formItems: [
    {
      type: 'input',
      prop: 'name',
      label: '网站名称',
      initialVal: '',
      placeholder: '请输入网站名称',
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
      prop: 'leader',
      label: '网站领导',
      initialVal: '',
      placeholder: '请输入网站领导',
      vIf: true
    },
    {
      type: 'select',
      prop: 'parentId',
      label: '上级网站',
      initialVal: '',
      placeholder: '请选择上级网站',
      selectValue: [],
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
    },
    {
      type: 'input',
      prop: 'phone',
      label: '手机号',
      initialVal: '',
      placeholder: '请输入手机号',
      vIf: true
    },
    {
      type: 'input',
      prop: 'email',
      label: '邮箱',
      initialVal: '',
      placeholder: '请输入邮箱',
      vIf: true
    },
    {
      type: 'input',
      prop: 'sn',
      label: '简称',
      initialVal: '',
      placeholder: '请输入网站简称',
      vIf: true
    },
    {
      type: 'createby',
      prop: 'create_by',
      newProp: 'create_by',
      editProp: 'updateBy',
      newLabel: '创建者',
      editLabel: '更新者',
      newPlaceholder: '请输入创建者',
      editPlaceholder: '请输入更新者',
      initialVal: '',
      vIf: true
    }
  ]
}

export default modalConfig
