<template>
  <div class="activity-detail">
    <div class="activity-title-container">
      <div class="activity-title">
        {{ activityTitle }}
      </div>
      <div class="activity-info-bar">
        <div>
          <span class="activity-host-nickname">{{ activityHostNickname }}</span>
          <span class="activity-lastmodification">
            &#8226; {{ activityLastModificationDate }}</span
          >
        </div>
        <div>
          <input type="button" class="activity-apply-button" value="참가신청" />
        </div>
      </div>
    </div>
    <div class="activity-content-container">
      <div class="activity-content">
        <p v-for="paragraph in activityContents" v-bind:key="paragraph">
          {{ paragraph }}
        </p>
      </div>
    </div>
  </div>
</template>

<script>
import { onMounted, ref } from "vue";
import { useRoute } from "vue-router";
export default {
  name: "ActivityList",
  setup: function () {
    // data
    var activityID = "123";
    let activityTitle = ref("동작구 상도동 모각코 그룹 모집"); // 레이아웃 디자인용 placeholder
    var activityContents = ref([
      "대통령이 궐위되거나 사고로 인하여 직무를 수행할 수 없을 때에는 국무총리, 법률이 정한 국무위원의 순서로 그 권한을 대행한다. 모든 국민은 거주·이전의 자유를 가진다. 대법원과 각급법원의 조직은 법률로 정한다. 감사원은 원장을 포함한 5인 이상 11인 이하의 감사위원으로 구성한다. 제안된 헌법개정안은 대통령이 20일 이상의 기간 이를 공고하여야 한다.",
      "국정의 중요한 사항에 관한 대통령의 자문에 응하기 위하여 국가원로로 구성되는 국가원로자문회의를 둘 수 있다. 이 헌법은 1988년 2월 25일부터 시행한다. 다만, 이 헌법을 시행하기 위하여 필요한 법률의 제정·개정과 이 헌법에 의한 대통령 및 국회의원의 선거 기타 이 헌법시행에 관한 준비는 이 헌법시행 전에 할 수 있다.",
      "대한민국의 국민이 되는 요건은 법률로 정한다. 외국인은 국제법과 조약이 정하는 바에 의하여 그 지위가 보장된다. 헌법재판소는 법률에 저촉되지 아니하는 범위안에서 심판에 관한 절차, 내부규율과 사무처리에 관한 규칙을 제정할 수 있다. 법률은 특별한 규정이 없는 한 공포한 날로부터 20일을 경과함으로써 효력을 발생한다.",
      "대통령·국무총리·국무위원·행정각부의 장·헌법재판소 재판관·법관·중앙선거관리위원회 위원·감사원장·감사위원 기타 법률이 정한 공무원이 그 직무집행에 있어서 헌법이나 법률을 위배한 때에는 국회는 탄핵의 소추를 의결할 수 있다. 모든 국민은 주거의 자유를 침해받지 아니한다. 주거에 대한 압수나 수색을 할 때에는 검사의 신청에 의하여 법관이 발부한 영장을 제시하여야 한다.",
    ]);
    var activityHostEmail = ref("admin@orgoal.com");
    var activityHostNickname = ref("김오르골");
    var activityLastModificationDate = ref("2022-05-18");
    // methods

    // lifecycle hook
    onMounted(() => {
      const router = useRoute();
      activityID = router.params.id;
      const axios = require("axios").default;
      const URI = `/api/activity/${activityID}`;
      axios
        .get(URI)
        .then((response) => {
          console.log("Successfully Loaded Activity Detail"); // for Debug
          activityTitle.value = response.data.title;
          activityContents.value = response.data.content.split("\n");
          activityHostEmail.value = response.data.email;
          activityHostNickname.value = response.data.nickname;
          activityLastModificationDate.value = response.data.lastModification;
          // TODO : 액티비티 정보 제대로 화면에 뿌려주는지 확인 필요
        })
        .catch((error) => {
          console.log(error);
          activityTitle.value = "오류!";
          activityContents.value = [`${error.message}`];
        });
    });

    return {
      activityID,
      activityTitle,
      activityContents,
      activityHostEmail,
      activityHostNickname,
      activityLastModificationDate,
    };
  },
};
</script>

<style scoped>
.activity-detail {
  margin: 20px auto;
  width: 800px;
}
.activity-title-container {
  font-family: sans-serif;
  border-bottom: solid 2px #dfdfdf;
  padding: 0 0 10px 0;
  margin: 50px 0 20px 0;
}
.activity-title {
  font-size: 20pt;
  font-weight: bold;
  margin: 0 0 10px 0;
}
.activity-info-bar {
  display: flex;
  justify-content: space-between;
}
.activity-host-nickname {
  color: 000000;
}
.activity-apply-button {
  overflow: visible;
  cursor: pointer;
  min-width: 125px;
  height: 32px;
  margin: 0 2px;
  padding: 0 15px;
  line-height: 32px;
  font-size: 14px;
  border: 1px solid #0099d2;
  border-radius: 10px;
  color: #fff;
  background: #0099d2 !important;
}
.activity-content-container {
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  min-height: 200px;
}
</style>