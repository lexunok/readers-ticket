import {http} from "../plugins/axios";
import router from "../router/index";

export default {
    actions: {
        async setBookInList({commit, rootState}){
            try {
                const response = await http.get("/api/v1/book/all",{
                    headers: {
                        'Authorization': `Bearer ${rootState.users.token}`
                    }
                })
                commit('setBook',response.data)
            } catch (error){
                if (error.response && error.response.status === 401) {
                router.push('/login');
                } else {
                    console.error(error);
                }
            }
        },
        async addBook({commit, rootState}, {name, description, publisher, yearOfPublishing, genres, authors, isbn, countOfPages}){
            try {
                const response = await http.post("/api/v1/admin/book/add",{name, description, publisher, yearOfPublishing, genres, authors, isbn, countOfPages},{
                    headers: {
                        'Authorization': `Bearer ${rootState.users.token}`
                    }
                })
                commit('updateBooks',response.data)
            } catch (error){
                if (error.response && error.response.status === 401) {
                router.push('/login');
                } else {
                    console.error(error);
                }
            }
        },
        async updateBook({commit, rootState}, {id, name, description, publisher, yearOfPublishing, genres, authors, isbn, countOfPages}){
            try {
                const response = await http.put("/api/v1/admin/book/update/" + id,{name, description, publisher, yearOfPublishing, genres, authors, isbn, countOfPages},{
                    headers: {
                        'Authorization': `Bearer ${rootState.users.token}`
                    }
                })
                commit('updateBook',response.data)
            } catch (error){
                if (error.response && error.response.status === 401) {
                router.push('/login');
                } else {
                    console.error(error);
                }
            }
        },
        async deleteBookInList({ commit, rootState }, id) {
            try {
                const response = await http.delete("/api/v1/admin/book/delete/" + id,{
                    headers: {
                        'Authorization': `Bearer ${rootState.users.token}`
                    }
                })
                commit('deleteBook',response.data)
            } catch (error){
                if (error.response && error.response.status === 401) {
                router.push('/login');
                } else {
                    console.error(error);
                }
            }
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
        books: []
    },
    getters: {
        getBooks(state){
            return state.books
        }
    }
}