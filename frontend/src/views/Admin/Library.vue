<template>
    <div class="w-screen h-screen bg-orange-200">
        <AdminNav/>
        <transition name="fade" enter-active-class="enter-active">
            <LibraryList v-if="show" :openModal="openModal" :books="books"/>
        </transition>
    </div>
    <transition name="modal" enter-active-class="enter-active" leave-active-class="leave-active">
        <LibraryModal v-if="showModal" :newBook="newBook" :activeBook="activeBook" :redactorActive="redactorActive"
                        :closeModal="closeModal" :openRedactor="openRedactor" :closeRedactor="closeRedactor"
                        :closeRedactorAndSafe="closeRedactorAndSafe" :deleteBook="deleteBook" :setData="setData"/>
    </transition>
</template>
<script>
    import AdminNav from "../../components/AdminNav.vue"
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
                activeBook: null,
                newBook: null,
                books: [
                    { 
                        id: 1, 
                        name: 'Тайны Солнечной системы', 
                        description: 'Путешествие по Солнечной системе, от Солнца до самых дальних уголков Орта. Эта книга раскроет вам удивительные факты и тайны наших космических соседей.', 
                        publisher: 'Galaxy Press', 
                        yearOfPublishing: '2024', 
                        genres: ['Научно-популярное', 'Астрономия'], 
                        authors: ['Джон Доу'], 
                        isbn: '978-3-16-148410-0', 
                        countOfPages: 350 
                    },
                    { 
                        id: 2, 
                        name: 'Приключения в волшебном лесу', 
                        description: 'История о маленькой девочке, которая отправляется в волшебное приключение, чтобы спасти своего плюшевого мишку. По пути она встречает различных волшебных существ, которые помогают ей в ее задании.', 
                        publisher: 'Fairy Tale Publications', 
                        yearOfPublishing: '2023', 
                        genres: ['Детская литература', 'Фэнтези'], 
                        authors: ['Мэри Смит'], 
                        isbn: '978-0-14-243721-2', 
                        countOfPages: 120 
                    },
                    { 
                        id: 3, 
                        name: 'Программирование на Python для начинающих', 
                        description: 'Эта книга представляет собой полное руководство по программированию на Python для начинающих. Она включает в себя основы языка, а также примеры проектов, которые помогут вам начать свой путь в программировании.', 
                        publisher: 'Tech Books Inc.', 
                        yearOfPublishing: '2024', 
                        genres: ['Образование', 'Программирование'], 
                        authors: ['Сара Джонсон'], 
                        isbn: '978-1-861001-30-3', 
                        countOfPages: 500 
                    },
                    { 
                        id: 4, 
                        name: 'История мировой культуры', 
                        description: 'Обзор истории мировой культуры от древних времен до наших дней. Книга содержит много иллюстраций и фотографий, которые помогут лучше понять историю различных культур.', 
                        publisher: 'World History Press', 
                        yearOfPublishing: '2022', 
                        genres: ['История', 'Культура'], 
                        authors: ['Роберт Браун'], 
                        isbn: '978-0-521-83975-8', 
                        countOfPages: 800 
                    },
                    { 
                        id: 5, 
                        name: 'Кулинарное искусство', 
                        description: 'Эта книга предлагает огромное количество рецептов от простых блюд до сложных кулинарных шедевров. Она станет незаменимым помощником на кухне для каждого, кто хочет улучшить свои кулинарные навыки.', 
                        publisher: 'Foodie Books', 
                        yearOfPublishing: '2023', 
                        genres: ['Кулинария'], 
                        authors: ['Люси Грин'], 
                        isbn: '978-3-16-148410-5', 
                        countOfPages: 250 
                    },
                    { 
                        id: 6, 
                        name: 'Кулинарное искусство', 
                        description: 'Эта книга предлагает огромное количество рецептов от простых блюд до сложных кулинарных шедевров. Она станет незаменимым помощником на кухне для каждого, кто хочет улучшить свои кулинарные навыки.', 
                        publisher: 'Foodie Books', 
                        yearOfPublishing: '2023', 
                        genres: ['Кулинария'], 
                        authors: ['Люси Грин'], 
                        isbn: '978-3-16-148410-5', 
                        countOfPages: 250 
                    },
                    { 
                        id: 7, 
                        name: 'Кулинарное искусство', 
                        description: 'Эта книга предлагает огромное количество рецептов от простых блюд до сложных кулинарных шедевров. Она станет незаменимым помощником на кухне для каждого, кто хочет улучшить свои кулинарные навыки.', 
                        publisher: 'Foodie Books', 
                        yearOfPublishing: '2023', 
                        genres: ['Кулинария'], 
                        authors: ['Люси Грин'], 
                        isbn: '978-3-16-148410-5', 
                        countOfPages: 250 
                    }
                ]
            }
        },
        mounted() {
            this.show = true;
        },
        methods: {
            openModal(book) {
                this.activeBook = book;
                this.showModal = true;
            },
            closeModal() {
                this.redactorActive = false;
                this.activeBook = null;
                this.showModal = false;
            },
            openRedactor(){
                this.newBook = Object.assign({}, this.activeBook);
                this.redactorActive = true;
            },
            closeRedactor(){
                this.newBook = null;
                this.redactorActive = false;
            },
            closeRedactorAndSafe(id){
                this.newBook.id = id;
                for (let i = 0; i < this.books.length; i++) {
                    if (this.books[i].id === id) {
                        this.books[i] = Object.assign({}, this.newBook);;
                        break;
                    }
                }
                this.activeBook = Object.assign({}, this.newBook);
                this.redactorActive = false;
            },
            deleteBook(id){
                this.books = this.books.filter(element => element.id !== id)
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