<template>
  <div>
    <b-button class="pa-3 mt-3 ml-3" @click="back" > 게시글 목록 </b-button>
    <b-card v-if="contentDetail !== null">
      <div class="content-detail-content-info">
        <div class="content-detail-content-info-left">
          <div class="content-detail-content-info-left-number">
            {{contentId}}
          </div>
          <div class="content-detail-content-info-left-subject">
            {{contentDetail[0].title}}
          </div>
        </div>
        <div class="content-detail-content-info-right">
          <div class="content-detail-content-info-right-user">
            글쓴이: {{contentDetail[0].userEmail}}
          </div>
          <div class="content-detail-content-info-right-created">
            등록일: {{contentDetail[0].createdAt}}
          </div>
          <div class="content-detail-content-info-right-created" v-if="contentDetail[0].updatedAt">
            수정됨: {{contentDetail[0].updatedAt}}
          </div>
        </div>
      </div>
      <div class="content-detail-content">
        {{contentDetail[0].context}}
      </div>

      <template v-if="contentDetail[0].fileName">
      <div class="content-detail-file"
      v-for="(item, index) in contentDetail" :key="index">
        <a @click="downloadFile(item.uuid)">
          {{item.fileName}} |
          {{item.fileSize}}byte
        </a>
      </div>
      </template>

      <div class="content-detail-button">
        <b-button v-if="userInfo.userEmail == contentDetail[0].userEmail" variant="primary" @click="updateData">수정</b-button>
        <b-button v-if="userInfo.userEmail == contentDetail[0].userEmail || userInfo.userEmail == 1" variant="success" @click="deleteData">삭제</b-button>
      </div>
      <div class="content-detail-comment">
        <CommentList :contentId="contentId"></CommentList>
      </div>
    </b-card>
  </div>
</template>

<script>
import {mapActions, mapState, mapMutations} from "vuex";
import CommentList from "./CommentList";

export default {
  name: "ContentDetail",
  components: {CommentList},

  data() {
    return {
      contentId: Number(this.$route.params.contentId),
    };
  },
  computed: {
    ...mapState("Content", ['contents', "contentDetail", "contentComment"]),
    ...mapState(['userInfo'])

  },
  created() {
    this.contentDetailLoad(Number(this.$route.params.contentId));
  },
  methods: {
    ...mapActions("Content",['contentDetailLoad', "contentList", "contentDelete", "fileDownLoad"]),

    async deleteData() {
      await this.contentDelete(Number(this.$route.params.contentId));

      await this.$router.push({
        path: '/board-page',
      })
    },
    updateData() {
      this.$router.push({
        path: `/board-page/create-page/${this.contentId}`,
      })
    },
    back(){
      this.$router.push({
        path: `/board-page/`,
      })
    },
    downloadFile(uuid){
      this.fileDownLoad(uuid);
    }
  }
};
</script>
<style scoped>
.content-detail-content-info {
  border: 1px solid black;
  display: flex;
  justify-content: space-between;
}
.content-detail-content-info-left {
  width: 720px;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  padding: 1rem;
}
.content-detail-content-info-right {
  width: 300px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding: 1rem;
}
.content-detail-content {
  border: 1px solid black;
  margin-top: 1rem;
  padding-top: 1rem;
  min-height: 720px;
}
.content-detail-button {
  border: 1px solid black;
  margin-top: 1rem;
  padding: 2rem;
}
.content-detail-comment {
  border: 1px solid black;
  margin-top: 1rem;
  padding: 2rem;
}
.content-detail-file {
  border: 1px solid black;
  margin-top: 1rem;
  padding-top: 1rem;
  min-height: auto;
}
</style>
