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
      this.createInfo.title = this.contentDetail.title;
      this.createInfo.context = this.contentDetail.context;
    }
  },
  data () {
    return {
      updateMode : this.$route.params.contentId > 0 ? true: false,
      createInfo: {
        title: this.updateMode ? this.contentDetail.title : '',
        context: this.updateMode ? this.contentDetail.context : '',
        userEmail: this.updateMode ? this.contentDetail.userEmail : '',
        contentId: '',
        image: null,
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
        formData.append('contentId', this.createInfo.contentId);

        if(this.$refs.fileInfo.files.length > -1){
          for ( let i = 0; i < this.$refs.fileInfo.files.length; i++){
            formData.append('image', this.$refs.fileInfo.files[i]);
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
        this.createInfo.contentId = this.$route.params.contentId;
        await this.contentUpdate(this.createInfo);
        await this.$router.push({
          path: `/board-page/detail/${this.createInfo.contentId}`
        })
      }
    },
    fileUpload(){
      this.createInfo.image = this.$refs.fileInfo.files;
    },
  }
}
</script>

<style scoped>

</style>
