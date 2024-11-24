<script setup lang="ts">
import Nav from './components/Nav.vue'
import Register from './components/Register.vue'
import Login from './components/Login.vue'
</script>

<template>
  <div class="common-layout">
    <el-container>
      <el-header>
        <Nav />
      </el-header>
      <el-main>
        <RouterView />
      </el-main>
    </el-container>
  </div>
  <Register />
  <Login />
</template>

<style scoped></style>
