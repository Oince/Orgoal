// 유저와 관련한 정보를 저장하는 모듈
export const User = {
    namespaced: true,   // namespace 옵션 활성화해서 state들 관리하기
    state: () => ({
        userID: 'test@example.com',
        username: 'test',
        token: '',
    }),
    mutations: {

    },
    actions: {

    },
    getters: {

    }
};
