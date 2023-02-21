import type { IModalConfig } from '@/types/system'

const modalConfig: IModalConfig = {
  pageName: 'user',
  header: {
    newTitle: '新建用户',
    editTitle: '编辑用户'
  },
  formItems: [
    {
      type: 'input',
      prop: 'userName',
      label: '用户名',
      initialVal: '',
      placeholder: '请输入用户名',
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
      type: 'select',
      prop: 'gender',
      label: '性别',
      initialVal: '',
      selectValue: [
        { label: '男', value: 0 },
        { label: '女', value: 1 }
      ],
      placeholder: '请选择性别',
      vIf: true
    },
    {
      type: 'input',
      prop: 'userAccount',
      label: '账号',
      initialVal: '',
      placeholder: '请输入账号',
      vIf: true
    },
    {
      type: 'input',
      prop: 'userPassword',
      label: '密码',
      initialVal: '',
      placeholder: '请输入密码',
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
      type: 'custom',
      slotName: 'dept',
      label: '部门',
      childrenTree: {
        rowKey: 'id',
        treeProps: { children: 'children', hasChildren: 'hasChildren' },
        defaultExpandAll: true
      },
      vIf: true
    },
    {
      type: 'custom',
      slotName: 'post',
      label: '岗位',
      prop: 'post',
      vIf: true
    },
    {
      type: 'custom',
      slotName: 'role',
      label: '角色',
      vIf: true
    }
  ]
}

export default modalConfig
