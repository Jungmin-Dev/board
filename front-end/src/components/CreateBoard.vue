<template>
  <div>
    <input v-model="writer" placeholder="글쓴이"/><br>
    <input v-model="title" placeholder="제목"/><br>
    <textarea v-model="content" placeholder="내용"/><br>
    <button @click="write">작성</button>
  </div>
</template>

<script>
import data from '@/store/testdata'
export default{
  name: 'CreateBoard',
  data () {
    return {
      data: data,
      writer: "",
      title: "",
      content: "",
    }
  },
  methods:{
    write(){
    this.data.push({
      writer: this.writer,
      title: this.title,
      content: this.content,
    })
      this.$router.push({
        path: '/board-views'
      })
    }
  },

  mounted() {
    // Data Base 에서 목록 가져오기 구현
  }
}
</script>

<style scoped>

</style>
