import { createApp } from 'vue'
import App from './App.vue'
import * as VueRouter from 'vue-router';
import store from './store';

// 주소와 vue 컴포넌트 매칭
const routes = [
    { path: '/', component: () => import('./components/Home.vue') },
    { path: '/index', redirect: '/' },
    { path: '/signin', component: () => import('./components/Login.vue') },
    { path: '/signup', component: () => import('./components/SignIn.vue') },
    { path: '/mypage', component: () => import('./components/Mypage.vue') },
]

const router = VueRouter.createRouter({
    history: VueRouter.createWebHistory(),
    routes,
})

const orgoal = createApp(App);
orgoal.use(store);
orgoal.use(router);
orgoal.mount('#app');