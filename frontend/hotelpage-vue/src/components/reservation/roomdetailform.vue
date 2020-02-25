<template>
  <div class="roomform" @click="roomcalc">
    <v-card class="formcard" shaped>
      <v-typography class="display-1">{{form}}</v-typography>
      <br />
      <v-typography class="subtitle-2">{{subtitle1}}</v-typography>
      <br />
      <v-typography class="subtitle-2">{{subtitle2}}</v-typography>
      <br />
      <div class="rightdiv">
        <v-typography>판매가</v-typography>
        <br />
        <v-typography class="subtitle-1">
          <b>{{price}}원</b>
        </v-typography>
        <br />
        <v-typography>추가인원</v-typography>
        <br />
        <v-typography class="subtitle-1">
          <b>{{addprice}}원</b> / 1인
        </v-typography>
      </div>
    </v-card>
  </div>
</template>

<script>
import Constant from "../../assets/constant";

export default {
  props: ["form", "price", "addprice", "id"],

  data() {
    return {
      subtitle1:
        this.form == "대실" ? "이용시간 최대 6시간" : "체크인 21:00 부터",
      subtitle2:
        this.form == "대실" ? "운영시간 10:00 ~ 22:00" : "체크아웃 12:00 까지"
    };
  },

  methods: {
    roomcalc() {
      this.$router.push({
        path: Constant.GOCALC(this.id, this.form)
      });
    }
  }
};
</script>

<style scoped>
.roomform {
  margin-bottom: 1em;
  cursor: pointer;
}

.formcard {
  padding: 1em;
}

.rightdiv {
  text-align: right;
}
</style>