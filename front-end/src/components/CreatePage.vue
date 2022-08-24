<template>
  <div>
    <b-input v-model="createInfo.title" placeholder="제목을 입력해 주세요.">{{createInfo.title}}</b-input>
    <b-form-textarea
        v-model="createInfo.context"
        placeholder="내용을 입력해 주세요"
        rows="3"
        max-rows="6"
    >{{createInfo.context}}</b-form-textarea>
    <input multiple name="uploadFile" ref="fileInfo" @change="fileUpload" class="form-control" type="file">

    <template v-if="updateMode && contentDetail[0].fileName"  >
      <div class="content-detail-file"
           v-for="(item, index) in contentDetail" :key="index">
          {{item.fileName}} |
          {{item.fileSize}}byte
        <b-button variant="danger" @click="listDelete(index)">X</b-button>
      </div>
    </template>
      <h2 class="mt-5" v-if="this.createInfo.file"> ↓ 추가 하는 파일 ↓ </h2>
      <div class="content-detail-file"
           v-for="(item, index) in this.createInfo.file" :key="index">
        <div>
          {{item.name}} |
          {{item.size}}byte
        </div>
      </div>
    <br>
    <b-button @click="updateMode ? update() : upload()">저장</b-button>
    <b-button @click="cancle">취소</b-button>
  </div>
</template>

<script>
import {mapActions, mapState, mapMutations} from "vuex";
export default{
  name: 'CreatePage',
  computed:{
    ...mapState(['userInfo']),
    ...mapState("Content", ['contents', 'contentDetail']),
  },
  created(){
    this.createInfo.userEmail = this.userInfo.userEmail;
    if(this.$route.params.contentId > 0){
      this.createInfo.title = this.contentDetail[0].title;
      this.createInfo.context = this.contentDetail[0].context;
    }
  },
  data () {
    return {
      updateMode : this.$route.params.contentId > 0 ? true: false,
      createInfo: {
        title: this.updateMode ? this.contentDetail[0].title : '',
        context: this.updateMode ? this.contentDetail[0].context : '',
        userEmail: this.updateMode ? this.contentDetail[0].userEmail : '',
        contentId: '',
        file: null,
      },
    }
  },
  methods:{
    ...mapActions("Content", ['contentInsert', 'contentUpdate']),

    cancle(){
      this.$router.push({
        path: '/board-page'
      })
    },
    async upload(){
      if(this.createInfo.title=='' || this.createInfo.title==''){
        alert("제목과 내용을 입력해주세요.");
      }
      else{
        let formData = new FormData();
        formData.append('title', this.createInfo.title);
        formData.append('context', this.createInfo.context);
        formData.append('userEmail', this.createInfo.userEmail);
        formData.append('contentId', '');

        if(this.$refs.fileInfo.files.length > -1){
          for ( let i = 0; i < this.$refs.fileInfo.files.length; i++){
            formData.append('file', this.$refs.fileInfo.files[i]);
          }
        }
        await this.contentInsert(formData);
        await this.$router.push({
          path: '/board-page'
        })
      }
    },
    async update(){

      if(this.createInfo.title=='' || this.createInfo.title==''){
        alert("제목과 내용을 입력해주세요.");
      }
      else{
        let formData = new FormData();
        formData.append('title', this.createInfo.title);
        formData.append('context', this.createInfo.context);
        formData.append('userEmail', this.createInfo.userEmail);
        formData.append('contentId', this.$route.params.contentId);

        if(this.$refs.fileInfo.files.length > -1){
          for ( let i = 0; i < this.$refs.fileInfo.files.length; i++){
            formData.append('file', this.$refs.fileInfo.files[i]);
          }
        }

        await this.contentUpdate(formData);
        await this.$router.push({
          path: `/board-page/detail/${this.$route.params.contentId}`
        })
      }
    },

    fileUpload(){
      this.createInfo.file = this.$refs.fileInfo.files;
    },

    listDelete(index){
      this.contentDetail = this.contentDetail.splice(index, 1);
    },
  }
}
</script>

<style scoped>
.content-detail-file {
  border: 1px solid black;
  margin-top: 1rem;
  padding-top: 1rem;
  min-height: auto;
}
</style>
