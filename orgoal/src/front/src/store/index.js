// store를 각 모듈로 분해하고 나중에 합칠 수 있어서 store의 root 파일명을 index.js로 짓는 게 국룰이라고 함.
import { createStore } from 'vuex';
import { User } from "@/store/modules/User";

// vuex storage
export default createStore({
    modules: { User },      // 필요한 경우 여기에 모듈 추가하기
})
// 참고 : state, getter, action, mutation은 'state.moduleName.stateName'으로 호출