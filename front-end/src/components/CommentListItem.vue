<template>
  <div>
    <div v-for="item in contentComment" :key="item.commentId">
      <div class="comment-list-item">
        <div class="comment-list-item-name">
          <div>{{ item?.commentEmail }}</div>
          <div>{{ item?.commentCreatedAt }} </div>
        </div>
        <div class="comment-list-item-context"> {{item?.commentContext}} </div>
        <div class="comment-list-item-button">
          <b-button variant="info" v-if="userInfo.userEmail == item?.commentEmail" @click="updateComment()">수정</b-button>
          <b-button variant="info" v-if="userInfo.userEmail == item?.commentEmail || userInfo.userEmail == 1" @click="deleteComment(item.commentId)">삭제</b-button>
          <b-button variant="info" @click="subCommentToggle(item.commentId)">댓글 달기</b-button>
        </div>
      </div>
      <template v-if="subCommentCreate == item.commentId && toggle==true">
        <CommentCreate :isSubComment="true" :commentId="item.commentId" ></CommentCreate>
      </template>
      <template v-if="contentComment.length > 0">
        <div  v-for="item2 in contentCommentSub" :key="item2.subCommentId">
          <div
              class="comment-list-item-subcomment-list"
              v-if="item2.commentId == item.commentId"
          >
            <div class="comment-list-item-name">
              <div>{{item2?.subCommentEmail }}</div>
              <div>{{item2?.subCommentCreatedAt }}</div>
            </div>
            <div class="comment-list-item-context">
              {{item2?.subCommentContext}}
            </div>
            <div class="comment-list-item-button" v-if="userInfo.userEmail == item2.subCommentEmail">
              <b-button variant="info" v-if="userInfo.userEmail == item2.subCommentEmail" >수정</b-button>
              <b-button variant="info" v-if="userInfo.userEmail == item2.subCommentEmail || userInfo.userEmail == 1" @click="subDeleteComment(item2?.subCommentId)">삭제</b-button>
            </div>
          </div>
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
      subCommentCreate: Number,
      toggle: Boolean,
      commentUpdateToggle: false,
      subCommentUpdateToggle: Number,
    }
  },

  methods: {
    ...mapActions('Content', ['subCommentDelete', 'subCommentUpdate', 'commentDelete', 'commentUpdate']),
    subCommentToggle(commentId) {
      this.subCommentCreate = commentId;
      this.toggle = !this.toggle;
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
