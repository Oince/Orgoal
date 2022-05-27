<template>
  <div class="mypage">
    <h1>마이페이지</h1>
    <h2>{{ nickname }}</h2>
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
  mounted() {},
};
</script>

<style></style>
