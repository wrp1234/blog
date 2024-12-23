<!--
 * 博客详情页面
 * @author: wrp
 * @since: 2024年11月26日 22:34:36
-->
<template>
    <div class="article-detail-container">
        <div class="article-title">{{ title }}</div>
        <div v-html="articleDetails" class="markdown-body"></div>
    </div>
</template>

<script setup lang="ts">
import { ref, shallowRef } from 'vue';
import { onMounted } from 'vue';
import { Marked } from 'marked';
import { markedHighlight } from "marked-highlight"
import httpUtils from '../utils/httpUtils';
import hljs from 'highlight.js'
//引入markdown样式
import 'highlight.js/styles/atom-one-dark.css'

const articleDetails = ref()
const title = ref()


const marked = new Marked(
    markedHighlight({
        langPrefix: 'hljs language-',
        highlight(code, lang) {
            const language = hljs.getLanguage(lang) ? lang : 'shell'
            return hljs.highlight(code, { language }).value
        }
    })
)



onMounted(async () => {
    const article = await httpUtils.get('/article/3');
    title.value = article.title
    articleDetails.value = marked.parse(article.content)
})
</script>

<style scoped>
.article-title {
    font-size: 48px;
    font-weight: bold;
    margin-bottom: 50px;
}

.article-detail-container {
    /* color: red; */
    width: 1400px;
}
</style>
