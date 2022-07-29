import Vue from 'vue'
import Vuex from 'vuex'
import {request} from "@/api";
import router from '../router'

Vue.use(Vuex);

const store = new Vuex.Store({
  state: {
    // commit 무조건 사용해야하는 ESLint로 빈 state 생성
    empty:null,
    emailSend:null,
    userInfo: null,
    duplicateCheck: null,
    emailCheck: null,
    user: {
      userEmail: '',
      userName: '',
      userPassword: '',
      selfAuth: '',
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
      payload >= 1 ? state.emailCheck = true : state.emailCheck = false;
    },
    emailSendCheck(state, payload){
      if(payload===1)
        state.emailSend = true;
      else
        state.emailSend = false;
    },
    joinClear(state, payload){
      if(payload === null)
      {
        state.user.userEmail= '';
        state.user.userName= '';
        state.user.userPassword= '';
        state.user.selfAuth= '';
        state.emailSend=null;
        state.duplicateCheck= null;
        state.emailCheck= null;
      }
    },
    empty(state){
      state.empty=null;
    }
  },
  getters:{

  },
  actions: {
    // 회원 가입 시 ID 중복 체크
    async duplicate({commit}, payload){
      if(payload.userEmail !== ''){
        const Check = await request('post', '/auth/duplicate', payload);
        commit('duplicateCheck', Check.userEmail);
      }
    },

    // 회원 가입
    async join({commit}, payload){
      const Check = await request('post', '/auth/join', payload);
      commit('joinClear', Check.user);
      if(Check.user>=1){
        await router.push({
          path: '/',
        })
      }
      else{
        console.log("서버에러");
      }
    },

    // 로그인 시도 -- 다시 짜야함
    async login({commit}, payload) {
      const Check = await request('post', '/auth/login', payload);
      console.log(Check.login);
      if(Check.login === 0){
        commit("loginError");
      }

      else if(Check.login !== 0){
        commit('loginSuccess', payload);
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
    },
    // 인증번호 보내기
    async emailCheckActions({commit}, payload){
      const Check = await request('post', '/auth/emailcheck', payload);
      commit('emailSendCheck', Check.userEmail);
    },

    // 인증번호 확인
    async certificationCheck({commit}, payload){
      const Check = await request('post', '/auth/certificationcheck', payload);
      commit('emailCheck', Check.selfAuth);
    }
  },

})
export default store;
