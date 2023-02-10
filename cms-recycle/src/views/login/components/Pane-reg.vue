<template>
  <el-form ref="formRef" :model="regVal" :rules="rules" status-icon>
    已有账号?
    <el-link :underline="false" @click="gotoLogin" type="primary"
      >去登录</el-link
    >
    <el-form-item prop="account">
      <el-input
        v-model="regVal.account"
        class="login-input"
        placeholder="账号"
        :prefix-icon="User"
      />
    </el-form-item>
    <el-form-item prop="pwd">
      <el-input
        v-model="regVal.pwd"
        class="login-input"
        type="password"
        show-password
        placeholder="密码"
        :prefix-icon="Lock"
      />
    </el-form-item>
    <el-form-item prop="repwd">
      <el-input
        v-model="regVal.repwd"
        class="login-input"
        type="password"
        show-password
        placeholder="确认密码"
        :prefix-icon="Lock"
      />
    </el-form-item>
    <el-form-item>
      <elButton style="width: 100%" size="large" round @click="register"
        >注册</elButton
      >
    </el-form-item>
  </el-form>
</template>

<script setup lang="ts">
import { ref, reactive } from 'vue'
import { User, Lock } from '@element-plus/icons-vue'
import { type FormRules, type ElForm, ElMessage } from 'element-plus'
import type { InternalRuleItem } from 'async-validator'

import useLoginStore from '@/store/login/login'
const emit = defineEmits(['changePan'])

/**注册账号/密码/确认密码  绑定初始值*/
const regVal = reactive({
  account: '',
  pwd: '',
  repwd: ''
})

/**基础表单验证 */
var validatePwd = (rule: InternalRuleItem, value: string, callback: any) => {
  if (value === '') {
    callback(new Error('请再次输入密码'))
  } else if (value !== regVal.pwd) {
    callback(new Error('两次输入密码不一致!'))
  } else {
    callback()
  }
}
const rules: FormRules = {
  account: [
    { required: true, message: '必须输入账号~', trigger: 'blur' },
    {
      pattern: /^[a-z0-9]{5,12}$/,
      message: '必须5-12位数字或字母组成~',
      trigger: 'blur'
    }
  ],
  pwd: [
    { required: true, message: '必须输入密码~', trigger: 'blur' },
    {
      pattern: /^[a-z0-9]{6,12}$/,
      message: '必须6-12位数字或字母组成~',
      trigger: 'blur'
    }
  ],
  repwd: [
    { required: true, validator: validatePwd, trigger: 'blur' },
    {
      pattern: /^[a-z0-9]{6,12}$/,
      message: '必须6-12位数字或字母组成~',
      trigger: 'blur'
    }
  ]
}

/**注册 method */
const formRef = ref<InstanceType<typeof ElForm>>()
const loginStore = useLoginStore()
const register = () => {
  formRef.value?.validate((validate) => {
    if (validate) {
      const userAccount = regVal.account
      const userPassword = regVal.pwd
      const checkPassword = regVal.repwd
      loginStore
        .registerAction({
          userAccount,
          userPassword,
          checkPassword
        })
        .then((res) => {
          console.log(res)
          if (res === 'success') {
            ElMessage.success('注册成功')
            gotoLogin()
          } else {
            ElMessage.error(res)
          }
        })
    } else {
      ElMessage.error('请输入正确的格式~')
    }
  })
}

/**切换到登录页面 */
const gotoLogin = () => {
  document.querySelector('.content')?.classList.remove('add-class-content')
  document
    .querySelector('.register-img')
    ?.classList.remove('add-class-register-img')
  const wrapper = document.querySelector('.login-wrapper') as HTMLElement
  const content = document.querySelector('.content') as HTMLElement
  wrapper.style.height = '70vh'
  content.style.height = '85vh'
  emit('changePan', 'login')
}
</script>
<style scoped></style>
