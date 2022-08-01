import Vue from 'vue';
import VueRouter from 'vue-router'
import store from '../store'
Vue.use(VueRouter);

// 라우터 가드
const rejectAuthUser = (to, from, next) =>{
  if(store.state.isLogin === true){
    //이미 로그인 된 유저
    alert('이미 로그인됨');
    next('/board-page');
  }
  else{
    next();
  }
}
// 라우터 가드
const onlyAuthUser = (to, from, next) =>{
  if(store.state.isLogin === true){
    // 이미 로그인 된 유저
    next();
  }
  else{
    alert('로그인하세요');
    next('/')
  }
}
const routes = [
  {
    path: '/',
    name:'Login',
    beforeEnter: rejectAuthUser,
    component: () =>
      import("@/views/loginViews"),
  },
  {
    path: '/board-page',
    name:'BoardPage',
    beforeEnter: onlyAuthUser,
    component: () =>
      import("@/components/BoardPage"),
  },
  {
    path: '/board-page/detail/:contentId',
    name: 'ContentDetail',
    component: () =>
      import("@/components/ContentDetail"),
  },
  {
    path: '/board-page/create-page/:contentId?',
    name: 'CreatePage',
    component: () =>
      import("@/components/CreatePage"),
  },
  {
    path: '/board-page/join-page',
    name: 'JoinPage',
    component: () =>
      import("@/components/JoinPage"),
  },
  {
    path: '/passwordfind',
    name: 'PasswordFind',
    component: () =>
      import("@/components/PasswordFind"),
  },
]

const router = new VueRouter({
  mode: 'history',
  routes,
})


export default router;
