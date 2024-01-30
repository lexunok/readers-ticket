<template>
    <div class="w-screen h-screen bg-orange-200">
        <AdminNav/>
        <div class="h-[92%] w-full flex flex-col items-center">
            <div class="h-[8%] flex items-center text-4xl font-semibold">Пользователи</div>
            <ul class="h-[92%] w-[90%] overflow-auto">
                <li v-for="item in items" :key="item.id" class="h-[25%] w-full my-2 shadow-lg bg-white rounded-xl p-1">
                    <div class="text-2xl">{{ item.name }}</div>
                    <div>Издатель {{ item.publisher }}</div>
                    <div>Год издания {{ item.yearOfPublishing }}</div>
                    <div>Количество страниц {{ item.countOfPages }}</div>
                    <div class="flex items-center">
                        <div v-if="item.genres.length > 1">Жанры</div>
                        <div v-else>Жанр</div>
                        <ul class="w-full flex">
                            <li v-for="genre in item.genres" class="bg-green-500 w-auto p-2 rounded mx-2">
                                {{ genre }}
                            </li>
                        </ul>
                    </div>
                    <div class="flex items-center">
                        <div v-if="item.authors.length > 1">Авторы</div>
                        <div v-else>Автор</div>
                        <ul class="w-full flex">
                            <li v-for="author in item.authors" class="bg-orange-500 w-auto p-2 rounded mx-2">
                                {{ author }}
                            </li>
                        </ul>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</template>
<script>
    import AdminNav from "../../components/AdminNav.vue"
    export default{
        components: {
                AdminNav
        },
        data() {
            return {
                items: [
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
                    }
                ]
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
<style></style>