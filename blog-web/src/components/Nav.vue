<!--
 * 导航栏
 * @author: wrp
 * @since: 2024年11月23日 12:01:54
-->
<template>
    <div class="nav-container">
        <div class="nav-left nav-item">
            <img height="50px" src="../assets/logo.svg" alt="">
        </div>
        <div class="nav-center nav-item">
            <div v-for="menu in menus"><router-link :to="menu.url">{{ menu.name }}</router-link></div>
        </div>

        <div class="nav-right nav-item">
            <el-button size="large" type="primary" @click="navStore.changeLoginDialogStatus">登录</el-button>
            <el-button size="large" type="primary" @click="navStore.changeRegisterDialogStatus">注册</el-button>
        </div>

    </div>
</template>

<script setup lang="ts">
import { useNavStore } from '../stores/useNavStore'
import { onMounted, reactive } from 'vue'
import httpUtils from '../utils/httpUtils';
import { Menu } from '../types'

const navStore = useNavStore()
const menus = reactive<Menu[]>([])

onMounted(async () => {
    // 查询菜单
    const menuData = await httpUtils.get('/menu');
    menuData.forEach((element: Menu) => {
        menus.push(element)
    });
})

</script>

<style scoped>
.nav-container {
    position: sticky;
    top: 0%;
    height: 50px;
    display: grid;
    grid-template-columns: 1fr 2fr 1fr;
    font-size: 22px;
    color: black;
    background-color: white;
    border-bottom: 1px solid black;
}

.nav-item {
    display: flex;
    /* 水平居中 */
    justify-content: center;
    /* 垂直居中 */
    align-items: center;
    text-align: center;
}

.nav-center>div {
    width: 100px;
}

.nav-center>div:hover {
    cursor: pointer;
}
</style>
