<template>
    <div class="w-screen h-screen bg-orange-200">
        <AdminNav/>
        <transition name="fade" enter-active-class="enter-active">
            <UsersList v-if="show" :users="users" :openModal="openModal"/>
        </transition>
    </div>
    <transition name="modal" enter-active-class="enter-active" leave-active-class="leave-active">
        <UsersModal v-if="showModal" :newUser="newUser" :activeUser="activeUser" :redactorActive="redactorActive" 
                        :closeModal="closeModal" :openRedactor="openRedactor" :closeRedactor="closeRedactor"
                        :closeRedactorAndSafe="closeRedactorAndSafe" :deleteUser="deleteUser" :setData="setData"/>
    </transition>
</template>
<script>
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
                redactorActive: false,
                activeUser: null,
                newUser: null,
                users: [
                    { 
                        id: 1, 
                        username: "user1", 
                        firstName: "Иван", 
                        lastName: "Иванов", 
                        role: "Admin", 
                        password: "password123"
                    }, 
                    { 
                        id: 2, 
                        username: "user2", 
                        firstName: "Петр", 
                        lastName: "Петров", 
                        role: "User", 
                        password: "password456"
                    }, 
                    { 
                        id: 3, 
                        username: "user3", 
                        firstName: "Сергей", 
                        lastName: "Сергеев", 
                        role: "User", 
                        password: "password789"
                    }, 
                    { 
                        id: 4, 
                        username: "user4", 
                        firstName: "Дмитрий", 
                        lastName: "Дмитриев", 
                        role: "Moderator", 
                        password: "password321"
                    }, 
                    { 
                        id: 5, 
                        username: "user5", 
                        firstName: "Алексей", 
                        lastName: "Алексеев", 
                        role: "Guest", 
                        password: "password654"
                    }
                ]
            }
        },
        mounted() {
            this.show = true;
        },
        methods:{
            openModal(user) {
                this.activeUser = user;
                this.showModal = true;
            },
            closeModal() {
                this.redactorActive = false;
                this.activeUser = null;
                this.showModal = false;
            },
            openRedactor(){
                this.newUser = Object.assign({}, this.activeUser);
                this.redactorActive = true;
            },
            closeRedactor(){
                this.newUser = null;
                this.redactorActive = false;
            },
            closeRedactorAndSafe(id){
                this.newUser.id = id;
                for (let i = 0; i < this.users.length; i++) {
                    if (this.users[i].id === id) {
                        this.users[i] = Object.assign({}, this.newUser);;
                        break;
                    }
                }
                this.activeUser = Object.assign({}, this.newUser);
                this.redactorActive = false;
            },
            deleteUser(id){
                this.users = this.users.filter(element => element.id !== id)
                this.showModal = false;
            },
            setData(property, data){
                this.newUser[property] = data;
            }
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