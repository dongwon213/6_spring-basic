<template>
  <Header main-content="notice"/>
  <main>
    <section>
      <div class="row">
        <div class="col-12 mb-4">
          <div class="card">
            <div class="card-header">
              공지사항 상세보기
            </div>
            <div class="card-body">
              <form @submit.prevent="updateNotice">
                <div class="mb-3 d-flex">
                  <input v-model="notice.noticeTitle" type="text" name="noticeTitle" class="form-control me-2" placeholder="공지사항 제목">
                </div>
                <div class="mb-3">
                  <textarea v-model="notice.noticeContent" class="form-control" name="noticeContent" placeholder="공지사항 내용" rows="6"></textarea>
                </div>
                <div class="d-flex justify-content-end">
                  <button type="submit" class="btn btn-primary">수정 하기</button>
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
  name: 'NoticeEdit',
  components: { Header },
  props: ['noticeId'],
  data() {
    return {
      notice: {
        noticeTitle: '',
        noticeContent: ''
      }
    };
  },
  mounted() {
    this.loadNotice();
  },
  methods: {
    async loadNotice() {
      try {
        const response = await axiosInstance.get(`/admin/notices/${this.noticeId}`);

        this.notice = response.data;

      } catch (error) {
        console.error(error);
        alert('공지사항을 불러오는 중 오류가 발생했습니다.');
      }
    },
    async updateNotice() {
      try {
        await axiosInstance.put(`/admin/notices`, this.notice);
        alert('공지사항이 수정되었습니다.');
        this.$router.push({name: 'Notice'}); // 수정 후 목록으로 이동
      } catch (error) {
        console.error('Error updating notice:', error);
        alert('공지사항 수정 중 오류가 발생했습니다.');
      }
    }
  },
};
</script>

<style scoped>
/* 필요에 따라 스타일을 추가하세요 */
</style>
