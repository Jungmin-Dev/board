import Vue from 'vue';
import VueRouter from 'vue-router'
import Login from '@/views/loginViews'
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
