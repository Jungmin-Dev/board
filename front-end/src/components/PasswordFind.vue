<template>
  <v-container fill-height style='max-width: 500px;' >
    <v-layout align-center row wrap>
      <v-flex xs12>
        <v-card>
          <v-toolbar color="cyan">
            <v-toolbar-title>
              비밀번호 찾기
            </v-toolbar-title>
          </v-toolbar>
          <v-text-field
              :disabled="emailFind===true"
              class="pa-3 mt-5"
              label="이메일을 입력해주세요."
              hide-details="auto"
              v-model="user.userEmail">
          </v-text-field>
          <b-button :disabled="user.userEmail==='' || emailFind===true" @click="findPw(user)" variant="info" class="mt-3" block> 이메일 확인 </b-button>
          <v-alert v-if="emailValidate===false" type="error" class="pa-3"> 이메일 형식으로 작성해주세요. </v-alert>
          <v-alert v-if="emailFind===true && emailValidate===true" type="info" class="pa-3"> 이메일 확인 되었습니다. :)<br> 아래 이메일 인증 해주세요.</v-alert>
          <v-alert v-else-if="emailFind===false" type="error" class="pa-3"> 이메일을 다시 확인해주세요.</v-alert>

          <b-button :disabled="emailFind===false || emailValidate===false" @click="emailCheckActions(user)" variant="info" class="mt-3" block> 이메일 인증 </b-button>
          <v-alert v-if="emailSend===true" type="info" class="pa-3 mt-3"> 멋진 이메일로 인증번호를 전송했습니다. :) <br> 인증번호를 확인해주세요 </v-alert>
          <v-alert v-else-if="emailSend===false" type="error" class="pa-3 mt-3"> 인증번호 전송에 실패했습니다. <br> 다시 시도해주세요 </v-alert>

          <v-text-field
              class="pa-3 mt-3"
              label="인증번호"
              hide-details="auto"
              :disabled="emailSend === null || emailSend === false || emailCheck===true"
              v-model="user.selfAuth"
          >
          </v-text-field>
          <!--요기 아래 이벤트에는 인증번호 일치하는지 확인하는 걸루 짜서 다시 연결-->
          <b-button v-if="emailSend===true" @click="certificationCheck(user)" variant="info" class="mt-3" block> 인증하기 </b-button>

          <v-alert v-if="emailCheck===true" type="info" class="pa-3 mt-3"> 이메일 인증 성공. </v-alert>
          <v-alert v-if="emailCheck===false" type="error" class="pa-3 mt-3"> 인증번호 불일치. </v-alert>
          <v-text-field
              v-if="emailCheck===true"
              type="password"
              class="pa-3"
              label="비밀번호"
              hide-details="auto"
              v-model="tempPassword">
          </v-text-field>

            <v-text-field
                v-if="emailCheck===true"
                type="password"
                class="pa-3"
                label="비밀번호 확인"
                hide-details="auto"
                v-model="user.userPassword"
                @keyup = "passwordCheck">
            </v-text-field>

          <v-alert v-if="passwordState===true" type="info" class="pa-3 mt-3"> 비밀번호가 일치합니다. </v-alert>
          <v-alert v-if="passwordState===false" type="error" class="pa-3 mt-3"> 비밀번호가 일치하지 않습니다. </v-alert>

          <b-button v-if="
                        emailFind===true
                        && emailCheck===true
                        && user.userEmail!==''
                        && passwordState === true
                        "
                    variant="info" @click="changePw(user)" block class="mb-3">
            비밀번호 변경
          </b-button>
          <v-alert v-else type="warning" class="pa-3 mt-5">
            회원가입 정보를 모두 입력해주세요.
          </v-alert>
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>
<script>
import {mapState, mapActions} from "vuex";

export default {
  computed:{
    ...mapState(['duplicateCheck', "user", 'emailCheck', 'emailSend', 'emailValidate', 'emailFind']),
  },
  data() {
    return{
      tempPassword: '',
      passwordState: null,
    }
  },

  methods:{
    ...mapActions(['duplicate', 'join', 'emailCheckActions', 'certificationCheck', 'findPw', 'changePw']),
    passwordCheck(){
      console.log(this.user.userPassword);
      console.log(this.tempPassword);
      if(this.user.userPassword === this.tempPassword){
        this.passwordState = true;
      }else
        this.passwordState = false;
    }
  }
}
</script>


<style scoped>

</style>
