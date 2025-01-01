<!--
 * new page
 * @author: wrp
 * @since: 2024年12月23日 22:32:55
-->
<template>
    <div class="container">
        笔记列表
    </div>
</template>

<script setup lang="ts">
import httpUtils from '../utils/httpUtils';
import { onMounted, reactive } from 'vue';

const articleList = reactive({});


onMounted(async () => {
    const list = await httpUtils.post('/article/page');
    Object.assign(articleList, list)
})
</script>

<style scoped></style>
