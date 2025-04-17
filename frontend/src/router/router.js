import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import MyPage from "../views/MyPage.vue";
import ApplicationForm from "../views/ApplicationForm.vue";
import CampaignDetail from "../views/CampaignDetail.vue";
import CampaignList from "../views/CampaignList.vue";

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            name: 'home',
            component: Home
        },
        {
            path: '/mypage',
            name: 'mypage',
            component: MyPage
        },
        {
            path: '/applyform/:id',
            name: 'applyform',
            component: ApplicationForm
        },
        {
            path: '/list',
            name: 'list',
            component: CampaignList
        },
        {
            path: '/detail/:id',
            name: 'detail',
            component: CampaignDetail
        }
    ]
})

export default router