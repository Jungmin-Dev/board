import Vue from 'vue';
import VueRouter from 'vue-router'
import Home from '@/views/HomeViews'
import Board from '@/views/BoardViews'
import Greet from '@/views/GreetViews'
import Login from '@/views/loginViews'
import CreateBoard from '@/components/CreateBoard'
import DetailBoard from '@/components/DetailBoard'
import BoardPage from "@/components/BoardPage";
import ContentDetail from "@/components/ContentDetail"
import CreatePage from "@/components/CreatePage"

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
    path: '/create-board/:contentId?',
    component:CreateBoard,
    name:'CreateBoard',
  },
  {
    path: '/detail-board/:contentId',
    component:DetailBoard,
    name:'DetailBoard',
  },
  {
    path: '/board-page',
    component:BoardPage,
    name:'BoardPage',
  },
  {
    path: '/board-page/detail/:contentId',
    name: 'ContentDetail',
    component: ContentDetail
  },
  {
    path: '/board-page/create-page/:contentId?',
    name: 'CreatePage',
    component: CreatePage
  },
]

const router = new VueRouter({
  mode: 'history',
  routes,
})


export default router;
