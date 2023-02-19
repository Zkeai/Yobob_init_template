import type { IModalConfig } from '@/types/system'

const modalConfig: IModalConfig = {
  pageName: 'role',
  header: {
    newTitle: '新建角色',
    editTitle: '编辑角色'
  },
  formItems: [
    {
      type: 'input',
      prop: 'name',
      label: '角色名称',
      initialVal: '',
      placeholder: '请输入角色名称',
      vIf: true
    },
    {
      type: 'input',
      prop: 'code',
      label: '权限标识',
      placeholder: '请输入权限标识',
      vIf: true
    },
    {
      type: 'custom',
      slotName: 'menuList',
      label: '菜单权限',
      vIf: true
    },
    {
      type: 'custom',
      slotName: 'deptList',
      label: '部门数据',
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
      prop: 'remark',
      label: '备注',
      initialVal: '',
      placeholder: '请输入备注',
      vIf: true
    }
  ]
}

export default modalConfig
