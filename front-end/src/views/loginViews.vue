<template>
  <div>
    <form action="" method="post">
  <input v-model="user.userId" placeholder="아이디" type="text"/>
  <input v-model="user.userPassword" placeholder="비밀번호" type="text"/>
  <b-button variant="info" @click="login">로그인</b-button>
    </form>
  </div>
</template>

<script>
import {request} from '@/api'
export default{
  name: 'loginViews',

  data(){
    return{
      user:{
        userId: '123',
        userPassword: '123',
      }
    }
  },
  methods:{
    async login(){
      await request('post', '/auth/login', this.user);
    }
  }
}
</script>

<style scoped>

</style>
