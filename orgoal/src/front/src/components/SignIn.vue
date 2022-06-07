<template>
  <div class="signin">
    <h1>Orgoal</h1>
    <form class="signinform">
      <p>
        <label for="emailInput">ID</label>
        <input
          type="text"
          id="emailInput"
          class="input_text"
          ref="emailInput"
          v-model.trim="email"
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
        <button @click.prevent="doSignin()" class="button blue">로그인</button>
        <button @click.prevent="doCancel()" class="button">취소</button>
      </p>
    </form>
    <p>{{ errorMessage }}</p>
  </div>
</template>

<script>
import { useStore } from "vuex";

export default {
  name: "SignIn",
  setup: function () {
    // data
    const store = useStore();

    let email = "";
    let memberPassword = "";
    let errorMessage = "";

    // methods
    const doSignin = function () {
      if (this.email == "") {
        alert("아이디를 입력하세요.");
        this.$refs.emailInput.focus();
        return;
      } else if (this.memberPassword == "") {
        alert("비밀번호를 입력하세요.");
        this.$refs.memberPasswordInput.focus();
        return;
      }
      let memberInfo = {
        email: this.email,
        password: this.memberPassword,
      };
      store
        .dispatch("signin/doSignin", memberInfo)
        .then(() => {
          store.commit("signin/setEmail", this.email);
          // 로그인 성공 시 닉네임 받아와서 nickname 모듈에 저장
          console.log(store.state.signin.token);
          store
            .dispatch("nickname/reqNickname", this.email)
            .catch((err2) => console.log(err2));
          this.$router.push("/");
        })
        .catch((err) => {
          this.errorMessage = err.response.data.errormessage;
        });
    };

    const doCancel = function () {
      this.$router.push("../");
    };

    return { email, memberPassword, errorMessage, doSignin, doCancel };
  },
  mounted() {
    this.$refs.emailInput.focus();
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
