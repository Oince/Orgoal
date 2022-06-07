// store를 각 모듈로 분해하고 나중에 합칠 수 있어서 store의 root 파일명을 index.js로 짓는 게 국룰이라고 함.
import { createStore } from "vuex";
import { signin } from "@/store/modules/signin";
import { nickname } from "@/store/modules/nickname";
import createPersistedState from "vuex-persistedstate"

// vuex storage
export default createStore({
  modules: { signin, nickname }, // 필요한 경우 여기에 모듈 추가하기
  plugins: [
    createPersistedState(
      { paths: ['signin'], }
    ),
  ]
});

// state     state.moduleName.stateName
// getters   computed(() => store.getters["moduleName/getterName"])
// mutation  store.commit("moduleName/mutationName", params)
// action    store.dispatch("moduleName/actionName", params)
