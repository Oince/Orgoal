<template>
  <div class="mypage">
    <h1>마이페이지</h1>
    <!-- <h2>{{ nickname }}</h2> -->
  </div>
  <hr />
  <myactivity />
</template>

<script>
import { useStore } from "vuex";
import myactivity from "./Myactivity.vue";

export default {
  name: "MyPage",
  components: {
    myactivity,
  },
  setup: function () {
    //data
    const store = useStore();
    const token = store.state.signin.token;

    // let nickname = computed(() => store.getters["signin/getNickname"]);

    //methods
    return {
      token,
      // nickname,
    };
  },
};
</script>

<style></style>
