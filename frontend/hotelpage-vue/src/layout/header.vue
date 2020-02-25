<template>
  <div>
    <v-app-bar color="teal lighten-3">
      <v-container color="teal lighten-3">
        <v-row>
          <v-col cols="4">
            <v-row>
              <v-typography class="leftnavtitle">소개</v-typography>
              <v-typography class="leftnavtitle" @click="register">예약하기</v-typography>
              <v-typography class="leftnavtitle">예약확인</v-typography>
              <v-typography class="leftnavtitle">방보기</v-typography>
            </v-row>
          </v-col>
          <v-col cols="4">
            <v-row justify="center">
              <v-typography class="centernavtitle" @click="main">SC Hotel</v-typography>
            </v-row>
          </v-col>
          <v-col cols="4">
            <v-row justify="end">
              <v-typography class="rightnavtitle">게시판</v-typography>
              <Login v-if="user == null"></Login>
              <Signup v-if="user == null"></Signup>
              <v-typography class="logintitle" v-if="user != null">반갑습니다 {{ user.nickname }}님</v-typography>
              <v-typography class="rightnavtitle" v-if="user != null" @click="logout">로그아웃</v-typography>
            </v-row>
          </v-col>
        </v-row>
      </v-container>
    </v-app-bar>
  </div>
</template>

<script>
import Login from "../components/login";
import Signup from "../components/signup";
import Constant from "../assets/constant";

export default {
  components: {
    Login,
    Signup
  },

  data() {
    return {
      user: this.$store.state.user
    };
  },

  computed: {
    test() {
      return this.$store.state.user;
    }
  },

  watch: {
    test() {
      this.user = this.$store.state.user;
    }
  },

  methods: {
    logout() {
      this.$store.state.user = null;
      this.$router.push({
        path: Constant.GOMAIN
      });
    },

    register() {
      this.$router.push({
        path: Constant.GOREGISTER
      });
    },

    main() {
      this.$router.push({
        path: Constant.GOMAIN
      });
    }
  }
};
</script>

<style scoped>
.leftnavtitle {
  color: white;
  margin-right: 1em;
  cursor: pointer;
}

.rightnavtitle {
  color: white;
  margin-left: 1em;
  cursor: pointer;
}

.centernavtitle {
  color: white;
  cursor: pointer;
}

.logintitle {
  color: white;
  margin-left: 1em;
}
</style>
