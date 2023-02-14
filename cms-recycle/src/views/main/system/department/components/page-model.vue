<template>
  <div class="modal">
    <el-dialog
      v-model="dialogVisible"
      :title="isNew_ ? '新增部门' : '编辑部门'"
      width="35%"
      center
    >
      <div class="form">
        <el-form :model="formData" label-width="80px" size="large">
          <el-row>
            <el-col :span="12">
              <el-form-item label="部门名称" prop="name">
                <el-input
                  v-model="formData.name"
                  placeholder="请输入部门名称"
                />
              </el-form-item>
            </el-col>
            <el-col :span="0">
              <el-form-item label="id" prop="id">
                <el-input v-model="formData.id" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="部门领导" prop="leader">
                <el-input
                  v-model="formData.leader"
                  placeholder="请输入领导姓名"
                />
              </el-form-item>
            </el-col>
          </el-row>

          <el-row
            ><el-col :span="12">
              <el-form-item label="上级部门" prop="parentId">
                <el-select
                  v-model="formData.parentId"
                  placeholder="请选择上级部门"
                  style="width: 100%"
                >
                  <template v-for="item in Departments" :key="item.id">
                    <el-option :label="item.name" :value="item.id" />
                  </template>
                </el-select> </el-form-item
            ></el-col>
            <el-col :span="12">
              <el-form-item label="状态" prop="status">
                <el-radio-group v-model="formData.status" class="ml-4">
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
                <el-input v-model="formData.phone" placeholder="请输入手机号" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="邮箱" prop="email">
                <el-input v-model="formData.email" placeholder="请输入邮箱" />
              </el-form-item> </el-col
          ></el-row>

          <el-row
            ><el-col :span="12">
              <el-form-item label="部门简写" prop="sn">
                <el-input v-model="formData.sn" placeholder="请输入部门简写" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item
                :label="isNew_ ? '创建人' : '更新人'"
                :prop="isNew_ ? 'create_by' : 'updateBy'"
              >
                <el-input
                  v-model="formData.create_by"
                  :style="isNew_ ? `display:none` : `display:inline`"
                  :placeholder="isNew_ ? '请输入创建人' : '请输入修改人'"
                />
                <el-input
                  :style="isNew_ ? `display:inline` : `display:none`"
                  v-model="formData.create_by"
                  :placeholder="isNew_ ? '请输入创建人' : '请输入修改人'"
                />
              </el-form-item>
            </el-col>
          </el-row>
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
import useSystemStore from '@/store/system/index'
import useOtherStore from '@/store/other/index'
import { storeToRefs } from 'pinia'
import { ElMessage } from 'element-plus'
const dialogVisible = ref(false)
const isNew_ = ref(true)
const formData = reactive<any>({
  id: '',
  name: '',
  leader: '',
  phone: '',
  email: '',
  status: '',
  parentId: '',
  sn: '',
  updateBy: '',
  create_by: '',
  ancestors: ''
})
const referEmit = defineEmits(['referMethod'])

//获取roles/departments
const otherStore = useOtherStore()
const { Departments } = storeToRefs(otherStore)
function setModalVisibel(isNew: boolean = true, itemData?: any) {
  isNew_.value = isNew
  if (!isNew && itemData) {
    for (const key in itemData) {
      formData[key] = itemData[key]
    }
  }
  dialogVisible.value = true
}

//点击确定的逻辑
const systemStore = useSystemStore()
function handelConFirm() {
  formData.createTime = ''
  formData.updateTime = ''
  systemStore.addOrSavePagelistAction('department', formData).then((res) => {
    if (res.code === 200) {
      ElMessage.success('提交成功')
      referEmit('referMethod')
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
