<template>
  <div class="layout">
    <el-container>
      <el-aside width="200px"><Aside /></el-aside>
      <el-container>
        <el-header><Header /></el-header>
        <el-main><Tabs /></el-main>
      </el-container>
    </el-container>
  </div>
</template>
<script setup lang="ts">
import Header from '@/components/layout/header/index.vue'
import Aside from '@/components/layout/aside/index.vue'
import Tabs from '@/components/layout/tabs/index.vue'
</script>
<style scoped lang="less">
.layout {
  height: 100%;
  .el-container {
    height: 100%;
    .el-aside {
      overflow-x: hidden;
      overflow-y: auto;
      text-align: left;
      cursor: pointer;
      background-color: #001529;
      transition: width 0.3s linear;
      scrollbar-width: none; /* firefox*/
      -ms-overflow-style: none; /*IE 10+ */
      &::-webkit-scrollbar {
        display: none;
      }
    }

    .el-main {
      background-color: #f0f2f5;
    }
  }
}
</style>
