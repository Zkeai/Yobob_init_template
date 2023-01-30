<template>
  <div>
    <slot name="login" text="qqq">
      <div class="box">
        <div class="content" ref="contentRef">
          <Panel>
            <template #img> </template>

            <template #login> </template>
          </Panel>
        </div>
      </div>
    </slot>
  </div>
</template>

<script setup lang="ts">
import './index.less'
import Panel from './components/Panel.vue'
</script>

<style scoped></style>
