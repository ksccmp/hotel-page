<template>
    <div>
        <v-row justify="center">
            <v-col cols="6">
                <Checkform v-for="(reservation, index) in reservations" :key="index" :reservation="reservation" />
            </v-col>
        </v-row>
    </div>
</template>

<script>
import axios from 'axios';
import Checkform from '../../components/reservation/checkform';

export default {
    components: {
        Checkform,
    },

    data() {
        return {
            user: this.$store.state.user,
            reservations: [],
        };
    },

    mounted() {
        this.reservationSelectByUid();
    },

    methods: {
        reservationSelectByUid() {
            axios
                .get('http://localhost:8080/reservationSelectByUid', {
                    params: {
                        uid: this.user.id,
                    },
                })
                .then(res => {
                    this.reservations = res.data.data;
                })
                .catch(e => {
                    console.log(e);
                });
        },
    },
};
</script>

<style scoped></style>
