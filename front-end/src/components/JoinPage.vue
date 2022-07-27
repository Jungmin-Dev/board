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
        <b-button v-if="type==='userId'" variant="info" @click="duplicate(user)"> 중복체크 </b-button>
        <b-button v-if="type==='Email'" variant="info" @click="emailCheck(user)"> 이메일 인증 </b-button>
      </b-col>
    </b-row>
    <b-button variant="info" @click="join">회원가입</b-button>
    <v-alert v-if="duplicateCheck===false" type="error"> 사용중인 아이디 입니다 (중복체크 해주세요) </v-alert>
    <v-alert v-else-if="duplicateCheck===true" type="info"> 아이디 중복체크 완료.</v-alert>

    <v-alert v-if="emailCheck===false" type="error"> 이메일 인증 해주세요 </v-alert>
    <v-alert v-else-if="emailCheck===true" type="info"> 이메일 인증 완료. </v-alert>
  </b-container>
</template>

<script>
import {mapState, mapActions} from "vuex";

export default {
  computed:{
    ...mapState(['duplicateCheck', "user", 'emailCheck'])
  },
  data() {
    return {
      types: [
        'userName',
        'userId',
        'userPassword',
        'Email',
      ],
    }
  },

  methods:{
    ...mapActions(['duplicate', 'join', 'emailCheck'])
  }
}
</script>
