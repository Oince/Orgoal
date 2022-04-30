<template>여기에 로그인 화면 만들기</template>
<script>
export default {
  name: "LoginPage",
  computed: {
    username() {
      // We will see what `params` is shortly
      return this.$route.params.username;
    },
  },
  methods: {
    goToDashboard() {
      console.log("gotodashboard");
    },
  },
};
</script>