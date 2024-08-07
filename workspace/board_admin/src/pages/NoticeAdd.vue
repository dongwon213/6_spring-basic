<template>
  <Header main-content="notice"/>
  <main>
    <section>
      <div class="row">
        <div class="col-12 mb-4">
          <div class="card">
            <div class="card-header">
              공지사항 추가
            </div>
            <div class="card-body">
              <form @submit.prevent="addNotice">
                <div class="mb-3 d-flex">
                  <input
                      type="text"
                      name="noticeTitle"
                      class="form-control me-2"
                      placeholder="공지사항 제목"
                      v-model="notice.noticeTitle"
                  >
                </div>
                <div class="mb-3">
                  <textarea
                      class="form-control"
                      name="noticeContent"
                      placeholder="공지사항 내용"
                      rows="6"
                      v-model="notice.noticeContent"
                  ></textarea>
                </div>
                <div class="d-flex justify-content-end">
                  <button type="submit" class="btn btn-primary">추가 하기</button>
                </div>
              </form>
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
  name: 'NoticeAdd',
  components: { Header },
  data() {
    return {
      notice: {
        noticeTitle: '',
        noticeContent: ''
      }
    };
  },
  methods: {
    async addNotice() {
      try {
        await axiosInstance.post(`/admin/notices`, this.notice);
        alert('공지사항이 추가되었습니다.');
        this.$router.push({name: 'Notice'}); // 수정 후 목록으로 이동
      } catch (error) {
        console.error('Error updating notice:', error);
        alert('공지사항 추가 중 오류가 발생했습니다.');
      }
    }
  }
}
</script>

<style scoped>

</style>
