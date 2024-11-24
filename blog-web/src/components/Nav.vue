<!--
 * 导航栏
 * @author: wrp
 * @since: 2024年11月23日 12:01:54
-->
<template>
    <div class="nav-container">
        <img src="../assets/vue.svg" alt="">
        <ul class="nav-list">
            <li><router-link to="/">博客</router-link></li>
            <li><router-link to="/">项目</router-link></li>
            <li><router-link to="/">简历</router-link></li>
        </ul>

        <div class="operation">
            <el-button type="primary" @click="navStore.changeLoginDialogStatus">登录</el-button>
            <el-button type="primary" @click="navStore.changeRegisterDialogStatus">注册</el-button>
        </div>

    </div>
</template>

<script setup lang="ts">
import { useNavStore } from '../stores/useNavStore'

const navStore = useNavStore()

</script>

<style scoped>
.nav-container {
    position: fixed;
    top: 0;
    height: 50px;
    width: 100%;
    background-color: green;
    margin: 0 auto;
}

.nav-list {
    margin: 0;
    display: inline-block;
    font-size: 36px;
}

.nav-list li {
    display: inline-block;
    width: 100px;
    margin-left: 20px;
}

.operation {
    display: inline;
    height: 50px;
    position: fixed;
    right: 30px;
}
</style>
