<template>
  <div class="container my-5">
    <h2 class="text-center fw-bold mb-4">🔥 체험단 목록</h2>

    <div class="row row-cols-1 row-cols-md-3 g-4">
      <div v-for="campaign in campaigns" :key="campaign.id" class="col">
        <div class="card h-100 shadow-sm">
          <!-- 임시 이미지 -->
          <img
              src="https://images.unsplash.com/photo-1496715976403-7e36dc43f17b?q=80&w=1470&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
              class="card-img-top"
              alt="campaign image"
          />
          <div class="card-body">
            <h5 class="card-title">{{ campaign.title }}</h5>
            <p class="card-text mb-1"><strong>카테고리:</strong> {{ campaign.category }}</p>
            <p class="card-text"><strong>제품명:</strong> {{ campaign.productName }}</p>
            <router-link
                :to="`/detail/${campaign.id}`"
                class="btn btn-outline-primary btn-sm mt-2"
            >
              자세히 보기
            </router-link>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'

const campaigns = ref([])

const fetchCampaigns = async () => {
  try {
    const response = await axios.get('/provider/campaign/list') // 실제 백엔드 주소로 바꿔줘
    campaigns.value = response.data
  } catch (err) {
    console.error('캠페인 목록 불러오기 실패:', err)
  }
}

onMounted(fetchCampaigns)
</script>

<style scoped>
.card-img-top {
  height: 180px;
  object-fit: cover;
}
</style>
