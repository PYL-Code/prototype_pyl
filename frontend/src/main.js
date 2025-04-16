import { createApp } from "vue";
import App from "./App.vue";
import router from "./router/router.js"; // Vue Router import

import 'bootstrap-icons/font/bootstrap-icons.css'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.js';
import 'bootstrap'

const app = createApp(App);
app.use(router); // Vue Router 적용
app.mount("#app");