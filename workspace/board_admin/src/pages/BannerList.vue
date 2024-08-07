<template>
  <Header main-content="banner"/>
  <main>
    <section>
      <div class="row mb-4">
        <div class="col">
          <h1 class="page-heading text-dark fw-bold fs-3">배너 관리</h1>
        </div>
      </div>
      <div class="row mb-4">
        <div class="col d-flex justify-content-end gap-3">
          <div class="form-check">
            <label class="form-check-label" for="selectAll">
              <input class="form-check-input" type="checkbox" id="selectAll" :checked="allSelected" @change="toggleSelectAll">
              전체 선택
            </label>
          </div>
          <button @click="deleteSelectedBanners" class="btn btn-danger">삭제</button>
        </div>
      </div>
      <div class="row">
        <div class="col-12">
          <div class="card">
            <div class="card-body">
              <div class="d-flex flex-wrap justify-content-center">
                <div v-for="banner in paginatedBanners" :key="banner.bannerId" class="card-inter">
                  <input v-model="selectedBanners" type="checkbox" :value="banner.bannerId">
                  <div>
                    {{ banner.bannerTitle }}
                  </div>
                  <div style="width: 100%;">
                    <img :src="'http://localhost:8090' + banner.bannerImg" alt="Banner" style="width: 100%; height: 132px; object-fit: cover;">
                  </div>
                </div>
              </div>
            </div>
            <nav>
              <ul class="pagination justify-content-center">
                <li class="page-item" :class="{ disabled: currentPage === 1 }">
                  <a class="page-link" href="#" @click.prevent="changePage(currentPage - 1)">Previous</a>
                </li>
                <li class="page-item" v-for="page in totalPages" :key="page" :class="{ active: page === currentPage }">
                  <a class="page-link" href="#" @click.prevent="changePage(page)">{{ page }}</a>
                </li>
                <li class="page-item" :class="{ disabled: currentPage === totalPages }">
                  <a class="page-link" href="#" @click.prevent="changePage(currentPage + 1)">Next</a>
                </li>
              </ul>
            </nav>
          </div>
        </div>
      </div>
    </section>
  </main>
</template>

<script>
import axios from 'axios';
import Header from "@/components/Header.vue";

const axiosInstance = axios.create({
  baseURL: 'http://localhost:8090' // Spring Boot 애플리케이션의 포트 번호
});

export default {
  name: 'BannerList',
  components: { Header },
  data() {
    return {
      banners: [],
      currentPage: 1,
      pageSize: 6,
      selectedBanners: [], // 선택된 공지사항의 ID 저장
    };
  },
  mounted() {
    this.fetchBanners();
  },
  methods: {
    async fetchBanners() {
      try {
        const response = await axiosInstance.get('/admin/banners');
        this.banners = response.data;
      } catch (error) {
        console.error('Error fetching notices:', error);
      }
    },
    changePage(page) {
      if (page >= 1 && page <= this.totalPages) {
        this.currentPage = page;
      }
    },
    toggleSelectAll(event) {
      if (event.target.checked) {
        this.selectedBanners = this.paginatedBanners.map(banner => banner.bannerId);
      } else {
        this.selectedBanners = [];
      }
    },
    async deleteSelectedBanners() {
      // 선택된 공지사항을 삭제하는 로직
      if (this.selectedBanners.length === 0) {
        alert("삭제할 공지사항을 선택하세요.");
        return;
      }

      if (confirm("선택한 공지사항을 정말 삭제하시겠습니까?")) {
        try {
          // 선택된 공지사항을 삭제하고 삭제 요청이 완료된 후에 목록을 새로고침
          await Promise.all(
              this.selectedBanners.map(bannerId =>
                  axiosInstance.delete(`/admin/banners/${bannerId}`)
              )
          );

          // 삭제가 완료된 후 공지사항 목록을 새로고침
          this.fetchBanners();

          // 선택 초기화
          this.selectedBanners = [];
          alert("선택한 배너가 삭제되었습니다.");
        } catch (error) {
          console.error("공지사항 삭제 중 오류 발생:", error);
          alert("배너 삭제 중 오류가 발생했습니다.");
        }
      }
    },
  },
  computed: {
    paginatedBanners() {
      const start = (this.currentPage - 1) * this.pageSize;
      const end = start + this.pageSize;
      return this.banners.slice(start, end);
    },
    totalPages() {
      return Math.ceil(this.banners.length / this.pageSize);
    },
    allSelected() {
      const paginatedBannersIds = this.paginatedBanners.map(banner => banner.bannerId);
      console.log(paginatedBannersIds.length > 0 && paginatedBannersIds.every(id => this.paginatedBanners.includes(id)))
      return paginatedBannersIds.length > 0 && paginatedBannersIds.every(id => this.selectedBanners.includes(id));
    }
  }
};
</script>

<style scoped>
.card-inter {
  border: 1px solid #ddd;
  border-radius: 4px;
  margin: 10px;
  padding: 10px;
  width: 200px;
  text-align: center;
}
.card-head header {
  font-weight: bold;
  margin-bottom: 10px;
}
</style>
