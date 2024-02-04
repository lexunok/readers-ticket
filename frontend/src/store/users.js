import {http} from "../plugins/axios";

export default {
    actions: {
        async userLogin({commit}, {username, password}){
            const response = await http.post("/api/v1/auth/login", {}, {
                auth: {
                    username, password
                }
            })
            commit('setUser',response.data)
        },
        async setUsersInList({commit}){
            const response = await http.get("/api/v1/admin/user/all")
            commit('setUsers',response.data)
        },
        async addUser({commit}, {username, firstName, lastName, role, password}){
            const response = await http.post("/api/v1/admin/user/register",{username, firstName, lastName, role, password})
            commit('updateUser',response.data)
        },
        async deleteUserInList({ commit }, id) {
            const response = await http.delete("/api/v1/admin/user/delete/" + id)
            commit('deleteUser',response.data)
        }
    },
    mutations: {
        setUser(state, token) {
            state.token = token
        },
        setUsers(state, users) {
            state.users = users
        },
        updateUser(state, user){
            state.users.push(user)
        },
        deleteUser(state,user) {
            state.tasks = state.users.filter(u => u.id != user.id)
        },
    },
    state: {
        token: null,
        users: []
    },
    getters: {
        getUsers(state){
            return state.users
        },
        getToken(state){
            return state.token
        }
    }
}