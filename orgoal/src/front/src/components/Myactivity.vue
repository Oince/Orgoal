<template>
  <div class="myactivity">
    <div class="table-container">
      <div class="table-head">개설 액티비티</div>
      <table class="table">
        <thead>
          <tr class="table-head">
            <th width="3%"></th>
            <th width="10%"></th>
            <th width="87%"></th>
          </tr>
        </thead>
        <tbody>
          <tr
            class="table-body"
            v-for="activity in myActivities"
            v-bind:key="activity"
          >
            <td class="table-notice">
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
                  :class="[false ? 'red-dot' : 'gray-dot']"
                >
                  <path
                    d="M160 467 c-49 -16 -133 -102 -148 -153 -28 -94 -8 -169 63 -239 102
-103 243 -101 338 5 90 100 90 220 0 320 -65 72 -158 97 -253 67z"
                  />
                </g>
              </svg>
            </td>
            <td class="table-state">
              <span v-if="activity.state == 'R'">모집중</span>
              <span v-if="activity.state == 'P'">진행중</span>
              <span v-if="activity.state == 'E'">완료</span>
            </td>
            <td class="table-title">
              <router-link v-bind:to="getURLbyActivityID(activity.aid)">{{
                activity.title
              }}</router-link>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <hr />
    <div class="table-container">
      <div class="table-head">참여 액티비티</div>
      <table class="table">
        <thead>
          <tr class="table-head">
            <th width="3%"></th>
            <th width="10%"></th>
            <th width="87%"></th>
          </tr>
        </thead>
        <tbody>
          <tr
            class="table-body"
            v-for="activity in joinedActivities"
            v-bind:key="activity"
          >
            <td class="table-notice">
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
                  :class="[false ? 'red-dot' : 'gray-dot']"
                >
                  <path
                    d="M160 467 c-49 -16 -133 -102 -148 -153 -28 -94 -8 -169 63 -239 102
-103 243 -101 338 5 90 100 90 220 0 320 -65 72 -158 97 -253 67z"
                  />
                </g>
              </svg>
            </td>
            <td class="table-state">
              <!-- api에 따라 수정 필요 -->
              <span v-if="activity.state == 'W'">진행중</span>
            </td>
            <td class="table-title">
              <router-link v-bind:to="getURLbyActivityID(activity.aid)">{{
                activity.title
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
      //   state: "r",
      //   title: "관악구 풋살 동호회 모집",
      // },
      // {
      //   notice: true,
      //   aid: 101,
      //   state: "p",
      //   title: "헬스장 파트너",
      // },
      // {
      //   notice: false,
      //   aid: 102,
      //   state: "e",
      //   title: "분당구 독서토론 모집",
      // },
    ];
    let joinedActivities = [];
    let config = {
      headers: {
        token: token,
      },
    };
    axios.get("/api/mypage/activity", config).then((response) => {
      console.log("GET Mypage Activity SUCCESS");
      response.data.list1
        .forEach((myActivity) => {
          myActivities.push(myActivity);
        })
        .catch((error) => {
          console.log(error);
        });
      response.data.list2
        .forEach((joinedActivity) => {
          joinedActivities.push(joinedActivity);
        })
        .catch((error) => {
          console.log(error);
        });
    });

    // methods
    function getURLbyActivityID(aid) {
      return "/activity/" + aid.toString();
    }
    return { token, myActivities, joinedActivities, getURLbyActivityID };
  },
};
</script>

<style scoped>
.myactivity {
  width: 800px;
  margin: auto;
}
.table {
  table-layout: fixed;
}
td {
  padding: 10px;
  font-size: 18px;
  font-weight: bold;
}
.table-head {
  width: 800px;
  margin: 20px 0px 20px 0px;
  text-align: left;
  font-size: 25px;
  font-weight: bolder;
}
.table-container {
  width: 800px;
  margin: auto;
}
.table-title {
  padding-left: 10px;
  text-align: left;
}
.table-state {
  width: 120px;
}
.red-dot {
  fill: red;
}
.gray-dot {
  fill: gray;
}
</style>
