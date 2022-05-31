<template>
  <!-- 임시 홈페이지 마크업 -->
  <div class="home">
    <h1>Home</h1>
    <div v-if="isSignedin">
      <router-link to="/newActivity">
        <button class="navbar-button">게시글 작성</button>
      </router-link>
    </div>
    <activitylist />
  </div>
</template>
<script>
import { computed } from "vue";
import { useStore } from "vuex";
import activitylist from "./ActivityList.vue";
export default {
  name: "HomePage",
  components: {
    activitylist,
  },
  props: {},
  setup: function () {
    // data
    const store = useStore(); // 훅을 사용하여 vuex store 호출
    const isSignedin = computed(() => store.getters["signin/hasToken"]);
    return { isSignedin };
  },
};
</script>
