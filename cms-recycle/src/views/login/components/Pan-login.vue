<template>
  <el-form ref="formRef" :model="loginVal" :rules="rules" status-icon>
    <el-form-item prop="account">
      <el-input
        class="login-input"
        v-model="loginVal.account"
        placeholder="账号"
        :prefix-icon="User"
      />
    </el-form-item>

    <el-form-item prop="password">
      <el-input
        v-model="loginVal.password"
        type="password"
        show-password
        class="login-input"
        placeholder="密码"
        :prefix-icon="Lock"
      />
    </el-form-item>

    <div class="login-forget">
      <el-checkbox class="forget-check" v-model="isRemPwd" label="记住密码" />

      <el-link @click="changeL" type="primary">注册账号</el-link>
    </div>

    <elButton style="width: 100%" size="large" round @click="login"
      >登录</elButton
    >
  </el-form>
</template>

<script setup lang="ts">
import { User, Lock } from '@element-plus/icons-vue'
import { ref, reactive } from 'vue'
import { type FormRules, type ElForm, ElMessage } from 'element-plus'
import { loginRequest } from '@/service/login/login'
import useLoginStore from '@/store/login/login'
const isLogin = ref('login')
const emit = defineEmits(['changePan'])
const changeL = () => {
  if (isLogin.value === 'register') {
    document.querySelector('.content')?.classList.remove('add-class-content')
    document
      .querySelector('.register-img')
      ?.classList.remove('add-class-register-img')
    const wrapper = document.querySelector('.login-wrapper') as HTMLElement
    const content = document.querySelector('.content') as HTMLElement
    wrapper.style.height = '70vh'
    content.style.height = '85vh'
    isLogin.value = 'login'
    emit('changePan', 'login')
  } else {
    document.querySelector('.content')?.classList.add('add-class-content')
    document
      .querySelector('.register-img')
      ?.classList.add('add-class-register-img')
    const wrapper = document.querySelector('.login-wrapper') as HTMLElement
    wrapper.style.height = '80vh'
    const content = document.querySelector('.content') as HTMLElement
    content.style.height = '90vh'
    isLogin.value = 'register'
    emit('changePan', 'register')
  }
}
/**登录账号、密码 绑定值*/
const loginVal = reactive({
  account: '',
  password: ''
})
/**基础表单验证 */
const rules: FormRules = {
  account: [
    { required: true, message: '必须输入账号~', trigger: 'blur' },
    {
      pattern: /^[a-z0-9]{5,12}$/,
      message: '必须5-12位数字或字母组成~',
      trigger: 'blur'
    }
  ],
  password: [
    { required: true, message: '必须输入密码~', trigger: 'blur' },
    {
      pattern: /^[a-z0-9]{6,12}$/,
      message: '必须6-12位数字或字母组成~',
      trigger: 'blur'
    }
  ]
}
/**checkbox 绑定初始状态*/
const isRemPwd = ref(false)

/**登录 method*/
const formRef = ref<InstanceType<typeof ElForm>>()
const loginStore = useLoginStore()
const login = () => {
  formRef.value?.validate((validate) => {
    if (validate) {
      const userAccount = loginVal.account
      const userPassword = loginVal.password
      loginStore.loginAction({ userAccount, userPassword })
    } else {
      ElMessage.error('请输入正确的格式~')
    }
  })
}
</script>
<style scoped></style>
