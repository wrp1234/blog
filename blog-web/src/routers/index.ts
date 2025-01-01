import { createWebHistory, createRouter } from 'vue-router'

import ArticleList from '../views/ArticleList.vue'
import ArticleDetail from '../views/ArticleDetail.vue'
import Home from '../views/Home.vue'

const routes = [
    { path: '/article/list', component: ArticleList },
    { path: '/article/detail', component: ArticleDetail },
    { path: '/', component: Home },
]

export const router = createRouter({
    history: createWebHistory(),
    routes,
})