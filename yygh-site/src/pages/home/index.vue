<template>
    <!-- 首页轮播图 -->
    <Carousel/>
    <!-- 首页搜索 -->
    <Search/>
    <!-- //医院等级 -->
    <el-row gutter="20">
        <el-col :span="20">
            <Level/>
             <!--地区 -->
            <Region/>
            <!-- 展示医院的结构 -->
            <div class="hospital">
                <Card class="item" v-for="item in 10" :key="item" />
                
            </div>
            <!-- 分页器 -->
            <el-pagination
                    v-model:current-page="pageNo"
                    v-model:page-size="pageSize"
                    :page-sizes="[10, 20, 30, 80]"
                    :background="true"
                    layout="prev,pager,next, jumper,->,sizes,total"
                    :total="13"
            />
        </el-col>
        <el-col :span="4">456</el-col>
    </el-row>

</template>

<script setup lang="ts">
    // 引入首页轮播图
    import Carousel from './carousel/index.vue'
    //引入搜索组件
    import Search from './search/index.vue'
    //引入医院等级
    import Level from './level/index.vue'
    //引入地区
    import Region from './region/index.vue'
    //引用医院信息
    import Card from './card/index.vue'

    //分页器所需要的数据
    import {ref} from 'vue'
    //分页器当前页码
    let pageNo =ref<number>(1);
    //一页展示几条数据
    let pageSize = ref<number>(10);



</script>

<style scoped lang="scss">
  .hospital {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;
  .item {
    width: 48%;
    margin: 10px 0px;
  }
}
</style>
