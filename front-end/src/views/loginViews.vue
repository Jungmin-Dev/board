<template>
  <v-container fill-height style='max-width: 500px;' >
    <v-layout align-center row wrap>
      <v-flex xs12>
        <v-alert
            class = "mb-3"
            type="error"
            :value="isLoginError"
        >
          아이디와 비밀번호를 확인해주세요.
        </v-alert>
        <v-card>
          <v-toolbar flat color="cyan">
            <v-toolbar-title>
              로그인
            </v-toolbar-title>
          </v-toolbar>
          <div class="pa-3">
          <v-text-field
              v-model="user.userEmail"
              label="아이디(이메일)를 입력하세요">
          </v-text-field>

          <v-text-field
              v-model="user.userPassword"
              label="비밀번호를 입력하세요"
              type="password">
          </v-text-field>
            <b-button block variant="info" @click="join">회원가입</b-button>
            <b-button block variant="info" @click="findPw">비밀번호 찾기</b-button>
            <b-button block variant="info" @click="login(user)">로그인</b-button>
          </div>
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import {mapActions, mapState} from 'vuex'
export default{
  name: 'loginViews',
  data(){
    return{
    }
  },
  computed:{
    ...mapState(['isLogin', 'isLoginError', 'user'])
  },
  methods:{
    ...mapActions(['login']),
    join(){
      this.$router.push({
        path : '/board-page/join-page'
      })
    },
    findPw(){
      this.$router.push({
        name : 'PasswordFind',
      })
    }
  }
}
</script>

<style scoped>

</style>
