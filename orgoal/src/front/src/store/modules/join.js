import axios from "axios";

export const join = {
  namespaced: true,
  state: {},
  getters: {},
  mutations: {},
  actions: {
    // 액티비티 신청
    async joinActivity(activityID, joinInfo) {
      let result = false;
      let resultErr = null;
      try {
        // joinInfo = {
        //      token: this.token,
        //      answer: this.answer,
        // }
        let res = await axios.post(
          "/activity/" + activityID.toString(),
          joinInfo
        );
        if (res.status == 201) {
          console.log("액티비티 신청 완료");
          result = true;
        } else {
          console.log("액티비티 신청 실패");
          let err = new Error("Request failed!");
          err.response = {
            data: {
              success: false,
              errormessage: "액티비티 신청에 실패했습니다.",
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
