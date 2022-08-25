<template>
  <div>
    <div>
      <CommentListItem></CommentListItem>
    </div>
    <CommentCreate :toggle=true :contentId="contentId"></CommentCreate>
  </div>
</template>

<script>
import {mapActions, mapState, mapMutations} from "vuex";
import CommentListItem from "@/components/CommentListItem";
import CommentCreate from "@/components/CommentCreate";
export default{
  name: 'CommentList',
  components:{
    CommentListItem,
    CommentCreate,
  },

  props: {
    contentId : Number,
  },

  computed:{
    ...mapState('Content',['contentComment', 'contentCommentSub'])
  },

  created() {
    this.contentCommentLoad(this.contentId);
    this.contentCommentSubLoad(this.contentId);
  },

  methods:{
    ...mapActions('Content', ['contentCommentLoad', 'contentCommentSubLoad'])
  }
}
</script>

<style scoped>

</style>
