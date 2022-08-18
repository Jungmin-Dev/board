<template>
  <div class="comment-create" v-if=toggle>
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

  <div v-else-if=updateToggle>
    <b-input-group :prepend="createCommentInfo.userEmail" class="mt-3">
      <b-form-textarea
          id="textarea"
          v-model="createCommentInfo.context"
          :placeholder="contentComment[index].commentContext"
          rows="3"
          max-rows="6"
      > </b-form-textarea>
      <b-input-group-append>
        <b-button variant="info" @click="updateComment()">수정하기</b-button>
      </b-input-group-append>
    </b-input-group>
  </div>

  <div v-else-if="subUpdateToggle">
    <b-input-group :prepend="createCommentInfo.userEmail" class="mt-3">
      <b-form-textarea
          id="textarea"
          v-model="createCommentInfo.context"
          :placeholder="contentCommentSub[index].subCommentContext"
          rows="3"
          max-rows="6"
      > </b-form-textarea>
      <b-input-group-append>
        <b-button variant="info" @click="updateSubComment()">수정하기</b-button>
      </b-input-group-append>
    </b-input-group>
  </div>

</template>

<script>
import {mapActions, mapState, mapMutations} from "vuex";
export default{
  name: 'CommentCreate',
  props:{
    commentId : String,
    isSubComment : Boolean,
    updateToggle : Boolean,
    subUpdateToggle : Boolean,
    toggle : Boolean,
    index : Number,
    subCommentId : String,
  },
  computed:{
    ...mapState(['userInfo']),
    ...mapState('Content',['contentComment', 'contentCommentSub'])
  },
  created() {
    this.createCommentInfo.userEmail = this.userInfo.userEmail;
    this.createCommentInfo.contentId = this.$route.params.contentId;
    this.createCommentInfo.commentId = this.commentId;
    this.createCommentInfo.context = '';
    this.createCommentInfo.subCommentId = this.subCommentId;
  },
  data(){
    return{
      createCommentInfo: {
        userEmail: '',
        contentId: '',
        commentId: '',
        context: '',
        subCommentId : '',
      },
    }
  },

  methods:{
    ...mapActions("Content",['contentCommentCreate', 'contentSubCommentCreate', 'commentUpdate', 'subCommentUpdate']),
    async createComment() {
      await this.contentCommentCreate(this.createCommentInfo);
      this.createCommentInfo.context = "";
      this.$router.go();

    },
    async createSubComment(){
      await this.contentSubCommentCreate(this.createCommentInfo);
      this.createCommentInfo.context = ""
      this.$router.go();
    },

    async updateComment(){
      if(this.createCommentInfo.context == ''){
        alert("수정할 내용을 입력하세요.")
      }
      else{
        await this.commentUpdate(this.createCommentInfo);
        this.createCommentInfo.context = ""
        this.$router.go();
      }
    },
    async updateSubComment(){
      if(this.createCommentInfo.context == ''){
        alert("수정할 내용을 입력하세요.")
      }
      else {
        await this.subCommentUpdate(this.createCommentInfo);
        this.createCommentInfo.context = ""
        this.$router.go();
      }
    },

  }
}
</script>

<style scoped>

</style>
