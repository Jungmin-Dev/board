<template>
  <v-container fill-height style='max-width: 500px;' >
    <v-layout align-center row wrap>
      <v-flex xs12>
        <v-card>
          <v-toolbar color="cyan">
            <v-toolbar-title>
              회원가입
            </v-toolbar-title>
          </v-toolbar>
          <v-text-field
              class="pa-3 mt-5"
              label="이메일을 입력해주세요."
              hide-details="auto"
              @blur="duplicate(user)" v-model="user.userEmail">
          </v-text-field>
          <v-alert v-if="duplicateCheck===false" type="error" class="pa-3"> 사용 중인 이메일입니다 </v-alert>
          <v-alert v-if="emailValidate===false" type="error" class="pa-3"> 이메일 형식으로 작성해주세요. </v-alert>
          <v-alert v-else-if="duplicateCheck===true && emailValidate===true" type="info" class="pa-3"> 아주 멋진 이메일 입니다 :)</v-alert>
          <v-text-field
              class="pa-3"
              label="이름"
              hide-details="auto"
              v-model="user.userName">
          </v-text-field>

          <v-text-field
              type="password"
              class="pa-3"
              label="비밀번호"
              hide-details="auto"
              v-model="user.userPassword">
          </v-text-field>

          <v-text-field
              class="pa-3"
              label="이메일"
              hide-details="auto"
              v-model="user.userEmail"
              disabled>
          </v-text-field>

          <b-button :disabled="duplicateCheck===null || emailValidate===false" @click="emailCheckActions(user)" variant="info" class="mt-3" block> 이메일 인증 </b-button>
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

          <b-button v-if="
                        duplicateCheck===true
                        && emailCheck===true
                        && user.userEmail!==''
                        && user.userPassword!==''
                        && user.userName!==''"
                    variant="info" @click="join(user)" block class="mb-3">
            회원가입
          </b-button>
          <v-alert v-else type="warning" class="pa-3 mt-5">
            회원가입 정보를 모두 입력해주세요.
            <br>(이메일 인증이 완료 되면 회원가입이 가능합니다.)
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
    ...mapState(['duplicateCheck', "user", 'emailCheck', 'emailSend', 'emailValidate']),
  },
  data() {
    return{
    }
  },

  methods:{
    ...mapActions(['duplicate', 'join', 'emailCheckActions', 'certificationCheck'])
  }
}
</script>


<style scoped>

</style>
