<template>
  <b-container fluid>
    <b-row class="my-1" v-for="type in types" :key="type">
      <b-col sm="3">
        <label><code>{{ type }}</code>:</label>

      </b-col>
      <b-col sm="3">
        <b-form-input v-model="info[type]"></b-form-input>
      </b-col>
      <b-col sm="3">
        <b-button v-if="type==='Id'" variant="info" @click="duplicate"> 중복체크 </b-button>
      </b-col>
    </b-row>
    <b-button variant="info" @click="join">회원가입</b-button>
    <v-alert v-if="check==='0'" type="error"> 중복체크 해주세요. </v-alert>
  </b-container>
</template>

<script>
import {request} from "@/api";
export default {
  data() {
    return {
      types: [
        'Name',
        'Id',
        'Password',
      ],
      info:{
        userName: '',
        userId: '',
        userPassword: '',
      },
      check : '',
    }
  },
  methods:{
    async duplicate(){
      let result = await request('post', '/auth/duplicate', this.userId)
      if(result!==false)
        this.check='1'
    },
    async join(){
      let result = await request('post', '/auth/join', this.info)
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
