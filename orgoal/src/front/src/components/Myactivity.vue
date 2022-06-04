<template>
  <div class="myactivity">
    <div class="myactivity-head">My Activity</div>
    <div class="myactivitiy-table-container">
      <table class="myactivity-table">
        <!-- <thead>
        <tr class="table-head">
          <th>알림</th>
          <th>상태</th>
          <th>액티비티</th>
        </tr>
      </thead> -->
        <tbody>
          <tr
            class="table-body"
            v-for="myActivity in myActivities"
            v-bind:key="myActivity"
          >
            <td class="myactivity-notice">
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
                  :class="[true ? 'red-dot' : 'gray-dot']"
                >
                  <!-- :class="[myActivity.notice ? 'red-dot' : 'gray-dot']" -->
                  <path
                    d="M160 467 c-49 -16 -133 -102 -148 -153 -28 -94 -8 -169 63 -239 102
-103 243 -101 338 5 90 100 90 220 0 320 -65 72 -158 97 -253 67z"
                  />
                </g>
              </svg>
            </td>
            <td class="myactivity-status">
              <span v-if="myActivity.status == 'r'">모집중</span>
              <span v-if="myActivity.status == 'p'">진행중</span>
              <span v-if="myActivity.status == 'e'">완료</span>
            </td>
            <td class="myactivity-title">
              <router-link v-bind:to="getURLbyActivityID(myActivity.aid)">{{
                myActivity.title
              }}</router-link>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import { useStore } from "vuex";
import axios from "axios";

export default {
  name: "MyActivity",
  setup: function () {
    // data
    const store = useStore();
    const token = store.state.signin.token;
    let myActivities = [
      // {
      //   notice: true,
      //   aid: 100,
      //   status: "r",
      //   title: "관악구 풋살 동호회 모집",
      // },
      // {
      //   notice: true,
      //   aid: 101,
      //   status: "p",
      //   title: "(온라인)롤 자유랭크 모집",
      // },
      // {
      //   notice: false,
      //   aid: 102,
      //   status: "e",
      //   title: "분당구 독서토론 모집",
      // },
    ];
    let config = {
      headers: {
        token: this.token,
      },
    };
    axios.get("/api/mypage/activity", config).then((response) => {
      console.log("GET Mypage Activity SUCCESS");
      response.data
        .forEach((myActivity) => {
          myActivities.push(myActivity);
        })
        .catch((error) => {
          console.log(error);
        });
    });
    store.dispatch("myactivity/getMyactivity", myActivities);

    // methods
    function getURLbyActivityID(aid) {
      return "/activity/" + aid.toString();
    }
    return { token, myActivities, getURLbyActivityID };
  },
};
</script>

<style scoped>
.myactivity {
  width: 600px;
  margin: auto;
}
.myactivity-head {
  width: 600px;
  margin: 20px 0px 20px 0px;
  text-align: left;
  font-size: 25px;
}
.myactivitiy-table-container {
  width: 600px;
  margin: auto;
}
.myactivity-table {
  width: 100%;
}
/* .myactivity-status {
} */
.myactivity-title {
  padding-left: 10px;
  text-align: left;
}
.red-dot {
  fill: red;
}
.gray-dot {
  fill: gray;
}
</style>
