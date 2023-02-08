<template>
  <div class="layout">
    <el-container>
      <el-aside :width="isOpen ? '55px' : '200px'"
        ><Aside :is-fold="isOpen"
      /></el-aside>
      <el-container>
        <el-header height="50px"
          ><Header @fold-change="handleFoldChange"
        /></el-header>
        <el-main><Main /></el-main>
      </el-container>
    </el-container>
  </div>
</template>
<script setup lang="ts">
import Header from '@/components/layout/header/index.vue'
import Aside from '@/components/layout/aside/index.vue'
import Main from '@/views/main/Main.vue'
import { ref } from 'vue'
const isOpen = ref(false)
const handleFoldChange = (isFold: boolean) => {
  isOpen.value = isFold
}
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
