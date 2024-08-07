<template>
  <div class="container-fluid">
    <div class="row">
      <AsideBar />
      <div class="col-md-10">
        <router-view></router-view>
      </div>
    </div>
  </div>
</template>

<script>
import AsideBar from "@/components/AsideBar.vue";

export default {
  name:"App",
  components: {AsideBar}
}

</script>

<style scoped>

</style>