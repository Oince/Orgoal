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
              <router-link to="/signin"><button>로그인</button></router-link>
              <router-link to="/signup"><button>회원가입</button></router-link>
            </div>
            <div v-if="isSignedin">
              <span>{{ nickname }}님 </span>
              <router-link to="/mypage"
                ><button>마이페이지</button></router-link
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

    return { isSignedin, nickname, searchText, doLogout, onClickSearchButton };
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
.mypage {
  color: gray;
  background-color: white;
  font-weight: 900;
  font-size: 14pt;
  width: 45px;
  height: 45px;
  border-radius: 50%;
  border: 1px solid #dfdfdf;
}
</style>
