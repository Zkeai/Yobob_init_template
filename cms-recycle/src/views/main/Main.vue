<template>
<<<<<<< HEAD
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
=======
  <div><El-button @click="exitHandle" type="primary">退出登录</El-button></div>
>>>>>>> 587c8a61070bff41b665e566ce3fbfc8578fe7e5
</template>

<script setup lang="ts">
import { CACHETOKEN } from '@/global/cache-constants'
import router from '@/router'
<<<<<<< HEAD
import { getDepartmentPageRequest } from '@/service/main/departMent'
import { localCache } from '@/utils/localCache'
import { ref } from 'vue'
const ress = ref()
=======
import { localCache } from '@/utils/localCache'

>>>>>>> 587c8a61070bff41b665e566ce3fbfc8578fe7e5
const exitHandle = () => {
  localCache.removeCache(CACHETOKEN)
  router.push('/login')
}
<<<<<<< HEAD
const getDepartment = () => {
  getDepartmentPageRequest({ pageNum: 1, pageSize: 3 }).then((res) => {
    if (res.code === 0) {
      ress.value = res.data
    }
  })
}
=======
>>>>>>> 587c8a61070bff41b665e566ce3fbfc8578fe7e5
</script>
<style scoped></style>
