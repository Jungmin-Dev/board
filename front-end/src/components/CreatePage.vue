<template>
  <div>
    <b-input v-model="createInfo.title" placeholder="제목을 입력해 주세요.">{{createInfo.title}}</b-input>
    <b-form-textarea
        v-model="createInfo.context"
        placeholder="내용을 입력해 주세요"
        rows="3"
        max-rows="6"
    >{{createInfo.context}}</b-form-textarea>
    <b-button @click="updateMode ? update() : upload()">저장</b-button>
    <b-button @click="cancle">취소</b-button>
  </div>
</template>

<script>
import {mapActions, mapState, mapMutations} from "vuex";
export default{
  name: 'CreatePage',
  computed:{
    ...mapState(['userInfo']),
    ...mapState("Content", ['contents']),
  },
  created(){
    this.createInfo.userEmail = this.userInfo.userEmail;
  },
  data () {
    return {
      createInfo: {
        title: '',
        context: '',
        userEmail: '',
      },
      updateMode : this.$route.params.contentId > 0 ? true: false,
    }
  },
  methods:{
    ...mapActions("Content", ['contentInsert']),

    cancle(){
      this.$router.push({
        path: '/board-page'
      })
    },
    upload(){
      // Data 삭제 후 DB에 업로드.
      // 객체 형태로 제목 내용 등 받아서 작성하기
      // 작성할 때 로그인 정보 넣어서 로그인된 계정으로 작성되게 만들기
      // 정보들 DB에 Insert하기
      this.contentInsert(this.createInfo);
      this.$router.push({
        path: '/board-page'
      })
      // let items = data.Content.sort((a, b) => {
      //   return b.content_id - a.content_id;
      // })
      // const content_id = items[0].content_id + 1;
      // data.Content.push({
      //   content_id: content_id,
      //   user_id: this.userId,
      //   title: this.subject,
      //   context: this.context,
      //   created_at: this.create_at,
      //   updated_at: null,
      // })

    },
    update(){
      // this.updateObject.title = this.subject;
      // this.updateObject.context = this.context
      // this.updateObject.created_at = this.create_at;
      this.$router.push({
        path: '/board-page'
      })
    }
  }
}
</script>

<style scoped>

</style>
