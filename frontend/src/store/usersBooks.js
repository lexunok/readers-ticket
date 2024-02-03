import {http} from "../plugins/axios";

export default {
    actions: {
        async setUsersBookInList({commit}){
            const response = await http.get("/api/v1/idea/get")
            commit('setUsersBooks',response.data)
        },
        async addUsersBook({commit},{userId, book, isRent, rentedAt, returnedAt}){
            const response = await http.post("/api/v1/idea/add",{userId, book, isRent, rentedAt, returnedAt})
            commit('updateUsersBooks',response.data)
        },
        async returnUsersBookInList({ commit }, id) {
            const response = await http.put("/api/v1/idea/delete/" + id)
            commit('returnUsersBook',response.data)
        }
    },
    mutations: {
        setUsersBooks(state, usersBooks) {
            state.usersBooks = usersBooks
        },
        updateUsersBooks(state, usersBook){
            state.usersBooks.push(usersBook)
        },
        returnUsersBook(state,usersBook) {
            for (let i = 0; i < state.usersBooks.length; i++) {
                if (state.usersBooks[i].id === usersBook.id) {
                    state.usersBooks[i] = usersBook;
                    break;
                }
            }
        },
    },
    state: {
        usersBooks: [
            { 
                id: 1, 
                userId: 1, 
                book: { 
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
                isRent: true,
                rentedAt: null,
                returnedAt: null
            },
            { 
                id: 2,
                userId: 1, 
                book: { 
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
                isRent: false,
                rentedAt: null,
                returnedAt: null
            },
            { 
                id: 3, 
                userId: 1, 
                book: { 
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
                isRent: false,
                rentedAt: null,
                returnedAt: null
            }
        ]
    },
    //private Long id;
    // private Long userId;
    // private Book book;
    // private Boolean isRent;
    // private LocalDate rentedAt;
    // private LocalDate returnedAt;
    getters: {
        getUsersBooks(state){
            return state.usersBooks
        }
    }
}