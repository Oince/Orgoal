import { createApp } from "vue";
import App from "./App.vue";
import store from "./store";
import router from "./router";
import axios from "axios";

const orgoal = createApp(App);
orgoal.use(store);
orgoal.use(router);
orgoal.config.globalProperties.axios = axios;
orgoal.mount("#app");
