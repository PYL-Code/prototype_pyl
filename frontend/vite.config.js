import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vite.dev/config/
export default defineConfig({
  plugins: [vue()],
  server: {
    port: 5173, // 프론트 포트
    proxy: {
      '/reviewer': {
        target: 'http://localhost:9876',
        changeOrigin: true,
        secure: false,
      },
      '/campaign': {
        target: 'http://localhost:9876',
        changeOrigin: true,
        secure: false,
      }
    }
  }
})


