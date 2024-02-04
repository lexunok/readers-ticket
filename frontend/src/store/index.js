import { createStore } from "vuex";
import library from "./library";
import usersBooks from "./usersBooks";
import users from "./users";

export const store = createStore({
    modules: {
        library, usersBooks, users
    }
})


