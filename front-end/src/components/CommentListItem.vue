<template>
  <div>
    <div class="comment-list-item">
      <div class="comment-list-item-name">
        <div>{{ name }}</div>
        <div>{{ commentObject.created_at }}</div>
      </div>
      <div class="comment-list-item-context"> {{commentObject.context}} </div>
      <div class="comment-list-item-button">
        <b-button variant="info">수정</b-button>
        <b-button variant="info" @click="deleteComment">삭제</b-button>
        <b-button variant="info" @click="subCommentToggle">댓글 달기</b-button>
      </div>
    </div>
    <template v-if="subCommentCreateToggle">
      <CommentCreate :isSubComment="true" :commentId="commentObject.comment_id" :reloadSubComments="reloadSubComment" :subCommentToggle="subCommentToggle"></CommentCreate>
    </template>
    <template v-if="subCommentList.length > 0">
      <div
          class="comment-list-item-subcomment-list"
          v-for="item in subCommentList"
          :key="item.user_name">
        <div class="comment-list-item-name">
          <div>{{item.user_name}}</div>
          <div>{{item.created_at}}</div>
        </div>
        <div class="comment-list-item-context">{{item.context}}</div>
        <div class="comment-list-item-button">
          <b-button variant="info">수정</b-button>
          <b-button variant="info">삭제</b-button>
        </div>
      </div>
    </template>
  </div>
</template>

<script>
import data from '@/store/testdata'
import CommentCreate from "@/components/CommentCreate";
export default{
  name: 'CommentListItem',
  components: {CommentCreate},
  props:{
    commentObject : Object,
  },

  data(){
    return {
      name: data.User.filter(
          item => item.user_id === this.commentObject.user_id
      )[0].name,
      subCommentList: data.SubComment.filter(
          item => item.comment_id === this.commentObject.comment_id
      ).map(subCommentItem => ({
        ...subCommentItem,
        user_name: data.User.filter(
            item => item.user_id === subCommentItem.user_id
        )[0].name
      })),
      subCommentCreateToggle: false
    }
  },
  methods:{
    subCommentToggle(){
      this.subCommentCreateToggle = !this.subCommentCreateToggle;
    },
    reloadSubComment(){
      this.subCommentList = data.SubComment.filter(
          item => item.comment_id === this.commentObject.comment_id
      ).map(subCommentItem => ({
        ...subCommentItem,
        user_name: data.User.filter(
            item => item.user_id === subCommentItem.user_id
        )[0].name
      }));
    },
    deleteComment(){
      const comment_index = data.Comment.findIndex(item => item.comment_id === this.commentObject.comment_id)
      data.Comment.splice(comment_index, 1);
      this.$router.push({
        path: '/board-page',
      })
      this.reloadComment();
    },

  }
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
