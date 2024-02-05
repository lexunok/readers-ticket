<template>
    <div class="background">
        <AdminNav/>
        <transition name="fade" enter-active-class="fade-enter-active">
            <UsersList v-if="show" :users="getUsers" :openModal="openModal" :userAdder="userAdder"/>
        </transition>
    </div>
    <transition name="modal" enter-active-class="modal-enter-active" leave-active-class="modal-leave-active">
        <UsersModal v-if="showModal" :newUser="newUser" :activeUser="activeUser" :redactorActive="redactorActive" 
                        :closeModal="closeModal" :openRedactor="openRedactor" :closeRedactor="closeRedactor" :newUserBool="newUserBool"
                        :closeRedactorAndSafe="closeRedactorAndSafe" :deleteUser="deleteUser" :setData="setData" :addderUser="addUser"/>
    </transition>
</template>
<script>
    import { mapActions, mapGetters } from 'vuex';
    import AdminNav from "../../components/Navigation/AdminNav.vue"
    import UsersList from "../../components/Users/UsersList.vue"
    import UsersModal from "../../components/Users/UsersModal.vue"
    export default{
        components: {
                AdminNav, UsersList, UsersModal
        },
        data() {
            return {
                show: false,
                showModal: false,
                newUserBool: false,
                redactorActive: false,
                activeUser: {
                    id: '',
                    username: '',
                    firstName: '',
                    lastname: '',
                    role: '',
                    password: ''
                },
                newUser: {
                    id: '',
                    username: '',
                    firstName: '',
                    lastname: '',
                    role: '',
                    password: ''
                }
            }
        },
        mounted() {
            this.show = true;
            this.setUsersInList()
        },
        computed: {
            ...mapGetters(['getUsers'])
        },
        methods:{
            ...mapActions(['setUsersInList']),
            openModal(user) {
                this.activeUser = user;
                this.showModal = true;
            },
            closeModal() {
                this.activeUser = {
                    id: '',
                    username: '',
                    firstName: '',
                    lastname: '',
                    role: '',
                    password: ''
                };
                this.redactorActive = false;
                this.newUserBool = false;
                this.showModal = false;
            },
            openRedactor(){
                this.newUser = Object.assign({}, this.activeUser);
                this.newUser.password = null;
                this.redactorActive = true;
            },
            closeRedactor(){
                this.newUserBool = false;
                this.redactorActive = false;
            },
            closeRedactorAndSafe(){
                this.activeUser = Object.assign({}, this.newUser);
                this.newUserBool = false;
                this.redactorActive = false;
            },
            addUser(){
                this.newUser = {
                    id: '',
                    username: '',
                    firstName: '',
                    lastname: '',
                    role: '',
                    password: ''
                };
                this.newUserBool = false;
                this.redactorActive = false;
                this.showModal = false;
            },
            deleteUser(){
                this.newUser = {
                    id: '',
                    username: '',
                    firstName: '',
                    lastname: '',
                    role: '',
                    password: ''
                };
                this.newUserBool = false;
                this.redactorActive = false;
                this.showModal = false;
            },
            setData(property, data){
                this.newUser[property] = data;
            },
            userAdder(){
                this.newUserBool = true;
                this.redactorActive = true;
                this.showModal = true;
            },
        }
    }
</script>