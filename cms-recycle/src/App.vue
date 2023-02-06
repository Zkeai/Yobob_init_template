<script setup lang="ts">
import { onMounted } from 'vue'

onMounted(() => {
  document.body.style.setProperty('--el-menu-text-color', '#b7bdc3')
  document.body.style.setProperty('--el-menu-active-text-color', '#fff')
  document.body.style.setProperty('--el-menu-background-color', '#001529')
})
</script>

<template>
  <div class="app">
    <router-view></router-view>
  </div>
</template>

<style scoped>
.app {
  width: 100vw;
  height: 100vh;
}
</style>
