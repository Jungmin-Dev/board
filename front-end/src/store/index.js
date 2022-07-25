import Vue from 'vue'
import Vuex from 'vuex'
import {request} from "@/api";
import router from '../router'

Vue.use(Vuex);

const store = new Vuex.Store({
  state: {
    userInfo: null,
    user: {
      userId: null,
      userPassword: null,
    },
    isLogin: false,
    isLoginError: false,
  },
  mutations: {
    // 로그인이 성공 했을 때
    loginSuccess(state, payload) {
      state.isLogin = true;
      state.isLoginError = false;
      state.userInfo = payload;
    },
    // 로그인이 실패 했을 때
    loginError(state) {
      state.isLogin = false;
      state.isLoginError = true;
    }
  },
  actions: {
    // 로그인 시도
    async login({commit}, payload) {
      let result = null;
      result = await request('post', '/auth/login', payload);
      console.log(result===null)
      if(result === null)
        commit("loginError");
      else{
        commit('loginSuccess', result);
        await router.push({
          name: 'BoardPage',
        })
      }
    },
  },
  getters: {

  },
})
export default store;
