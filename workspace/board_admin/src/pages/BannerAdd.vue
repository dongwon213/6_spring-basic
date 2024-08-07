<template>
  <div>
    <Header main-content="banner"/>
    <main>
      <section>
        <div class="row">
          <div class="col-12 mb-4">
            <div class="card">
              <div class="card-header">
                배너 추가
              </div>
              <div class="card-body">
                <form class="mb-4" @submit.prevent="handleSubmit" enctype="multipart/form-data">
                  <div class="mb-3">
                    <label for="bannerTitle" class="form-label">배너 제목</label>
                    <input type="text" class="form-control" id="bannerTitle" v-model="bannerTitle" placeholder="배너 제목 입력">
                  </div>
                  <div class="mb-3">
                    <label for="bannerImage" class="form-label">배너 이미지</label>
                    <input class="form-control" type="file" id="bannerImage" @change="previewImage" accept="image/*">
                    <img v-if="imageSrc" :src="imageSrc" class="image-preview mt-3" style="width: 100%; height: 255px" alt="Image Preview">
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
  </div>
</template>

<script>
import axios from 'axios';
import Header from "@/components/Header.vue";

const axiosInstance = axios.create({
  baseURL: 'http://localhost:8090' // Spring Boot 애플리케이션의 포트 번호
});

export default {
  name: 'BannerAdd',
  components: { Header },
  data() {
    return {
      bannerTitle: '',
      imageFile: null,
      imageSrc: null
    };
  },
  methods: {
    previewImage(event) {
      const file = event.target.files[0];
      if (file) {
        this.imageFile = file;
        const reader = new FileReader();
        reader.onload = (e) => {
          this.imageSrc = e.target.result;
        };
        reader.readAsDataURL(file);
      }
    },
    async handleSubmit() {
      if (!this.bannerTitle || !this.imageFile) {
        alert("배너 제목과 이미지를 모두 입력해주세요.");
        return;
      }

      const formData = new FormData();
      formData.append('bannerTitle', this.bannerTitle);
      formData.append('bannerImage', this.imageFile);

      try {
        const response = await axiosInstance.post('/admin/banners', formData, {
          headers: {
            'Content-Type': 'multipart/form-data'
          }
        });
        alert('배너가 추가되었습니다.');
        this.$router.push({name: 'Banner'}); // 수정 후 목록으로 이동
      } catch (error) {
        console.error('업로드 실패:', error);
      }
    }
  }
};
</script>

<style scoped>
.image-preview {
  width: 100%;
  height: 255px;
  object-fit: cover;
}
</style>
