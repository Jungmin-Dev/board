<template>
  <b-container fluid>
    <b-row class="my-1" v-for="type in types" :key="type">
      <b-col sm="3">
        <label><code>{{ type }}</code>:</label>
      </b-col>
      <b-col sm="3">
        <b-form-input v-model="user[type]"></b-form-input>
      </b-col>
      <b-col sm="3">
        <b-button v-if="type==='userId'" variant="info" @click="duplicate" :disabled="check=== '1'"> 중복체크 </b-button>
      </b-col>
    </b-row>
    <b-button variant="info" @click="join">회원가입</b-button>
    <v-alert v-if="check==='0'" type="error"> 중복체크 해주세요. </v-alert>
    <v-alert v-else-if="check==='1'" type="info"> 중복체크 완료. </v-alert>
  </b-container>
</template>

<script>
import {request} from "@/api";
export default {
  data() {
    return {
      types: [
        'userName',
        'userId',
        'userPassword',
        'Email',
      ],
      user:{
        userName: '',
        userId: '',
        userPassword: '',
        Email: '',
      },
      check : '',
    }
  },
  methods:{
    async duplicate(){
      let result = await request('post', '/auth/duplicate', this.user.userId);
      console.log("result 입니다" + result);
      if(result!==null)
        this.check='1'
      else if(result===null)
        this.check='0'
    },
    async join(){
      let result = await request('post', '/auth/join', this.user);

      if(result!=false && this.check === '1'){
      await this.$router.push({
        path: '/',
      })
      }
      else if(this.check!= '1'){
        this.check='0'
      }
    }
  }
}
</script>
