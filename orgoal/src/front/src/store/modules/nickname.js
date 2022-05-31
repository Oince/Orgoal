import axios from "axios";
axios.defaults.headers = {
  email: "",
};
// 닉네임을 관리하기 위한 모듈
export const nickname = {
  namespaced: true,
  state: () => ({
    nickname: "",
  }),
  getters: {
    getNickname(state) {
      return state.nickname;
    },
  },
  mutations: {
    setNickname(state, nickname) {
      state.nickname = nickname;
    },
  },
  actions: {
    reqNickname({ commit }, email) {
      let config = {
        headers: {
          email: email,
        },
      };
      axios.get("/nickname", config).then((response) => {
        commit("setNickname", response.data.nickname);
      });
    },
  },
};
