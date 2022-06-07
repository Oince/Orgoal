<template>
  <div class="activitiy-list-container">
    <ul class="activity-list">
      <li v-for="activity in activities" v-bind:key="activity">
        <div class="activity-list-element">
          <div class="activity-status">
            <!--액티비티 상태-->
            <span v-if="activity.state == 'R'">모집중</span>
            <span v-if="activity.state == 'P'">진행중</span>
            <span v-if="activity.state == 'E'">완료</span>
          </div>
          <div class="activity-title-container">
            <router-link v-bind:to="getURLbyActivityID(activity.aid)" class="activity-title">{{
              activity.title
            }}</router-link>
          </div>
          <div class="activity-date">
            {{ activity.lastModification }}
          </div>
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
import { onMounted, ref } from "vue";
import { useRoute } from "vue-router";
export default {
  name: "ActivityList",
  props: ["list_count_max"],
  setup: function (props) {
    // data
    let activities = ref([
      // TODO : 서버와의 통신 잘 되는 것 확인하면 테스트용 데이터 주석처리하기
      {
        aid: 111,
        state: "R", //'Recruit', 'Proceeding', 'End'
        title: "(테스트) 상도동 모각코 그룹 모집",
        lastModification: "2022-05-11",
      },
      {
        aid: 222,
        state: "P",
        title: "(테스트) 숭실대 축제 같이 갈 사람 모집합니다",
        lastModification: "2022-05-09",
      },
    ]);
    // methods
    function getURLbyActivityID(id) {
      return "/activity/" + id.toString();
    }

    // lifecycle hooks
    onMounted(() => {
      const axios = require("axios").default;
      const route = useRoute();
      let query = route.query.query;
      if (query === undefined) query = "";
      const URI = "/api/search?" + "query=" + query;

      axios
        .get(URI)
        .then((response) => {
          console.log("Loaded Activity List"); // for Debug
          activities.value = [];
          response.data.forEach((activity) => {
            let datetime = new Date(activity.lastModification);
            let year = datetime.getFullYear();
            let month = datetime.getMonth() + 1;
            month = (month > 9 ? "" : "0") + month;
            let date = datetime.getDate();
            date = (date > 9 ? "" : "0") + date;
            activity.lastModification = `${year}-${month}-${date}`;
            console.log(activity.lastModification);
            activities.value.push(activity);

            if(props.list_count_max != undefined && props.list_count_max > 0)
            {
              activities.value = activities.value.splice(0, props.list_count_max);
            }
          });
        })
        .catch((error) => {
          console.log(error);
        });
    });
    return { activities, getURLbyActivityID };
  },
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
.activity-title-container {
  display: inline-block;
  width: auto;
  flex: 1;
  margin: 0px 20px;
  text-align: left;
}
.activity-title{
  text-decoration: none;
  color: #404040;
  font-size: 16pt;
  font-family: sans-serif;
  font-weight: 600;
}
</style>