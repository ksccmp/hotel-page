import Vue from 'vue';
import App from './App.vue';
import VueRouter from 'vue-router';
import router from './assets/router';
import vuetify from './plugins/vuetify';
import store from './assets/vuex';
import axios from 'axios';

Vue.config.productionTip = false;

Vue.use(VueRouter);

if (localStorage.token) {
  axios
    .get("http://localhost:8080/user/selectByToken", {
      params: {
        token: localStorage.token
      }
    })
    .then(res => {
      store.state.user = res.data.data;
      new Vue({
        router,
        vuetify,
        store,
        render: h => h(App)
      }).$mount('#app')
    })
    .catch(e => {
      console.log(e);
    });
} else {
  new Vue({
    router,
    vuetify,
    store,
    render: h => h(App)
  }).$mount('#app')
}