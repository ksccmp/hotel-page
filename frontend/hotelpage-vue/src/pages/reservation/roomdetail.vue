<template>
  <div>
    <v-row justify="center">
      <v-col cols="6">
        <v-typography class="display-1">{{room.name}} ({{room.num}}호)</v-typography>
        <br />
        <v-typography class="title">{{room.contents}}</v-typography>
        <br />
        <v-avatar class="roomimage" tile>
          <v-img src="https://miro.medium.com/max/1200/1*t9q_j5XVT66Y1jfbKeYB2A.jpeg"></v-img>
        </v-avatar>
        <v-menu v-model="datepicker" :close-on-content-click="false" max-width="290">
          <template v-slot:activator="{ on }">
            <v-row>
              <v-col cols="5">
                <v-text-field :value="dates[0]" label="입실날짜" readonly v-on="on"></v-text-field>
              </v-col>

              <v-col cols="2"></v-col>

              <v-col cols="5">
                <v-text-field :value="dates[1]" label="퇴실날짜" readonly v-on="on"></v-text-field>
              </v-col>
            </v-row>
          </template>
          <v-date-picker v-model="dates" range></v-date-picker>
        </v-menu>

        <Roomdetailform
          :form="'대실'"
          :price="room.rentprice"
          :addprice="room.rentaddprice"
          :id="room.id"
        />
        <Roomdetailform
          :form="'숙박'"
          :price="room.fullprice"
          :addprice="room.fulladdprice"
          :id="room.id"
        />

        <v-typography class="subtitle-1">
          <b>전달사항</b>
        </v-typography>
        <ul>
          <li>객실 내 흡연 금지, 걸리면 퇴실 조치</li>
          <li v-for="(addcontent, index) in room.addcontents" :key="index">{{addcontent}}</li>
        </ul>

        <br />

        <v-typography class="subtitle-1">
          <b>취소규정</b>
        </v-typography>
        <ul>
          <li>입실 1일 전 24시 전까지: 전액환불</li>
          <li>당일 취소 및 No-Show, 입실시간 경과/실제 입실 후: 환불불가</li>
        </ul>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import axios from "axios";
import moment from "moment";
import Constant from "../../assets/constant";
import Roomdetailform from "../../components/reservation/roomdetailform";

export default {
  props: ["id"],

  components: {
    Roomdetailform
  },

  data() {
    return {
      room: {},
      dates: this.$store.state.dates,
      datepicker: false
    };
  },

  mounted() {
    axios
      .get("http://localhost:8080/roomSelectById", {
        params: {
          id: this.id
        }
      })
      .then(res => {
        this.room = res.data.data;
      })
      .catch(e => {
        console.log(e);
      });
  },

  watch: {
    dates() {
      if (this.dates[0] > this.dates[1]) {
        let temp = this.dates[0];
        this.dates[0] = this.dates[1];
        this.dates[1] = temp;
      } else if (this.dates[0] == this.dates[1]) {
        this.dates[1] = moment(this.dates[1])
          .add("days", 1)
          .format("YYYY-MM-DD");
      }

      this.$store.dispatch(Constant.SETDATES, { dates: this.dates });
    }
  }
};
</script>

<style scoped>
.roomimage {
  width: 100% !important;
  height: 25em !important;
}
</style>