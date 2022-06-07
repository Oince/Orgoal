<template>
  <div class="myactivity">
    <div class="list-container">
      <div class="list-head">개설 액티비티</div>
      <ul class="list">
        <li v-for="activity in myActivities" v-bind:key="activity">
          <details class="details-activity">
            <summary class="summary">
              <div class="summary-element">
                <div class="summary-state">
                  <!--액티비티 상태-->
                  <span v-if="activity.state == 'R'">모집중</span>
                  <span v-if="activity.state == 'P'">진행중</span>
                  <span v-if="activity.state == 'E'">완료</span>
                </div>
                <div class="summary-title">
                  <router-link v-bind:to="getURLbyActivityID(activity.aid)">{{
                    activity.title
                  }}</router-link>
                </div>
              </div>
            </summary>
            <div class="more-wrapper">
              <div class="more-head">• 참여자</div>
              <table class="more-table">
                <thead>
                  <tr class="table-head">
                    <th>닉네임</th>
                    <th>이메일</th>
                  </tr>
                </thead>
                <tbody>
                  <tr
                    class="table-body"
                    v-for="person in activity.member"
                    v-bind:key="person"
                  >
                    <td class="member-nickname">
                      <p>{{ person.nickname }}</p>
                    </td>
                    <td class="member-email">
                      <p>{{ person.email }}</p>
                    </td>
                  </tr>
                </tbody>
              </table>
              <div class="more-head">• 신청자</div>
              <table class="more-table">
                <thead>
                  <tr class="table-head">
                    <th>닉네임</th>
                    <th>이메일</th>
                    <th>답변</th>
                    <th></th>
                  </tr>
                </thead>
                <tbody>
                  <tr
                    class="table-body"
                    v-for="person in activity.apply"
                    v-bind:key="person"
                  >
                    <td class="table-nickname">
                      <p>{{ person.nickname }}</p>
                    </td>
                    <td class="table-email">
                      <p>{{ person.email }}</p>
                    </td>
                    <td class="table-answer">
                      <p class="answer">
                        {{ person.answer }}
                      </p>
                    </td>
                    <td class="table-button">
                      <button
                        class="accept-button"
                        @click="doAccept(activity.id)"
                      >
                        수락
                      </button>
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>
          </details>
        </li>
      </ul>
      <div class="list-head">참여 액티비티</div>
      <ul class="list">
        <li v-for="activity in joinedActivities" v-bind:key="activity">
          <details class="details-activity">
            <summary class="summary">
              <div class="summary-element">
                <div class="summary-state">
                  <!--액티비티 상태-->
                  <span v-if="activity.state == 'W'">신청중</span>
                  <span v-if="activity.state == 'A'">참여중</span>
                </div>
                <div class="summary-title">
                  <router-link v-bind:to="getURLbyActivityID(activity.aid)">{{
                    activity.title
                  }}</router-link>
                </div>
              </div>
            </summary>
            <div v-if="activity.state == 'A'" class="more-wrapper">
              <div class="more-head">• 참여자</div>
              <table class="more-table">
                <thead>
                  <tr class="table-head">
                    <th>닉네임</th>
                    <th>이메일</th>
                  </tr>
                </thead>
                <tbody>
                  <tr
                    class="table-body"
                    v-for="person in activity.member"
                    v-bind:key="person"
                  >
                    <td class="member-nickname">
                      <p>{{ person.nickname }}</p>
                    </td>
                    <td class="member-email">
                      <p>{{ person.email }}</p>
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>
          </details>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import { useStore } from "vuex";
import { ref, onMounted } from "vue";
import axios from "axios";

export default {
  name: "MyActivity",
  setup: function () {
    // data
    const store = useStore();
    const token = store.state.signin.token;
    const email = store.state.signin.email;
    let myActivities = ref([]);
    let joinedActivities = ref([]);

    // methods
    function getURLbyActivityID(aid) {
      return "/activity/" + aid.toString();
    }
    function doAccept(aid) {
      let config = {
        headers: {
          token: token,
        },
      };
      let URI =
        "/api/activity/" + aid.toString() + "/accept?email=" + this.email;
      axios
        .post(URI, {}, config)
        .then(() => {
          alert("신청이 수락되었습니다!");
        })
        .catch((err) => console.log(err));
    }
    function updatePage() {
      console.log("화면 재랜더링");
      this.$forceUpdate();
    }

    // lifecycle hook
    onMounted(() => {
      let config = {
        headers: {
          token: token,
        },
      };
      axios.get("/api/mypage/activity", config)
        .then((response) => {
          console.log("GET Mypage Activity SUCCESS");

          // 개설 액티비티 리스트
          response.data.list1.forEach((activity) => {
            // 임시 객체로 원하는 모양 만들기
            let temp = activity;
            // 참여자 받아오기
            axios
              .get("/api/activity/" + activity.aid.toString() + "/member", config)
              .then((response1) => {
                temp.member = response1.data;
                // 신청자 받아오기
                axios
                  .get("/api/activity/" + activity.aid.toString() + "/list", config)
                  .then((response2) => {
                    temp.apply = response2.data;
                    // 받아온 정보 종합하여 push
                    myActivities.value.push(temp);
                    console.log("this is myactivity object");
                    console.log(myActivities);
                  })
                  .catch((err) => {
                    console.log(err);
                  });
              })
              .catch((err) => {
                console.log(err);
              });
          });

          // 참여 액티비티 리스트
          response.data.list2.forEach((activity) => {
            // 임시 객체로 원하는 모양 만들기
            let temp2 = activity;
            // 참여자 받아오기
            if (activity.state == "A") {
              axios
                .get("/api/activity/" + activity.aid.toString() + "/member", config)
                .then((response1) => {
                  temp2.member = response1.data;
                  // 받아온 정보 종합하여 push
                  joinedActivities.value.push(temp2);
                  console.log("this is joinedactivity object");
                  console.log(joinedActivities);
                });
            }
          });
        })
        .catch((err) => {
          console.log(err);
        });
    });

    return {
      token,
      email,
      myActivities,
      joinedActivities,
      getURLbyActivityID,
      doAccept,
      updatePage,
    };
  },
  
};
</script>

<style scoped>
.myactivity {
  width: 800px;
  margin: auto;
}
ul {
  padding: 0;
}
li {
  display: flex;
  justify-content: left;
  list-style: none;
}
.list-head {
  width: 750px;
  margin: 20px 0px 20px 0px;
  text-align: left;
  font-size: 25px;
  font-weight: bolder;
}
.button {
  border: none;
  background-color: white;
  font-size: 20px;
  font-weight: bold;
}
.details-activity {
  border: 1px #dfdfdf solid;
  border-radius: 10px;
  margin: 10px 0px 10px 0px;
  padding: 0px 20px 0px 20px;
}
.summary-element {
  display: inline-flex;
  justify-content: flex-end;
  padding-top: 10px;
  box-sizing: border-box;
  height: 50px;
}
.summary-element > * {
  display: inline-block;
  line-height: 32px;
}
.summary-state {
  min-width: 110px;
  max-width: 110px;
}
.summary-state > span {
  font-size: 14pt;
  font-weight: bold;
}
.summary-title {
  flex: 1;
  text-align: left;
  margin: 0px 10px;
  font-size: 16pt;
  font-family: sans-serif;
  font-weight: bold;
  min-width: 600px;
  max-width: 600px;
}
.more-wrapper {
  width: 750px;
  padding-bottom: 20px;
}
.more-head {
  text-align: left;
  font-size: 14pt;
  font-weight: bolder;
  margin-top: 20px;
  margin-bottom: 10px;
}
table {
  border-collapse: collapse;
  border: 1px solid black;
  border-radius: 10px;
}
.table-head {
  width: 750px;
  margin: 20px 0px 10px 0px;
  text-align: left;
  font-size: 12pt;
  font-weight: bolder;
  border-bottom: 1px solid black;
}
.table-head > * {
  padding-bottom: 10px;
}
th {
  padding: 10px 0px 0px 10px;
}
td {
  padding-left: 10px;
}
.table-body > * {
  padding-right: 10px;
  text-align: left;
}
.member-nickname > p {
  min-width: 150px;
  max-width: 150px;
  word-wrap: break-word;
}
.table-nickname > p {
  min-width: 150px;
  max-width: 150px;
  word-wrap: break-word;
}
.member-email > p {
  min-width: 200px;
  max-width: 200px;
  word-wrap: break-word;
}
.table-email > p {
  min-width: 200px;
  max-width: 200px;
  word-wrap: break-word;
}
.table-answer > p {
  min-width: 270px;
  max-width: 270px;
  word-wrap: break-word;
}
.table-button {
  min-width: 50px;
  max-width: 50px;
}
.accept-button {
  border: 1px solid #dfdfdf;
  border-radius: 10px;
  overflow: visible;
  cursor: pointer;
  color: #fff;
  border-color: #0099d2 !important;
  background: #0099d2 !important;
  font-weight: bold;
}
</style>
