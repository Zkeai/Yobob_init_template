<template>
  <div class="content">
    <div class="header">
      <h3 class="title">
        {{ contentConfig.header?.title ?? '数据列表' }}
      </h3>
      <el-button v-if="isAdd" type="primary" @click="handelAddClick">{{
        contentConfig.header?.btnTitle ?? '新建数据'
      }}</el-button>
    </div>
    <div class="content_">
      <div class="tree" v-if="contentConfig.treeVisible">
        <el-tree
          defaultExpandAll
          :expand-on-click-node="false"
          :data="Departments"
          :props="{ children: 'children', label: 'name' }"
          @node-click="handleNodeClick"
        />
      </div>
      <div
        class="table"
        :style="contentConfig.treeVisible ? 'width:90%' : 'width:100%'"
      >
        <el-table
          size="small"
          :data="pageList"
          border
          style="width: 100%"
          :row-key="contentConfig.childrenTree?.rowKey"
          :tree-props="contentConfig.childrenTree?.treeProps"
          :default-expand-all="contentConfig.childrenTree?.defaultExpandAll"
        >
          <template v-for="item in contentConfig.propsList" :key="item.prop">
            <!-- 自定义需要插入table的数据 利用插槽(具名+作用域)实现 -->
            <template v-if="item.type === 'custom'">
              <el-table-column
                align="center"
                :prop="item.prop"
                :label="item.label"
                :width="item.width"
              >
                <template #default="scope">
                  <slot :name="item.slotName" v-bind="scope"></slot>
                </template>
              </el-table-column>
            </template>
            <!-- avatar -->
            <template v-else-if="item.type === 'avatar'">
              <el-table-column
                align="center"
                prop="userAvatar"
                label="头像"
                width="80px"
              >
                <template v-slot="scope">
                  <el-image
                    align="center"
                    style="width: 40px; height: 40px"
                    :src="
                      scope.row[item.prop] ?? 'http://dummyimage.com/100x100'
                    "
                    fit="cover"
                  />
                </template>
              </el-table-column>
            </template>
            <!-- isban -->
            <template v-else-if="item.type === 'isBan'">
              <el-table-column
                align="center"
                :prop="item.prop"
                :label="item.label"
                :width="item.width"
              >
                <template v-slot="scope">
                  <el-popconfirm
                    confirm-button-text="确定"
                    cancel-button-text="取消"
                    icon-color="#626AEF"
                    title="你确定要修改么?"
                    @confirm="handleIsBanChange($event, scope.row)"
                  >
                    <template #reference>
                      <el-switch
                        :model-value="scope.row[item.prop]"
                        active-color="#5352ed"
                        inactive-color="#2ed573"
                        inline-prompt
                        active-text="封禁"
                        inactive-text="正常"
                        :active-value="1"
                        :inactive-value="0"
                      />
                    </template>
                  </el-popconfirm>
                </template>
              </el-table-column>
            </template>
            <!-- tag -->
            <template v-else-if="item.type === 'tag'">
              <el-table-column
                align="center"
                :prop="item.prop"
                :label="item.label"
                :width="item.width"
              >
                <template v-slot="scope">
                  <el-tag
                    :type="
                      scope.row.gender === 0
                        ? item.tagList[0].type
                        : item.tagList[1].type
                    "
                    class="mx-1"
                    effect="light"
                  >
                    {{
                      scope.row.gender === 0
                        ? item.tagList[0].value
                        : item.tagList[1].value
                    }}
                  </el-tag>
                </template>
              </el-table-column>
            </template>
            <!-- time -->
            <template v-else-if="item.type === 'time'">
              <el-table-column
                align="center"
                :prop="item.prop"
                :label="item.label"
                :width="item.width"
                v-if="item.vIf"
              >
                <template #default="scope">
                  {{ formatUTC(scope.row[item.prop]) }}
                </template>
              </el-table-column>
            </template>
            <!-- 操作 -->
            <template v-else-if="item.type === 'handle'">
              <el-table-column
                v-if="isEdit || isDelete"
                align="center"
                :label="item.label"
                :width="item.width"
              >
                <template #default="scope">
                  <el-button
                    v-if="isEdit"
                    size="small"
                    :icon="Edit"
                    type="primary"
                    text
                    @click="handleEditClick(scope.row)"
                    >编辑</el-button
                  >
                  <el-popconfirm
                    confirm-button-text="确定"
                    cancel-button-text="取消"
                    icon-color="#626AEF"
                    title="你确定要删除么?"
                    @confirm="handelDeleteClick(scope.row.id)"
                  >
                    <template #reference>
                      <el-button
                        v-if="isDelete"
                        size="small"
                        :icon="Delete"
                        type="danger"
                        text
                        >删除</el-button
                      >
                    </template>
                  </el-popconfirm>
                </template>
              </el-table-column>
            </template>
            <!-- 普通遍历 -->
            <template v-else>
              <el-table-column
                align="center"
                :prop="item.prop"
                :label="item.label"
                :width="item.width"
                v-if="item.vIf"
              />
            </template>
          </template>
        </el-table>
      </div>
    </div>

    <div class="pagination" v-if="contentConfig.hasPagination && isQuery">
      <el-pagination
        v-model:current-page="currentPage"
        v-model:page-size="currentPageSize"
        :page-sizes="[10, 20, 30, 50, 100]"
        layout="total, sizes, prev, pager, next, jumper"
        :total="pageTotalCount"
        small
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
      />
    </div>
  </div>
</template>

<script setup lang="ts">
import { ElMessage } from 'element-plus'
import useSystemStore from '@/store/system/index'
import { Delete, Edit } from '@element-plus/icons-vue'
import { storeToRefs } from 'pinia'
import { nextTick, reactive, ref } from 'vue'
import { formatUTC } from '@/utils/time-format'
import usePermission from '@/hooks/usePermission'
import useOtherStore from '@/store/other'

const otherStore = useOtherStore()
const { Departments } = storeToRefs(otherStore)

interface Iprops {
  contentConfig: {
    pageName: string
    treeVisible: boolean
    hasPagination: boolean

    header?: {
      title?: string
      btnTitle?: string
    }

    propsList: any[]

    childrenTree?: {
      rowKey: string
      treeProps: Object
      defaultExpandAll: boolean
    }
  }
  msg?: any
}
const props = defineProps<Iprops>()
//自定事件
const emit = defineEmits(['newClick', 'editClick', 'treeDeptId'])
//获取登录用户的权限 usePermission hooks
const isAdd = usePermission(`system:${props.contentConfig.pageName}:add`)
const isDelete = usePermission(`system:${props.contentConfig.pageName}:delete`)
const isEdit = usePermission(`system:${props.contentConfig.pageName}:edit`)
const isQuery = usePermission(`system:${props.contentConfig.pageName}:query`)

//页码相关
const systemStore = useSystemStore()
const { pageList, pageTotalCount } = storeToRefs(systemStore)
if (!isQuery) {
  pageList.value = []
}
const currentPage = ref(1)
const currentPageSize = ref(10)

if (
  props.contentConfig.pageName === 'department' ||
  props.contentConfig.pageName === 'menu'
) {
  currentPageSize.value = 1000
}

fetchPageListAction()
const handleSizeChange = () => {
  fetchPageListAction(props.msg)
}
const handleCurrentChange = () => {
  fetchPageListAction(props.msg)
}
//发动网络请求函数
function fetchPageListAction(formData: any = {}) {
  if (!isQuery) return
  const pageSize = currentPageSize.value
  const pageNum = currentPage.value
  const info = { pageNum, pageSize }
  const pageInfo = { ...info, ...formData }
  systemStore.postPageListAction(props.contentConfig.pageName, pageInfo)
}
//删除 增加 编辑 isBan切换
function handelDeleteClick(id: number) {
  systemStore
    .deletePageAction(props.contentConfig.pageName, id)
    .then((res: any) => {
      if (res.code === 200) {
        fetchPageListAction()
        ElMessage.success('删除成功')
      } else {
        ElMessage.error('删除失败')
      }
    })
}
function handelAddClick() {
  emit('newClick')
}
function handleEditClick(itemData: any) {
  emit('editClick', itemData)
}
function handleIsBanChange(_: any, row: any) {
  systemStore
    .updateIsBanAction(props.contentConfig.pageName, {
      isBan: row.isBan === 0 ? 1 : 0,
      id: row.id
    })
    .then((res) => {
      if (res.code === 200) {
        fetchPageListAction()
        ElMessage.success('修改成功')
      } else {
        ElMessage.error('修改失败')
      }
    })
}
//监听增删改action被执行
systemStore.$onAction(({ name, after }) => {
  after(() => {
    if (
      name === 'deletePageAction' ||
      name === 'addOrSavePagelistAction' ||
      name === 'updateIsBanAction'
    ) {
      currentPage.value = 1
    }
  })
})

//复选框节点被点击
let deptId = ref()
function handleNodeClick(target: any) {
  deptId.value = target.id
  emit('treeDeptId', deptId)

  nextTick(() => {
    fetchPageListAction(props.msg)
  })
}
defineExpose({ fetchPageListAction })
</script>
<style scoped lang="less">
.content {
  background-color: white;
  padding: 20px;
  margin-top: 20px;
  .content_ {
    display: flex;
    .tree {
      padding-right: 3%;
    }
  }

  .header {
    display: flex;
    justify-content: space-between;
    align-items: flex-end;
    margin-bottom: 20px;
    .title {
      font-size: 22px;
    }
  }
}
.pagination {
  display: flex;
  justify-content: flex-end;
  margin-top: 10px;
}
</style>
