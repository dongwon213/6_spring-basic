<template>
  <header v-if="mainContent === 'main'" class="bg-dark text-white p-3 mb-4 header_wrapper">
    <div class="d-flex justify-content-between">
      관리자 페이지
    </div>
  </header>
  <header v-else class="bg-dark text-white p-3 mb-4 header_wrapper">
    <div class="d-flex justify-content-between">
      <ul class="nav">
        <li class="nav-item">
          <router-link class="nav-link text-white" :to="mainContent === 'banner' ? '/banner' : '/notice'">
            List
          </router-link>
        </li>
        <li class="nav-item">
          <router-link class="nav-link text-white" :to="mainContent === 'banner' ? '/bannerAdd' : '/noticeAdd'">
            Add
          </router-link>
        </li>
      </ul>
    </div>
  </header>
</template>

<script>
export default {
  name: 'BannerHeader',
  props: {
    mainContent: {
      type: String,
      required: true
    }
  },

}
</script>

<style scoped>

</style>
