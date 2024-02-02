<template>
    <div class="w-screen h-screen bg-orange-200">
        <AdminNav/>
        <transition name="fade" enter-active-class="enter-active">
            <div v-if="show" class="h-[92%] w-full flex flex-col items-center">
                <Title title="Админ панель"/>
                <div class="h-[92%] w-full flex flex-row gap-10">
                    <div class="w-1/2 h-full flex justify-end items-center">
                        <button type="button" @click="$router.push({ path: '/admin/library' })" class="main-button">
                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-[45%] h-[45%]">
                                <path stroke-linecap="round" stroke-linejoin="round" d="M6 6.878V6a2.25 2.25 0 0 1 2.25-2.25h7.5A2.25 2.25 0 0 1 18 6v.878m-12 0c.235-.083.487-.128.75-.128h10.5c.263 0 .515.045.75.128m-12 0A2.25 2.25 0 0 0 4.5 9v.878m13.5-3A2.25 2.25 0 0 1 19.5 9v.878m0 0a2.246 2.246 0 0 0-.75-.128H5.25c-.263 0-.515.045-.75.128m15 0A2.25 2.25 0 0 1 21 12v6a2.25 2.25 0 0 1-2.25 2.25H5.25A2.25 2.25 0 0 1 3 18v-6c0-.98.626-1.813 1.5-2.122" />
                            </svg>
                            <Title title="Список литературы"/>
                        </button>
                    </div>
                    <div class="w-1/2 h-full flex items-center">
                        <button type="button" @click="$router.push({ path: '/admin/users' })" class="main-button">
                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-[45%] h-[45%]">
                                <path stroke-linecap="round" stroke-linejoin="round" d="M18 18.72a9.094 9.094 0 0 0 3.741-.479 3 3 0 0 0-4.682-2.72m.94 3.198.001.031c0 .225-.012.447-.037.666A11.944 11.944 0 0 1 12 21c-2.17 0-4.207-.576-5.963-1.584A6.062 6.062 0 0 1 6 18.719m12 0a5.971 5.971 0 0 0-.941-3.197m0 0A5.995 5.995 0 0 0 12 12.75a5.995 5.995 0 0 0-5.058 2.772m0 0a3 3 0 0 0-4.681 2.72 8.986 8.986 0 0 0 3.74.477m.94-3.197a5.971 5.971 0 0 0-.94 3.197M15 6.75a3 3 0 1 1-6 0 3 3 0 0 1 6 0Zm6 3a2.25 2.25 0 1 1-4.5 0 2.25 2.25 0 0 1 4.5 0Zm-13.5 0a2.25 2.25 0 1 1-4.5 0 2.25 2.25 0 0 1 4.5 0Z" />
                            </svg>
                            <Title title="Список пользователей"/>
                        </button>
                    </div>
                </div>
            </div>
        </transition>
    </div>
</template>
<script>
    import AdminNav from "../../components/AdminNav.vue"
    import Title from "../../components/Title.vue"
    export default{
        components: {
                AdminNav, Title
            },
        data(){
            return{
                show: false
            }
        },
        mounted() {
            this.show = true;
        }
    }
</script>
<style>
.main-button {
    @apply transition duration-300 ease-in-out w-2/3 h-3/4 bg-white rounded-xl flex flex-col justify-center items-center gap-4 hover:bg-gray-300
}
.enter-active {
  animation: fade-in 0.2s ease-in;
}
@keyframes fade-in {
  0% {
    opacity: 0;
  }
  100% {
    opacity: 1;
  }
}
</style>