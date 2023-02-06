<template>
  <el-form ref="formRef" :model="loginVal" :rules="rules" status-icon>
    <el-form-item prop="userAccount">
      <el-input
        class="login-input"
        v-model="loginVal.userAccount"
        placeholder="账号"
        :prefix-icon="User"
      />
    </el-form-item>

    <el-form-item prop="userPassword">
      <el-input
        v-model="loginVal.userPassword"
        type="password"
        show-password
        class="login-input"
        placeholder="密码"
        :prefix-icon="Lock"
      />
    </el-form-item>

    <div class="login-forget">
      <el-checkbox class="forget-check" v-model="isRemVal" label="记住密码" />

      <el-link :underline="false" @click="changeL" type="primary"
        >注册账号</el-link
      >
    </div>

    <elButton style="width: 100%" size="large" round @click="login"
      >登录</elButton
    >
  </el-form>
</template>

<script setup lang="ts">
import { User, Lock } from '@element-plus/icons-vue'
import { ref, reactive, watch } from 'vue'
import { type FormRules, type ElForm, ElMessage } from 'element-plus'
import useLoginStore from '@/store/login/login'
import { localCache } from '@/utils/localCache'
const emit = defineEmits(['changePan'])

const CACHEUSERACCOUNT: string = 'userAccount'
const CACHEUSERPASSWORD: string = 'userPassword'
const ISREMVAL: string = 'isRemVal'
/**切换注册页面 */
const changeL = () => {
  document.querySelector('.content')?.classList.add('add-class-content')
  document
    .querySelector('.register-img')
    ?.classList.add('add-class-register-img')
  const wrapper = document.querySelector('.login-wrapper') as HTMLElement
  wrapper.style.height = '80vh'
  const content = document.querySelector('.content') as HTMLElement
  content.style.height = '90vh'
  emit('changePan', 'register')
}

/**登录账号、密码 绑定值*/
const loginVal = reactive({
  userAccount: localCache.getCache(CACHEUSERACCOUNT) ?? '',
  userPassword: localCache.getCache(CACHEUSERPASSWORD) ?? ''
})

/**基础表单验证 */
const rules: FormRules = {
  userAccount: [
    { required: true, message: '必须输入账号~', trigger: 'blur' },
    {
      pattern: /^[a-z0-9]{5,12}$/,
      message: '必须5-12位数字或字母组成~',
      trigger: 'blur'
    }
  ],
  userPassword: [
    { required: true, message: '必须输入密码~', trigger: 'blur' },
    {
      pattern: /^[a-z0-9]{6,12}$/,
      message: '必须6-12位数字或字母组成~',
      trigger: 'blur'
    }
  ]
}

/**checkbox 记住密码绑定初始状态*/
const isRemVal = ref<boolean>(
  localCache.getCache(ISREMVAL) === 'true' ? true : false
)
watch(isRemVal, (newVal) => {
  if (newVal) {
    localCache.setCache(ISREMVAL, 'true')
  } else {
    localCache.setCache(ISREMVAL, 'false')
  }
})
/**登录 method*/
const formRef = ref<InstanceType<typeof ElForm>>()
const loginStore = useLoginStore()
const login = () => {
  formRef.value?.validate((validate) => {
    if (validate) {
      loginStore.loginAction(loginVal).then((res) => {
        if (res === 'success') {
          //是否记住密码
          if (isRemVal.value) {
            localCache.setCache(CACHEUSERACCOUNT, loginVal.userAccount)
            localCache.setCache(CACHEUSERPASSWORD, loginVal.userPassword)
          } else {
            localCache.removeCache(CACHEUSERACCOUNT)
            localCache.removeCache(CACHEUSERPASSWORD)
          }
        } else {
          ElMessage.error(res)
        }
      })
    } else {
      ElMessage.error('请输入正确的格式~')
    }
  })
}
</script>
<style scoped></style>
