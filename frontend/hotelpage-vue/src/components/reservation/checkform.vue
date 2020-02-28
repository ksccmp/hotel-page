<template>
    <div class="reservation">
        <v-card class="reservationcard" shaped>
            <v-typography class="display-1">{{ reservation.roomname }} ({{ reservation.roomnum }}호)</v-typography>
            <br />
            <v-row>
                <v-col cols="3">
                    <v-typography class="caption lefttypo">예약자명</v-typography>
                    <v-typography class="title">{{ reservation.name }}</v-typography>
                </v-col>

                <v-col cols="1"></v-col>

                <v-col cols="3">
                    <v-typography class="caption lefttypo">지불금액</v-typography>
                    <v-typography class="title">{{ reservation.price }}</v-typography>
                </v-col>

                <v-col cols="1"></v-col>

                <v-col cols="3">
                    <v-typography class="caption lefttypo">총인원</v-typography>
                    <v-typography class="title">{{ reservation.person }}</v-typography>
                </v-col>
            </v-row>
            <v-row>
                <v-col cols="3">
                    <v-typography class="caption lefttypo">예약형태</v-typography>
                    <v-typography class="title">{{ reservation.form }}</v-typography>
                </v-col>

                <v-col cols="1"></v-col>

                <v-col cols="3">
                    <v-typography class="caption lefttypo">방문수단</v-typography>
                    <v-typography class="title">{{ reservation.way }}</v-typography>
                </v-col>
            </v-row>
            <v-row>
                <v-col cols="6">
                    <v-typography class="caption lefttypo">입실일자</v-typography>
                    <v-typography class="title">{{ reservation.startdate }} 21:00</v-typography>
                </v-col>

                <v-col cols="6">
                    <v-typography class="caption lefttypo">퇴실일자</v-typography>
                    <v-typography class="title">{{ reservation.enddate }} 12:00</v-typography>
                </v-col>
            </v-row>
            <v-row v-if="overdates == true">
                <v-col cols="6">
                    <v-btn color="primary afterbtn" block>후기 남기기</v-btn>
                </v-col>
                <v-col cols="6">
                    <v-btn color="red removebtn" block @click="reservationDelete">내역 삭제</v-btn>
                </v-col>
            </v-row>
            <v-row v-if="overdates == false">
                <v-col cols="12">
                    <v-btn color="red removebtn" block @click="reservationDelete">예약 취소</v-btn>
                </v-col>
            </v-row>
        </v-card>
    </div>
</template>

<script>
import moment from 'moment';
import axios from 'axios';

export default {
    props: ['reservation'],

    data() {
        return {
            overdates: '',
        };
    },

    mounted() {
        this.reservation.startdate = moment(this.reservation.startdate).format('YYYY-MM-DD(dd)');
        this.reservation.enddate = moment(this.reservation.enddate).format('YYYY-MM-DD(dd)');

        this.overdates = new Date(this.reservation.startdate + '21:00').getTime() > new Date().getTime() ? false : true;
    },

    methods: {
        reservationDelete() {
            console.log(this.reservation);
            axios
                .delete('http://localhost:8080/reservationDeleteById/' + this.reservation.id)
                .then(res => {
                    if (res.data.data == 1) {
                        alert('삭제 완료');
                        this.$router.go();
                    }
                })
                .catch(e => {
                    console.log(e);
                });
        },
    },
};
</script>

<style scoped>
.reservation {
    margin-bottom: 1em;
}

.reservationcard {
    padding: 1em;
}

.lefttypo {
    margin-right: 1em;
}

.afterbtn {
    color: white;
}

.removebtn {
    color: white;
}
</style>
