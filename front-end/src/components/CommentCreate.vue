<template>
  <div class="comment-create" v-if="!commentUpdateToggle">
    <b-input-group :prepend="createCommentInfo.userEmail" class="mt-3">
      <b-form-textarea
          id="textarea"
          v-model="createCommentInfo.context"
          :placeholder="isSubComment ? '덧글에 덧글을 달아주세요~!' : '코멘트를 달아주세요~!'"
          rows="3"
          max-rows="6"
      ></b-form-textarea>
      <b-input-group-append>
        <b-button variant="info" @click="isSubComment ? createSubComment() : createComment()">작성하기</b-button>
      </b-input-group-append>
    </b-input-group>
  </div>
  <div v-else-if="commentUpdateToggle">
    <b-input-group :prepend="createCommentInfo.userEmail" class="mt-3">
      <b-form-textarea
          id="textarea"
          v-model="contentComment.context"
          :placeholder=contentComment.context
          rows="3"
          max-rows="6"
      ></b-form-textarea>
      <b-input-group-append>
        <b-button variant="info" @click="isSubComment ? createSubComment() : createComment()">수정하기</b-button>
      </b-input-group-append>
    </b-input-group>
  </div>
</template>

<script>
import {mapActions, mapState, mapMutations} from "vuex";
export default{
  name: 'CommentCreate',
  props:{
    contentId : Number,
    commentId : Number,
    isSubComment : Boolean,
    commentUpdateToggle : Boolean,
  },
  computed:{
    ...mapState(['userInfo']),
    ...mapState('Content',['contentComment'])

  },
  created() {
    this.createCommentInfo.userEmail = this.userInfo.userEmail;
    this.createCommentInfo.contentId = this.$route.params.contentId;
    this.createCommentInfo.commentId = this.commentId;
    this.createCommentInfo.context = '';
  },
  data(){
    return{
      createCommentInfo: {
        userEmail: '',
        contentId: '',
        commentId: '',
        context: '',
      }
    }
  },

  methods:{
    ...mapActions("Content",['contentCommentCreate', 'contentSubCommentCreate']),
    async createComment() {
      await this.contentCommentCreate(this.createCommentInfo);
      this.createCommentInfo.context = "";
      this.$router.go();

    },
    async createSubComment(){
      await this.contentSubCommentCreate(this.createCommentInfo);
      this.createCommentInfo.context = ""
      this.$router.go();
    }
  }
}
</script>

<style scoped>

</style>
