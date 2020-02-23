<template>
    <div>
        main페이지 입니다!
        {{hello}}는 Vuex의 hello입니다.
    </div>
</template>

<script>
export default {
    data() {
        return {
            hello: '',
        }
    },

    mounted() {
        this.hello = this.$store.state.hello;
    },
}
</script>

<style scoped>

</style>