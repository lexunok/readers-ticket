<template>
    <div class="w-[100%] h-[8%] bg-white flex justify-center shadow-lg">
        <div class="w-[80%] h-[100%] flex items-center">
            <button type="button" @click="$router.push({ path: '/library/' })" class="nav-button">
                <div>Онлайн библиотека</div>
            </button>
            <button type="button" @click="$router.push({ path: '/library/books' })" class="nav-button">
                <div>Библиотека</div>
            </button>
            <button type="button" @click="$router.push({ path: '/library/user' })" class="nav-button">
                <div>Профиль</div>
            </button>
        </div>
    </div>
</template>
<script>
export default{
}
</script>
<style>
    .nav-button {
        @apply transition duration-300 ease-in-out w-1/3 h-[80%] flex justify-center items-center mx-2 hover:bg-gray-300 rounded-lg text-xl
    }
</style>