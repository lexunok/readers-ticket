<template>
    <div class="w-[100%] h-[8%] bg-white flex justify-center shadow-lg">
            <div class="w-[80%] h-[100%] flex items-center">
                <button type="button" @click="$router.push({ path: '/admin/' })" class="nav-button">
                    <div>Админ панель</div>
                </button>
                <button type="button" @click="$router.push({ path: '/admin/users' })" class="nav-button">
                    <div>Список пользователей</div>
                </button>
                <button type="button" @click="$router.push({ path: '/admin/library' })" class="nav-button">
                    <div>Список литературы</div>
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