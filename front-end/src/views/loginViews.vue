<template>
  <div>
  <input v-model="user.userId" placeholder="아이디" type="text"/>
  <input v-model="user.userPassword" placeholder="비밀번호" type="text"/>
  <b-button variant="info" @click="login">로그인</b-button>
  <b-button variant="info" @click="test">Test</b-button>

  </div>
</template>

<script>
import {request} from '@/api'
export default{
  name: 'loginViews',

  data(){
    return{
      user:{
        userId: '1234',
        userPassword: '1234',
      }
    }
  },
  methods:{
    async login(){
      await request('post', '/auth/login', this.user);
    },
    async test(){
      await request('get', '/auth/test', this.user);

    }
  }
}
</script>

<style scoped>

</style>
