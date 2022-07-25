<template>
  <v-app>
  <HeaderPage></HeaderPage>
    <router-view></router-view>
  </v-app>
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

    }
  },
}
</script>

<style>

</style>
