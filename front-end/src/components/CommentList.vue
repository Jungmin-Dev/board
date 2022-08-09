<template>
  <div>

    <div v-for="item in comments" :key="item.comment_id">
      <CommentListItem :commentObject="item"></CommentListItem>
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
    ...mapState('Content',['contentDetail'])
  },
  data(){
    return{
      comments: this.contentDetail,
    }
  },
  methods:{

  }
}
</script>

<style scoped>

</style>
