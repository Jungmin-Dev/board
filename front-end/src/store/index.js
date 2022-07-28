import Vue from 'vue'
import Vuex from 'vuex'
import {request} from "@/api";
import router from '../router'

Vue.use(Vuex);

const store = new Vuex.Store({
  state: {
    userInfo: null,
    duplicateCheck: null,
    emailCheck: null,
    user: {
        userName: '',
        userId: '',
        userPassword: '',
        userEmail: '',
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
    },
    logout(state){
      state.isLogin = false;
      state.isLoginError = false;
      state.userInfo = null;
    },
    duplicateCheck(state, payload){
      payload === null ? state.duplicateCheck = true : state.duplicateCheck = false;
    },
    emailCheck(state, payload){
      payload === null ? state.emailCheck = true : state.emailCheck = false;
    },
  },
  getters:{
    duplicateStat: (state) => {
      return state.duplicateCheck;
    }
  },
  actions: {
    // 회원 가입 시 ID 중복 체크
    async duplicate({commit}, payload){
      if(payload.userId !== ''){
      const Check = await request('post', '/auth/duplicate', payload);
        console.log(Check);

        commit('duplicateCheck', Check.userId);
      }
    },

    // 회원 가입
    async join({commit}, payload){
      const Check = await request('post', '/auth/join', payload);
      commit('duplicateCheck', Check.userId);
      if(Check.user>=1){
        await router.push({
          path: '/',
        })
      }
      else{
        console.log("서버에러");
      }
    },
    // 이메일 체크
    async emailCheck({commit}, payload){
      if(payload.Email !== '') {
        const Check = await request('post', '/auth/duplicate', payload);
        commit('emailCheck', Check.Email);
      }
    },

    // 로그인 시도 -- 다시 짜야함
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
    logout({commit}) {
      commit('logout')
      router.push({
        name : 'Login'
      })
    }
  },
})
export default store;
