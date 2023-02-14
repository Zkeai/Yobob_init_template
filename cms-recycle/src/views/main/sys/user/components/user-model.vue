<template>
  <div class="modal">
    <el-dialog v-model="dialogVisible" title="新增用户" width="35%" center>
      <div class="form">
        <el-form :model="formData" label-width="80px" size="large">
          <el-row>
            <el-col :span="12">
              <el-form-item label="用户名" prop="userName">
                <el-input
                  v-model="formData.user.userName"
                  placeholder="请输入用户名"
                />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="性别" prop="gender">
                <el-select
                  v-model="formData.user.gender"
                  placeholder="请选择性别"
                  style="width: 100%"
                >
                  <el-option label="男" :value="0" />
                  <el-option label="女" :value="1" />
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row
            ><el-col :span="12">
              <el-form-item label="账号" prop="userAccount">
                <el-input
                  v-model="formData.user.userAccount"
                  placeholder="请输入账号"
                />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="密码" prop="userPassword">
                <el-input
                  show-password
                  v-model="formData.user.userPassword"
                  placeholder="请输入密码"
                />
              </el-form-item> </el-col
          ></el-row>

          <el-row
            ><el-col :span="12">
              <el-form-item label="角色" prop="roleIds">
                <el-select
                  v-model="formData.roleIds"
                  multiple
                  filterable
                  allow-create
                  default-first-option
                  :reserve-keyword="false"
                  placeholder="请选择角色"
                >
                  <el-option
                    v-for="item in Roles"
                    :key="item.value"
                    :label="item.name"
                    :value="item.id"
                  />
                </el-select> </el-form-item
            ></el-col>
            <el-col :span="12">
              <el-form-item label="状态" prop="isBan">
                <el-radio-group v-model="radio" class="ml-4">
                  <el-radio model-value="0" label="0" size="large"
                    >正常</el-radio
                  >
                  <el-radio model-value="1" label="1" size="large"
                    >封禁</el-radio
                  >
                </el-radio-group>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row
            ><el-col :span="12">
              <el-form-item label="手机号" prop="phone">
                <el-input
                  v-model="formData.user.phone"
                  placeholder="请输入手机号"
                />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="邮箱" prop="email">
                <el-input
                  v-model="formData.user.email"
                  placeholder="请输入邮箱"
                />
              </el-form-item> </el-col
          ></el-row>

          <el-row
            ><el-col :span="12">
              <el-form-item label="部门" prop="deptId">
                <el-select
                  v-model="formData.user.deptId"
                  placeholder="请选择部门"
                  style="width: 100%"
                >
                  <template v-for="item in Departments" :key="item.id">
                    <el-option :label="item.name" :value="item.id" />
                  </template>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="岗位" prop="postIds">
                <el-select
                  v-model="formData.postIds"
                  multiple
                  filterable
                  allow-create
                  default-first-option
                  :reserve-keyword="false"
                  placeholder="请选择岗位"
                >
                  <el-option
                    v-for="item in Posts"
                    :key="item.id"
                    :label="item.name"
                    :value="item.id"
                  />
                </el-select>
              </el-form-item> </el-col
          ></el-row>
        </el-form>
      </div>

      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="handelConFirm"> 确定 </el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script setup lang="ts">
import { reactive, ref } from 'vue'
import useUserStore from '@/store/user/index'
import useOtherStore from '@/store/other/index'
import { storeToRefs } from 'pinia'
import type { IAddOrUpdateInfo } from '@/types/user'
import { ElMessage } from 'element-plus'
const dialogVisible = ref(false)
const radio = ref('0')
const formData: IAddOrUpdateInfo = reactive({
  user: {
    id: '',
    userName: '',
    userAccount: '',
    userPassword: '',
    deptId: '',
    isBan: '',
    gender: '',
    phone: '',
    email: ''
  },
  roleIds: '',
  postIds: ''
})

//获取roles/departments
const otherStore = useOtherStore()
const { Roles, Departments, Posts } = storeToRefs(otherStore)
function setModalVisibel() {
  dialogVisible.value = true
}

//点击确定
const userStore = useUserStore()
function handelConFirm() {
  userStore.addOrSaveUserlistAction(formData).then((res) => {
    if (res.code === 200) {
      ElMessage.success('提交成功')
    } else {
      ElMessage.error('提交失败')
    }
  })
  dialogVisible.value = false
}

//暴露的属性和方法
defineExpose({ setModalVisibel })
</script>

<style scoped lang="less">
.form {
  padding: 0 20px;
}
</style>
