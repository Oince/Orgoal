import { createApp } from 'vue'
import App from './App.vue'
import store from './store';
import router from './router'


const orgoal = createApp(App);
orgoal.use(store);
orgoal.use(router);
orgoal.mount('#app');