<template>
    <div>
        <v-app-bar color="teal lighten-3">
            <v-title class="leftnavtitle">소개</v-title>
            <v-title class="leftnavtitle">예약하기</v-title>
            <v-title class="leftnavtitle">예약확인</v-title>
            <v-title class="leftnavtitle">방보기</v-title>
            <v-title class="leftnavtitle">게시판</v-title>
            <v-spacer></v-spacer>
            <v-title class="rightnavtitle">로그인</v-title>
            <v-title class="rightnavtitle">회원가입</v-title>
        </v-app-bar>
    </div>
</template>

<script>
export default {

}
</script>

<style scoped>
    .leftnavtitle {
        color: white;
        margin-right: 1em;
        cursor: pointer;
    }

    .rightnavtitle {
        color: white;
        margin-left: 1em;
        cursor: pointer;
    }
</style>