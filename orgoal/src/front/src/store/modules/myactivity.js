// import axios from "axios";
// 내 액티비티 기반 알림 관리 모듈
export const myactivity = {
  namespaced: true,
  state: () => ({
    notice: 0,
  }),
  getters: {
    getNotice(state) {
      return state.notice;
    },
  },
  mutations: {
    // notice +1
    plusNotice(state) {
      state.notice += 1;
    },
    // notice -1
    minusNotice(state) {
      state.notice -= 1;
    },
    // 초기화
    reset(state) {
      state.notice = 0;
    },
  },
  actions: {
    // 내 액티비티 불러오기
    // getMyactivity({ commit }, config) {
    //   let myActivities = [];
    //   axios.get("/mypage/activity", config).then((response) => {
    //     console.log("GET Mypage Activity SUCCESS");
    //     response.data
    //       .forEach((myActivity) => {
    //         myActivities.push(myActivity);
    //         if (myActivity.notice == true) commit("plusNotice");
    //       })
    //       .catch((error) => {
    //         console.log(error);
    //       });
    //   });
    //   return myActivities;
    // },
    getMyactivity({ commit }, myActivities) {
      commit("reset");
      myActivities.forEach((myActivity) => {
        if (myActivity.notice == true) commit("plusNotice");
      });
    },
  },
};
