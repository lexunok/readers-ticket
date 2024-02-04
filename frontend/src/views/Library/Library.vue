<template>
    <div class="w-screen h-screen bg-orange-200">
        <Nav/>
        <transition name="fade" enter-active-class="enter-active">
            <LibraryList v-if="show" title="Библиотека" :openModal="openModal" :books="getBooks"/>
        </transition>
    </div>
    <transition name="modal" enter-active-class="enter-active" leave-active-class="leave-active">
        <LibraryModal v-if="showModal" :activeBook="activeBook" :closeModal="closeModal" />
    </transition>
</template>
<script>
import { mapGetters, mapActions } from 'vuex';
import LibraryList from "../../components/Library/LibraryList.vue"
import LibraryModal from "../../components/Library/LibraryModal.vue"
import Nav from "../../components/Navigation/Nav.vue"
export default{
    components: {
        LibraryList, Nav, LibraryModal
    },
    data(){
        return{
            show: false,
            showModal: false,
            activeBook: null
        }
    },
    mounted() {
        this.show = true;
        this.setBookInList();
    },
    computed: {
        ...mapGetters(['getBooks'])
    },
    methods: {
        ...mapActions(['setUsersBookInList','returnUsersBookInList', 'addUsersBook', 'setBookInList']),
        openModal(book) {
            this.activeBook = book;
            this.showModal = true;
        },
        closeModal() {
            this.activeBook = null;
            this.showModal = false;
        }
    }
}
</script>
<style>
.enter-active {
  animation: modal-in 0.3s ease-in;
}
.leave-active {
  animation: modal-out 0.3s ease-out;
}
@keyframes modal-in {
  0% {
    opacity: 0;
  }
  100% {
    opacity: 1;
  }
}
@keyframes modal-out {
  0% {
    opacity: 1;
  }
  100% {
    opacity: 0;
  }
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