import axios from "axios";

export const signup = {
  namespaced: true,
  state: {},
  getters: {},
  mutations: {},
  actions: {
    // 로그인
    async doSignup(signupInfo) {
      let result = false;
      let resultErr = null;
      try {
        //     let signupInfo = {
        //     id: this.memberId,
        //     password: this.memberPassword,
        //     nickname: this.nickname,
        //     question: this.passwordQuestion,
        //     answer: this.passwordAnswer,
        //   };
        const hostname = window.location.hostname;
        const URI = hostname + "/signup";
        let res = await axios.post(URI, signupInfo);
        if (res.status == 201) {
          console.log("회원가입 성공");
          result = true;
        } else {
          console.log("회원가입 실패");
          let err = new Error("Request failed with status code 409");
          err.response = {
            data: { success: false, errormessage: "회원가입에 실패했습니다." },
          };
          resultErr = err;
        }
      } catch (err) {
        console.log(err);
        if (!err.response) {
          err.response = {
            data: { success: false, errormessage: err.message },
          };
        }
        resultErr = err;
      }
      return new Promise((resolve, reject) => {
        if (result) {
          resolve();
        } else {
          reject(resultErr);
        }
      });
    },
  },
};
