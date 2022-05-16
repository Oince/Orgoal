<template>
  <div class="activityDetail">
      <div class="title">
          {{activityID}}
      </div>
  </div>
</template>

<script>
export default {
  name: "ActivityList",
  setup: function() {
    // data
    var activityID;
    // methods
    
    return {activityID};
  },
  created() {
    this.activityID = this.$route.params.id;
  },
  mounted() {
    const axios = require('axios').default;
    const hostName = window.location.hostname;  // 호스트 주소 바뀌어도 대응 가능
    const URI = hostName + '/activity/' + this.activityID;    // API 이거 맞나? 확인 필요
    axios.get(URI)
      .then((response) => {
        console.log("Loaded Activity Detail" + response);  // for Debug
        // TODO : 액티비티 정보 화면에 뿌려주기
      })
      .catch((error)=> {
        console.log(error);
      });
  },
  updated() {

  }
};
</script>

<style scoped>
ul{
  padding:0;
}
li{
  list-style: none;
}
.activitiy-list-container{
  max-width: 800px;
  margin:auto;
}
.activity-list-element{
  display:flex;
  flex-direction: row;
  justify-content: space-between;
  align-content: center;
  padding:10px;
  margin: 5px 0;
  box-sizing: border-box;
  border: 1px #dfdfdf solid;
  border-radius: 10px;
  height: 64px;
}
.activity-list-element>*{
  display:inline-block;
  line-height: 42px;
}
.activity-title{
  display:inline-block;
  width: auto;
  flex:1;
  margin: 0px 20px;
  text-align: left;
  font-size: 16pt;
  font-family: sans-serif;
}

</style>