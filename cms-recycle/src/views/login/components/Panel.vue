<template>
  <slot name="img">
    <img
      :class="`${
        isLogin === 'login'
          ? 'login-img images '
          : 'register-img images add-class-register-img'
      }`"
      :src="`${isLogin === 'login' ? LOGIN_IMG : REGISTER_IMG}`"
      :alt="`${isLogin === 'login' ? '登录' : '注册'}`"
    />
  </slot>

  <slot name="login">
    <div class="login-wrapper">
      <h2 class="login-title">{{ LOGIN_TITLE }}</h2>

      <div
        class="login-content"
        :style="`${isLogin === 'login' ? `display:block` : `display:none`}`"
      >
        <PanLogin @change-pan="getPan"></PanLogin>
      </div>

      <div
        class="login-register"
        :style="`${isLogin === 'login' ? `display:none` : `display:block`}`"
      >
        <PanReg></PanReg>
      </div>
    </div>
  </slot>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { LOGIN_IMG, REGISTER_IMG } from '@/global/constant'
import PanLogin from './Pan-login.vue'
import PanReg from './Pan-reg.vue'
import { LOGIN_TITLE } from '@/global/constant'

/**控制登录/注册界面隐藏/显示  绑定初始值*/
const isLogin = ref('login')
const getPan = (value: string) => {
  isLogin.value = value
}
</script>

<style scoped></style>
