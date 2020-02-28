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

                <v-tabs class="tabs">
                    <v-tab v-for="(room, index) in rooms" :key="index">
                        {{ room.name }}
                    </v-tab>

                    <v-tab-item v-for="(room, index) in rooms" :key="index">
                        <Room v-for="(room, index) in room.data" :key="index" :room="room"></Room>
                    </v-tab-item>
                </v-tabs>
            </v-col>
        </v-row>
    </div>
</template>

<script>
import Constant from '../../assets/constant';
import moment from 'moment';
import 'moment/locale/ko';
import Room from '../../components/reservation/room';
import axios from 'axios';

export default {
    components: {
        Room,
    },
    data() {
        return {
            user: this.$store.state.user,
            dates: [
                moment(new Date()).format('YYYY-MM-DD (dd)'),
                moment(new Date())
                    .add('days', 1)
                    .format('YYYY-MM-DD (dd)'),
            ],
            datepicker: false,
            rooms: [],
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
                    .add('days', 1)
                    .format('YYYY-MM-DD');
            }

            if (this.dates[1] != null) {
                this.selectByDates();
            }
            this.$store.dispatch(Constant.SETDATES, { dates: this.dates });
        },
    },

    mounted() {
        this.selectByDates();
        this.$store.dispatch(Constant.SETDATES, { dates: this.dates });
    },

    methods: {
        selectByDates() {
            axios
                .get('http://localhost:8080/roomSelectByDates', {
                    params: {
                        startdate: this.dates[0],
                        enddate: this.dates[1],
                    },
                })
                .then(res => {
                    this.rooms = [];
                    for (let room of res.data.data) {
                        let isexist = false;
                        for (let curroom of this.rooms) {
                            if (curroom.name == room.name) {
                                curroom.data.push(room);
                                isexist = true;
                                break;
                            }
                        }

                        if (isexist == false) {
                            let setroom = {
                                name: room.name,
                                data: [room],
                            };
                            this.rooms.push(setroom);
                        }
                    }
                })
                .catch(e => {
                    console.log(e);
                });
        },
    },
};
</script>

<style scoped></style>
