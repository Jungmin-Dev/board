<template>
  <div>

    <div v-for="item in comments" :key="item.comment_id">
      <CommentListItem :commentObject="item"></CommentListItem>
    </div>
    <CommentCreate :contentId="contentId" :reloadComment="reloadComment"></CommentCreate>
  </div>
</template>

<script>
import data from '@/store/testdata'
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
  data(){
    return{
      comments: data.Comment.filter(item => item.content_id === this.contentId)
    }
  },
  methods:{
    reloadComment(){
      this.comments = data.Comment.filter(item => item.content_id === this.contentId)
    }
  }
}
</script>

<style scoped>

</style>
