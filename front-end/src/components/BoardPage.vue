<template>

  <div>
    <div align="right"> {{ this.userInfo.userName }}님 환영합니다.</div>
    <template>
      <v-data-table
          v-if="contents!==null"
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
        rowClick(event, item)
        {
          this.$router.push({
            path: `/board-page/detail/${item.item.contentId}`,
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
    ...mapState("Content", ["contents"]),
    ...mapState(["userInfo"]),

  }
  ,
  async created()
  {
    this.contents = await this.contentList();
  }

}
</script>
<style scoped>

</style>
