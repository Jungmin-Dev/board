<template>
  <div>
    <b-table striped hover :items="items" :per-page="perPage" :current-page="currentPage" :fields="fields" @row-clicked="rowClick"></b-table>
    <b-pagination
        align="center"
        v-model="currentPage"
        :total-rows="rows"
        :per-page="perPage"
        aria-controls="my-table"
    ></b-pagination>
    <b-button @click="writeContent">글쓰기</b-button>
  </div>
</template>

<script>
import data from '@/store/testdata'

export default {
  name: "BoardPage",

  data() {

    let items = data.Content.sort((a,b)=>{
      return b.content_id - a.content_id
    })
    items = items.map(item => {
      return {...item,
        user_name: data.User.filter(userItem => userItem.user_id === item.user_id)[0].name
      }
    })
    return {
      perPage: 10,
      currentPage: 1,
      fields:[
        {
          key: 'content_id',
          label: '글번호'
        },
        {
          key: 'title',
          label: '제목'
        },
        {
          key: 'created_at',
          label: '작성'
        },
        {
          key: 'user_name',
          label: '글쓴이'
        }
      ],
      items: items
    }
  },
  methods:{
    rowClick(item){
      this.$router.push({
        path: `/board-page/detail/${item.content_id}`,
      })
    },
    writeContent(){
      this.$router.push({
        path: '/board-page/create-page'
      })
    }
  },
  computed:{
    rows(){
      return this.items.length
    }
  }
}
</script>
<style scoped>

</style>
