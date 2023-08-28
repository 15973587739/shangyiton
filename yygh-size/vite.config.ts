import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
//引入node提供的内置模块path；可以获取绝对路径
//npm i @types/node -- save-dev path爆红需要安装此依赖
import path from 'path';
export default defineConfig({
  plugins: [vue()],
  resolve:{
    alias:{
      "@":path.resolve(__dirname,'src')
    }
  }
})
