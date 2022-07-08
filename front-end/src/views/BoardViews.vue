<template>
  <div>
      <table>
        <tr>
          <td>제목</td>
          <td>글쓴이</td>
          <td>내용</td>
        </tr>
        <tr v-for="(item,index) in data" :key="index" @click="detail(index)">
          <td>{{item.title}}</td>
          <td>{{item.writer}}</td>
          <td>{{item.content}}</td>
        </tr>
      </table>
    <button @click="write"> 글쓰기 </button>
  </div>
</template>

<script>
import data from '@/store/testdata'
export default{
  name: 'BoardViews',
  data () {
    return {
      data:data
    }
  },
  methods:{
    write(){
      this.$router.push({
        path: '/create-board',
      })
    },
    detail(index){
      this.$router.push({
        name: 'detail-board',
        params:{
          contentId: index,
        }
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
