<template>
  <div>
    <form action="" method="post">
  <input v-model="User.user_id" placeholder="아이디" type="text"/>
  <input v-model="User.user_password" placeholder="비밀번호" type="text"/>
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
      User:{
        user_id: '',
        user_password: '',
      }
    }
  },
  methods:{
    async login(){
      await request('post', '/auth/login', this.User);
    }
  }
}
</script>

<style scoped>

</style>
