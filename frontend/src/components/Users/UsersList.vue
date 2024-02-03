<template>
    <div class="h-[92%] w-full flex flex-col items-center">
        <Title title="Пользователи" />
        <ul class="h-[92%] w-[90%] overflow-auto">
            <li class="h-auto m-2 w-[99%]">
                <button type="button" @click="startUserAdder" 
                        class="transition duration-300 ease-in-out h-full w-full flex justify-center items-center shadow-lg bg-white text-2xl rounded-xl py-4 px-5 
                                hover:bg-gray-200 hover:shadow-md focus:outline-none">Добавить пользователя</button>
            </li>
            <li v-for="user in users" :key="user.id" class="h-auto inline-block w-[49%] m-2">
                <button type="button" @click="startModal(user)" class="transition duration-300 ease-in-out h-full w-full shadow-lg bg-white rounded-xl py-4 px-5 
                                                            hover:bg-gray-200 hover:shadow-md focus:outline-none">
                    <div class="flex flex-col gap-1">
                        <div class="flex h-[20%] text-2xl">
                            {{ user.firstName }} {{ user.lastName }}, #{{ user.username }}
                        </div>
                        <div class="flex">Пароль {{ user.password }}</div>
                        <div class="flex items-center gap-1" >
                            <div>Роль</div>
                            <div class="flex bg-amber-500 w-auto p-[5px] rounded">{{ user.role }}</div>
                        </div>
                    </div>
                </button>
            </li>
        </ul>
    </div>
</template>
<script>
    import Title from "../../components/Title.vue"
    export default {
        components: { 
            Title
        },
        props: ['users', 'openModal','addUser', 'userAdder'],
        data(){
            return{
                
            }
        },
        methods: {
            startModal(user){
                this.openModal(user);
            },
            startUserAdder(){
                this.userAdder();
            }
        }
    }
</script>
<style>

</style>