<template>
  <div>
    <div v-for="item in contentComment" :key="item.commentId">
      <CommentListItem></CommentListItem>
    </div>
    <CommentCreate :contentId="contentId"></CommentCreate>
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
    ...mapState('Content',['contentComment'])
  },
  created() {
    this.contentDetailComment(this.contentId);
  },
  data(){
    return{
    }
  },
  methods:{
    ...mapActions('Content', ['contentDetailComment'])
  }
}
</script>

<style scoped>

</style>
