<template>
    <v-app id="inspire">
      <v-navigation-drawer
          v-model="drawer"
          app
      >
        <v-list>
          <v-list-item router :to="{name : 'Login'}" exact>
            <v-list-item-action>
              <v-icon class="mdi mdi-home pa-3"></v-icon>
            </v-list-item-action>
            <v-list-item-content>
              <v-list-item-title>로그인</v-list-item-title>
            </v-list-item-content>
          </v-list-item>
          <v-list-item router :to="{name : 'BoardPage'}" exact>
            <v-list-item-action>
              <v-icon class="mdi mdi-clipboard-text-multiple-outline pa-3"></v-icon>
            </v-list-item-action>
            <v-list-item-content>
              <v-list-item-title >게시판</v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </v-list>

      </v-navigation-drawer>

      <v-app-bar color="indigo" app fixed dark>
        <v-app-bar-nav-icon @click="drawer = !drawer"></v-app-bar-nav-icon>
        <v-toolbar-title @click="home" >자유 게시판</v-toolbar-title>
        <v-spacer></v-spacer>
        <v-toolbar-items>
          <v-row
              align="center"
              justify="space-around"
          >
            <v-menu offset-y v-if="isLogin">
              <template v-slot:activator="{ on, attrs }">
                <v-btn
                    color="indigo"
                    dark
                    v-bind="attrs"
                    v-on="on"
                >
                  <v-icon class="mdi mdi-menu"></v-icon>
                </v-btn>
              </template>
              <v-list>
                <v-list-item>
                  <v-list-item-title @click="logout">로그아웃</v-list-item-title>
                </v-list-item>
              </v-list>
            </v-menu>
          <b-btn
              depressed
              class="pink white--text"
              color="indigo"
              v-else router :to="{name : 'Login'}"> Login </b-btn>
          </v-row>
        </v-toolbar-items>
      </v-app-bar>
      <v-main>
        <router-view></router-view>
      </v-main>
    </v-app>
</template>

<script>
import {mapState, mapActions} from "vuex";
export default {
  name: 'App',

  computed:{
    ...mapState(['isLogin', 'isLoginError'])
  },
  data () {
    return {
    drawer: null,
      items:{
      title: ['마이페이지', '로그아웃']
      }
    }
  },
  methods:{
    ...mapActions(['logout']),
    home(){
      if(this.isLogin){
        this.$router.push({
          path: '/board-page'
        })
      }else if (!this.isLogin){
        this.$router.push({
          path: '/'
        })
      }
    }
  }
}
</script>

<style>

</style>
