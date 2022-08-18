<template>
  <div>
    <div v-for="(item, index) in contentComment" :key="item.commentId">
      <div class="comment-list-item">
        <div class="comment-list-item-name">
          <div>{{ item?.commentEmail }}</div>
          <div> 작성일 :<br> {{item.commentCreatedAt }} </div>
          <div v-if="item.commentUpdatedAt "><br> 수정일: <br>{{item.commentUpdatedAt  }}  </div>
        </div>
        <div class="comment-list-item-context"> {{item?.commentContext}} </div>
        <div class="comment-list-item-button">
          <b-button variant="info" v-if="userInfo.userEmail == item?.commentEmail" @click="updateComment(item.commentId)">수정</b-button>
          <b-button variant="info" v-if="userInfo.userEmail == item?.commentEmail || userInfo.userEmail == 1" @click="deleteComment(item.commentId)">삭제</b-button>
          <b-button variant="info" @click="subCommentToggle(item.commentId)">댓글 달기</b-button>
        </div>
      </div>
      <template v-if="subCommentCreate == item.commentId && toggle == true">
        <CommentCreate :index="index" :toggle=toggle :isSubComment="true" :commentId="item.commentId" ></CommentCreate>
      </template>
      <template v-else-if="commentUpdateNumber == item.commentId && updateToggle == true">
        <CommentCreate :index="index" :updateToggle=updateToggle :isSubComment="true" :commentId="item.commentId" ></CommentCreate>
      </template>
      <template v-if="contentComment.length > 0">
        <div  v-for="(item2, index2) in contentCommentSub" :key="index2">
          <div
              class="comment-list-item-subcomment-list"
              v-if="item2.commentId == item.commentId"
          >
            <div class="comment-list-item-name">
              <div>  {{item2?.subCommentEmail }}</div>
              <div> 작성일 : <br>  {{item2?.subCommentCreatedAt }} </div>
              <div v-if="item2.subCommentUpdatedAt"><br> 수정일: <br> {{item2.subCommentUpdatedAt }} </div>
            </div>
            <div class="comment-list-item-context">
              {{item2?.subCommentContext}}
            </div>
            <div class="comment-list-item-button" v-if="userInfo.userEmail == item2.subCommentEmail">
              <b-button variant="info" v-if="userInfo.userEmail == item2.subCommentEmail" @click="updateSubComment(item2.subCommentId)">수정</b-button>
              <b-button variant="info" v-if="userInfo.userEmail == item2.subCommentEmail || userInfo.userEmail == 1" @click="subDeleteComment(item2?.subCommentId)">삭제</b-button>
            </div>
          </div>
          <template v-if="item2.commentId == item.commentId && subCommentUpdateNumber === item2.subCommentId && subUpdateToggle == true">
            <CommentCreate :index="index2" :subUpdateToggle=subUpdateToggle
                           :isSubComment="true" :commentId="item.commentId" :subCommentId="item2.subCommentId" ></CommentCreate>
          </template>
        </div>
      </template>
    </div>
  </div>
</template>

<script>
import {mapActions, mapState, mapMutations} from "vuex";
import CommentCreate from "@/components/CommentCreate";
export default{
  name: 'CommentListItem',
  components: {CommentCreate},
  computed:{
    ...mapState(['userInfo']),
    ...mapState('Content',['contentComment', 'contentCommentSub', 'contentDetail'])
  },
  data(){
    return {
      subCommentCreate: null,
      commentUpdateNumber: null,
      subCommentUpdateNumber: null,
      toggle: false,
      updateToggle : false,
      subUpdateToggle : false,
    }
  },

  methods: {
    ...mapActions('Content', ['subCommentDelete', 'subCommentUpdate', 'commentDelete', 'commentUpdate']),
    subCommentToggle(commentId) {
      console.log("subCommentCreate : " + commentId);
      this.commentUpdateNumber = null;
      this.subCommentUpdateNumber = null;
      this.subUpdateToggle = false;
      this.updateToggle = false;
      this.toggle = true;
      this.subCommentCreate = commentId;
    },

    updateComment(commentId){
      console.log("commentUpdateNumber : " + commentId);
      this.subCommentCreate = null;
      this.subCommentUpdateNumber = null;
      this.toggle = false;
      this.subUpdateToggle = false;
      this.updateToggle = true;
      this.commentUpdateNumber = commentId;
    },

    updateSubComment(subCommentId){
      console.log("subCommentUpdateNumber : " + subCommentId);
      this.subCommentCreate = null;
      this.commentUpdateNumber = null;
      this.toggle = false;
      this.updateToggle = false;
      this.subUpdateToggle = true;
      this.subCommentUpdateNumber = subCommentId;
    },

    deleteComment(commentId){
      this.commentDelete(commentId);
      this.$router.go();
    },
    subDeleteComment(subCommentId){
      this.subCommentDelete(subCommentId);
      this.$router.go();
    },
  },
}
</script>

<style scoped>
.comment-list-item {
  display: flex;
  justify-content: space-between;
  padding-bottom: 1em;
}
.comment-list-item-name {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  border: 0.5px solid black;
  padding: 1em;
}
.comment-list-item-context {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 50em;
  border: 0.5px solid black;
}
.comment-list-item-button {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  border: 0.5px solid black;
  padding: 1em;
}
.btn {
  margin-bottom: 1em;
}
.comment-list-item-subcomment-list {
  display: flex;
  justify-content: space-between;
  padding-bottom: 1em;
  margin-left: 10em;
}
</style>
