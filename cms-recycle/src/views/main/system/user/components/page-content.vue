<template>
  <div class="content">
    <div class="header">
      <h3 class="title">用户列表</h3>
      <el-button type="primary" @click="handelAddClick">新建用户</el-button>
    </div>
    <div class="table">
      <el-table size="small" :data="usersList" border style="width: 100%">
        <!-- 不显示 -->
        <el-table-column prop="id" label="id" v-if="false" />
        <el-table-column prop="postIds" label="postIds" v-if="false" />
        <el-table-column prop="roleIds" label="roleIds" v-if="false" />
        <!-- 显示 -->
        <el-table-column
          align="center"
          prop="userName"
          label="用户名"
          width="120px"
        />
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
              :src="scope.row.userAvatar ?? 'http://dummyimage.com/100x100'"
              fit="cover"
            />
          </template> </el-table-column
        >>
        <el-table-column
          align="center"
          prop="userAccount"
          label="账号"
          width="150px"
        />
        <el-table-column
          align="center"
          prop="userRole"
          label="角色"
          width="100px"
        >
          <template v-slot="scope">
            <el-tag
              :type="scope.row.userRole === 'admin' ? '' : 'warning'"
              class="mx-1"
              effect="dark"
            >
              {{ scope.row.userRole === 'admin' ? '管理员' : '普通用户' }}
            </el-tag>
          </template>
        </el-table-column>

        <el-table-column align="center" prop="deptId" label="部门" />
        <el-table-column
          align="center"
          prop="email"
          label="邮箱"
          width="180px"
        />

        <el-table-column align="center" prop="phone" label="手机" />
        <el-table-column align="center" prop="age" label="年龄" width="55" />
        <el-table-column align="center" prop="gender" label="性别" width="80">
          <template v-slot="scope">
            <el-tag
              :type="scope.row.gender === 1 ? '' : 'warning'"
              class="mx-1"
              effect="light"
            >
              {{ scope.row.gender === 1 ? '女' : '男' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column align="center" prop="isBan" label="状态" width="80">
          <template v-slot="scope">
            <el-switch
              :model-value="scope.row.isBan"
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
        :total="userTotalCount"
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
const { usersList, userTotalCount } = storeToRefs(systemStore)

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
  systemStore.postPageListAction('user', pageInfo)
}
//删除 增加 编辑
function handelDeleteClick(id: number) {
  systemStore.deletePageAction('user', id).then((res) => {
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
