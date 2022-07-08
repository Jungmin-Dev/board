<template>
  <div>
    <div>{{data.title}}</div>
    <div>{{data.writer}}</div>
    <div>{{data.content}}</div>
  </div>
</template>

<script>
import data from '@/store/testdata'
export default{
  name: 'DetailBoard',
  data(){
   const index = this.$route.params.contentId
    console.log(this.$route)
    console.log(this.$router)
    return{
      data: data[index],
    }
  }
}
</script>

<style scoped>

</style>
