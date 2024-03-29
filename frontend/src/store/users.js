import {http} from "../plugins/axios";
import router from "../router/index";

export default {
    actions: {
        async userLogin({commit}, {username, password}){
            const response = await http.post("/api/v1/auth/login", {}, {
                auth: {
                    username, password
                }
            })
            commit('setToken',response.data)
        },
        async setUserInUser({commit, state}){
            try {
                const response = await http.get("/api/v1/auth/profile", {
                    headers: {
                        'Authorization': `Bearer ${state.token}`
                    }
                })
                commit('setUser',response.data)
            } catch (error){
                if (error.response && error.response.status === 401) {
                router.push('/login');
                } else {
                    console.error(error);
                }
            }
        },
        async setUsersInList({commit,state}){
            try {
                const response = await http.get("/api/v1/admin/user/all",{
                    headers: {
                        'Authorization': `Bearer ${state.token}`
                    }
                })
                commit('setUsers',response.data)
            } catch (error){
                if (error.response && error.response.status === 401) {
                router.push('/login');
                } else {
                    console.error(error);
                }
            }
        },
        async addUser({commit,state}, {username, firstName, lastName, role, password}){
            try {
                const response = await http.post("/api/v1/admin/user/register",{username, firstName, lastName, role, password},{
                    headers: {
                        'Authorization': `Bearer ${state.token}`
                    }
                })
                commit('updateUsers',response.data)
            } catch (error){
                if (error.response && error.response.status === 401) {
                router.push('/login');
                } else {
                    console.error(error);
                }
            }
        },
        async deleteUserInList({ commit,state }, id) {
            try{
                const response = await http.delete("/api/v1/admin/user/delete/" + id,{
                    headers: {
                        'Authorization': `Bearer ${state.token}`
                    }
                })
                commit('deleteUser',response.data)
            } catch (error){
                if (error.response && error.response.status === 401) {
                router.push('/login');
                } else {
                    console.error(error);
                }
            }
        },
        async updateExistUser({commit,state}, {id, username, firstName, lastName, role, password}){
            try {
                const response = await http.post("/api/v1/admin/user/register",{id, username, firstName, lastName, role, password},{
                    headers: {
                        'Authorization': `Bearer ${state.token}`
                    }
                })
                commit('updateUser',response.data)
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
        setToken(state, token) {
            state.token = token
        },
        setUser(state, user){
            state.user = user
        },
        setUsers(state, users) {
            state.users = users
        },
        updateUsers(state, user){
            state.users.push(user)
        },
        updateUser(state, user){
            for (let i = 0; i < state.users.length; i++) {
                if (state.users[i].id === user.id) {
                    state.users[i] = user;
                    break;
                }
            }
        },
        deleteUser(state,user) {
            state.tasks = state.users.filter(u => u.id != user.id)
        },
    },
    state: {
        token: null,
        user: null,
        users: []
    },
    getters: {
        getUsers(state){
            return state.users
        },
        getToken(state){
            return state.token
        },
        getUser(state){
            return state.user
        }
    }
}