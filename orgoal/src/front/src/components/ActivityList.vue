<template>
  <div class="activitiy-list-container">
    <ul class="activity-list">
      <li v-for="activity in activities" v-bind:key="activity">
        <div class="activity-list-element">
          <div class="activity-status">
            <!--액티비티 상태-->
            <span v-if="activity.status == 'r'">모집중</span>
            <span v-if="activity.status == 'p'">진행중</span>
            <span v-if="activity.status == 'e'">완료</span>
          </div>
          <div class="activity-title">
            <router-link v-bind:to="getURLbyActivityID(activity.id)">{{
              activity.title
            }}</router-link>
          </div>
          <div class="activity-date">
            {{ activity.date }}
          </div>
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "ActivityList",
  setup: function () {
    // data
    var activities = [
      // 테스트용
      {
        id: 111,
        status: "r", //'Recruit', 'Proceeding', 'End'
        title: "(테스트) 상도동 모각코 그룹 모집",
        date: "2022-05-11",
      },
      {
        id: 222,
        status: "p",
        title: "(테스트) 숭실대 축제 같이 갈 사람 모집합니다",
        date: "2022-05-09",
      },
    ];
    // methods
    function getURLbyActivityID(id) {
      return "/activity/" + id.toString();
    }

    return { activities, getURLbyActivityID };
  },
  mounted() {
    const URI = '/search?tag="' + '"&query=' + this.$router.query;
    axios
      .get(URI)
      .then((response) => {
        console.log("Loaded Activity List"); // for Debug
        response.forEach((activity) => this.activities.push(activity));
      })
      .catch((error) => {
        console.log(error);
      });
  },
  updated() {},
};
</script>

<style scoped>
ul {
  padding: 0;
}
li {
  list-style: none;
}
.activitiy-list-container {
  max-width: 800px;
  margin: auto;
}
.activity-list-element {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-content: center;
  padding: 10px;
  margin: 5px 0;
  box-sizing: border-box;
  border: 1px #dfdfdf solid;
  border-radius: 10px;
  height: 64px;
}
.activity-list-element > * {
  display: inline-block;
  line-height: 42px;
}
.activity-title {
  display: inline-block;
  width: auto;
  flex: 1;
  margin: 0px 20px;
  text-align: left;
  font-size: 16pt;
  font-family: sans-serif;
}
</style>
