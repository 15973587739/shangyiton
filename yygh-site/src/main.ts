//vue3框架提供的方法，可用于创建应用实例
import { createApp } from 'vue'
//引入清除样式 需要安装npm i sass
import '@/style/reset.scss'
//引入根组件App
import App from '@/App.vue'
//引入全局组件--顶部、底部都是全局组件
import HospitalTop from '@/components/hospital_top/index.vue'
import HospitalButtom from '@/components/hospital_bottom/index.vue'
//引入vue-router核心插件并且安装
import router from '@/router'
//引入element-plus插件 需要提前安装npm i vue-router
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
//国际化文件
//@ts-ignore
import zhCn from 'element-plus/dist/locale/zh-cn.mjs'

//利用createapp方法创建实例，并且将应用挂在到挂载点上 
const app =createApp(App);
app.component('HospitalTop',HospitalTop)
app.component('HospitalButtom',HospitalButtom)
// 安装国际化插件
app.use(ElementPlus,{
    locale:zhCn,
})
//安装router
app.use(router);
//安装element-plus
app.use(ElementPlus)
//挂载
app.mount('#app');

// createApp(App).mount('#app')
