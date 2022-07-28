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
        <b-button v-if="type==='userId'" variant="info" @click="duplicate(user)"> 중복 체크 </b-button>
        <b-button v-if="type==='userEmail'" variant="info"> 이메일 인증 </b-button>
      </b-col>
    </b-row>

    <b-button v-if="
                        duplicateCheck===true
                        & user.Email!==''
                        & user.userPassword!==''
                        & user.userId!==''
                        & user.userName!==''"
              variant="info" @click="join(user)" block class="mb-3">
      회원가입
    </b-button>
    <v-alert v-else type="error" class="pa-3">
      회원가입 정보를 모두 입력해주세요.
      <br>(중복 체크가 완료 되면 회원가입이 가능합니다.)
    </v-alert>

    <v-alert v-if="duplicateCheck===false" type="error" class="pa-3"> 사용중인 아이디 입니다 (중복체크 해주세요) </v-alert>
    <v-alert v-else-if="duplicateCheck===true" type="info" class="pa-3"> 아이디 중복체크 완료.</v-alert>

    <v-alert v-if="emailCheck===false" type="error" class="pa-3"> 이메일 인증 해주세요 </v-alert>
    <v-alert v-else-if="emailCheck===true" type="info" class="pa-3"> 이메일 인증 완료. </v-alert>

  </b-container>
</template>

<script>
import {mapState, mapActions} from "vuex";

export default {
  computed:{
    ...mapState(['duplicateCheck', "user", 'emailCheck']),
  },
  data() {
    return {
      types: [
        'userName',
        'userId',
        'userPassword',
        'userEmail',
      ],
    }
  },

  methods:{
    ...mapActions(['duplicate', 'join', 'emailCheck'])
  }
}
</script>
