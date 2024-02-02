<template>
    <div class="fixed z-10 inset-0 overflow-y-auto flex items-center justify-center min-h-screen bg-gray-200 bg-opacity-75">
        <div class="w-[30%] h-[45%] bg-white p-6 rounded shadow-lg relative flex flex-col gap-2">
            <div class="absolute right-[2%]">
                <button type="button" @click="fCloseModal">
                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6">
                        <path stroke-linecap="round" stroke-linejoin="round" d="M6 18 18 6M6 6l12 12" />
                    </svg>
                </button>
            </div>
            <div class="w-full h-full flex flex-col gap-1">
                <div class="w-full">
                    <div v-if="redactorActive" class="w-full flex flex-row gap-2">
                        <input type="text" 
                            v-if="redactorActive"
                            v-model="newUser.firstName" 
                            class="w-[40%] rounded text-2xl border border-slate-300 placeholder-slate-400 px-2 
                                    focus:outline-none focus:border-sky-500 focus:ring-1 focus:ring-sky-500" />
                        <input type="text" 
                            v-if="redactorActive"
                            v-model="newUser.lastName" 
                            class="w-[40%] rounded text-2xl border border-slate-300 placeholder-slate-400 px-2 
                                    focus:outline-none focus:border-sky-500 focus:ring-1 focus:ring-sky-500" />
                    </div>
                    <div v-else class="text-2xl w-full">{{ activeUser.firstName }} {{ activeUser.lastName }}</div>
                </div>
                <div class="w-full">
                    <TitleInput v-if="redactorActive" :model="newUser.username" :getModel='fSetData' property="username" title="Логин" 
                                classStyle="w-full flex" titleStyle="w-[10%] mr-2" 
                                inputStyle="w-[15%] rounded border border-slate-300 placeholder-slate-400 px-2 
                                            focus:outline-none focus:border-sky-500 focus:ring-1 focus:ring-sky-500" />
                    <div v-else class="w-full">Логин {{ activeUser.username }}</div>
                </div>
                <div class="w-full">
                    <TitleInput v-if="redactorActive" :model="newUser.password" :getModel='fSetData' property="password" title="Пароль" 
                                classStyle="w-full flex" titleStyle="w-[12%] mr-2"
                                inputStyle="w-[25%] resize-none rounded border border-slate-300 placeholder-slate-400 px-2 
                                            focus:outline-none focus:border-sky-500 focus:ring-1 focus:ring-sky-500" />
                    <div v-else>Пароль {{ activeUser.password }}</div>
                </div>
                <div class="w-full">
                    <TitleInput v-if="redactorActive" :model="newUser.role" :getModel='fSetData' property="role" title="Роль" 
                                classStyle="w-full flex" titleStyle="w-[8%] mr-2" 
                                inputStyle="w-[25%] rounded border border-slate-300 placeholder-slate-400 px-2
                                            focus:outline-none focus:border-sky-500 focus:ring-1 focus:ring-sky-500" />
                    <div v-else class="flex items-center gap-1" >
                        <div>Роль</div>
                        <div class="flex bg-amber-500 w-auto p-[5px] rounded">{{ activeUser.role }}</div>
                    </div>
                </div>
            </div>
            <div v-if="redactorActive" class="w-full flex gap-2">
                <button type="button" @click="fCloseRedactorAndSafe(activeUser.id)" class="bg-green-500 p-2 rounded-lg active:bg-green-600">Сохранить изменения</button>
                <button type="button" @click="fCloseRedactor" class="bg-red-500 p-2 rounded-lg">Закрыть редактор</button>
            </div>
            <div v-else class="w-full flex gap-2">
                <button type="button" @click="fOpenRedactor" class="bg-green-500 p-2 rounded-lg active:bg-green-600">Редактировать пользователя</button>
                <button type="button" @click="fDeleteUser(activeUser.id)" class="bg-red-500 p-2 rounded-lg">Удалить пользователя</button>
            </div>
        </div>
    </div>
</template>
<script>
    import TitleInput from "../../components/TitleInput.vue"
    export default {
        components: {
            TitleInput
        },
        props: ['closeModal', 'openRedactor', 'closeRedactor', 'closeRedactorAndSafe', 'deleteUser', 'setData', 'newUser', 'activeUser', 'redactorActive'],
        mounted() {
            document.addEventListener('keyup', this.escFunction);
        },
        beforeDestroy() {
            document.removeEventListener('keyup', this.escFunction);
        },
        methods: {
            escFunction(event) {
                if(event.keyCode === 27) {
                    this.closeModal();
                }
            },
            fCloseModal(){
                this.closeModal();
            },
            fOpenRedactor(){
                this.openRedactor();
            },
            fCloseRedactor(){
                this.closeRedactor();
            },
            fCloseRedactorAndSafe(id){
                this.closeRedactorAndSafe(id);
            },
            fDeleteUser(id){
                this.deleteUser(id);
            },
            fSetData(property, data){
                this.setData(property, data);
            }
        }
    }
</script>
<style>

</style>