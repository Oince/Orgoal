import axios from "axios";

export const signin = {
  namespaced: true,
  state: {
    memberId: "",
    accessToken: "",
  },
  getters: {
    hasToken(state) {
      return state.accessToken === "" ? false : true;
    },
  },
  mutations: {
    // memberId 설정
    setMemberId(state, memberId) {
      state.memberId = memberId;
    },
    // accessToken 설정
    setAccessToken(state, accessToken) {
      state.accessToken = accessToken;
    },
    // 초기화
    reset(state) {
      state.memberId = "";
      state.accessToken = "";
    },
  },
  actions: {
    // 로그인
    async doSignin({ commit }, memberInfo) {
      let result = false;
      let resultErr = null;
      try {
        // memberInfo = { id: memberId, password: memberPassword }
        let hostname = window.location.hostname;
        let res = await axios.post(hostname + "/signin", memberInfo);
        /* 응답 객체 res
          {
            "data": {
              "success": true,
              "accessToken": -
            }
          }
        */
        if (res.status == 200) {
          console.log("로그인 성공");
          // commit("setMemberId", memberInfo.id);
          commit("setAccessToken", res.data.token);
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
