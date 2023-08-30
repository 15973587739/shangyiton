<template>
  <div class="app-container">
    <!-- 公共头 -->
    <myheaders/>
    <div id="main">
      <div class="main-container">
        <el-scrollbar class='page-component__scroll'>
          <!-- 内容区域 -->
          <nuxt/>
        </el-scrollbar>
      </div>
    </div>
    <!-- 公共底 -->
    <myfooter/>
  </div>
</template>
<script>
import '~/assets/css/app.css'
import '~/assets/css/chunk.css'
import '~/assets/css/iconfont.css'
import '~/assets/css/main.css'
import myheaders from "./myheaders";
import myfooter from "./myfooter";
export default {
  components:{myheaders, myfooter}
}
</script>
