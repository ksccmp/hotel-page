<template>
  <v-app>
    <Header></Header>
    <router-view></router-view>
    <Footer></Footer>
  </v-app>
</template>

<script>
import Footer from "./layout/footer";
import Header from "./layout/header";
import Constant from "./assets/constant";

export default {
  name: "App",

  components: {
    Header,
    Footer
  },

  mounted() {
    this.$router.push({
      path: Constant.GOMAIN
    });
  }
};
</script>
