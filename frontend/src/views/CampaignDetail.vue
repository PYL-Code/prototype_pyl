<template>
  <div class="container my-5">
    <div v-if="loading" class="text-center my-5">
      <div class="spinner-border text-primary" role="status">
        <span class="visually-hidden">Loading...</span>
      </div>
    </div>

    <div v-else-if="!campaign" class="alert alert-danger text-center">
      해당 캠페인을 찾을 수 없습니다.
    </div>

    <div v-else>
      <h2 class="fw-bold mb-4 text-center">📢 {{ campaign.title }}</h2>

      <div class="card shadow-sm p-4">
        <p><strong>🛍️ 카테고리:</strong> {{ campaign.category }}</p>
        <p><strong>📦 제품명:</strong> {{ campaign.productName }}</p>
        <p><strong>🗓️ 신청 마감일:</strong> {{ formatDate(campaign.applicationDeadline) }}</p>
        <p><strong>🗓️ 리뷰 마감일:</strong> {{ formatDate(campaign.reviewDeadline) }}</p>
        <p><strong>👥 모집 인원:</strong> {{ campaign.quota }}명</p>
        <p><strong>📄 설명:</strong></p>
        <p class="bg-light p-3 rounded">{{ campaign.description }}</p>

        <div class="text-center mt-4">
          <router-link :to="`/applyform/${campaign.id}`" class="btn btn-primary">
            신청하기
          </router-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue'
import { useRoute } from 'vue-router'
import axios from 'axios'

const route = useRoute()
const campaignId = route.params.id
// console.log('캠페인 id:', campaignId)
const campaign = ref(null)
const loading = ref(true)

const fetchCampaign = async () => {
  try {
    const response = await axios.get(`/provider/campaign/${campaignId}`)
    campaign.value = response.data
  } catch (err) {
    console.error('불러오기 실패:', err)
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
