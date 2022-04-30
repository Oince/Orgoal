<template>여기에 회원가입 화면 만들기</template>
<script>
export default {
  name: "SignUpPage",
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