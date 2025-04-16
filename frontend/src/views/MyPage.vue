<template>
  <div class="container my-5">
    <h2 class="mb-4 text-center fw-bold">📝 나의 체험단 신청 내역</h2>

    <div v-if="loading" class="text-center my-5">
      <div class="spinner-border text-primary" role="status">
        <span class="visually-hidden">Loading...</span>
      </div>
    </div>

    <div v-else-if="applications.length === 0" class="alert alert-info text-center">
      신청한 체험단이 없습니다.
    </div>

    <div v-else class="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-4">
      <div class="col" v-for="app in applications" :key="app.id">
        <div class="card h-100 shadow-sm border-0">
          <div class="card-body">
            <h5 class="card-title">
              <span class="badge bg-dark me-2">{{ app.campaignTitle }}</span>
            </h5>
            <p class="card-text"><strong>📞 전화번호:</strong> {{ app.phone }}</p>
            <p class="card-text">
              <strong>🔗 SNS:</strong>
              <a v-if="app.snsUrl" :href="app.snsUrl" target="_blank" class="link-primary">{{ app.snsUrl }}</a>
              <span v-else class="text-muted">없음</span>
            </p>
            <p class="card-text"><strong>💬 신청 사유:</strong> {{ app.text }}</p>
          </div>
          <div class="card-footer d-flex justify-content-between align-items-center bg-light">
            <span class="badge rounded-pill" :class="statusClass(app.status)">
              {{ app.status }}
            </span>
            <button class="btn btn-outline-danger btn-sm" @click="deleteApplication(app.id)">
              <i class="bi bi-trash-fill"></i> 삭제
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue'
import axios from 'axios'

const reviewerId = 1
const applications = ref([])
const loading = ref(true)

const fetchApplications = async () => {
  try {
    const response = await axios.get(`/reviewer/myapplication/${reviewerId}`)
    // console.log('응답 데이터 구조 확인:', response.data)
    applications.value = Array.isArray(response.data)
        ? response.data
        : response.data.content || []
  } catch (err) {
    console.error('신청 내역 조회 오류:', err)
  } finally {
    loading.value = false
  }
}

const deleteApplication = async (id) => {
  if (!confirm('정말 삭제하시겠습니까?')) return
  try {
    await axios.delete(`/reviewer/delete/${id}`)
    applications.value = applications.value.filter(app => app.id !== id)
  } catch (err) {
    console.error('삭제 오류:', err)
  }
}

const statusClass = (status) => {
  switch (status) {
    case '신청대기': return 'bg-secondary text-white'
    case '당첨': return 'bg-success text-white'
    case '미당첨': return 'bg-danger text-white'
    case '신청취소': return 'bg-warning text-dark'
    default: return 'bg-light text-dark'
  }
}

onMounted(fetchApplications)
</script>

<style scoped>
.card {
  border-radius: 1rem;
}
.card-footer {
  border-top: none;
}
</style>
