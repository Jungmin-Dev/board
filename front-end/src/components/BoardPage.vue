<template>

  <div>
    <div align="right">{{this.$store.state.userInfo}}님 환영합니다.</div>
    <div align="right">{{contents.title}}님 환영합니다.</div>
      <b-table striped hover :items="contents" :per-page="perPage" :current-page="currentPage" :fields="fields" @row-clicked="rowClick"></b-table>
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
import {mapState, mapActions, mapMutations} from "vuex";

export default {
  name: "BoardPage",

  data() {

    // let items = this.contentList.Content.sort((a,b)=>{
    //   return b.content_id - a.content_id
    // })
    // items = items.map(item => {
    //   return {...item,
    //     user_name: this.contentList.User.filter(userItem => userItem.userEmail === item.userEmail)[0].name
    //   }
    // })
    return {
      perPage: 10,
      currentPage: 1,
      fields:[
        {
          key: 'contentId',
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
    },
    list(){
      this.contents = this.contentList();
    },
    ...mapActions("Content",['contentList']),
  },
  computed:{
    ...mapState("Content",["contents"]),
    ...mapMutations("Content",["contentListUpdate"])
    // rows(){
    //   return this.items.length
    // },
  },
  mounted() {
    this.list();

  }
}
</script>
<style scoped>

</style>
