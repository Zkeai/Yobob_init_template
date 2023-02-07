<template>
  <div class="header-info">
    <div class="operation">
      <span
        ><el-icon><Search /></el-icon
      ></span>
      <span
        ><el-icon><Message /></el-icon
      ></span>
      <span>
        <span class="dot"></span>
        <el-icon><ChatDotRound /></el-icon
      ></span>
    </div>
    <div class="info-s">
      <el-dropdown>
        <span
          class="user-info"
          style="display: flex; align-items: center; cursor: pointer"
        >
          <el-avatar
            :size="30"
            src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
          />
          <span class="name" style="margin-left: 5px">lemon</span>
        </span>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item>
              <el-icon><User /></el-icon>
              <span>个人信息</span>
            </el-dropdown-item>
            <el-dropdown-item divided>
              <el-icon><CircleClose /></el-icon>
              <span @click="handleExit">退出登录</span>
            </el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
  </div>
</template>

<script setup lang="ts">
import { CACHETOKEN, MENULIST, USERINFO } from '@/global/cache-constants'
import { localCache } from '@/utils/localCache'
import { useRouter } from 'vue-router'
const router = useRouter()
const handleExit = () => {
  localCache.removeCache(CACHETOKEN)
  localCache.removeCache(MENULIST)
  localCache.removeCache(USERINFO)

  router.push('/login')
}
</script>
<style scoped lang="less">
.header-info {
  display: flex;
  align-items: center;

  .operation {
    display: inline-flex;
    margin-right: 20px;

    span {
      position: relative;
      display: flex;
      align-items: center;
      justify-content: center;
      width: 40px;
      height: 35px;

      &:hover {
        background-color: #f2f2f2;
      }

      i {
        font-size: 20px;
      }

      .dot {
        position: absolute;
        top: 3px;
        right: 3px;
        z-index: 10;
        width: 6px;
        height: 6px;
        background: red;
        border-radius: 100%;
      }
    }
  }
  .info {
    :global(.el-dropdown-menu__item) {
      line-height: 30px !important;
      padding: 6px 22px;
    }
  }
}
</style>
