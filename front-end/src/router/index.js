import Vue from 'vue';
import VueRouter from 'vue-router'
import Home from '@/views/HomeViews'
import Board from '@/views/BoardViews'
import Greet from '@/views/GreetViews'
import Login from '@/views/loginViews'
import CreateBoard from '@/components/CreateBoard'
import DetailBoard from '@/components/DetailBoard'
Vue.use(VueRouter);


const routes = [
  {
    path: '/',
    component:Login,
    name:'Login',
  },
  {
    path: 'home-views',
    component:Home,
    name:'Home',
  },
  {
    path: '/board-views',
    component:Board,
    name:'Board',
  },
  {
    path: '/greet-views',
    component:Greet,
    name:'Greet',
  },
  {
    path: '/create-board',
    component:CreateBoard,
    name:'CreateBoard',
  },
  {
    path: '/detail-board/:contentId',
    component:DetailBoard,
    name:'DetailBoard',
  },

]

const router = new VueRouter({
  mode: 'history',
  routes,
})


export default router;
