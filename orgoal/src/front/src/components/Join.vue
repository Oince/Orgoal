<template>
  <div class="join">
    <h2 class="title">Join Activity</h2>
    <form class="joinForm">
      <div>
        <textarea
          type="text"
          id="answerInput"
          class="input_text"
          ref="answerInput"
          v-model.trim="answer"
          placeholder="답변을 입력하세요."
        />
      </div>
    </form>
    <p class="buttons">
      <button @click.prevent="joinActivity()" class="button blue">신청</button>
      <button @click.prevent="doCancel()" class="button">취소</button>
    </p>
    <p>{{ errorMessage }}</p>
  </div>
</template>

<script>
import { useStore } from "vuex";
// import axios from "axios";

export default {
  name: "Join",
  setup: function () {
    // data
    const store = useStore();
    const token = store.state.signin.token;

    let aid;
    let answer = "";

    // methods
    const joinActivity = function () {
      if (this.answer == "") {
        alert("답변을 입력하세요.");
        this.$refs.answerInput.focus();
        return;
      }
      let joinInfo = {
        token: this.token,
        answer: this.answer,
      };
      store
        .dispatch("join/joinActivity", this.aid, joinInfo)
        .then(() => {
          if (confirm("신청이 완료되었습니다. 마이페이지로 이동하시겠습니까?"))
            this.$router.push("/mypage");
          else this.$router.push("/activity/" + this.aid.toString());
        })
        .catch((err) => {
          this.errorMessage = err.response.data.errormessage;
        });
    };

    const doCancel = function () {
      this.$router.push("../");
    };
    return { token, aid, answer, joinActivity, doCancel };
  },
  created() {
    this.aid = this.$route.params.id;
  },
  // mounted() {
  //   const axios = require("axios").default;
  //   const hostName = window.location.hostname;
  //   const getURI = hostName + "/activity/" + this.aid;
  //   const config = {
  //     headers: {
  //       token: this.token,
  //     }
  //   }
  //   axios
  //     .get(getURI, config)
  //     .then((response) => {

  //     })
  //     .catch((error) => {
  //       console.log(error);
  //     });
};
</script>

<style scoped>
.join {
  width: 800px;
  margin: 20px auto;
}
.joinForm {
  width: 400px;
  margin: auto;
}
.joinForm div > #answerInput {
  width: 400px;
  font-size: 20px;
  height: 400px;
  margin: 10px 0 10px 0;
}
.buttons {
  position: relative;
  height: 32px;
  margin-top: 20px;
}
.buttons > .button {
  overflow: visible;
  cursor: pointer;
  min-width: 125px;
  height: 32px;
  margin: 0 2px;
  padding: 0 15px;
  line-height: 32px;
  font-size: 14px;
  border: 1px solid #dfdfdf;
  background: #fff;
  border-radius: 10px;
}
.buttons > .button.blue {
  color: #fff;
  border-color: #0099d2 !important;
  background: #0099d2 !important;
}
</style>
