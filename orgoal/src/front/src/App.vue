<template>
  <navbar />
  <router-view :key="$route.fullPath"></router-view>
</template>

<script>
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

    // methods
    let goHome = function() {
      this.$route.push({path: '/'});
    }

    return { goHome };
  },
};
</script>

<style>
body {
  margin: 0;
}
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
navbar {
  position: sticky;
  top: 0;
}
</style>
