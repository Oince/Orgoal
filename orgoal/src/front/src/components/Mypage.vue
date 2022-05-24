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
import axios from "axios";

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
  mounted() {
    const hostname = window.location.hostname;
    const URI = hostname + "/mypage/activity";
    let config = {
      headers: {
        token: this.token,
      },
    };
    axios.get(URI, config).then((response) => {
      console.log("Loaded Mypage Activity");
      response.data
        .forEach((myActivity) => {
          this.myActivities.push(myActivity);
        })
        .catch((error) => {
          console.log(error);
        });
    });
  },
};
</script>

<style></style>
