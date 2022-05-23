import axios from "axios";

export const signin = {
  namespaced: true,
  state: () => ({
    // nickname: "",
    token: "",
  }),
  getters: {
    // getNickname(state) {
    //   return state.nickname;
    // },
    hasToken(state) {
      return state.token == "" ? false : true;
    },
  },
  mutations: {
    // nickname 설정
    // setNickname(state, nickname) {
    //   state.nickname = nickname;
    // },
    // accessToken 설정
    setToken(state, token) {
      state.token = token;
    },
    // 초기화
    reset(state) {
      // state.nickname = "";
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
        const hostname = window.location.hostname;
        const URI = hostname + "/signin";
        let res = await axios.post(URI, memberInfo);
        /* 응답 객체 res
          {
            "data": {
              "nickname": dongha
              "token": abcd1234
            }
          }
        */
        if (res.status == 200) {
          console.log("로그인 성공");
          // commit("setNickname", res.data.nickname);
          commit("setToken", res.data.token);
          result = true;
        } else {
          console.log("로그인 실패");
          let err = new Error("Request failed with status code 401");
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
