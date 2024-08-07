<template>
  <Header main-content="notice"/>
  <main>
    <section>
      <div class="row">
        <div class="col-12 mb-4">
          <div class="card">
            <div class="card-header">
              공지사항
            </div>
            <div class="card-body">
              <div class="mb-3 d-flex justify-content-end">
                <button id="deleteNoticeBtn" class="btn btn-danger ms-2" @click="deleteSelectedNotices">삭제</button>
              </div>
              <div class="table-responsive">
                <table class="table table-striped table-bordered">
                  <thead class="table-dark">
                  <tr>
                    <th><input type="checkbox" id="selectAll" :checked="allSelected" @change="toggleSelectAll"></th>
                    <th>번호</th>
                    <th>제목</th>
                    <th>작성날짜</th>
                    <th>상세보기</th>
                  </tr>
                  </thead>
                  <tbody>
                  <tr v-for="notice in paginatedNotices" :key="notice.noticeId">
                    <td><input type="checkbox" v-model="selectedNotices" :value="notice.noticeId"></td>
                    <td>{{ notice.noticeId }}</td>
                    <td>{{ notice.noticeTitle }}</td>
                    <td>{{ formatDate(notice.noticeDate) }}</td>
                    <td><button class="btn btn-sm btn-secondary" @click="editNotice(notice.noticeId)">상세보기</button></td>
                  </tr>
                  </tbody>
                </table>
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
  name: 'NoticeList',
  components: { Header },
  data() {
    return {
      notices: [],
      currentPage: 1,
      pageSize: 10,
      selectedNotices: [], // 선택된 공지사항의 ID 저장
    };
  },
  mounted() {
    this.fetchNotices();
  },
  methods: {
    async fetchNotices() {
      try {
        const response = await axiosInstance.get('/admin/notices');
        this.notices = response.data;
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
        this.selectedNotices = this.paginatedNotices.map(notice => notice.noticeId);
      } else {
        this.selectedNotices = [];
      }
    },
    async deleteSelectedNotices() {
      // 선택된 공지사항을 삭제하는 로직
      if (this.selectedNotices.length === 0) {
        alert("삭제할 공지사항을 선택하세요.");
        return;
      }

      if (confirm("선택한 공지사항을 정말 삭제하시겠습니까?")) {
        try {
          // 선택된 공지사항을 삭제하고 삭제 요청이 완료된 후에 목록을 새로고침
          await Promise.all(
              this.selectedNotices.map(noticeId =>
                  axiosInstance.delete(`/admin/notices/${noticeId}`)
              )
          );

          // 삭제가 완료된 후 공지사항 목록을 새로고침
          this.fetchNotices();

          // 선택 초기화
          this.selectedNotices = [];
          alert("선택한 공지사항이 삭제되었습니다.");
        } catch (error) {
          console.error("공지사항 삭제 중 오류 발생:", error);
          alert("공지사항 삭제 중 오류가 발생했습니다.");
        }
      }
    },
    editNotice(noticeId) {
      this.$router.push({name : 'NoticeEdit', params : {noticeId}})
    },
    formatDate(dateString) {
      // 날짜 형식을 'YYYY-MM-DD'로 변환
      const options = { year: 'numeric', month: '2-digit', day: '2-digit' };
      return new Date(dateString).toLocaleDateString('ko-KR', options);
    }
  },
  computed: {
    paginatedNotices() {
      const start = (this.currentPage - 1) * this.pageSize;
      const end = start + this.pageSize;
      return this.notices.slice(start, end);
    },
    totalPages() {
      return Math.ceil(this.notices.length / this.pageSize);
    },
    allSelected() {
      const paginatedNoticeIds = this.paginatedNotices.map(notice => notice.noticeId);
      return paginatedNoticeIds.length > 0 && paginatedNoticeIds.every(id => this.selectedNotices.includes(id));
    }
  }
};
</script>

<style scoped>
</style>
