<template>
  <div class="container my-5 campaign-detail-page">
    <!-- 로딩 중 표시 -->
    <div v-if="loading" class="text-center my-5">
      <div class="spinner-border text-primary" role="status">
        <span class="visually-hidden">Loading...</span>
      </div>
    </div>

    <!-- 캠페인 없음 -->
    <div v-else-if="!campaign" class="alert alert-danger text-center">
      해당 캠페인을 찾을 수 없습니다.
    </div>

    <!-- 캠페인 상세 정보 -->
    <div v-else>
      <h2 class="fw-bold mb-4 text-center">📢 {{ campaign.title }}</h2>

      <div class="campaign-detail-wrapper shadow-sm rounded">
        <!-- 왼쪽 이미지 -->
        <div class="campaign-image-wrapper">
          <img
              src="https://images.unsplash.com/photo-1496715976403-7e36dc43f17b?q=80&w=1470&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
              alt="Campaign Image"
              class="campaign-image"
          />
        </div>

        <!-- 오른쪽 정보 -->
        <div class="campaign-info">
          <div class="mb-2"><strong>🛍️ 카테고리:</strong> {{ campaign.category }}</div>
          <div class="mb-2"><strong>📦 제품명:</strong> {{ campaign.productName }}</div>
          <div class="mb-2"><strong>🗓️ 신청 마감일:</strong> {{ formatDate(campaign.applicationDeadline) }}</div>
          <div class="mb-2"><strong>🗓️ 리뷰 마감일:</strong> {{ formatDate(campaign.reviewDeadline) }}</div>
          <div class="mb-2"><strong>👥 모집 인원:</strong> {{ campaign.quota }}명</div>
          <div class="mb-2">
            <strong>✅ 모집 중:</strong>
            <span :class="campaign.recruitActive ? 'text-success' : 'text-danger'">
              {{ campaign.recruitActive ? '예' : '아니오' }}
            </span>
          </div>
          <div class="mb-2">
            <strong>📝 리뷰 중:</strong>
            <span :class="campaign.reviewActive ? 'text-success' : 'text-danger'">
              {{ campaign.reviewActive ? '예' : '아니오' }}
            </span>
          </div>
          <div class="mb-3">
            <strong>📄 설명:</strong>
            <div class="bg-light p-3 rounded mt-2">{{ campaign.description }}</div>
          </div>

          <div class="mt-4 text-end">
            <router-link :to="`/applyform/${campaign.id}`" class="btn btn-success px-4">
              체험단 신청하기
            </router-link>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import axios from 'axios'

const route = useRoute()
const campaignId = route.params.id
const campaign = ref(null)
const loading = ref(true)

const fetchCampaign = async () => {
  try {
    const response = await axios.get(`/provider/campaign/${campaignId}`)
    campaign.value = response.data
  } catch (error) {
    console.error('캠페인 정보를 불러오는 데 실패했습니다:', error)
  } finally {
    loading.value = false
  }
}

const formatDate = (dateStr) => {
  const date = new Date(dateStr)
  return date.toLocaleDateString('ko-KR')
}

onMounted(fetchCampaign)
</script>

<style scoped>
.campaign-detail-wrapper {
  display: flex;
  gap: 2rem;
  padding: 2rem;
  background-color: #fff;
  border: 1px solid #ddd;
  flex-wrap: wrap;
  max-width: 1000px;
  margin: 0 auto;
}

.campaign-image-wrapper {
  flex: 1 1 300px;
  display: flex;
  align-items: center;
  justify-content: center;
  min-width: 280px;
}

.campaign-image {
  width: 100%;
  max-width: 400px;
  aspect-ratio: 1/1;
  object-fit: cover;
}

.campaign-info {
  flex: 2 1 400px;
  display: flex;
  flex-direction: column;
  justify-content: center;
}
</style>
