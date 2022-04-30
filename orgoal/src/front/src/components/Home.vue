<template>임시 홈페이지</template>
<script>
export default {
  name: "HomePage",
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