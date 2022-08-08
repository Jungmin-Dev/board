import Vue from 'vue'
import Vuex from 'vuex'
import {request} from "@/api";
import router from '../router'
import Content from "./Content";

Vue.use(Vuex);

const store = new Vuex.Store({
  state: {
    emailSend:null, // 이메일 전송 성공 여부
    userInfo: null, // 유저 정보(현재 이름, 이메일 담음)
    duplicateCheck: null, // 아이디(이메일)중복 체크 여부
    emailCheck: null, // 인증번호 일치 여부
    emailValidate: null, // 이메일 유효성 검사 여부
    emailFind:null, // 비밀번호 찾기 시 존재하는 이메일인지 확인 여부
    user: { // user 정보
      userEmail: '',
      userName: '',
      userPassword: '',
      selfAuth: '',
    },
    isLogin: false, // 로그인 성공 여부
    isLoginError: false, // 로그인 성공 여부
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
    // 로그아웃
    logout(state){
      state.isLogin = false;
      state.isLoginError = false;
      state.userInfo = null;
    },
    // 아이디(이메일) 중복 체크
    duplicateCheck(state, payload){
      payload === null ? state.duplicateCheck = true : state.duplicateCheck = false;
    },
    // 인증번호 일치 여부
    emailCheck(state, payload){
      payload >= 1 ? state.emailCheck = true : state.emailCheck = false;
    },
    // 이메일 전송 성공 여부
    emailSendCheck(state, payload){
      if(payload===1)
        state.emailSend = true;
      else
        state.emailSend = false;
    },
    // 회원 가입시 작성한 정보 초기화
    joinClear(state){
      state.user.userEmail= '';
      state.user.userName= '';
      state.user.userPassword= '';
      state.user.selfAuth= '';
      state.emailSend=null;
      state.duplicateCheck= null;
      state.emailCheck= null;
      state.emailValidate= null;
      state.emailFind = null;
    },
    // 유효성 검사
    Validate(state, payload){
      if(payload===1)
        state.emailValidate=true;
      else
        state.emailValidate=false;
    },
    // 비밀번호 찾기 시 존재하는 이메일인지 확인 여부
    emailFind(state, payload){
      if(payload===1)
        state.emailFind=true;
      else
        state.emailFind=false;
    },
  },
  getters:{

  },
  actions: {
    // 회원 가입 시 ID 중복 체크
    async duplicate({commit}, payload){
      // 이메일 유효성 검사
      if (/^\w+([/.-]?\w+)*@\w+([/.-]?\w+)*(\.\w{2,3})+$/.test(payload.userEmail)){
        commit('Validate', 1);
        if(payload.userEmail !== ''){
          const Check = await request('post', '/auth/duplicate', payload);
          commit('duplicateCheck', Check.userEmail);
        }
      }
      else{
        commit('Validate', 0);
      }
    },

    // 회원 가입
    async join({commit}, payload){
      const Check = await request('post', '/auth/join', payload);
      commit('joinClear');
      if(Check.user>=1){
        await router.push({
          path: '/',
        })
      }
      else{
        console.log("서버에러");
      }
    },

    // 로그인
    async login({commit}, payload) {
      const Check = await request('post', '/auth/login', payload);
      if(Check.login === 0){
        commit("loginError");
      }
      else if(Check.login !== 0){
        commit('loginSuccess', Check.login);
        await router.push({
          name: 'BoardPage',
        })
        commit('joinClear')
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
    },

    // 비밀번호 찾기(아이디 유효 검사)
    async findPw({commit}, payload){
      // 이메일 유효성 검사
      if (/^\w+([/.-]?\w+)*@\w+([/.-]?\w+)*(\.\w{2,3})+$/.test(payload.userEmail)){
        commit('Validate', 1);
        if(payload.userEmail !== ''){
          const Check = await request('post', '/auth/emailfind', payload);
          commit('emailFind', Check.userEmail);
        }else
        {
          commit('emailFind', 0);
        }
      }
      else{
        commit('Validate', 0);
      }
    },
    // 비밀번호 찾기(비밀번호 재설정)
    async changePw({commit}, payload) {
      const Check = await request('post', '/auth/changepw', payload);
      if(Check.changePw>=1){
        await router.push({
          name: 'Login',
        })
      commit('joinClear');
      }
    }
  },
  modules: {
    Content,
  }
})
export default store;
