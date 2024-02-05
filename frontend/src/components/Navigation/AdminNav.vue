<template>
    <div class="nav-class">
        <div class="nav-under-class">
            <button type="button" @click="$router.push({ path: '/admin/' })" class="nav-button">
                <div>Админ панель</div>
            </button>
            <button type="button" @click="$router.push({ path: '/admin/library' })" class="nav-button">
                <div>Список литературы</div>
            </button>
            <button type="button" @click="$router.push({ path: '/admin/users' })" class="nav-button">
                <div>Список пользователей</div>
            </button>
        </div>
    </div>
</template>
<script>
export default{
}
</script>