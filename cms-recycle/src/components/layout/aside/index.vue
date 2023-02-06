<template>
  <div class="menu">
    <div class="aside-logo">
      <img class="aside-image" src="@/assets/img/logo.svg" alt="" />
      <h2 class="aside-title">hupi管理系统</h2>
    </div>
    <div class="aside-menu">
      <el-menu :default-active="'/index'">
        <el-menu-item index="/index">
          <el-icon><home-filled /></el-icon>
          <span>首页</span>
        </el-menu-item>

        <el-sub-menu
          :key="menu.path"
          :index="menu.path"
          v-for="menu in menuList"
        >
          <template #title>
            <el-icon><component :is="menu.icon" /></el-icon>
            <span>{{ menu.name }}</span>
          </template>
          <el-menu-item :key="item.path" v-for="item in menu.children">
            <svg-icon :icon="item.icon" />
            <span>{{ item.name }}</span>
          </el-menu-item>
        </el-sub-menu>
      </el-menu>
    </div>
  </div>
</template>

<script setup lang="ts">
import useLoginStore from '@/store/login/login'

const loginStore = useLoginStore()
const menuList = loginStore.GET_MENULIST
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
