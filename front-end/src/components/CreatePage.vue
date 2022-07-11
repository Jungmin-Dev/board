<template>
  <div>
    <b-input v-model="subject" placeholder="제목을 입력해 주세요.">{{subject}}</b-input>
    <b-form-textarea
        v-model="context"
        placeholder="내용을 입력해 주세요"
        rows="3"
        max-rows="6"
    >{{ context }}</b-form-textarea>
    <b-button @click="contentId === undefined ? upload() : update()">{{contentId === undefined ? "저장" : "수정"}}</b-button>
    <b-button @click="cancle">취소</b-button>
  </div>
</template>

<script>
import data from '@/store/testdata'
export default{
  name: 'CreatePage',
  data () {
    const contentId = Number(this.$route.params.contentId);
    let contentData = data.Content.filter(item => item.content_id === contentId)[0];
    return {
      contentId: contentId,
      subject: contentId === false ? '' : contentData.title,
      context: contentId === false ? '' : contentData.context,
      userId: 1,
      create_at: new Date(+new Date() + 3240 * 10000).toISOString().split("T")[0] + "   " + new Date().toTimeString().split(" ")[0],
      update_at: null,
    }
  },
  methods:{
    cancle(){
      this.$router.push({
        path: '/board-page'
      })
    },
    upload(){
      let items = data.Content.sort((a, b) => {
        return b.content_id - a.content_id;
      })
      const content_id = items[0].content_id + 1;
      data.Content.push({
        content_id: content_id,
        user_id: this.userId,
        title: this.subject,
        context: this.context,
        created_at: this.create_at,
        updated_at: null,
      })
      this.$router.push({
        path: '/board-page'
      })
    },
    update(){
      this.$router.push({
        path: `/board-page/detail/${this.contentId}`
      })
    }
  }
}
</script>

<style scoped>

</style>
