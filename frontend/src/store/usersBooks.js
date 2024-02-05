import {http} from "../plugins/axios";
import router from "../router/index";

export default {
    actions: {
        async setUsersBookInList({commit, rootState}) {
            try {
                const response = await http.get("/api/v1/book/user/all", {
                headers: {
                    'Authorization': `Bearer ${rootState.users.token}`
                }
                });
                commit('setUsersBooks', response.data);
            } catch (error) {
                if (error.response && error.response.status === 401) {
                router.push('/login');
                } else {
                    console.error(error);
                }
            }
        },
        async addUsersBook({rootState}, bookId){
            try{
                await http.post("/api/v1/book/rent/" + bookId, {},{
                    headers: {
                        'Authorization': `Bearer ${rootState.users.token}`
                    }
                })
            } catch (error){
                if (error.response && error.response.status === 401) {
                router.push('/login');
                } else {
                    console.error(error);
                }
            }
        },
        async returnUsersBookInList({ rootState }, bookId) {
            try{
                await http.put("/api/v1/book/return/" + bookId, {},{
                    headers: {
                        'Authorization': `Bearer ${rootState.users.token}`
                    }
                })
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
        setUsersBooks(state, usersBooks) {
            state.usersBooks = usersBooks
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