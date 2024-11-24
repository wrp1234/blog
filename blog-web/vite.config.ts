import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
// yarn add @types/node
import {resolve} from 'path'

// https://vite.dev/config/
export default defineConfig({
  plugins: [vue()],

  resolve: {
    // 导入文件夹别名
    alias: {
      '@': resolve(__dirname, './src'),
      views: resolve(__dirname, './src/views'),
      components: resolve(__dirname, './src/components'),
      utils: resolve(__dirname, './src/utils'),
      assets: resolve(__dirname, "./src/assets"),
      stores: resolve(__dirname, "./src/store"),
    },
  }
})
