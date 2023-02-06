<template>
  <div class="menu">
    <div class="aside-logo">
      <img class="aside-image" src="@/assets/img/logo.svg" alt="" />
      <h2 class="aside-title">hupi管理系统</h2>
    </div>
    <div class="aside-menu">
      <el-menu>
        <el-sub-menu>
          <template #title>
            <el-icon><Monitor /></el-icon>
            <span>系统总览</span>
          </template>
          <el-menu-item>核心技术</el-menu-item>
          <el-menu-item>商品统计</el-menu-item>
        </el-sub-menu>

        <el-sub-menu>
          <template #title>
            <el-icon><Setting /></el-icon>
            <span>系统管理</span>
          </template>
          <el-menu-item>用户管理</el-menu-item>
          <el-menu-item>部门管理</el-menu-item>
          <el-menu-item>菜单管理</el-menu-item>
          <el-menu-item>角色管理</el-menu-item>
        </el-sub-menu>
      </el-menu>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive } from 'vue'
</script>
<style scoped lang="less">
.menu {
  height: 100%;
  background-color: #001529;
}
.aside-logo {
  display: flex;
  height: 28px;
  padding: 12px 10px 8px 10px;
  flex-direction: row;
  justify-content: flex-start;
  overflow: hidden;
  .aside-img {
    height: 100%;
    margin: 0 10px;
  }
  .aside-title {
    padding: 4px 4px 4px 16px;
    color: white;
  }
}

.el-menu {
  border-right: none;
  user-select: none;
  background-color: #001529;
}
.el-sub-menu {
  .el-menu-item {
    padding-left: 50px !important ;
    background-color: #0c2135;
  }
  .el-menu-item:hover {
    color: #fff;
  }
  el-menu-item.is-active {
    background-color: #0a60bd;
  }
}
</style>
