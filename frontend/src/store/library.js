import {http} from "../plugins/axios";

export default {
    actions: {
        async setBookInList({commit}){
            const response = await http.get("/api/v1/idea/get")
            commit('setBook',response.data)
        },
        async addBook({commit},{name, description, publisher, yearOfPublishing, genres, authors, isbn, countOfPages}){
            const response = await http.post("/api/v1/idea/add",{name, description, publisher, yearOfPublishing, genres, authors, isbn, countOfPages})
            commit('updateBooks',response.data)
        },
        async updateBook({commit},{id, name, description, publisher, yearOfPublishing, genres, authors, isbn, countOfPages}){
            const response = await http.put("/api/v1/idea/add",{id, name, description, publisher, yearOfPublishing, genres, authors, isbn, countOfPages})
            commit('updateBook',response.data)
        },
        async deleteBookInList({ commit }, id) {
            const response = await http.delete("/api/v1/idea/delete/" + id)
            commit('deleteBook',response.data)
        }
    },
    mutations: {
        setBook(state, books) {
            state.books = books
        },
        updateBooks(state, book){
            state.books.push(book)
        },
        updateBook(state, book){
            for (let i = 0; i < state.books.length; i++) {
                if (state.books[i].id === book.id) {
                    state.books[i] = book;
                    break;
                }
            }
        },
        deleteBook(state,book) {
            state.tasks = state.books.filter(b => b.id != book.id)
        },
    },
    state: {
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
    },
    getters: {
        getBooks(state){
            return state.books
        }
    }
}