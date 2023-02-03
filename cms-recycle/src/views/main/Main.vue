<template>
  <div>
    <El-button @click="exitHandle" type="primary">退出登录</El-button>
    <br />
    <El-button @click="getDepartment" type="primary">测试</El-button>
    <br />
    <ol>
      <p>page----{{ ress?.page }}</p>
      <p>pageSize----{{ ress?.pageSize }}</p>
      <p>totalCount----{{ ress?.totalCount }}</p>
      <p>totalPages----{{ ress?.totalPages }}</p>
      <p>lastPage----{{ ress?.lastPage }}</p>
      <p>nextPage----{{ ress?.nextPage }}</p>
      <li v-for="(name, index) in ress?.list" :key="index">
        {{ name }}
      </li>
    </ol>
  </div>
</template>

<script setup lang="ts">
import { CACHETOKEN } from '@/global/cache-constants'
import router from '@/router'

import { getDepartmentPageRequest } from '@/service/main/departMent'
import { localCache } from '@/utils/localCache'
import { ref } from 'vue'
const ress = ref()
const exitHandle = () => {
  localCache.removeCache(CACHETOKEN)
  router.push('/login')
}
const getDepartment = () => {
  getDepartmentPageRequest({ pageNum: 1, pageSize: 3 }).then((res) => {
    if (res.code === 0) {
      ress.value = res.data
    }
  })
}
</script>
<style scoped></style>
