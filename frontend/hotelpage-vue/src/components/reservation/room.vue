<template>
  <div class="room" @click="roomdetail">
    <v-card class="roomcard" shaped>
      <v-row>
        <v-col cols="4">
          <v-avatar class="roomimage" tile>
            <v-img src="https://miro.medium.com/max/1200/1*t9q_j5XVT66Y1jfbKeYB2A.jpeg"></v-img>
          </v-avatar>
        </v-col>
        <v-col cols="8">
          <v-typography class="title">
            <b>{{room.name}} ({{room.num}}호)</b>
          </v-typography>
          <br />
          <v-typography class="subtitle-1">
            <b>{{room.contents}}</b>
          </v-typography>
          <br />
          <v-typography class="caption">기준 {{room.standard}}명 / 최대 {{room.max}}명 / 면적 {{room.size}}평</v-typography>

          <Roomform :form="'대실'" :price="room.rentprice" :addprice="room.rentaddprice" />
          <Roomform :form="'숙박'" :price="room.fullprice" :addprice="room.fulladdprice" />
        </v-col>
      </v-row>
    </v-card>
  </div>
</template>

<script>
import Constant from "../../assets/constant";
import Roomform from "./roomform";

export default {
  props: ["room"],

  components: {
    Roomform
  },

  methods: {
    roomdetail() {
      this.$router.push({
        path: Constant.GODETAIL(this.room.id)
      });
    }
  }
};
</script>

<style scoped>
.roomcard {
  padding: 1em;
}

.room {
  margin-bottom: 1em;
  cursor: pointer;
}

.roomimage {
  width: 100% !important;
  height: 100% !important;
}
</style>
