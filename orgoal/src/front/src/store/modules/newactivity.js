import axios from "axios";

export const newactivity = {
  namespaced: true,
  state: () => ({
    newAid: "",
  }),
  getters: {
    getNewAid(state) {
      return state.newAid;
    },
  },
  mutations: {
    setNewAid(state, newAid) {
      state.newAid = newAid;
    },
  },
  actions: {
    createNewActivity({ commit }, newactivityInfo, token) {
      let config = {
        headers: {
          token: token,
        },
      };
      console.log("module " + token);
      axios
        .post("/api/activity", newactivityInfo, config)
        .then((res) => {
          alert("새 액티비티가 생성되었습니다!");
          commit("setNewAid", res.data.aid);
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
