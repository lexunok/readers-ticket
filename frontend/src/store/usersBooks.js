import {http} from "../plugins/axios";

export default {
    actions: {
        async setUsersBookInList({commit}){
            const response = await http.get("/api/v1/book/user/all")
            commit('setUsersBooks',response.data)
        },
        async addUsersBook({commit}, bookId){
            const response = await http.post("/api/v1/book/rent/" + bookId)
            commit('updateUsersBooks',response.data)
        },
        async returnUsersBookInList({ commit }, bookId) {
            const response = await http.put("/api/v1/book/return/" + bookId)
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
        usersBooks: []
    },
    getters: {
        getUsersBooks(state){
            return state.usersBooks
        }
    }
}