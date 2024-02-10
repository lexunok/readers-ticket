<template>
    <div class="background">
        <Nav/>
        <transition name="fade" enter-active-class="fade-enter-active">
            <div v-if="show" class="h-[92%] w-full flex flex-col items-center">
                <div class="w-[95%] h-[30%] flex my-5 bg-white rounded-xl">
                    <div class="h-full w-1/6 select-none">
                        <svg xmlns="http://www.w3.org/2000/svg" fill="#e5e7eb" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="h-full">
                            <path stroke-linecap="round" stroke-linejoin="round" d="M17.982 18.725A7.488 7.488 0 0 0 12 15.75a7.488 7.488 0 0 0-5.982 2.975m11.963 0a9 9 0 1 0-11.963 0m11.963 0A8.966 8.966 0 0 1 12 21a8.966 8.966 0 0 1-5.982-2.275M15 9.75a3 3 0 1 1-6 0 3 3 0 0 1 6 0Z" />
                        </svg>
                    </div>
                    <div class="w-5/6 flex flex-col gap-1 p-5">
                        <div class="2xl:text-6xl xl:text-5xl lg:text-4xl">{{ getUser.firstName }} {{ getUser.lastName }}</div>
                        <div class="2xl:text-4xl xl:text-3xl lg:text-2xl">#{{ getUser.username }}</div>
                    </div>
                </div>
                <div class="w-[95%] h-[63%] bg-white rounded-xl flex flex-col items-center gap-1 p-1 divide-y-2 divide-solid">
                    <div class="h-[8%] w-[90%] flex flex-row items-center select-none">
                        <div class="table-name">Название</div>
                        <div class="table-name">Статус</div>
                        <div class="table-name">Дата взятия</div>
                        <div class="table-name">Дата сдачи</div>
                    </div>
                    <ul class="h-[92%] w-[90%] overflow-y-auto divide-y divide-solid">
                        <li v-for="userBook in getUsersBooks" :key="userBook.id">
                            <button type="button" @click="openModal(userBook)" class="transition duration-300 ease-in-out flex flex-row w-full items-center hover:bg-gray-300 py-3">
                                <div class="table-li">{{ userBook.book.name }}</div>
                                <div v-if="userBook.isRent" class="table-li">Взята</div>
                                <div v-else class="table-li">Отдана</div>
                                <div class="table-li">{{ userBook.rentedAt }}</div>
                                <div class="table-li">{{ userBook.returnedAt }}</div>
                            </button>
                        </li>
                    </ul>
                </div>
            </div>
        </transition>
    </div>
    <transition name="modal" enter-active-class="modal-enter-active" leave-active-class="modal-leave-active">
        <LibraryModal v-if="showModal" :activeUserBook="activeUserBook" :activeBook="activeUserBook.book" :closeModal="closeModal" profile="profile" />
    </transition>
</template>
<script>
import { mapGetters, mapActions } from 'vuex';
import Nav from "../../components/Navigation/Nav.vue"
import LibraryModal from "../../components/Library/LibraryModal.vue"
export default{
    components: {
        Nav, LibraryModal
    },
    data(){
        return{
            show: false,
            showModal: false,
            activeUserBook: null
        }
    },
    mounted() {
        this.show = true;
        this.setUsersBookInList();
    },
    computed: {
        ...mapGetters(['getUsersBooks', 'getUser'])
    },
    methods: {
        ...mapActions(['setUsersBookInList']),
        openModal(userBook) {
            this.activeUserBook = userBook;
            this.showModal = true;
        },
        closeModal() {
            this.activeUserBook = null;
            this.showModal = false;
        },

    }
}
</script>
<style>
.table-name{
    @apply w-1/4 flex justify-center 2xl:text-3xl xl:text-2xl lg:text-xl
}
.table-li {
    @apply w-1/4 flex justify-center 2xl:text-xl xl:text-lg lg:text-base
}
</style>