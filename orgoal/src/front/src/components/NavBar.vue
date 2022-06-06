<template>
  <div class="navbar">
    <div class="desktop_container">
      <div class="content">
        <router-link to="/" class="logo">
          <span>ORGOAL</span>
        </router-link>
        <div class="navbar-menu">
          <div class="navbar-left">
            <div class="search">
              <input
                class="search-input"
                v-model="searchText"
                @keyup.enter="onClickSearchButton"
              />
              <button class="search-button" @click="onClickSearchButton">
                검색
              </button>
            </div>
          </div>
          <div class="navbar-right">
            <!--로그인 여부에 따라 조건부 렌더링-->
            <div v-if="!isSignedin">
              <router-link to="/signin"><button>로그인</button></router-link>
              <router-link to="/signup"><button>회원가입</button></router-link>
            </div>
            <div v-if="isSignedin">
              <span>{{ nickname }}님 </span>
              <router-link to="/mypage">
                <button class="mypage">
                  <svg
                    xmlns="http://www.w3.org/2000/svg"
                    width="16"
                    height="16"
                    fill="currentColor"
                    class="bi bi-person"
                    viewBox="0 0 16 16"
                  >
                    <path
                      d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0zm4 8c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4zm-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10z"
                    />
                  </svg>
                  <div class="reddot" v-if="hasNewAlarm"></div>
                </button>
              </router-link>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { computed, onMounted } from "vue";
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
    let hasNewAlarm = false;

    //methods
    let onClickSearchButton = function () {
      if (this.searchText == "") {
        alert("검색어를 입력하세요");
        return;
      }
      // TODO : 쿼리문 외에 태그를 사용한 검색도 구현하기
      this.$router.push({ name: "search", query: { query: this.searchText } });
      console.log(this.searchText);
    };

    onMounted(() => {
      const axios = require("axios").default;
      const URI = "/api/notification";
      axios
        .get(URI, store.state.signin.memberId)
        .then((response) => {
          console.log("get if there is new events"); // for Debug
          this.hasNewAlarm = response.data.hasNewEvent;
        })
        .catch((error) => {
          console.log(error);
        });
      console.log("navbar");
    });

    return {
      isSignedin,
      nickname,
      searchText,
      hasNewAlarm,
      nickname,
      onClickSearchButton,
      doLogout,
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
  width: 800px;
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
.search {
  height: 40px;
}
.mypage {
  position: relative;
  color: gray;
  background-color: white;
  font-weight: 900;
  font-size: 14pt;
  width: 40px;
  height: 40px;
  border-radius: 50%;
  border: 1px solid #dfdfdf;
}
.mypage:hover {
  background-color: #dfdfdf;
}
.reddot {
  position: absolute;
  top: 0px;
  right: 0px;
  width: 7px;
  height: 7px;
  background: red;
  border-radius: 4px;
}
</style>
