<template>
  <div class="modal">
    <el-dialog
      v-model="dialogVisible"
      :title="
        isNew_
          ? modalConfig.header?.newTitle ?? '新建数据'
          : modalConfig.header?.editTitle ?? '编辑数据'
      "
      width="42%"
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
                    <el-radio-group
                      @change="radioChange(item, formData[item.prop])"
                      v-model="formData[item.prop]"
                      v-for="(item1, index) in item.radioList"
                      :key="item1.value"
                    >
                      <el-radio
                        :label="item.radioList[index].value"
                        :size="item.size"
                        style="margin-right: 10px"
                      >
                        {{ item.radioList[index].label }}</el-radio
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
                <template v-else-if="item.type === 'input-num'">
                  <el-form-item :label="item.label" :prop="item.prop">
                    <el-input-number
                      initialVal="item.initialVal"
                      v-model="formData[item.prop]"
                      :min="0"
                    />
                  </el-form-item>
                </template>
                <template v-else>
                  <el-form-item
                    v-show="item.prop === 'code' ? true : FShow"
                    :label="item.label"
                    :prop="item.prop"
                  >
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
import { nextTick, reactive, ref } from 'vue'
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
  otherInfo?: any
}

const props = defineProps<IModalProps>()
const FShow = ref(true)
const dialogVisible = ref(false)
const initialData: any = {}
//初始值赋值
for (const item of props.modalConfig.formItems) {
  initialData[item.prop] = item.initialVal ?? ''
}
const isNew_ = ref(false)
const formData = reactive<any>(initialData)
const referEmit = defineEmits(['referMethod'])

//获取roles/departments 判断新建/编辑
function setModalVisibel(isNew: boolean = true, itemData?: any) {
  isNew_.value = isNew
  if (!isNew_.value && itemData) {
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

  //区分一下请求的页面 构造请求
  if (props.modalConfig.pageName === 'user') {
    newData['user'] = formData
    newData['roleIds'] = formData.roleIds
    newData['postIds'] = formData.postIds
    newData['deptIds'] = props.otherInfo.deptIds
  } else if (props.modalConfig.pageName === 'role') {
    newData['sysRole'] = formData
    newData['menuIds'] = props.otherInfo.menuIds
    newData['deptIds'] = props.otherInfo.deptIds
  } else if (props.modalConfig.pageName === 'menu') {
    newData = { ...formData, parent_id: props.otherInfo.menuIds[0] }
  } else {
    newData = formData
  }

  //发送请求
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

function radioChange(item: any, value: any) {
  if (item.prop === 'menu_type') {
    switch (value) {
      case 'F':
        FShow.value = false
        break
      default:
        FShow.value = true
    }
  }
}
//暴露的属性和方法
defineExpose({ setModalVisibel })
</script>

<style scoped lang="less">
.form {
  padding: 0 20px;
}
</style>
