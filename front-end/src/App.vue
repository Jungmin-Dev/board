<template>
  <div>
  <HeaderPage></HeaderPage>
    <router-view></router-view>
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

    }
  },
}
</script>

<style>

</style>
