<template>
  <div class="navbar">
    <div class="desktop_container">
      <div class="content">
        <router-link to="/" class="logo">여기에 로고 삽입</router-link>
        <div class="navbar-menu">
          <div class="navbar-left">
            <div class="search">
              <input type="text" />
              <button>검색</button>
            </div>
          </div>
          <div class="navbar-right">
            <router-link to="/signin"><button>로그인</button></router-link>
            <router-link to="/signup"><button>회원가입</button></router-link>
            <!--v-if 사용해서 조건부 렌더링 하기-->
            <router-link to="/mypage"><button>마이페이지</button></router-link>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {};
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