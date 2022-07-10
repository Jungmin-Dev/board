<template>
  <div>
    <div>{{data.title}}</div>
    <div>{{data.writer}}</div>
    <div>{{data.content}}</div>
    <button @click="updateData">수정</button>
    <button @click="deleteData">삭제</button>
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
      index: index,
    }
  },
  methods:{
    updateData(){
      this.$router.push({
        name: 'CreateBoard',
        params:{
          contentId: this.index,
        }
      })
    },
    deleteData(){
      data.splice(this.index, 1)
      this.$router.push({
        path:'/board-views',
      })
    }
  }
}
</script>

<style scoped>

</style>
