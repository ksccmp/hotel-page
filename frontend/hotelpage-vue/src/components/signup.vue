<template>
  <v-dialog v-model="dialog" persistent width="30em">
    <template v-slot:activator="{ on }">
      <span class="rightnavtitle" v-on="on">회원가입</span>
    </template>
    <v-card class="logincard">
      <v-typography>회원가입</v-typography>
      <v-text-field label="아이디" name="userid" v-model="user.userid" :rules="rules"></v-text-field>
      <v-text-field
        label="비밀번호"
        name="password"
        v-model="user.password"
        type="password"
        :rules="rules"
      ></v-text-field>
      <v-text-field label="닉네임" name="nickname" v-model="user.nickname" :rules="rules"></v-text-field>
      <v-radio-group row name="gender" v-model="user.gender">
        <v-radio label="남성" value="남성"></v-radio>
        <v-spacer></v-spacer>
        <v-radio label="여성" value="여성"></v-radio>
      </v-radio-group>
      <v-text-field label="나이" name="age" v-model="user.age" :rules="rules"></v-text-field>
      <v-menu v-model="datepicker" :close-on-content-click="false" max-width="290">
        <template v-slot:activator="{ on }">
          <v-text-field
            :value="user.birth"
            clearable
            label="생년월일"
            readonly
            v-on="on"
            @click:clear="user.birth = null"
          ></v-text-field>
        </template>
        <v-date-picker v-model="user.birth" @change="datepicker = false"></v-date-picker>
      </v-menu>
      <v-text-field label="전화번호" name="phone" v-model="user.phone" :rules="rules"></v-text-field>
      <v-text-field label="이메일" name="email" v-model="user.email" :rules="rules"></v-text-field>
      <v-card-actions class="cardactions">
        <v-btn color="blue darken-2" text @click="clickSignup">
          <b>회원가입</b>
        </v-btn>
        <v-btn text @click="dialog = false">취소</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
import axios from "axios";
import moment from "moment";

export default {
  data() {
    return {
      dialog: false,
      datepicker: false,
      user: {
        gender: "남성",
        position: "브론즈",
        birth: moment(new Date()).format("YYYY-MM-DD")
      },
      rules: [value => !!value || "필수입력해야 됩니다."]
    };
  },

  methods: {
    clickSignup() {
      axios
        .post("http://localhost:8080/user/insert", this.user)
        .then(res => {
          console.log(res.data.data);
          this.dialog = false;
        })
        .catch(e => {
          console.log(e);
        });
    }
  }
};
</script>

<style scoped>
.rightnavtitle {
  color: white;
  margin-left: 1em;
  cursor: pointer;
}
.logincard {
  padding: 2em;
  text-align: center;
}
.cardactions {
  text-align: center;
  justify-content: center;
}
</style>
