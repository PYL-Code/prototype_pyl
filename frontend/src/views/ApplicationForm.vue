<template>
  <div class="container my-5">
    <h2 class="text-center fw-bold mb-4">📩 체험단 신청</h2>

    <form @submit.prevent="submitApplication" class="card p-4 shadow-sm">
      <!-- 캠페인 제목 (읽기 전용) -->
      <div class="mb-3">
        <label class="form-label">캠페인 제목</label>
        <input v-model="form.campaignTitle" type="text" class="form-control" readonly />
      </div>

      <!-- 전화번호 -->
      <div class="mb-3">
        <label class="form-label">전화번호</label>
        <input v-model="form.phone" type="tel" class="form-control" required />
      </div>

      <!-- SNS URL -->
      <div class="mb-3">
        <label class="form-label">SNS URL</label>
        <input v-model="form.snsUrl" type="url" class="form-control" placeholder="https://..." required/>
      </div>

      <!-- 신청 사유 -->
      <div class="mb-3">
        <label class="form-label">신청 사유</label>
        <textarea v-model="form.text" class="form-control" rows="3" required></textarea>
      </div>

      <!-- 숨겨진 필드들 -->
      <input v-model="form.campaignId" type="hidden" />
      <input v-model="form.reviewerId" type="hidden" />
      <input v-model="form.reviewerNickname" type="hidden" />
      <input v-model="form.status" type="hidden" />

      <div class="text-center">
        <button type="submit" class="btn btn-primary px-4">신청하기</button>
      </div>
    </form>
  </div>
</template>

<script setup>
import { reactive, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import axios from 'axios'

const router = useRouter()
const route = useRoute()
const campaignId = route.params.id
const reviewerId = 1

const form = reactive({
  campaignId: campaignId,
  campaignTitle: '',
  reviewerId: 1, // 임시 더미 ID
  reviewerNickname: 'reviewer_yo', // 임시 닉네임
  phone: '010-1234-5678', // 임시 전화번호
  snsUrl: 'https://instagram.com/reviewer_yo', //임시 sns 링크
  text: '',
  status: '신청대기', // 고정값
})

// 캠페인 제목 불러오기
const fetchCampaign = async () => {
  try {
    const response = await axios.get(`/provider/campaign/${campaignId}`)
    form.campaignTitle = response.data.title
  } catch (err) {
    console.error('캠페인 정보를 불러오는 데 실패했습니다:', err)
  }
}

const submitApplication = async () => {
  try {
    await axios.post(`/reviewer/upload`, form)
    alert('신청이 완료되었습니다!')
    Object.assign(form, {
      campaignId: campaignId,
      campaignTitle: '',
      reviewerId: reviewerId,
      reviewerNickname: 'reviewer_yo',
      phone: '010-1234-5678',
      snsUrl: 'https://instagram.com/reviewer_yo',
      text: '',
      status: '신청대기',
    })
    router.push('/mypage')
  } catch (err) {
    console.error(err)
    alert('신청 중 오류가 발생했습니다.')
  }
}

onMounted(fetchCampaign)
</script>

<style scoped>
form {
  max-width: 600px;
  margin: 0 auto;
  background-color: #fff;
  border-radius: 1rem;
}
</style>
