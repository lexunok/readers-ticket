import { createStore } from "vuex";
import library from "./library";
import usersBooks from "./usersBooks";

export const store = createStore({
    modules: {
        library, usersBooks
    }
})


