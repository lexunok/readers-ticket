<template>
    <div class="background">
        <AdminNav/>
        <transition name="fade" enter-active-class="fade-enter-active">
            <LibraryList v-if="show" title="Список литературы" :openModal="openModal" :books="getBooks" :admin="true" :bookAdder="bookAdder"/>
        </transition>
    </div>
    <transition name="modal" enter-active-class="modal-enter-active" leave-active-class="modal-leave-active">
        <LibraryModal v-if="showModal" :admin="true" :newBook="newBook" :activeBook="activeBook" :redactorActive="redactorActive"
                        :closeModal="closeModal" :openRedactor="openRedactor" :closeRedactor="closeRedactor" :newBookBool="newBookBool"
                        :closeRedactorAndSafe="closeRedactorAndSafe" :deleteBook="deleteBook" :setData="setData" :bookAdder="addBook"/>
    </transition>
</template>
<script>
    import { mapGetters, mapActions } from 'vuex';
    import AdminNav from "../../components/Navigation/AdminNav.vue"
    import LibraryList from "../../components/Library/LibraryList.vue"
    import LibraryModal from "../../components/Library/LibraryModal.vue"
    export default{
        components: {
                AdminNav, LibraryList, LibraryModal
        },
        data() {
            return {
                show: false,
                showModal: false,
                redactorActive: false,
                newBookBool: false,
                activeBook: { 
                    id: '', 
                    name: '', 
                    description: '', 
                    publisher: '', 
                    yearOfPublishing: '', 
                    genres: '', 
                    authors: '', 
                    isbn: '', 
                    countOfPages: '' 
                },
                newBook: { 
                    id: '', 
                    name: '', 
                    description: '', 
                    publisher: '', 
                    yearOfPublishing: '', 
                    genres: '', 
                    authors: '', 
                    isbn: '', 
                    countOfPages: '' 
                }
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
            ...mapActions(['setBookInList','deleteBookInList', 'updateBook']),
            openModal(book) {
                this.activeBook = book;
                this.showModal = true;
            },
            closeModal() {
                this.newBook = { 
                    id: '', 
                    name: '', 
                    description: '', 
                    publisher: '', 
                    yearOfPublishing: '', 
                    genres: '', 
                    authors: '', 
                    isbn: '', 
                    countOfPages: '' 
                };
                this.activeBook = { 
                    id: '', 
                    name: '', 
                    description: '', 
                    publisher: '', 
                    yearOfPublishing: '', 
                    genres: [''], 
                    authors: [''], 
                    isbn: '', 
                    countOfPages: '' 
                };
                this.newBookBool = false;
                this.redactorActive = false;
                this.showModal = false;
            },
            openRedactor(){
                this.newBook = Object.assign({}, this.activeBook);
                this.redactorActive = true;
            },
            closeRedactor(){
                this.redactorActive = false;
                this.newBookBool = false;
            },
            bookAdder(){
                this.newBookBool = true;
                this.redactorActive = true;
                this.showModal = true;
            },
            closeRedactorAndSafe(){
                this.activeBook = Object.assign({}, this.newBook);
                this.newBookBool = false;
                this.redactorActive = false;
            },
            addBook(){
                this.newBook = { 
                    id: '', 
                    name: '', 
                    description: '', 
                    publisher: '', 
                    yearOfPublishing: '', 
                    genres: '', 
                    authors: '', 
                    isbn: '', 
                    countOfPages: '' 
                };
                this.activeBook = { 
                    id: '', 
                    name: '', 
                    description: '', 
                    publisher: '', 
                    yearOfPublishing: '', 
                    genres: [''], 
                    authors: [''], 
                    isbn: '', 
                    countOfPages: '' 
                };
                this.newBookBool = false;
                this.redactorActive = false;
                this.showModal = false;
            },
            deleteBook(){
                this.newBook = { 
                    id: '', 
                    name: '', 
                    description: '', 
                    publisher: '', 
                    yearOfPublishing: '', 
                    genres: '', 
                    authors: '', 
                    isbn: '', 
                    countOfPages: '' 
                };
                this.activeBook = { 
                    id: '', 
                    name: '', 
                    description: '', 
                    publisher: '', 
                    yearOfPublishing: '', 
                    genres: [''], 
                    authors: [''], 
                    isbn: '', 
                    countOfPages: '' 
                };
                this.newBookBool = false;
                this.redactorActive = false;
                this.showModal = false;
            },
            setData(property, data){
                this.newBook[property] = data;
            }
        }
    }
</script>