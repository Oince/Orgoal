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
    async createNewActivity({ commit }, newactivityInfo) {
      let result = false;
      let resultErr = null;
      try {
        // newactivityInfo = {
        //   token: this.token,
        //   title: this.title,
        //   content: this.content,
        // }

        const hostname = window.location.hostname;
        const URI = hostname + "/activity";
        let res = await axios.post(URI, newactivityInfo);
        /* 응답 객체 res
          {
            "data": {
              "aid": -
            }
          }
        */
        if (res.status == 201) {
          console.log("새 액티비티 생성 완료");
          commit("setNewAid", res.data.newAid);
          result = true;
        } else {
          console.log("새 액티비티 생성 실패");
          let err = new Error("Request failed with status code 401");
          err.response = {
            data: {
              success: false,
              errormessage: "액티비티 생성에 실패했습니다.",
            },
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
