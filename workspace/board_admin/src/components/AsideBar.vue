<template>
  <aside class="col-md-2 bg-light p-3">
    <router-link class="router-link text-center mb-4" to="/">
      <h1>호근 커뮤니티</h1>
    </router-link>
    <ul class="nav flex-column">
      <li class="nav-item">
        <router-link class="nav-link" to="/banner">배너관리</router-link>
      </li>
      <li class="nav-item">
        <router-link class="nav-link" to="/notice">공지사항 관리</router-link>
      </li>
      <li class="nav-item">
        <router-link class="nav-link" to="/user">유저관리</router-link>
      </li>
    </ul>
  </aside>
</template>

<style scoped>

</style>
<script setup lang="ts">
</script>