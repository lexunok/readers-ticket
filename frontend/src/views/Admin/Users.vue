<template>
    <div class="w-screen h-screen bg-orange-200">
        <AdminNav/>
        <transition name="fade" enter-active-class="enter-active">
            <UsersList v-if="show" :users="getUsers" :openModal="openModal" :userAdder="userAdder"/>
        </transition>
    </div>
    <transition name="modal" enter-active-class="enter-active" leave-active-class="leave-active">
        <UsersModal v-if="showModal" :newUser="newUser" :activeUser="activeUser" :redactorActive="redactorActive" 
                        :closeModal="closeModal" :openRedactor="openRedactor" :closeRedactor="closeRedactor" :newUserBool="newUserBool"
                        :closeRedactorAndSafe="closeRedactorAndSafe" :deleteUser="deleteUser" :setData="setData"/>
    </transition>
</template>
<script>
    import { mapGetters } from 'vuex';
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
        },
        computed: {
            ...mapGetters(['getUsers'])
        },
        methods:{
            openModal(user) {
                this.activeUser = user;
                this.showModal = true;
            },
            closeModal() {
                this.redactorActive = false;
                this.activeUser = {
                    id: '',
                    username: '',
                    firstName: '',
                    lastname: '',
                    role: '',
                    password: ''
                };
                this.showModal = false;
            },
            openRedactor(){
                this.newUser = Object.assign({}, this.activeUser);
                this.redactorActive = true;
            },
            closeRedactor(){
                this.newUser = {
                    id: '',
                    username: '',
                    firstName: '',
                    lastname: '',
                    role: '',
                    password: ''
                };
                this.redactorActive = false;
            },
            closeRedactorAndSafe(){
                this.activeUser = Object.assign({}, this.newUser);
                this.redactorActive = false;
            },
            deleteUser(){
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
    // private Long id;
    // private String username;
    // private String firstName;
    // private String lastName;
    // private User.Role role;
    // private String password;
</script>
<style>
.enter-active {
  animation: modal-in 0.3s ease-in;
}
.leave-active {
  animation: modal-out 0.3s ease-out;
}
@keyframes modal-in {
  0% {
    opacity: 0;
  }
  100% {
    opacity: 1;
  }
}
@keyframes modal-out {
  0% {
    opacity: 1;
  }
  100% {
    opacity: 0;
  }
}
.enter-active {
  animation: fade-in 0.2s ease-in;
}
@keyframes fade-in {
  0% {
    opacity: 0;
  }
  100% {
    opacity: 1;
  }
}
::-webkit-scrollbar {
    width: 5px;
}

::-webkit-scrollbar-thumb {
    border-radius: 5px;
    background-color: #FFFFFF;
}

::-webkit-scrollbar-track {
    border-radius: 5px;
}
</style>