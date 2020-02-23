<template>
  <v-app>
    <Header></Header>
    <router-view></router-view>
    <Footer></Footer>
  </v-app>
</template>

<script>
import Footer from './layout/footer';
import Header from './layout/header';
import Constant from './assets/constant';

export default {
  name: 'App',

  components: {
    Header,
    Footer,
  },

  mounted() {
    this.$store.dispatch(Constant.HELLO, {hello: 'hello'});

    this.$router.push({
      path: '/main'
    });
  },
};
</script>
