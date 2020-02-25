<template>
  <v-row>
    <v-col cols="2">
      <v-typography class="body-2">{{form}}</v-typography>
    </v-col>
    <v-col cols="4"></v-col>
    <v-col cols="6" class="price">
      <v-typography class="caption">
        판매가
        <b>{{price}}원</b>
      </v-typography>
      <br />
      <v-typography class="caption">
        추가인원
        <b>{{addprice}}원</b> / 1명
      </v-typography>
    </v-col>
  </v-row>
</template>

<script>
export default {
  props: ["form", "price", "addprice"]
};
</script>

<style scoped>
.price {
  text-align: end;
}
</style>