<template>
  <div class="modal">
    <el-dialog
      v-model="dialogVisible"
      :title="
        isNew_
          ? modalConfig.header?.newTitle ?? '新建数据'
          : modalConfig.header?.editTitle ?? '编辑数据'
      "
      width="35%"
      center
    >
      <div class="form">
        <el-form :model="formData" label-width="80px" size="large">
          <el-row :gutter="20">
            <template v-for="item in modalConfig.formItems" :key="item.prop">
              <el-col :span="item.vIf === true ? 12 : 0">
                <template v-if="item.type === 'select'">
                  <el-form-item :label="item.label" :prop="item.prop">
                    <el-select
                      v-model="formData[item.prop]"
                      :placeholder="item.placeholder"
                      style="width: 100%"
                    >
                      <el-option
                        v-for="(val, index) in item.selectValue"
                        :key="index"
                        :label="val.label"
                        :value="val.value"
                      />
                    </el-select>
                  </el-form-item>
                </template>
                <template v-else-if="item.type === 'date-picker'">
                  <el-form-item :label="item.label" :prop="item.prop">
                    <el-date-picker
                      v-model="formData[item.prop]"
                      :type="item.dateType"
                      :range-separator="item.rangeSeparator"
                      :start-placeholder="item.startPlaceholder"
                      :end-placeholder="item.endPlaceholder"
                    />
                  </el-form-item>
                </template>
                <template v-else-if="item.type === 'radio'">
                  <el-form-item :label="item.label" :prop="item.prop">
                    <el-radio-group v-model="formData[item.prop]">
                      <el-radio
                        :label="item.radioList[0].value"
                        :size="item.size"
                      >
                        {{ item.radioList[0].label }}</el-radio
                      >
                      <el-radio
                        :label="item.radioList[1].value"
                        :size="item.size"
                        >{{ item.radioList[1].label }}</el-radio
                      >
                    </el-radio-group>
                  </el-form-item>
                </template>
                <template v-else-if="item.type === 'createby'">
                  <el-form-item
                    :label="isNew_ ? item.newLabel : item.editLabel"
                    :prop="isNew_ ? item.newProp : item.editProp"
                  >
                    <el-input
                      v-if="isNew_"
                      v-model="formData[item.newProp]"
                      :placeholder="item.newPlaceholder"
                    />
                    <el-input
                      v-else
                      v-model="formData[item.editProp]"
                      :placeholder="item.editPlaceholder"
                    />
                  </el-form-item>
                </template>
                <template v-else-if="item.type === 'custom'">
                  <el-form-item :label="item.label" :prop="item.prop">
                    <template #default>
                      <slot :name="item.slotName" :formData="formData"></slot>
                    </template>
                  </el-form-item>
                </template>
                <template v-else>
                  <el-form-item :label="item.label" :prop="item.prop">
                    <el-input
                      v-model="formData[item.prop]"
                      :placeholder="item.placeholder"
                    ></el-input>
                  </el-form-item>
                </template>
              </el-col>
            </template>
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

import { ElMessage } from 'element-plus'
export interface IModalProps {
  modalConfig: {
    pageName: string
    header?: {
      newTitle: string
      editTitle: string
    }
    formItems: any[]
  }
}

const props = defineProps<IModalProps>()

const dialogVisible = ref(false)
const initialData: any = {}
for (const item of props.modalConfig.formItems) {
  initialData[item.prop] = item.initialVal ?? ''
}
const isNew_ = ref(true)
const formData = reactive<any>(initialData)
const referEmit = defineEmits(['referMethod'])

//获取roles/departments
function setModalVisibel(isNew: boolean = true, itemData?: any) {
  isNew_.value = isNew
  if (!isNew && itemData) {
    for (const key in formData) {
      formData[key] = itemData[key]
    }
  } else {
    for (const key in formData) {
      const item = props.modalConfig.formItems.find((item) => item.prop === key)
      formData[key] = item ? item.initialVal : ''
    }
  }
  dialogVisible.value = true
}

//点击确定的逻辑
const systemStore = useSystemStore()
function handelConFirm() {
  formData.createTime = ''
  formData.updateTime = ''
  let newData: any = {}
  if (props.modalConfig.pageName === 'user') {
    newData['user'] = formData
    newData['roleIds'] = formData.roleIds
    newData['postIds'] = formData.postIds
  } else {
    newData = formData
  }
  systemStore
    .addOrSavePagelistAction(props.modalConfig.pageName, newData)
    .then((res) => {
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
