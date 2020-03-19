<template>
  <v-dialog v-model="dialog" persistent width="30em">
    <template v-slot:activator="{ on }">
      <v-typograph class="rightnavtitle" v-on="on">로그인</v-typograph>
    </template>
    <v-card class="logincard">
      <v-typography>로그인</v-typography>
      <v-text-field label="아이디" name="userid" v-model="user.userid" :rules="rules"></v-text-field>
      <v-text-field
        type="password"
        label="비밀번호"
        name="password"
        v-model="user.password"
        :rules="rules"
      ></v-text-field>
      <v-card-actions class="cardactions" justify-content="center">
        <v-btn color="blue darken-2" text @click="login">
          <b>로그인</b>
        </v-btn>
        <v-btn text @click="dialog = false">취소</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
import Constant from "../assets/constant";
import axios from "axios";

export default {
  data() {
    return {
      dialog: false,
      user: {},
      rules: [value => !!value || "필수입력해야 됩니다."]
    };
  },

  methods: {
    login() {
      axios
        .get("http://localhost:8080/user/selectById", {
          params: {
            userid: this.user.userid,
            password: this.user.password
          }
        })
        .then(res => {
          if (res.data.status == true) {
            this.$store.dispatch(Constant.LOGIN, { user: res.data.data });
            localStorage.token = res.headers["jwt-auth-token"];
            this.dialog = false;
          } else {
            alert("로그인 실패");
          }
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
