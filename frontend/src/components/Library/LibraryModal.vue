<template>
    <div @click="fCloseModal" class="fixed z-10 inset-0 overflow-y-auto flex items-center justify-center min-h-screen bg-gray-200 bg-opacity-75">
        <div @click.stop class="w-1/2 h-auto bg-white p-6 rounded shadow-lg relative flex flex-col gap-3">
            <div class="absolute right-[2%]">
                <button type="button" @click="fCloseModal">
                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6">
                        <path stroke-linecap="round" stroke-linejoin="round" d="M6 18 18 6M6 6l12 12" />
                    </svg>
                </button>
            </div>
            <div class="flex flex-row gap-1 h-full">
                <div class="w-3/4 flex flex-col gap-2 h-full">
                    <div class="w-full">
                        <input type="text" 
                                v-if="redactorActive"
                                v-model="newBook.name" 
                                class="w-full rounded text-2xl border border-slate-300 placeholder-slate-400 px-2 
                                        focus:outline-none focus:border-sky-500 focus:ring-1 focus:ring-sky-500" />
                        <div v-else class="text-2xl w-full">{{ activeBook.name }}</div>
                    </div>
                    <div class="w-full">
                        <TitleInput v-if="redactorActive" :model="newBook.countOfPages" :getModel='fSetData' property="countOfPages" title="Количество страниц" 
                                    classStyle="w-full flex" titleStyle="w-[25%]" 
                                    inputStyle="w-[15%] rounded border border-slate-300 placeholder-slate-400 px-2 
                                                focus:outline-none focus:border-sky-500 focus:ring-1 focus:ring-sky-500" />
                        <div v-else class="w-full">Количество страниц {{ activeBook.countOfPages }}</div>
                    </div>
                    <div class="w-full h-full">
                        <TitleInput v-if="redactorActive" :model="newBook.description" :getModel='fSetData' property="description" title="О книге:" 
                                    classStyle="h-full" titleStyle="" textarea="true"
                                    inputStyle="w-full h-[45%] resize-none rounded border border-slate-300 placeholder-slate-400 px-2 
                                                focus:outline-none focus:border-sky-500 focus:ring-1 focus:ring-sky-500" />
                        <div v-else class="overflow-auto">О книге:<br>{{ activeBook.description }}</div>
                    </div>
                    <div class="w-full">
                        <div v-if="redactorActive" class="w-full flex">
                            <div class="w-[10%]">Жанры</div> 
                            <input type="text" 
                                    v-model="genres"
                                    @input="convertToGenres"
                                    class="w-[90%] rounded border border-slate-300 placeholder-slate-400 px-2
                                                focus:outline-none focus:border-sky-500 focus:ring-1 focus:ring-sky-500"/>
                        </div>
                        <GenresAuthors v-else IfTitle="Жанры" ElseTitle="Жанр" :list="activeBook.genres" classStyle="bg-green-500 w-auto p-[5px] rounded" />
                    </div>
                    <div class="w-full">
                        <div v-if="redactorActive" class="w-full flex">
                            <div class="w-[10%]">Авторы</div> 
                            <input type="text" 
                                    v-model="authors"
                                    @input="convertToAuthors"
                                    class="w-[90%] rounded border border-slate-300 placeholder-slate-400 px-2
                                                focus:outline-none focus:border-sky-500 focus:ring-1 focus:ring-sky-500"/>
                        </div>
                        <GenresAuthors v-else IfTitle="Авторы" ElseTitle="Автор" :list="activeBook.authors" classStyle="bg-blue-500 w-auto p-[5px] rounded" />
                    </div>
                    <div class="w-full">
                        <TitleInput v-if="redactorActive" @input="convertToAuthors" :model="newBook.isbn" :getModel='fSetData' property="isbn" title="ISBN" 
                                    classStyle="w-full flex text-xs" titleStyle="w-[5%]" 
                                    inputStyle="w-[25%] rounded border border-slate-300 placeholder-slate-400 px-2
                                                focus:outline-none focus:border-sky-500 focus:ring-1 focus:ring-sky-500" />
                        <div v-else class="text-xs">ISBN {{ activeBook.isbn }}</div>
                    </div>
                </div>
                <div class="w-1/4 flex flex-col gap-2">
                    <BookRedactorRight title="Издатель" :redactive="redactorActive" :ElseTitle="activeBook.publisher" :getModel='fSetData' property="publisher" />
                    <BookRedactorRight title="Год издания" :redactive="redactorActive" :ElseTitle="activeBook.yearOfPublishing" :getModel='fSetData' property="yearOfPublishing" />
                </div>
            </div>
            <div v-if="admin" class="w-full">
                <div v-if="redactorActive" class="w-full flex gap-2">
                    <button v-if="newBookBool" type="button" @click="fAddBook" class="transition ease-in-out duration-300 bg-green-500 p-2 rounded-lg active:bg-green-600">Сохранить книгу</button>
                    <button v-else type="button" @click="fCloseRedactorAndSafe" class="transition ease-in-out duration-300 bg-green-500 p-2 rounded-lg active:bg-green-600">Сохранить изменения</button>
                    <button v-if="newBookBool" type="button" @click="fCloseModal" class="transition ease-in-out duration-300 bg-red-500 p-2 rounded-lg active:bg-red-600">Закрыть редактор</button>
                    <button v-else type="button" @click="fCloseRedactor" class="transition ease-in-out duration-300 bg-red-500 p-2 rounded-lg active:bg-red-600">Закрыть редактор</button>
                </div>
                <div v-else class="w-full flex gap-2">
                    <button type="button" @click="fOpenRedactor" class="transition ease-in-out duration-300 bg-green-500 p-2 rounded-lg active:bg-green-600">Редактировать книгу</button>
                    <button type="button" @click="fDeleteBook(activeBook.id)" class="transition ease-in-out duration-300 bg-red-500 p-2 rounded-lg active:bg-red-600">Удалить книгу</button>
                </div>
            </div>
            <div v-else class="w-full">
                <button type="button" v-if="isBookInList(activeBook)" @click="fReturnUsersBook(activeBook.id)" 
                class="transition ease-in-out duration-300 bg-red-500 p-2 rounded-lg active:bg-red-600">Убрать книгу из читательского билета</button>
                <button type="button" v-else @click="fAddUsersBook(activeBook.id)" 
                class="transition ease-in-out duration-300 bg-green-500 p-2 rounded-lg active:bg-green-600">Добавить книгу в читательский билет</button>
            </div>
        </div>
    </div>
</template>
<script>
import { mapGetters, mapActions } from 'vuex';
import TitleInput from "../../components/TitleInput.vue"
import BookRedactorRight from "../Library/BookRedactorRight.vue"
import GenresAuthors from "../Library/GenresAuthors.vue"
export default {
    components: {
        TitleInput, BookRedactorRight, GenresAuthors
    },
    props: ['admin', 'closeModal', 'openRedactor', 'closeRedactor', 'closeRedactorAndSafe', 'deleteBook', 
            'setData', 'newBook', 'activeBook', 'redactorActive', 'addUsersBook', 'returnUsersBook', 'bookAdder', 'newBookBool'],
    data(){
        return{
            genres: null,
            authors: null,
        }
    },
    mounted() {
        document.addEventListener('keyup', this.escFunction);
        if (Array.isArray(this.activeBook.genres))
        {
            this.genres = this.activeBook.genres.join(' ');
        }
        if (Array.isArray(this.activeBook.authors))
        {
            this.genres = this.activeBook.authors.join(' ');
        }
    },
    beforeDestroy() {
        document.removeEventListener('keyup', this.escFunction);
    },
    computed: {
        ...mapGetters(['getUsersBooks'])
    },
    methods: {
        ...mapActions(['addBook', 'updateBook', 'deleteBookInList', 'setBookInList', 
                        'addUsersBook', 'returnUsersBookInList', 'setUsersBookInList']),
        escFunction(event) {
            if(event.keyCode === 27) {
                this.closeModal();
            }
        },
        convertToAuthors(){
            this.newBook.genres = this.genres.split(' ');
        },
        convertToGenres(){
            this.newBook.authors = this.genres.split(' ');
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
        async fCloseRedactorAndSafe(){
            await this.updateBook(this.newBook.id, {name: this.newBook.name, description: this.newBook.name, publisher: this.newBook.name, 
                    yearOfPublishing: this.newBook.name, genres: this.newBook.name, authors: this.newBook.name, 
                    isbn: this.newBook.name, countOfPages: this.newBook.name})
            this.closeRedactorAndSafe();
        },
        async fDeleteBook(id){
            await this.deleteBookInList(id)
            await this.setBookInList()
            this.deleteBook(id);
        },
        async fAddBook(){
            await this.addBook({name: this.newBook.name, description: this.newBook.name, publisher: this.newBook.name, 
                    yearOfPublishing: this.newBook.name, genres: this.newBook.name, authors: this.newBook.name, 
                    isbn: this.newBook.name, countOfPages: this.newBook.name})
            await this.setBookInList()
            this.bookAdder();
        },
        fSetData(property, data){
            this.setData(property, data);
        },
        isBookInList(bookToFind) {
            return this.getUsersBooks.some(userBook => {
                return userBook.book.id === bookToFind.id && userBook.isRent === true;
            });
        },
        async fAddUsersBook(id){
            await this.addUsersBook(id)
            await this.setUsersBookInList()
        },
        async fReturnUsersBook(id){
            await this.returnUsersBookInList(id)
            await this.setUsersBookInList()
        }
    }
}
</script>
<style>

</style>