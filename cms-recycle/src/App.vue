<script setup lang="ts">
import { onMounted } from 'vue'
import zhCn from 'element-plus/dist/locale/zh-cn.mjs'
onMounted(() => {
  document.body.style.setProperty('--el-menu-text-color', '#b7bdc3')
  document.body.style.setProperty('--el-menu-active-text-color', '#fff')
  document.body.style.setProperty('--el-menu-background-color', '#001529')
})
</script>

<template>
  <div class="app">
    <el-config-provider :locale="zhCn">
      <router-view></router-view>
    </el-config-provider>
  </div>
</template>

<style scoped>
.app {
  width: 100vw;
  height: 100vh;
}
</style>
