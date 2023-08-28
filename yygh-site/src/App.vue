<template>
  <div class="container">
    <!-- 顶部全局组件 -->
    <HospitalTop/>
    <!-- 展示路由组件的区域 -->
    <!-- 需要提前安装路由插件npm i vue-router -->
  <div class="content">
    <!-- 展示路由组件区域 -->
    <router-view></router-view>
  </div>
  <HospitalButtom/>
  </div>
</template>
<script setup lang="ts">

</script>
<style scoped lang="scss">
.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  .content {
    margin-top: 70px;
    width: 1200px;
    min-height: 700px;
  }
}</style>