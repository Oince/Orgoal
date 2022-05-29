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
              <input v-model="searchText" @keyup.enter="onClickSearchButton" />
              <button @click="onClickSearchButton()">검색</button>
            </div>
          </div>
          <div class="navbar-right">
            <!--로그인 여부에 따라 조건부 렌더링-->
            <div v-if="!isSignedin">
              <router-link to="/signin"
                ><button class="navbar-button">로그인</button></router-link
              >
              <router-link to="/signup"
                ><button class="navbar-button">회원가입</button></router-link
              >
            </div>
            <div v-if="isSignedin">
              <span v-if="notice != 0" class="navbar-context">{{
                notice
              }}</span>
              <svg
                version="1.0"
                xmlns="http://www.w3.org/2000/svg"
                width="6pt"
                height="6pt"
                viewBox="0 0 48.000000 48.000000"
                preserveAspectRatio="xMidYMid meet"
              >
                <g
                  transform="translate(0.000000,48.000000) scale(0.100000,-0.100000)"
                  stroke="none"
                  :class="[notice > 0 ? 'red-dot' : 'gray-dot']"
                >
                  <path
                    d="M160 467 c-49 -16 -133 -102 -148 -153 -28 -94 -8 -169 63 -239 102
-103 243 -101 338 5 90 100 90 220 0 320 -65 72 -158 97 -253 67z"
                  />
                </g>
              </svg>
              <span class="nickname">{{ nickname }}</span>
              <span class="navbar-context">님</span>
              <router-link to="/mypage"
                ><button class="navbar-button">마이페이지</button></router-link
              >
              <button @click.prevent="doLogout()">로그아웃</button>
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
    const nickname = computed(() => store.getters["nickname/getNickname"]);
    let searchText = "";

    // Navbar의 notice는 GET하여 받는 것으로 수정해야 함
    let notice = computed(() => store.getters["myactivity/getNotice"]);

    //methods
    const doLogout = function () {
      store
        .dispatch("signin/doLogout")
        .then(() => {
          this.$router.push("/");
        })
        .catch((err) => {
          console.log(err);
        });
    };

    let onClickSearchButton = function () {
      if (this.searchText == "") {
        alert("검색어를 입력하세요");
        return;
      }
      // TODO : 쿼리문 외에 태그를 사용한 검색도 구현하기
      this.$router.push({ name: "search", query: { query: this.searchText } });
      console.log(this.searchText);
    };

    return {
      isSignedin,
      nickname,
      searchText,
      notice,
      doLogout,
      onClickSearchButton,
    };
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
  padding: 1em 0;
}
.logo {
  flex: 0 0 200px;
  text-align: left;
  font-size: 24pt;
}
.navbar-menu {
  width: 1080px;
  display: flex;
  justify-content: space-between;
}
.red-dot {
  fill: red;
}
.gray-dot {
  fill: gray;
}
.navbar-context {
  margin: 0px 1px 0px 5px;
}
.nickname {
  margin: 0px 1px 0px 5px;
  font-weight: bold;
}
.navbar-button {
  margin: 0px 5px 0px 5px;
}
</style>
