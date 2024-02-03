<template>
    <div class="w-screen h-screen bg-orange-200">
        <Nav/>
        <transition name="fade" enter-active-class="enter-active">
            <div v-if="show" class="h-[92%] w-full flex flex-col items-center">
                <div class="w-[95%] h-[30%] flex my-5 bg-white rounded-xl">
                    <div class="h-full w-1/6">
                        <svg xmlns="http://www.w3.org/2000/svg" fill="#e5e7eb" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="h-full">
                            <path stroke-linecap="round" stroke-linejoin="round" d="M17.982 18.725A7.488 7.488 0 0 0 12 15.75a7.488 7.488 0 0 0-5.982 2.975m11.963 0a9 9 0 1 0-11.963 0m11.963 0A8.966 8.966 0 0 1 12 21a8.966 8.966 0 0 1-5.982-2.275M15 9.75a3 3 0 1 1-6 0 3 3 0 0 1 6 0Z" />
                        </svg>
                    </div>
                    <div class="w-5/6 flex flex-col gap-1 p-5">
                        <div class="text-6xl">
                            {{ user.firstName }} {{ user.lastName }}
                        </div>
                        <div class="text-4xl">
                            #{{ user.username }}
                        </div>
                    </div>
                </div>
                <div class="w-[95%] h-[63%] bg-white rounded-xl flex flex-col items-center gap-1 p-1 divide-y-2 divide-solid">
                    <div class="h-[8%] w-[90%] flex flex-row items-center">
                        <div class="table-name">Название</div>
                        <div class="table-name">Статус</div>
                        <div class="table-name">Дата взятия</div>
                        <div class="table-name">Дата сдачи</div>
                    </div>
                    <ul class="h-[92%] w-[90%] overflow-y-auto divide-y divide-solid">
                        <li v-for="userBook in getUsersBooks" :key="userBook.id">
                            <button type="button" @click="openModal(userBook.book)" class="transition duration-300 ease-in-out flex flex-row w-full items-center hover:bg-gray-300 py-3">
                                <div class="table-li">{{ userBook.book.name }}</div>
                                <div v-if="userBook.isRent" class="table-li">Взята</div>
                                <div v-else class="table-li">Отдана</div>
                                <div class="table-li">{{ userBook.startDate }}</div>
                                <div class="table-li">{{ userBook.finishDate }}</div>
                            </button>
                        </li>
                    </ul>
                </div>
            </div>
        </transition>
    </div>
    <transition name="modal" enter-active-class="enter-active" leave-active-class="leave-active">
        <LibraryModal v-if="showModal" :activeBook="activeBook" :closeModal="closeModal" />
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
            activeBook: null,
            user: {
                id: 1,
                username: "user1", 
                firstName: "Иван", 
                lastName: "Иванов", 
                role: "Admin"
            }
        }
    },
    mounted() {
        this.show = true;
    },
    computed: {
        ...mapGetters(['getUsersBooks'])
    },
    methods: {
        ...mapActions(['setUsersBookInList','returnUsersBookInList', 'addUsersBook']),
        getDate(){
            const today = new Date()
            var day
            var month
            var year
            if (today.getDate() < 10){day = '0' + today.getDate()}
            else {day = today.getDate()}
            if (today.getMonth() + 1 < 10){month = '0' + (today.getMonth() + 1)}
            else {month = today.getMonth() + 1}
            year = today.getFullYear()
            const date = day + '.' + month + '.' + year
            return date
        },
        openModal(book) {
            this.activeBook = book;
            this.showModal = true;
        },
        closeModal() {
            this.activeBook = null;
            this.showModal = false;
        },

    }
}
</script>
<style>
.table-name{
    @apply w-1/4 flex justify-center text-3xl
}
.table-li {
    @apply w-1/4 flex justify-center text-xl
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

::-webkit-scrollbar {
    width: 5px;
}

::-webkit-scrollbar-thumb {
    border-radius: 5px;
    background-color: #FFFFFF;
}

::-webkit-scrollbar-track {
    border-radius: 5px;
}
</style>