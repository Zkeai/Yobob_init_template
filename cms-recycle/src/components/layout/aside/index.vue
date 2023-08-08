<template>
  <div class="menu">
    <div class="aside-logo">
      <img class="aside-image" src="@/assets/img/logo.svg" alt="" />
      <h2 class="aside-title">Yo管理系统</h2>
    </div>
    <div class="aside-menu">
      <el-menu :collapse="isFold" :default-active="defaultActive">
        <el-menu-item index="/index" @click="handleIndexClick">
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
          <el-menu-item
            :index="item.path"
            :key="item.path"
            v-for="item in menu.children"
            @click="handleItemClick(item.path)"
          >
            <span>{{ item.name }}</span>
          </el-menu-item>
        </el-sub-menu>
      </el-menu>
    </div>
  </div>
</template>

<script setup lang="ts">
import { useRoute, useRouter } from 'vue-router'
import { mapPathToMenu } from '@/utils/map-menus'
import { computed } from 'vue'
import { localCache } from '@/utils/localCache'
import { MENULIST } from '@/global/cache-constants'
defineProps({
  isFold: {
    type: Boolean,
    default: false
  }
})
//获取动态菜单
const menuList = JSON.parse(localCache.getCache(MENULIST) ?? '')

//监听item点击
const handleIndexClick = () => {
  router.push('/main/index')
}
const router = useRouter()
const handleItemClick = (item: string) => {
  router.push(item)
}
//ElMenu 默认菜单
//不用首页
const route = useRoute()

const defaultActive = computed(() => {
  const pathMenu = mapPathToMenu(route.path, menuList)
  return pathMenu.path
})
//用首页
</script>
<style scoped lang="less">
.menu {
  height: 100%;
  background-color: #001529;
}
.aside-logo {
  display: flex;
  height: 19px;
  padding: 10px 10px 8px 23px;
  flex-direction: row;
  justify-content: flex-start;
  overflow: hidden;
  .aside-image {
    height: 100%;
    margin: 5px 0 0 0;
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
