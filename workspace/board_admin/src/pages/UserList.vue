<template>
  <Header main-content="main"/>
  <main>
    <section>
      <div class="row mb-4">
        <div class="col">
          <h1 class="page-heading text-dark fw-bold fs-3">회원 목록</h1>
        </div>
        <div class="col d-flex justify-content-end">
          <button id="deleteMemberButton" class="btn btn-danger" @click="deleteSelectedUsers">회원 탈퇴</button>
        </div>
      </div>
      <div class="table-responsive">
        <table class="table table-striped table-bordered">
          <thead class="table-dark">
          <tr>
            <th><input type="checkbox" id="selectAll" :checked="allSelected" @change="toggleSelectAll"></th>
            <th>No.</th>
            <th>구분</th>
            <th>이름</th>
            <th>전화번호</th>
            <th>가입일</th>
            <th>주소</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="user in paginatedUsers" :key="user.providerId">
            <td><input type="checkbox" v-model="selectedUsers" :value="user.providerId"></td>
            <td>{{ user.providerId }}</td>
            <td>{{ user.provider }}</td>
            <td>{{ user.name }}</td>
            <td>{{ user.phoneNumber }}</td>
            <td>{{ user.createAt }}</td>
            <td>{{ user.address }}</td>
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
  name: 'UserList',
  components: {Header},
  data() {
    return {
      users: [],
      currentPage: 1,
      pageSize: 10,
      selectedUsers: [] // 선택된 사용자의 ID 저장
    };
  },
  mounted() {
    this.fetchUsers();
  },
  methods: {
    async fetchUsers() {
      try {
        const response = await axiosInstance.get('/admin/users');
        this.users = response.data;
      } catch (error) {
        console.error('Error fetching users:', error);
      }
    },
    changePage(page) {
      if (page >= 1 && page <= this.totalPages) {
        this.currentPage = page;
      }
    },
    toggleSelectAll(event) {
      // 전체 선택/해제 기능
      if (event.target.checked) {
        this.selectedUsers = this.paginatedUsers.map(user => user.providerId);
      } else {
        this.selectedUsers = [];
      }
    },
    async deleteSelectedUsers() {
      // 선택된 사용자를 탈퇴시키는 로직
      if (this.selectedUsers.length === 0) {
        alert("탈퇴할 사용자를 선택하세요.");
        return;
      }

      if (confirm("선택한 사용자를 정말 탈퇴시키겠습니까?")) {
        try {
          // 선택된 사용자를 삭제하고 삭제 요청이 완료된 후에 목록을 새로고침
          await Promise.all(
              this.selectedUsers.map(providerId =>
                  axiosInstance.delete(`/admin/users/${providerId}`)
              )
          );

          // 삭제가 완료된 후 사용자 목록을 새로고침
          await this.fetchUsers();

          // 선택 초기화
          this.selectedUsers = [];
          alert("선택한 사용자가 탈퇴되었습니다.");
        } catch (error) {
          console.error("사용자 삭제 중 오류 발생:", error);
          alert("사용자 삭제 중 오류가 발생했습니다.");
        }
      }
    }
  },
  computed: {
    paginatedUsers() {
      const start = (this.currentPage - 1) * this.pageSize;
      const end = start + this.pageSize;
      return this.users.slice(start, end);
    },
    totalPages() {
      return Math.ceil(this.users.length / this.pageSize);
    },
    allSelected() {
      // 현재 페이지의 모든 사용자가 선택되었는지 확인
      const paginatedUserIds = this.paginatedUsers.map(user => user.providerId);
      return paginatedUserIds.length > 0 && paginatedUserIds.every(id => this.selectedUsers.includes(id));
    }
  }
};
</script>

<style scoped>
</style>
