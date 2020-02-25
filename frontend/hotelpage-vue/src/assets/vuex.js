import Vue from 'vue';
import Vuex from 'vuex';
import Constant from './constant';
import moment from 'moment';
import "moment/locale/ko";

Vue.use(Vuex);

const store = new Vuex.Store({
    state: {
        user: null,
        dates: [
            moment(new Date()).format("YYYY-MM-DD (dd)"),
            moment(new Date())
              .add("days", 1)
              .format("YYYY-MM-DD (dd)")
          ]
    },

    mutations: {
        [Constant.LOGIN]: function(state, payload) {
            state.user = payload.user;
        },
        [Constant.SETDATES]: function(state, payload) {
            state.dates = payload.dates;
        }
    },

    actions: {
        [Constant.LOGIN]: function(store, payload) {
            store.commit(Constant.LOGIN, payload);
        },
        [Constant.SETDATES]: function(store, payload) {
            store.commit(Constant.SETDATES, payload);
        }
    }
})

export default store;