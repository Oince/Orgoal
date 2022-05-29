<template>
  <div class="mypage">
    <div class="mypage-head">My Page</div>
    <div class="mypage-body">
      <span class="nickname">{{ nickname }}</span
      >님, 반갑습니다!
    </div>
  </div>
  <hr />
  <myactivity />
</template>

<script>
import { computed } from "vue";
import { useStore } from "vuex";
import myactivity from "./Myactivity.vue";
// import axios from "axios";

export default {
  name: "MyPage",
  components: {
    myactivity,
  },
  setup: function () {
    //data
    const store = useStore();
    const token = store.state.signin.token;

    const nickname = computed(() => store.getters["nickname/getNickname"]);

    return {
      token,
      nickname,
    };
  },
};
</script>

<style scoped>
.mypage {
  width: 600px;
  margin: auto;
}
.mypage-head {
  width: 600px;
  margin: 20px 0px 20px 0px;
  text-align: left;
  font-size: 40px;
  font-weight: bolder;
}
.mypage-body {
  width: 600px;
  margin: 20px 0px 20px 0px;
  text-align: left;
  font-size: 25px;
}
.nickname {
  margin-right: 5px;
  font-weight: bold;
  color: blue;
}
</style>
