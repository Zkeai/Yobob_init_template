<template>
  <div class="header">
    <div class="icon" @click="headerIconClick">
      <el-icon size="22px"
        ><component :is="isFold ? 'Expand' : 'Fold'"></component
      ></el-icon>
    </div>
    <div class="content">
      <div class="breadcrumb"><HeaderCrumb /></div>
      <div class="info">
        <HeaderInfo />
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import HeaderInfo from './components/info.vue'
import HeaderCrumb from './components/crumb.vue'
const isFold = ref(false)

const emit = defineEmits(['foldChange'])
const headerIconClick = () => {
  //1.内部修改状态
  isFold.value = !isFold.value
  //2.通过defineEmits 传给父组件
  emit('foldChange', isFold.value)
}
</script>
<style scoped lang="less">
.header {
  display: flex;
  align-items: center;
  flex: 1;
  height: 100%;

  .icon {
    display: flex;
    align-items: center;
    cursor: pointer;
  }

  .content {
    display: flex;
    justify-content: space-between;
    align-items: center;
    flex: 1;
    padding: 0 18px;
  }
}
</style>
