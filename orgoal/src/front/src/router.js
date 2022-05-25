// 새로운 페이지를 생성했으면 여기서 라우터에 추가하기
import * as VueRouter from "vue-router";

// 주소와 vue 컴포넌트 매칭
const routes = [
  {
    path: "/",
    component: () => import("./components/Home.vue"),
  },
  {
    path: "/index",
    redirect: "/",
  },
  {
    path: "/signin",
    name: "signin",
    component: () => import("./components/SignIn.vue"),
  },
  {
    path: "/signup",
    component: () => import("./components/SignUp.vue"),
  },
  {
    path: "/mypage",
    component: () => import("./components/Mypage.vue"),
    meta: { requiresAuth: true },
  },
  {
    path: "/newActivity",
    component: () => import("./components/NewActivity.vue"),
  },
  {
    path: "/activityList",
    component: () => import("./components/ActivityList.vue"),
  },
];

const router = VueRouter.createRouter({
  history: VueRouter.createWebHistory(),
  routes,
});

// 허용되지 않은 접근 필터링. ex: 로그인 없이 마이페이지
router.beforeEach(function (to) {
  if (to.meta.requiresAuth /*&& !auth.isLoggedIn()*/) {
    return {
      path: "/signin",
      // query: { redirect: to.fullPath } // 로그인 화면 접근 이전 화면으로 리다이렉트하기 위해 저장해두기.
    };
  }
});

export default router;
