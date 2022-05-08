import axios from "axios";

const signupStore = {
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
        let res = await axios.post("http://localhost:8080/signup", signupInfo);
        /* 응답 객체 res
          {
            "data": {
              "success": true
            }
          }
        */
        if (res.data.success == true) {
          console.log("회원가입 성공");
          result = true;
        } else {
          console.log("회원가입 실패");
          let err = new Error("Request failed with status code 401");
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

export default signupStore;
