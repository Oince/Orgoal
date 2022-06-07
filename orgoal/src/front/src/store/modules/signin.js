import axios from "axios";
// 로그인 요청 및 토큰 관리 모듈
export const signin = {
  namespaced: true,
  state: () => ({
    token: "",
    email: "",
  }),
  getters: {
    hasToken(state) {
      return state.token == "" ? false : true;
    },
    getToken(state) {
      return state.token;
    },
  },
  mutations: {
    // token 설정
    setToken(state, token) {
      state.token = token;
    },
    // email 설정
    setEmail(state, email) {
      state.email = email;
    },
    // 초기화
    reset(state) {
      state.token = "";
    },
  },
  actions: {
    // 로그인
    async doSignin({ commit }, memberInfo) {
      let result = false;
      let resultErr = null;
      try {
        // memberInfo = { email: memberId, password: memberPassword }
        let res = await axios.post("/api/signin", memberInfo);
        /* 응답 객체 res
          {
            "data": {
              "token": abcd1234
            }
          }
        */
        if (res.status == 200) {
          console.log("로그인 성공");
          commit("setToken", res.data.token);
          result = true;
        } else {
          console.log("로그인 실패");
          let err = new Error("Request failed with status code 401!");
          err.response = {
            data: { success: false, errormessage: "로그인에 실패했습니다." },
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
    // 로그아웃
    doLogout({ commit }) {
      commit("reset");
    },
  },
};
