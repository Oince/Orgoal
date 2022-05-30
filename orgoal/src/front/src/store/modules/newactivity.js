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
    createNewActivity({ commit }, newactivityInfo, config) {
      axios.post("/activity", newactivityInfo, config)
        .then((response) => {
          console.log("새 액티비티 생성 완료");
          commit("setNewAid", response.data.newAid);
          })
        .catch((err) => {
          console.log(err);
        });
      }
  },
};
