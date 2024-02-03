<template>
    <div class="w-screen h-screen bg-orange-200">
        <div class="w-full h-full flex justify-center items-center">
            <div class="bg-white w-1/3 h-1/2 shadow-xl rounded-xl flex flex-col items-center gap-20 py-10 px-2">
                <Title title="Вход" />
                <div class="h-[92%] w-[60%] flex flex-col gap-5">
                    <input type="text" v-model="username" class="input-style" />
                    <input type="password" v-model="password" class="input-style" />
                    <button type="button" @click="login" class="transition ease-in-out duration-300 h-[20%] bg-blue-500 rounded-lg text-2xl p-2 hover:bg-blue-600 action:bg-blue-700">
                        Вход
                    </button>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
import { mapActions } from 'vuex';
import Title from "../components/Title.vue"
export default{
    components: {
        Title
    },
    data(){
        return{
            username: null,
            password: null
        }
    },
    methods: {
        ...mapActions(['userLogin']),
        async login(){
            await this.userLogin({username: this.username, password: this.password})
            this.$router.push('/library/')
        }
    }
}
</script>
<style>
.input-style{
    @apply h-[20%] rounded border text-2xl border-slate-300 placeholder-slate-400 px-2
            focus:outline-none focus:border-sky-500 focus:ring-1 focus:ring-sky-500
}
</style>