<template>
  <div class="signup">
    <h1>회원가입</h1>
    <form class="signupform">
      <div>
        <label for="memberIdInput">아이디 (이메일)</label>
        <input
          type="text"
          id="memberIdInput"
          class="input_text"
          ref="memberIdInput"
          v-model.trim="memberId"
        />
      </div>
      <div>
        <label for="memberPasswordInput">비밀번호</label>
        <input
          type="password"
          id="memberPasswordInput"
          class="input_text"
          ref="memberPasswordInput"
          v-model.trim="memberPassword"
        />
      </div>
      <div>
        <label for="passwordConfirmInput">비밀번호 재확인</label>
        <input
          type="password"
          id="passwordConfirmInput"
          class="input_text"
          ref="passwordConfirmInput"
          v-model.trim="passwordConfirm"
        />
      </div>
      <hr />
      <div>
        <label for="nicknameInput">닉네임</label>
        <input
          type="text"
          id="nicknameInput"
          class="input_text"
          ref="nicknameInput"
          v-model.trim="nickname"
        />
      </div>
      <div>
        <label for="passwordQuestionInput">비밀번호 분실 시 질문</label>
        <input
          type="password"
          id="passwordQuestionInput"
          class="input_text"
          ref="passwordQuestionInput"
          v-model.trim="passwordQuestion"
        />
      </div>
      <div>
        <label for="passwordAnswerInput">답변</label>
        <input
          type="password"
          id="passwordAnswerInput"
          class="input_text"
          ref="passwordAnswerInput"
          v-model.trim="passwordAnswer"
        />
      </div>
      <p class="buttons">
        <button @click.prevent="doSignup()" class="button blue">
          가입하기
        </button>
        <button @click.prevent="doCancel()" class="button">취소</button>
      </p>
    </form>
    <p>{{ errorMessage }}</p>
  </div>
</template>

<script>
export default {
  name: "SignUp",
  setup: function () {
    // data
    let memberId = "";
    let memberPassword = "";
    let passwordConfirm = "";
    let nickname = "";
    let passwordQuestion = "";
    let passwordAnswer = "";
    let errorMessage = "";

    // methods
    const doSignup = function () {
      if (this.memberId == "") {
        alert("아이디를 입력하세요.");
        this.$refs.memberIdInput.focus();
        return;
      } else if (this.memberPassword == "") {
        alert("비밀번호를 입력하세요.");
        this.$refs.memberPasswordInput.focus();
        return;
      } else if (this.memberPassword != this.passwordConfirm) {
        alert("비밀번호 재확인이 일치하지 않습니다.");
        this.$refs.passwordConfirm.focus();
        return;
      } else if (this.passwordQuestion == "") {
        alert("비밀번호 분실 시 질문을 입력하세요.");
        this.$refs.passwordQuestionInput.focus();
        return;
      } else if (this.passwordAnswer == "") {
        alert("답변을 입력하세요.");
        this.$refs.passwordAnswerInput.focus();
        return;
      } else if (this.nickname == "") {
        alert("닉네임을 입력하세요.");
        this.$refs.nicknameInput.focus();
        return;
      }

      let signupInfo = {
        id: this.memberId,
        password: this.memberPassword,
        nickname: this.nickname,
        question: this.passwordQuestion,
        answer: this.passwordAnswer,
      };
      this.$store
        .dispatch("signupStore/doSignup", signupInfo)
        .then(() => {
          this.$router.push("/login");
        })
        .catch((err) => {
          this.errorMessage = err.response.data.errormessage;
        });
    };
    const doCancel = function () {
      this.$router.push("../");
    };
    return {
      memberId,
      memberPassword,
      passwordConfirm,
      nickname,
      passwordQuestion,
      passwordAnswer,
      errorMessage,
      doSignup,
      doCancel,
    };
  },
  mounted() {
    this.$refs.memberIdInput.focus();
  },
};
</script>

<style scoped>
.signup {
  width: 800px;
  margin: 20px auto;
}
.signupform {
  width: 400px;
  margin: auto;
}

.signupform div {
  margin-bottom: 20px;
}

.signupform div > label {
  display: block;
  width: 400px;
  font-size: 20px;
  padding-right: 10px;
  text-align: left;
  margin-bottom: 5px;
}
.signupform div > .input_text {
  width: 400px;
  font-size: 20px;
  height: 40px;
}
.buttons {
  position: relative;
  height: 40px;
  margin-top: 50px;
}
.buttons > .button {
  overflow: visible;
  cursor: pointer;
  min-width: 180px;
  height: 40px;
  margin: 0 2px;
  padding: 0 15px;
  line-height: 32px;
  font-size: 20px;
  border: 1px solid #dfdfdf;
  background: #fff;
  border-radius: 10px;
}
.buttons > .button.blue {
  color: #fff;
  border-color: #0099d2 !important;
  background: #0099d2 !important;
}
hr {
  margin-top: 30px;
  margin-bottom: 30px;
}
</style>
