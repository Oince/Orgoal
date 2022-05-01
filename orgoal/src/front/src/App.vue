<template>
  <navbar />
  <router-view></router-view>
  <br />{{ userString }}
</template>

<script>
import { computed } from "vue"; // computed 속성 사용을 위해 필요
import { useStore } from "vuex";
import navbar from "./components/NavBar.vue";

export default {
  name: "App",
  components: {
    navbar,
  },
  computed: {
    storeGetMsg() {
      return this.$store.getters.getMsg;
    },
  },
  setup: function () {
    // data
    const store = useStore();
    const username = store.state.User.username;

    // computed
    const userString = computed(() => store.state.User.username + "님"); // 테스트용

    // methods

    return { username, userString };
  },
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>
