import Vue from 'vue';
import Vuex from 'vuex';
import Constant from './constant';

Vue.use(Vuex);

const store = new Vuex.Store({
    state: {
        user: null,
    },

    mutations: {
        [Constant.LOGIN]: function(state, payload) {
            state.user = payload.user;
        },
    },

    actions: {
        [Constant.LOGIN]: function(store, payload) {
            store.commit(Constant.LOGIN, payload);
        }
    }
})

export default store;