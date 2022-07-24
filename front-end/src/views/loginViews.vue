<template>
  <div>
  <input v-model="user.userId" placeholder="아이디" type="text"/>
  <input v-model="user.userPassword" placeholder="비밀번호" type="text"/>
  <b-button variant="info" @click="login">로그인</b-button>
  <b-button variant="info" @click="join">회원가입</b-button>


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
    join(){
      this.$router.push({
        path : '/board-page/join-page'
      })
    }
  }
}
</script>

<style scoped>

</style>
