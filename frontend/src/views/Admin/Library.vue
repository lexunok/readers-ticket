<template>
    <div class="w-screen h-screen bg-orange-200">
        <AdminNav/>
        <transition name="fade" enter-active-class="enter-active">
            <LibraryList v-if="show" title="Список литературы" :openModal="openModal" :books="getBooks" :admin="true" :bookAdder="bookAdder"/>
        </transition>
    </div>
    <transition name="modal" enter-active-class="enter-active" leave-active-class="leave-active">
        <LibraryModal v-if="showModal" :admin="true" :newBook="newBook" :activeBook="activeBook" :redactorActive="redactorActive"
                        :closeModal="closeModal" :openRedactor="openRedactor" :closeRedactor="closeRedactor"
                        :closeRedactorAndSafe="closeRedactorAndSafe" :deleteBook="deleteBook" :setData="setData" :bookAdder="bookAdder"/>
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
        },
        computed: {
            ...mapGetters(['getBooks'])
        },
        methods: {
            ...mapActions(['setBookInList','deleteBookInList', 'addBook', 'updateBook']),
            openModal(book) {
                this.activeBook = book;
                this.showModal = true;
            },
            closeModal() {
                this.redactorActive = false;
                this.activeBook = { 
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
                this.showModal = false;
            },
            openRedactor(){
                this.newBook = Object.assign({}, this.activeBook);
                this.redactorActive = true;
            },
            closeRedactor(){
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
                this.redactorActive = false;
            },
            bookAdder(){
                this.redactorActive = true;
                this.showModal = true;
            },
            async closeRedactorAndSafe(){
                await this.updateBook({id: this.newBook.id, name: this.newBook.name, description: this.newBook.name, publisher: this.newBook.name, 
                    yearOfPublishing: this.newBook.name, genres: this.newBook.name, authors: this.newBook.name, 
                    isbn: this.newBook.name, countOfPages: this.newBook.name})
                this.activeBook = Object.assign({}, this.newBook);
                this.redactorActive = false;
            },
            async addBook(){
                await this.addBook({name: this.newBook.name, description: this.newBook.name, publisher: this.newBook.name, 
                    yearOfPublishing: this.newBook.name, genres: this.newBook.name, authors: this.newBook.name, 
                    isbn: this.newBook.name, countOfPages: this.newBook.name})
                await this.setBookInList()
                this.activeBook = Object.assign({}, this.newBook);
                this.redactorActive = false;
            },
            async deleteBook(id){
                await this.deleteBookInList(id)
                await this.setBookInList()
                this.showModal = false;
            },
            setData(property, data){
                this.newBook[property] = data;
            }
        }
    }
    // private Long id;
    // private String name;
    // private String description;
    // private String publisher;
    // private String yearOfPublishing;
    // private List<String> genres;
    // private List<String> authors;
    // private String isbn;
    // private Integer countOfPages;
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