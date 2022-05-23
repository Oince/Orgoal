<template>
  <div class="navbar">
    <div class="desktop_container">
      <div class="content">
        <router-link to="/">
          <span class="logo">로고</span>
          <span>ORGOAL</span>
        </router-link>
        <div class="navbar-menu">
          <div class="navbar-left">
            <div class="search">
              <input type="text" />
              <button>검색</button>
            </div>
          </div>
          <div class="navbar-right">
            <!--로그인 여부에 따라 조건부 렌더링-->
            <div v-if="!isSignedin">
              <router-link to="/signin"><button>로그인</button></router-link>
              <router-link to="/signup"><button>회원가입</button></router-link>
            </div>
            <div v-if="isSignedin">
              <span>{{ username }}님 </span>
              <router-link to="/mypage"
                ><button>마이페이지</button></router-link
              >
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { computed } from "vue";
import { useStore } from "vuex";

export default {
  name: "NavBar",
  props: {},
  setup: function () {
    // data
    const store = useStore(); // 훅을 사용하여 vuex store 호출
    const isSignedin = computed(() => store.getters["signin/hasToken"]);

    //methods

    return { isSignedin };
  },
};
</script>

<style scoped>
.desktop_container {
  background-color: aliceblue;
  height: 70px;
}
.content {
  display: flex;
  width: 1280px;
  margin: 0 auto;
  justify-content: space-between;
}
.navbar-menu {
  width: 1080px;
  display: flex;
  justify-content: space-between;
}
</style>
