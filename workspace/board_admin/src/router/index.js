import { createRouter, createWebHistory } from 'vue-router'

import Main from '@/pages/Main.vue'
import Banner from '@/pages/BannerList.vue'
import BannerAdd from '@/pages/BannerAdd.vue'
import Notice from '@/pages/NoticeList.vue'
import NoticeAdd from '@/pages/NoticeAdd.vue'
import NoticeEdit from '@/pages/NoticeEdit.vue'
import User from '@/pages/UserList.vue'

const router = createRouter({
    history: createWebHistory(),
    routes : [
        { path: '/', component: Main },
        {
            path: '/banner',
            name : 'Banner',
            component: Banner },
        { path: '/bannerAdd', component: BannerAdd },
        {
            path: '/notice',
            name : 'Notice',
            component: Notice },
        { path: '/noticeAdd', component: NoticeAdd },
        { path: '/user', component: User },
        {
            path: '/noticeEdit/:noticeId',
            name: 'NoticeEdit',
            component: NoticeEdit,
            props: true
        }
    ]
})

export default router;