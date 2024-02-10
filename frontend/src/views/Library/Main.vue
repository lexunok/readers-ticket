<template>
    <div class="background">
        <Nav/>
        <transition name="fade" enter-active-class="fade-enter-active">
            <div v-if="show" class="h-[92%] w-full flex flex-col items-center select-none">
                <Title title="Онлайн библиотека"/>
                <div class="h-[92%] w-full flex flex-row gap-1">
                    <div class="w-1/3 h-full flex justify-end items-center">
                        <button type="button" @click="$router.push({ path: '/login' })" class="main-button">
                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-[45%] h-[45%]">
                                <path stroke-linecap="round" stroke-linejoin="round" d="M8.25 9V5.25A2.25 2.25 0 0 1 10.5 3h6a2.25 2.25 0 0 1 2.25 2.25v13.5A2.25 2.25 0 0 1 16.5 21h-6a2.25 2.25 0 0 1-2.25-2.25V15m-3 0-3-3m0 0 3-3m-3 3H15" />
                            </svg>
                            <Title title="Выход"/>
                        </button>
                    </div>
                    <div class="w-1/3 h-full flex justify-center items-center">
                        <button type="button" @click="$router.push({ path: '/library/books' })" class="main-button">
                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-[45%] h-[45%]">
                                <path stroke-linecap="round" stroke-linejoin="round" d="M6 6.878V6a2.25 2.25 0 0 1 2.25-2.25h7.5A2.25 2.25 0 0 1 18 6v.878m-12 0c.235-.083.487-.128.75-.128h10.5c.263 0 .515.045.75.128m-12 0A2.25 2.25 0 0 0 4.5 9v.878m13.5-3A2.25 2.25 0 0 1 19.5 9v.878m0 0a2.246 2.246 0 0 0-.75-.128H5.25c-.263 0-.515.045-.75.128m15 0A2.25 2.25 0 0 1 21 12v6a2.25 2.25 0 0 1-2.25 2.25H5.25A2.25 2.25 0 0 1 3 18v-6c0-.98.626-1.813 1.5-2.122" />
                            </svg>
                            <Title title="Библиотека"/>
                        </button>
                    </div>
                    <div class="w-1/3 h-full flex items-center">
                        <button type="button" @click="$router.push({ path: '/library/user' })" class="main-button">
                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-[45%] h-[45%]">
                                <path stroke-linecap="round" stroke-linejoin="round" d="M15.75 6a3.75 3.75 0 1 1-7.5 0 3.75 3.75 0 0 1 7.5 0ZM4.501 20.118a7.5 7.5 0 0 1 14.998 0A17.933 17.933 0 0 1 12 21.75c-2.676 0-5.216-.584-7.499-1.632Z" />
                            </svg>
                            <Title title="Профиль"/>
                        </button>
                    </div>
                </div>
            </div>
        </transition>
    </div>
</template>
<script>
    import Nav from "../../components/Navigation/Nav.vue"
    import Title from "../../components/Title.vue"
    export default{
        components: {
                Nav, Title
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