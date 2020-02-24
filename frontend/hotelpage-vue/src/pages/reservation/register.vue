<template>
    <div>
        <v-row justify="center">
            <v-col cols="4">
                <v-menu v-model="datepicker" :close-on-content-click="false" max-width="290">
                    <template v-slot:activator="{ on }">
                        <v-row>
                            <v-col cols="5">
                                <v-text-field :value="dates[0]" label="입실날짜" readonly v-on="on"></v-text-field>
                            </v-col>
                            <v-col cols="1"></v-col>
                            <v-col cols="5">
                                <v-text-field :value="dates[1]" label="퇴실날짜" readonly v-on="on"></v-text-field>
                            </v-col>
                        </v-row>
                    </template>
                    <v-date-picker v-model="dates" range></v-date-picker>
                </v-menu>

                <Room v-for="(data, index) in 4" :key="index" :test="data"></Room>
            </v-col>
        </v-row>
    </div>
</template>

<script>
import Constant from '../../assets/constant';
import moment from 'moment';
import Room from '../../components/reservation/room';

export default {
    components: {
        Room,
    },
    data() {
        return {
            user: this.$store.state.user,
            dates: [moment(new Date()).format('YYYY-MM-DD'), moment(new Date()).format('YYYY-MM-DD')],
            datepicker: false,
        };
    },

    watch: {
        dates() {
            if (this.dates[0] > this.dates[1]) {
                let temp = this.dates[0];
                this.dates[0] = this.dates[1];
                this.dates[1] = temp;
            }
        },
    },

    mounted() {
        if (this.user == null) {
            alert('로그인을 해주세요');
            this.$router.push({
                path: Constant.GOMAIN,
            });
        }
    },
};
</script>

<style scoped></style>
