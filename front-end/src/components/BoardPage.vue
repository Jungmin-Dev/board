<template>

  <div>
    <template>
      <v-data-table
          :headers="headers"
          :items="contents"
          item-key="contents.contentId"
          :items-per-page="5"
          class="elevation-1"
          @click:row="rowClick"
      ></v-data-table>
    </template>
    <b-button @click="writeContent">글쓰기</b-button>
  </div>
</template>

<script>
import {mapState, mapActions} from "vuex";

export default {
  name: "BoardPage",


  // items = items.map(item => {
  //   return {...item,
  //     user_name: this.contentList.User.filter(userItem => userItem.userEmail === item.userEmail)[0].name
  //   }
  // })
  data() {

    return {

      headers: [
        {
          text: '글 번호',
          align: 'start',
          sortable: false,
          value: 'contentId',
        },
        { text: '글 제목', value: 'title' },
        { text: '작성자', value: 'userEmail' },
        { text: '작성일', value: 'createdAt' },
      ],
    }
  }
  ,
  methods:
      {
        rowClick(event, { item } )
        {
          console.log(item);
          console.log(this.contents.indexOf(item));
          console.log(this.editedItem = Object.assign({}, item));
          this.$router.push({
            path: `/board-page/detail/${item}`,
          })
        }
        ,
        writeContent()
        {
          this.$router.push({
            path: '/board-page/create-page'
          })
        }
        ,
        ...
            mapActions("Content", ['contentList']),
      }
  ,
  computed: {
    ...
        mapState("Content", ["contents"]),

  }
  ,
  created()
  {
    this.contents = this.contentList();
  }

}
</script>
<style scoped>

</style>
