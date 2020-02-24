import Vue from 'vue';
import App from './App.vue';
import VueRouter from 'vue-router';
import router from './assets/router';
import vuetify from './plugins/vuetify';
import store from './assets/vuex';

Vue.config.productionTip = false;

Vue.use(VueRouter);

new Vue({
  router,
  vuetify,
  store,
  render: h => h(App)
}).$mount('#app')
