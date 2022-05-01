<template>
  <div class="signin">
    <h1>Orgoal</h1>
    <form class="signinform">
      <p>
        <label for="memberIdInput">ID</label>
        <input
          type="text"
          id="memberIdInput"
          class="input_text"
          ref="memberIdInput"
          v-model.trim="memberId"
          placeholder="아이디"
        />
      </p>
      <p>
        <label for="memberPasswordInput">PW</label>
        <input
          type="password"
          id="memberPasswordInput"
          class="input_text"
          ref="memberPasswordInput"
          v-model.trim="memberPassword"
          placeholder="비밀번호"
        />
      </p>
      <p class="buttons">
        <button @click.prevent="doSignin" class="button blue">로그인</button>
        <button @click.prevent="doCancel" class="button">취소</button>
      </p>
    </form>
    <p>{{ errorMessage }}</p>
  </div>
</template>

<script>
export default {
  name: "SignIn",
  data: function () {
    return {
      memberId: "",
      memberPassword: "",
      errorMessage: "",
    };
  },
  methods: {
    doSignin() {
      if (this.memberId == "") {
        alert("아이디를 입력하세요.");
        this.$refs.memberIdInput.focus();
        return;
      } else if (this.memberPassword == "") {
        alert("비밀번호를 입력하세요.");
        this.$refs.memberPasswordInput.focus();
        return;
      }
      let memberInfo = { id: this.memberId, password: this.memberPassword };
      this.$store
        .dispatch("signinStore/doSignin", memberInfo)
        .then(() => {
          // this.$router.push( url );
        })
        .catch((err) => {
          this.errorMessage = err.response.data.errormessage;
        });
    },
    doCancel() {
      this.$router.push("../");
    },
  },
  mounted() {
    this.$refs.memberIdInput.focus();
  },
};
</script>

<style scoped>
.signin {
  width: 800px;
  margin: 20px auto;
}
.signinform {
  width: 400px;
  margin: auto;
}
.signinform p > label {
  display: inline-block;
  width: 100px;
  font-size: 14px;
  padding-right: 10px;
}
.signinform p > .input_text {
  width: 200px;
  font-size: 14px;
  height: 32px;
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
