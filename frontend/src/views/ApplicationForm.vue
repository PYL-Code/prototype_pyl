<template>
  <div class="container my-5">
    <h2 class="text-center fw-bold mb-4">📩 체험단 신청</h2>

    <form @submit.prevent="submitApplication" class="card p-4 shadow-sm">
      <div class="mb-3">
        <label class="form-label">캠페인 ID</label>
        <input v-model="form.campaignId" type="number" class="form-control" required />
      </div>

      <div class="mb-3">
        <label class="form-label">캠페인 제목</label>
        <input v-model="form.campaignTitle" type="text" class="form-control" required />
      </div>

      <div class="mb-3">
        <label class="form-label">신청자 ID</label>
        <input v-model="form.reviewerId" type="number" class="form-control" required />
      </div>

      <div class="mb-3">
        <label class="form-label">신청자 닉네임</label>
        <input v-model="form.reviewerNickname" type="text" class="form-control" required />
      </div>

      <div class="mb-3">
        <label class="form-label">전화번호</label>
        <input v-model="form.phone" type="tel" class="form-control" required />
      </div>

      <div class="mb-3">
        <label class="form-label">SNS URL (선택)</label>
        <input v-model="form.snsUrl" type="url" class="form-control" placeholder="https://..." />
      </div>

      <div class="mb-3">
        <label class="form-label">신청 사유</label>
        <textarea v-model="form.text" class="form-control" rows="3" required></textarea>
      </div>

      <div class="mb-3">
        <label class="form-label">신청 상태</label>
        <select v-model="form.status" class="form-select" required>
          <option value="신청대기">신청대기</option>
          <option value="당첨">당첨</option>
          <option value="미당첨">미당첨</option>
          <option value="신청취소">신청취소</option>
        </select>
      </div>

      <div class="text-center">
        <button type="submit" class="btn btn-primary px-4">신청하기</button>
      </div>
    </form>
  </div>
</template>

<script setup>
import { reactive } from 'vue'
import axios from 'axios'

const form = reactive({
  campaignId: '',
  campaignTitle: '',
  reviewerId: '',
  reviewerNickname: '',
  phone: '',
  snsUrl: '',
  text: '',
  status: '신청대기',
})

const submitApplication = async () => {
  try {
    const id = form.reviewerId
    await axios.post(`/reviewer/upload/${id}`, form)
    alert('신청이 완료되었습니다!')
    // 폼 초기화
    Object.keys(form).forEach(key => form[key] = key === 'status' ? '신청대기' : '')
  } catch (err) {
    console.error(err)
    alert('신청 중 오류가 발생했습니다.')
  }
}
</script>

<style scoped>
form {
  max-width: 600px;
  margin: 0 auto;
  background-color: #fff;
  border-radius: 1rem;
}
</style>
