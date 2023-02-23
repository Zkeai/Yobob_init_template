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
      prop: 'id',
      label: 'id',
      placeholder: '请输入id',
      vIf: false
    },
    {
      type: 'custom',
      slotName: 'menu',
      label: '上级菜单',
      childrenTree: {
        rowKey: 'id',
        treeProps: { children: 'children', hasChildren: 'hasChildren' },
        defaultExpandAll: true
      },
      vIf: true
    },
    {
      type: 'radio',
      label: '菜单类型',
      prop: 'menu_type',
      radioList: [
        { label: '目录', value: 'M' },
        { label: '菜单', value: 'C' },
        { label: '按钮', value: 'F' }
      ],
      size: 'large',
      vIf: true
    },
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
      prop: 'icon',
      label: '菜单图标',
      initialVal: '',
      placeholder: '请输入菜单图标',
      vIf: true
    },
    {
      type: 'input-num',
      prop: 'order_num',
      label: '显示排序',
      initialVal: 0,
      placeholder: '请选择显示排序',
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
      prop: 'code',
      label: '权限字符',
      initialVal: '',
      placeholder: '请输入权限字符(目录无需)',
      vIf: true
    },
    {
      type: 'input',
      prop: 'path',
      label: '路由地址',
      initialVal: '',
      placeholder: '请输入路由地址',
      vIf: true
    },
    {
      type: 'input',
      prop: 'component',
      label: '组件路径',
      initialVal: '',
      placeholder: '请输入组件路径(目录无需)',
      vIf: true
    }
  ]
}

export default modalConfig
