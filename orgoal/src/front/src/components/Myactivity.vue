<template>
  <h2>내 액티비티</h2>
  <div class="myactivitiy-list-container">
    <ul class="myactivity-list">
      <li v-for="myActivity in myActivities" v-bind:key="myActivity">
        <div class="myactivity-list-element">
          <div class="myactivity-status">
            <!--액티비티 상태-->
            <span v-if="myActivity.status == 'r'">모집중</span>
            <span v-if="myActivity.status == 'p'">진행중</span>
            <span v-if="myActivity.status == 'e'">완료</span>
          </div>
          <div class="myactivity-title">
            <router-link v-bind:to="getURLbyActivityID(myActivity.aid)">{{
              myActivity.title
            }}</router-link>
          </div>
        </div>
      </li>
    </ul>
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

    let myActivities = [];

    // methods
    function getURLbyActivityID(aid) {
      return "/activity/" + aid.toString();
    }
    return { token, myActivities, getURLbyActivityID };
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
