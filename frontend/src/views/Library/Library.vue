<template>
    <div class="background">
        <Nav/>
        <transition name="fade" enter-active-class="fade-enter-active">
            <LibraryList v-if="show" title="Библиотека" :openModal="openModal" :books="getBooks"/>
        </transition>
    </div>
    <transition name="modal" enter-active-class="modal-enter-active" leave-active-class="modal-leave-active">
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