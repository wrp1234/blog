<script setup lang="ts">
import Nav from './components/Nav.vue'
import Register from './components/Register.vue'
import Login from './components/Login.vue'
</script>

<template>
  <Nav />
  <div class="main-container">
    <div class="main-left"></div>
    <div class="main-center">

    </div>
    <div class="main-right"></div>
  </div>
  <Register />
  <Login />
</template>

<style scoped>
.main-container {
  height: 100vh;
  display: grid;
  grid-template-columns: 1fr 2fr 1fr;
  background-color: rgb(233, 232, 232);

}


.main-center {
  background-color: white;
}
</style>
