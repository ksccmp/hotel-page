import Vue from 'vue';
import Vuex from 'vuex';
import Constant from './constant';

Vue.use(Vuex);

const store = new Vuex.Store({
    state: {
        hello: '',
    },

    mutations: {
        [Constant.HELLO]: function(state, payload) {
            state.hello = payload.hello;
        },
    },

    actions: {
        [Constant.HELLO]: function(store, payload) {
            store.commit(Constant.HELLO, payload);
        }
    }
})

export default store;