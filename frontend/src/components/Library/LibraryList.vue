<template>
    <div class="h-[92%] w-full flex flex-col items-center">
        <Title :title="title" />
        <ul class="h-[92%] w-[90%] overflow-y-auto">
            <li v-if="admin" class="h-auto m-2 w-[97%] flex justify-center">
                <button type="button" @click="startBookAdder" 
                        class="transition duration-300 ease-in-out h-full w-[48%] flex justify-center items-center shadow-lg bg-white 2xl:text-2xl xl:text-xl lg:text-lg rounded-xl py-4 px-5 
                                hover:bg-gray-200 active:bg-gray-300 hover:shadow-md focus:outline-none select-none">Добавить книгу</button>
            </li>
            <li v-for="book in books" :key="book.id" class="h-auto inline-block w-[48%] m-2">
                <button type="button" @click="startModal(book)" class="transition duration-300 ease-in-out h-full w-full shadow-lg bg-white rounded-xl py-4 px-5 
                                                                    hover:bg-gray-200 active:bg-gray-300 hover:shadow-md focus:outline-none">
                    <div class="w-full h-full flex flex-col gap-1">
                        <div class="flex items-end gap-2 h-[20%]">
                            <div class="2xl:text-2xl xl:text-xl lg:text-lg">{{ book.name }}</div>
                            <div class="flex gap-1 2xl:text-base xl:text-sm lg:text-xs">
                                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6">
                                    <path stroke-linecap="round" stroke-linejoin="round" d="M12 6.042A8.967 8.967 0 0 0 6 3.75c-1.052 0-2.062.18-3 .512v14.25A8.987 8.987 0 0 1 6 18c2.305 0 4.408.867 6 2.292m0-14.25a8.966 8.966 0 0 1 6-2.292c1.052 0 2.062.18 3 .512v14.25A8.987 8.987 0 0 0 18 18a8.967 8.967 0 0 0-6 2.292m0-14.25v14.25" />
                                </svg>
                                {{ book.countOfPages }} страниц
                            </div>
                        </div>
                        <div class="h-[15%] flex 2xl:text-base xl:text-sm lg:text-xs">Издатель {{ book.publisher }}, {{ book.yearOfPublishing }} г.</div>
                        <GenresAuthors IfTitle="Жанры" ElseTitle="Жанр" :list="book.genres" classStyle="bg-green-500 w-auto p-[5px] rounded 2xl:text-base xl:text-sm lg:text-xs" />
                        <GenresAuthors IfTitle="Авторы" ElseTitle="Автор" :list="book.authors" classStyle="bg-blue-500 w-auto p-[5px] rounded 2xl:text-base xl:text-sm lg:text-xs" />
                    </div>
                </button>
            </li>
        </ul>
    </div>
</template>
<script>
    import GenresAuthors from "../Library/GenresAuthors.vue"
    import Title from "../../components/Title.vue"
    export default {
        components: { 
            GenresAuthors, Title
        },
        props: ['books', 'openModal', 'title', 'admin', 'bookAdder'],
        methods: {
            startModal(book){
                this.openModal(book);
            },
            startBookAdder(){
                this.bookAdder();
            }
        }
    }
</script>