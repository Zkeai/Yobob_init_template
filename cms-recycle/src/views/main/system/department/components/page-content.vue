<template>
  <div class="content">
    <div class="header">
      <h3 class="title">部门列表</h3>
      <el-button type="primary" @click="handelAddClick">新建部门</el-button>
    </div>
    <div class="table">
      <el-table size="small" :data="pageList" border style="width: 100%">
        <!-- 不显示 -->
        <el-table-column prop="id" label="id" v-if="false" />
        <!-- 显示 -->
        <el-table-column
          align="center"
          prop="name"
          label="部门昵称"
          width="120px"
        />
        <el-table-column
          align="center"
          prop="parentId"
          label="上级部门"
          width="150px"
        />
        <el-table-column
          align="center"
          prop="leader"
          label="部门领导"
          width="100px"
        >
        </el-table-column>

        <el-table-column
          align="center"
          prop="email"
          label="邮箱"
          width="180px"
        />

        <el-table-column align="center" prop="phone" label="手机" />

        <el-table-column align="center" prop="status" label="状态" width="80">
          <template v-slot="scope">
            <el-switch
              :model-value="scope.row.status"
              active-color="#5352ed"
              inactive-color="#2ed573"
              inline-prompt
              active-text="封禁"
              inactive-text="正常"
              :active-value="1"
              :inactive-value="0"
            /> </template
        ></el-table-column>
        <el-table-column
          align="center"
          prop="createTime"
          label="创建时间"
          width="180px"
        />
        <el-table-column
          align="center"
          prop="updateTime"
          label="更新时间"
          width="180px"
        />
        <el-table-column align="center" label="操作" width="161x">
          <template #default="scope">
            <el-button
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
                <el-button size="small" :icon="Delete" type="danger" text
                  >删除</el-button
                >
              </template>
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div class="pagination">
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
import { ref } from 'vue'

//自定事件
const emit = defineEmits(['newClick', 'editClick'])

const currentPage = ref(1)
const currentPageSize = ref(10)

const systemStore = useSystemStore()
const { pageList, pageTotalCount } = storeToRefs(systemStore)

//页码相关
fetchPageListAction()
const handleSizeChange = () => {
  fetchPageListAction()
}
const handleCurrentChange = () => {
  fetchPageListAction()
}
//发动网络请求函数
function fetchPageListAction(formData: any = {}) {
  const pageSize = currentPageSize.value
  const pageNum = currentPage.value
  const info = { pageNum, pageSize }

  const pageInfo = { ...info, ...formData }
  systemStore.postPageListAction('department', pageInfo)
}
//删除 增加 编辑
function handelDeleteClick(id: number) {
  systemStore.deletePageAction('department', id).then((res: any) => {
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
//暴露方法 属性
defineExpose({ fetchPageListAction })
</script>
<style scoped lang="less">
.content {
  background-color: white;
  padding: 20px;
  margin-top: 20px;
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
