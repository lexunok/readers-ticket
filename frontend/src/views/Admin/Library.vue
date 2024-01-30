<template>
    <div class="w-screen h-screen bg-orange-200">
        <AdminNav/>
        <div class="h-[92%] w-full flex flex-col items-center">
            <div class="h-[8%] flex items-center text-4xl font-semibold">Список литературы</div>
            <ul class="h-[92%] w-[90%] overflow-auto flex flex-wrap gap-4">
                <li v-for="book in books" :key="book.id" class="h-auto w-[49.5%] my-2">
                    <button type="button" @click="openModal(book)" class="transition duration-300 ease-in-out h-full w-full shadow-lg bg-white rounded-xl py-2 px-4 hover:bg-gray-200 hover:shadow-md">
                        <div class="flex items-end gap-2 h-[20%]">
                            <div class="text-2xl">{{ book.name }}</div>
                            <div class="flex gap-1">
                                <div>
                                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6">
                                        <path stroke-linecap="round" stroke-linejoin="round" d="M12 6.042A8.967 8.967 0 0 0 6 3.75c-1.052 0-2.062.18-3 .512v14.25A8.987 8.987 0 0 1 6 18c2.305 0 4.408.867 6 2.292m0-14.25a8.966 8.966 0 0 1 6-2.292c1.052 0 2.062.18 3 .512v14.25A8.987 8.987 0 0 0 18 18a8.967 8.967 0 0 0-6 2.292m0-14.25v14.25" />
                                    </svg>
                                </div>
                                <div>
                                    {{ book.countOfPages }} страниц
                                </div>
                            </div>
                        </div>
                        <div class="h-[15%] flex">Издатель {{ book.publisher }}, {{ book.yearOfPublishing }} г.</div>
                        <div class="flex items-center gap-1 h-[32.5%]">
                            <div v-if="book.genres.length > 1">Жанры</div>
                            <div v-else>Жанр</div>
                            <ul class="w-full flex gap-1">
                                <li v-for="genre in book.genres" class="bg-green-500 w-auto p-2 rounded">
                                    {{ genre }}
                                </li>
                            </ul>
                        </div>
                        <div class="flex items-center gap-1 h-[32.5%]">
                            <div v-if="book.authors.length > 1">Авторы</div>
                            <div v-else>Автор</div>
                            <ul class="w-full flex gap-1">
                                <li v-for="author in book.authors" class="bg-orange-500 w-auto p-2 rounded mx-2">
                                    {{ author }}
                                </li>
                            </ul>
                        </div>
                    </button>
                </li>
            </ul>
        </div>
    </div>
    <transition 
        name="modal" 
        enter-active-class="enter-active" 
        leave-active-class="leave-active"
    >
        <div 
            v-if="showModal" 
            class="fixed z-10 inset-0 overflow-y-auto flex items-center justify-center min-h-screen bg-gray-200 bg-opacity-75"
        >
            <div class="w-1/2 h-[45%] bg-white p-6 rounded shadow-lg relative flex flex-col gap-2">
                <div class="absolute right-[2%]">
                    <button type="button" @click="closeModal">
                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6">
                            <path stroke-linecap="round" stroke-linejoin="round" d="M6 18 18 6M6 6l12 12" />
                        </svg>
                    </button>
                </div>
                <div class="flex flex-row gap-1 h-full">
                    <div class="w-3/4 flex flex-col gap-1 h-full">
                        <div class="w-full">
                            <input type="text" 
                                    v-if="redactorActive"
                                    v-model="newBook.name" 
                                    class="w-full rounded text-2xl w-full border border-slate-300 placeholder-slate-400 px-2 
                                            focus:outline-none focus:border-sky-500 focus:ring-1 focus:ring-sky-500" />
                            <div v-else class="text-2xl w-full">{{ activeBook.name }}</div>
                        </div>
                        <div class="w-full">
                            <div v-if="redactorActive" class="w-full flex">
                                <div class="w-[25%]">Количество страниц</div>
                                <input type="text" 
                                        v-model="newBook.countOfPages" 
                                        class="w-[15%] rounded border border-slate-300 placeholder-slate-400 px-2
                                                focus:outline-none focus:border-sky-500 focus:ring-1 focus:ring-sky-500" />
                            </div>
                            <div v-else class="w-full">Количество страниц {{ activeBook.countOfPages }}</div>
                        </div>

                        <div class="w-full h-full">
                            <div v-if="redactorActive" class="h-full">
                                <div>О книге:</div>
                                <textarea type="text" 
                                        v-model="newBook.description" 
                                        class="w-full h-[45%] resize-none rounded border border-slate-300 placeholder-slate-400 px-2
                                                focus:outline-none focus:border-sky-500 focus:ring-1 focus:ring-sky-500" />
                            </div>
                            <div v-else>
                                <div>О книге:</div>
                                <div>{{ activeBook.description }}</div>
                            </div>
                        </div>
                        <div class="w-full">
                            <div v-if="redactorActive" class="w-full flex text-xs">
                                <div class="w-[5%]">
                                    ISBN
                                </div> 
                                <input type="text" 
                                        v-model="newBook.isbn" 
                                        class="w-[25%] rounded border border-slate-300 placeholder-slate-400 px-2
                                                focus:outline-none focus:border-sky-500 focus:ring-1 focus:ring-sky-500" />
                            </div>
                            <div v-else class="text-xs">ISBN {{ activeBook.isbn }}</div>
                        </div>
                    </div>
                    <div class="w-1/4 flex flex-col gap-2">
                        <div class="flex flex-col items-center">
                            <div>Издатель</div>
                            <div class="w-[75%] flex justify-center">
                                <input type="text" 
                                        v-if="redactorActive"
                                        v-model="newBook.publisher" 
                                        class="w-full text-xl rounded border border-slate-300 placeholder-slate-400 px-2
                                                focus:outline-none focus:border-sky-500 focus:ring-1 focus:ring-sky-500" />
                                <div v-else class="text-xl">{{ activeBook.publisher }}</div>
                            </div>
                        </div>
                        <div class="flex flex-col items-center">
                            <div>Год издания</div>
                            <div class="w-[75%] flex justify-center">
                                <input type="text" 
                                        v-if="redactorActive"
                                        v-model="newBook.yearOfPublishing" 
                                        class="w-full text-xl rounded border border-slate-300 placeholder-slate-400 px-2
                                                focus:outline-none focus:border-sky-500 focus:ring-1 focus:ring-sky-500" />
                                <div v-else class="text-xl">{{ activeBook.yearOfPublishing }}</div>
                            </div>
                        </div>
                    </div>
                </div>
                <div v-if="redactorActive" class="w-full flex gap-2">
                    <button type="button" @click="closeRedactorAndSafe(activeBook.id)" class="bg-green-500 p-2 rounded-lg active:bg-green-600">Сохранить изменения</button>
                    <button type="button" @click="closeRedactor" class="bg-red-500 p-2 rounded-lg">Закрыть редактор</button>
                </div>
                <div v-else class="w-full flex gap-2">
                    <button type="button" @click="openRedactor" class="bg-green-500 p-2 rounded-lg active:bg-green-600">Редактировать книгу</button>
                    <button type="button" @click="deleteBook(activeBook.id)" class="bg-red-500 p-2 rounded-lg">Удалить книгу</button>
                </div>
            </div>
        </div>
    </transition>
</template>
<script>
    import AdminNav from "../../components/AdminNav.vue"
    export default{
        components: {
                AdminNav
        },
        data() {
            return {
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
        methods: {
                    openModal(book) {
                        this.activeBook = book;
                        this.showModal = true;
                    },
                    closeModal() {
                        this.showModal = false;
                        this.redactorActive = false;
                        this.activeBook = null;
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
                    }
                },
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
<style scoped>
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
</style>