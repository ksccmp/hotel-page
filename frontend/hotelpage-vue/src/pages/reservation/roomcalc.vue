<template>
  <div>
    <v-row justify="center">
      <v-col cols="6">
        <v-typography class="display-1">{{ room.name }} ({{ room.num }}호)</v-typography>
        <br />
        <v-row>
          <v-col cols="6">
            <v-typography>입실날짜</v-typography>
            <br />
            <v-typography class="title">{{ reservation.startdate }} {{ starttime }}부터</v-typography>
          </v-col>
          <v-col cols="6">
            <v-typography>퇴실날짜</v-typography>
            <br />
            <v-typography class="title">{{ reservation.enddate }} {{ endtime }}까지</v-typography>
          </v-col>
        </v-row>

        <v-text-field label="예약자명" name="name" v-model="reservation.name" :rules="rules"></v-text-field>
        <v-text-field label="예약자번호" name="phone" v-model="reservation.phone" :rules="rules"></v-text-field>
        <v-text-field label="추가인원" name="addperson" v-model="addperson" :rules="rules"></v-text-field>
        <v-row>
          <v-col col="6">
            <v-btn
              class="walk"
              color="deep-purple accent-4"
              depressed
              block
              @click="btnclick(false)"
              v-if="reservation.way == false"
            >도보</v-btn>
            <v-btn
              class="walk"
              color="deep-purple accent-4"
              outlined
              block
              @click="btnclick(false)"
              v-if="reservation.way == true"
            >도보</v-btn>
          </v-col>
          <v-col col="6">
            <v-btn
              class="car"
              color="deep-purple accent-4"
              depressed
              block
              @click="btnclick(true)"
              v-if="reservation.way == true"
            >차량</v-btn>
            <v-btn
              class="car"
              color="deep-purple accent-4"
              outlined
              block
              @click="btnclick(true)"
              v-if="reservation.way == false"
            >차량</v-btn>
          </v-col>
        </v-row>
        <v-row justify="end">
          <v-col cols="2" class="moneyright">
            <typography class="subtitle-1">기존 금액</typography>
          </v-col>
          <v-col cols="2" class="moneyright">
            <typography class="title">{{ price }}원</typography>
          </v-col>
        </v-row>
        <v-row justify="end">
          <v-col cols="2" class="moneyright">
            <typography class="subtitle-1">추가 금액</typography>
          </v-col>
          <v-col cols="2" class="moneyright">
            <typography class="title">{{ addprice * addperson }}원</typography>
          </v-col>
        </v-row>
        <v-row justify="end">
          <v-col cols="2" class="moneyright">
            <typography class="subtitle-1">총 금액</typography>
          </v-col>
          <v-col cols="2" class="moneyright">
            <typography class="title">{{ price + addprice * addperson }}원</typography>
          </v-col>
        </v-row>
        <v-btn color="primary" block @click="calc">{{ price + addprice * addperson }}원 결제하기</v-btn>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import axios from "axios";
import Constant from "../../assets/constant";

export default {
  props: ["id", "form"],
  data() {
    return {
      reservation: {
        uid: this.$store.state.user.id,
        form: this.form,
        startdate: this.$store.state.dates[0],
        enddate:
          this.form == "대실"
            ? this.$store.state.dates[0]
            : this.$store.state.dates[1],
        way: false
      },
      user: this.$store.state.user,
      room: {},
      rules: [value => !!value || "필수입력해야 됩니다."],
      starttime: "",
      endtime: "",
      addperson: 0,
      price: 0,
      addprice: 0
    };
  },

  mounted() {
    this.roomSelectById();

    this.starttime = this.form == "대실" ? "10:00" : "21:00";
    this.endtime = this.form == "대실" ? "16:00" : "12:00";
  },

  methods: {
    calc() {
      this.reservation.rid = this.room.id;
      this.reservation.way = this.reservation.way == false ? "도보" : "차량";
      this.reservation.person =
        Number(this.room.standard) + Number(this.addperson);
      this.reservation.price = this.price + this.addprice * this.addperson;
      this.reservation.roomname = this.room.name;
      this.reservation.roomnum = this.room.num;

      this.reservationInsert();
    },

    btnclick(status) {
      this.reservation.way = status;
    },

    roomSelectById() {
      axios
        .get("http://localhost:8080/room/selectById", {
          params: {
            id: this.id
          },
          headers: {
            "jwt-auth-token": localStorage.token
          }
        })
        .then(res => {
          this.room = res.data.data;
          this.reservation.rid = res.data.data.id;

          this.price =
            this.form == "대실" ? this.room.rentprice : this.room.fullprice;
          this.addprice =
            this.form == "대실"
              ? this.room.rentaddprice
              : this.room.fulladdprice;
        })
        .catch(e => {
          console.log(e);
        });
    },

    reservationInsert() {
      axios
        .get("http://localhost:8080/room/selectByDatesAndId", {
          params: {
            startdate: this.reservation.startdate,
            enddate: this.reservation.enddate,
            id: this.room.id
          },
          headers: {
            "jwt-auth-token": localStorage.token
          }
        })
        .then(res => {
          if (res.data.data != null) {
            axios
              .post(
                "http://localhost:8080/reservation/insert",
                this.reservation,
                {
                  headers: {
                    "jwt-auth-token": localStorage.token
                  }
                }
              )
              .then(res => {
                if (res.data.data == 1) {
                  console.log("예약완료");
                  this.$router.push({
                    path: Constant.GOMAIN
                  });
                }
              })
              .catch(e => {
                console.log(e);
              });
          } else {
            console.log("이미 방이 예약되었습니다");
          }
        })
        .catch(e => {
          console.log(e);
        });
    }
  },

  watch: {
    addperson() {
      if (this.addperson > this.room.max - this.room.standard) {
        this.addperson = this.room.max - this.room.standard;
        console.log("최대 추가인원은 " + this.addperson + "명 입니다.");
      } else if (this.addperson < 0) {
        this.addperson = 0;
        console.log("추가 인원은 0명 이상이여야 합니다.");
      }
    }
  }
};
</script>

<style scoped>
.walk {
  color: white;
}

.car {
  color: white;
}

.moneyright {
  text-align: end;
}

.moneygab {
  margin-left: 1em;
}
</style>
