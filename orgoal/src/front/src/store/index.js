// store를 각 모듈로 분해하고 나중에 합칠 수 있어서 store의 root 파일명을 index.js로 짓는 게 국룰이라고 함.
import { createStore } from "vuex";
import { User } from "@/store/modules/User";
import { signin } from "@/store/modules/signin";
import { signup } from "@/store/modules/signup";
import { newactivity } from "@/store/modules/newactivity";
import { nickname } from "@/store/modules/nickname";
import { join } from "@/store/modules/join";
import { myactivity } from "@/store/modules/myactivity";

// vuex storage
export default createStore({
  modules: { User, signin, signup, newactivity, nickname, join, myactivity }, // 필요한 경우 여기에 모듈 추가하기
});

// state     state.moduleName.stateName
// getters   computed(() => store.getters["moduleName/getterName"])
// mutation  store.commit("moduleName/mutationName", params)
// action    store.dispatch("moduleName/actionName", params)
