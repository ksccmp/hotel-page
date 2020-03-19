<template>
  <div>
    <v-row justify="center">
      <v-col cols="6">
        <v-row align="center">
          <v-col cols="2">
            <v-typography class="subtitle-1">
              <b>질문유형</b>
            </v-typography>
          </v-col>
          <v-col cols="10">
            <v-select v-model="request.type" :items="typeItems" label="Type" dense solo></v-select>
          </v-col>
        </v-row>

        <v-row align="center">
          <v-col cols="2">
            <v-typography class="subtitle-1">
              <b>제목</b>
            </v-typography>
          </v-col>
          <v-col cols="10">
            <v-text-field v-model="request.title" label="Title" :rules="rules"></v-text-field>
          </v-col>
        </v-row>

        <v-row align="center">
          <v-col cols="2">
            <v-typography class="subtitle-1">
              <b>내용</b>
            </v-typography>
          </v-col>
          <v-col cols="10">
            <v-textarea v-model="request.contents" solo label="Contents"></v-textarea>
          </v-col>
        </v-row>

        <v-row align="center">
          <v-col cols="2">
            <v-typography class="subtitle-1">
              <b>닉네임</b>
            </v-typography>
          </v-col>
          <v-col cols="10">
            <v-text-field v-model="request.nickname" label="NickName" :rules="rules"></v-text-field>
          </v-col>
        </v-row>

        <v-row align="center">
          <v-col cols="2">
            <v-typography class="subtitle-1">
              <b>전화번호</b>
            </v-typography>
          </v-col>
          <v-col cols="10">
            <v-text-field v-model="request.phone" label="Phone" :rules="rules"></v-text-field>
          </v-col>
        </v-row>

        <v-row align="center">
          <v-col cols="2">
            <v-typography class="subtitle-1">
              <b>이메일</b>
            </v-typography>
          </v-col>
          <v-col cols="10">
            <v-text-field v-model="request.email" label="Email" :rules="rules"></v-text-field>
          </v-col>
        </v-row>

        <v-btn block color="primary" @click="register">등록</v-btn>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import axios from "axios";
import Constant from "../../assets/constant";

export default {
  data() {
    return {
      typeItems: ["예약", "컴플레인", "물품손실", "기타"],
      rules: [value => !!value || "필수입력해야 됩니다."],
      request: {}
    };
  },

  created() {
    if (this.$store.state.user) {
      this.request.nickname = this.$store.state.user.nickname;
      this.request.email = this.$store.state.user.email;
      this.request.phone = this.$store.state.user.phone;
    }
  },

  mounted() {
    if (this.$store.state.user) {
      this.request.nickname = this.$store.state.user.nickname;
      this.request.email = this.$store.state.user.email;
      this.request.phone = this.$store.state.user.phone;
    }
  },

  methods: {
    register() {
      axios
        .post("http://localhost:8080/request/insert", this.request)
        .then(res => {
          if (res.data.data == 1) {
            alert("문의사항이 등록되었습니다.");
            this.$router.push({
              path: Constant.GOMAIN
            });
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
</style>