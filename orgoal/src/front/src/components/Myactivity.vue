<template>
  <h2>내 액티비티</h2>
  <div class="myactivitiy-list-container">
    <ul class="myactivity-list">
      <li v-for="myActivity in myActivities" v-bind:key="myActivity">
        <div class="myactivity-list-element">
          <span class="myactivity-notice">
            <!--액티비티 알림-->
            <!-- <icon-base class="dot" icon-name="dot"><icon-dot /></icon-base> -->
            <svg
              version="1.0"
              xmlns="http://www.w3.org/2000/svg"
              width="6pt"
              height="6pt"
              viewBox="0 0 48.000000 48.000000"
              preserveAspectRatio="xMidYMid meet"
            >
              <g
                transform="translate(0.000000,48.000000) scale(0.100000,-0.100000)"
                stroke="none"
                :class="[myActivity.notice ? 'red-dot' : 'gray-dot']"
              >
                <path
                  d="M160 467 c-49 -16 -133 -102 -148 -153 -28 -94 -8 -169 63 -239 102
-103 243 -101 338 5 90 100 90 220 0 320 -65 72 -158 97 -253 67z"
                />
              </g>
            </svg>
          </span>
          <span class="myactivity-status">
            <!--액티비티 상태-->
            <span v-if="myActivity.status == 'r'">모집중</span>
            <span v-if="myActivity.status == 'p'">진행중</span>
            <span v-if="myActivity.status == 'e'">완료</span>
          </span>
          <span class="myactivity-title">
            <router-link v-bind:to="getURLbyActivityID(myActivity.aid)">{{
              myActivity.title
            }}</router-link>
          </span>
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
import { useStore } from "vuex";
// import axios from "axios";

export default {
  name: "MyActivity",
  setup: function () {
    // data
    const store = useStore();
    const token = store.state.signin.token;

    let myActivities = [
      {
        notice: true,
        aid: 100,
        status: "r",
        title: "관악구 풋살 동호회 모집",
      },
      {
        notice: true,
        aid: 101,
        status: "p",
        title: "(온라인)롤 자유랭크 모집",
      },
      {
        notice: false,
        aid: 102,
        status: "e",
        title: "분당구 독서토론 모집",
      },
    ];

    // methods
    function getURLbyActivityID(aid) {
      return "/activity/" + aid.toString();
    }
    return { token, myActivities, getURLbyActivityID };
  },
  mounted() {
    // 테스트를 위해 잠시 주석 처리
    //   let config = {
    //     headers: {
    //       token: this.token,
    //     },
    //   };
    //   axios.get("/mypage/activity", config).then((response) => {
    //     console.log("Loaded Mypage Activity");
    //     response.data
    //       .forEach((myActivity) => {
    //         this.myActivities.push(myActivity);
    //       })
    //       .catch((error) => {
    //         console.log(error);
    //       });
    //   });
  },
};
</script>

<style scoped>
.red-dot {
  fill: red;
}
.gray-dot {
  fill: gray;
}
</style>
