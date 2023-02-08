<template>
  <div class="breadcrumb">
    <el-breadcrumb separator-icon="CaretRight">
      <el-breadcrumb-item to="/main/index">首页</el-breadcrumb-item>
      <template v-for="item in breadcrumbs" :key="item.name">
        <el-breadcrumb-item :to="item.path">{{ item.name }}</el-breadcrumb-item>
      </template>
    </el-breadcrumb>
  </div>
</template>

<script setup lang="ts">
import { mapPathToBreadcrumbs } from '@/utils/map-menus'
import useLoginStore from '@/store/login/login'
import { useRoute } from 'vue-router'
import { computed } from 'vue'

const route = useRoute()
const menuList = useLoginStore().menuList
const breadcrumbs = computed(() => {
  return mapPathToBreadcrumbs(route.path, menuList)
})
</script>
<style scoped lang="less"></style>
