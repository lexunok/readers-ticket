<template>
    <div v-if="list != null" class="flex items-center gap-1 h-[25%]">
        <div v-if="list.length > 1">{{ IfTitle }}</div>
        <div v-else>{{ ElseTitle }}</div>
        <ul class="w-full flex gap-1">
            <li v-for="genre in list" :class="[classStyle]">
                {{ genre }}
            </li>
        </ul>
    </div>
</template>
<script>
    export default {
        props: ['IfTitle','ElseTitle', 'list', 'classStyle']
    }
</script>
<style>

</style>