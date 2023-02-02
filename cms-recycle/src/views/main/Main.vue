<template>
  <div><El-button @click="exitHandle" type="primary">退出登录</El-button></div>
</template>

<script setup lang="ts">
import { CACHETOKEN } from '@/global/cache-constants'
import router from '@/router'
import { localCache } from '@/utils/localCache'

const exitHandle = () => {
  localCache.removeCache(CACHETOKEN)
  router.push('/login')
}
</script>
<style scoped></style>
