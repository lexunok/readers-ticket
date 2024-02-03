import {http} from "../plugins/axios";

export default {
    actions: {
        async setUserInUser({commit}){
            const response = await http.get("/api/v1/book/all")
            commit('setBook',response.data)
        },
        async setUsersInList({commit}){
            const response = await http.get("/api/v1/admin/user/all")
            commit('setBook',response.data)
        },
        async addUser({commit}, {username, firstName, lastName, role, password}){
            const response = await http.post("/api/v1/admin/user/register",{username, firstName, lastName, role, password})
            commit('updateBooks',response.data)
        },
        async deleteUserInList({ commit }, id) {
            const response = await http.delete("/api/v1/admin/user/delete/" + id)
            commit('deleteUser',response.data)
        }
    },
    mutations: {
        setUser(state, user) {
            state.user = user
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
        user: null,
        users: []
    },
    getters: {
        getUsers(state){
            return state.users
        },
        getUser(state){
            return state.user
        }
    }
}