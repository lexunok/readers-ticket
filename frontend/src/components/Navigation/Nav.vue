<template>
    <div class="nav-class">
        <div class="nav-under-class">
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