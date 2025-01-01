import { createApp } from 'vue'
import './style.css'
import { createPinia } from 'pinia'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import App from './App.vue'
import { router } from './routers'


createApp(App).use(ElementPlus).use(router).use(createPinia()).mount('#app')
