<template>
  <div>
    <v-row justify="center">
      <v-col cols="6">
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

        <Room v-for="(room, index) in rooms" :key="index" :room="room"></Room>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import Constant from "../../assets/constant";
import moment from "moment";
import "moment/locale/ko";
import Room from "../../components/reservation/room";
import axios from "axios";

export default {
  components: {
    Room
  },
  data() {
    return {
      user: this.$store.state.user,
      dates: [
        moment(new Date()).format("YYYY-MM-DD (dd)"),
        moment(new Date())
          .add("days", 1)
          .format("YYYY-MM-DD (dd)")
      ],
      datepicker: false,
      rooms: []
    };
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
  },

  mounted() {
    if (this.user == null) {
      alert("로그인을 해주세요");
      this.$router.push({
        path: Constant.GOMAIN
      });
    } else {
      axios
        .get("http://localhost:8080/roomSelectAll")
        .then(res => {
          this.rooms = res.data.data;
        })
        .catch(e => {
          console.log(e);
        });

      this.$store.dispatch(Constant.SETDATES, { dates: this.dates });
    }
  }
};
</script>

<style scoped></style>
