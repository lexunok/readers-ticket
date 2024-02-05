import {http} from "../plugins/axios";

export default {
    actions: {
        async setUsersBookInList({commit,rootState}){
            const response = await http.get("/api/v1/book/user/all",{
                headers: {
                    'Authorization': `Bearer ${rootState.users.token}`
                }
            })
            commit('setUsersBooks',response.data)
        },
        async addUsersBook({commit,rootState}, bookId){
            const response = await http.post("/api/v1/book/rent/" + bookId, {},{
                headers: {
                    'Authorization': `Bearer ${rootState.users.token}`
                }
            })
            commit('updateUsersBooks',response.data)
        },
        async returnUsersBookInList({ commit,rootState }, bookId) {
            const response = await http.put("/api/v1/book/return/" + bookId, {},{
                headers: {
                    'Authorization': `Bearer ${rootState.users.token}`
                }
            })
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