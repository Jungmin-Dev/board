<template>
  <div>

  <HeaderPage></HeaderPage>
    <router-view></router-view>
<!--    <v-app>-->
<!--      <v-app-bar-->
<!--          app-->
<!--          color="black-light-2"-->
<!--          dark-->
<!--      >-->
<!--        <v-app-bar-nav-icon @click="drawer = !drawer"></v-app-bar-nav-icon>-->

<!--        <v-spacer />-->

<!--      </v-app-bar>-->
<!--      <v-card-->
<!--          class="mx-auto"-->
<!--      >-->
<!--        <v-navigation-drawer-->
<!--        v-model="drawer"-->
<!--        dark-->
<!--        app-->
<!--        >-->
<!--          <v-list-item>-->
<!--            <v-list-item-content>-->
<!--              <v-list-item-title class="text-h6">-->
<!--                Application-->
<!--              </v-list-item-title>-->
<!--              <v-list-item-subtitle>-->
<!--                subtext-->
<!--              </v-list-item-subtitle>-->
<!--            </v-list-item-content>-->
<!--          </v-list-item>-->

<!--          <v-divider></v-divider>-->

<!--          <v-list-->
<!--              dense-->
<!--              nav-->
<!--          >-->
<!--            <v-list-item-->
<!--                v-for="item in items"-->
<!--                :key="item.title"-->
<!--                link-->
<!--                :to="item.to"-->
<!--                active-class="black-light-3"-->
<!--                class="pa-2"-->
<!--            >-->
<!--              <v-list-item-icon>-->
<!--                <v-icon>{{ item.icon }}</v-icon>-->
<!--              </v-list-item-icon>-->

<!--              <v-list-item-content>-->
<!--                <v-list-item-title>{{ item.title }}</v-list-item-title>-->
<!--              </v-list-item-content>-->
<!--            </v-list-item>-->
<!--          </v-list>-->
<!--        </v-navigation-drawer>-->
<!--      </v-card>-->
<!--      <v-main>-->
<!--        <v-container fluid>-->
<!--          <router-view></router-view>-->
<!--        </v-container>-->
<!--      </v-main>-->
<!--    </v-app>-->
  </div>
</template>

<script>
import HeaderPage from "@/components/HeaderPage";
export default {
  name: 'App',
  components: {
    HeaderPage
  },

  data () {
    return {
      drawer: false,
      items: [
        { title: '홈', icon: 'mdi-home', to:'/home-views' },
        { title: '가입인사', icon: 'mdi-handshake', to:'/greet-views' },
        { title: '자유게시판', icon: 'mdi-view-dashboard', to:'/board-views' },
      ],
      right: null,
    }
  },
}
</script>

<style>

</style>
