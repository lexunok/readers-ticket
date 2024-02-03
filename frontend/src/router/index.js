import { createRouter, createWebHashHistory, createWebHistory } from 'vue-router'

const routes = [
    {
      path: '/',
      redirect: '/login'
    },
    {
      path: '/login',
      name: 'Вход',
      component: () => import( '../views/Login.vue'),
    },
    {
      path: '/register',
      name: 'Регистрация',
      component: () => import( '../views/Registration.vue'),
    },
    {
      path: '/admin/',
      children: [
          {
              path: '',
              name: 'Админ панель',
              component: () => import('../views/Admin/Admin.vue')
          },
          {
              path: 'users',
              name: 'Список пользователей',
              component: () => import('../views/Admin/Users.vue')
          },
          {
            path: 'library',
            name: 'Список литературы',
            component: () => import('../views/Admin/Library.vue')
        },
      ],
    },
    {
      path: '/library/',
      children: [
          {
              path: '',
              name: 'Главная',
              component: () => import('../views/Library/Main.vue')
          },
          {
              path: 'user',
              name: 'Профиль',
              component: () => import('../views/Library/Profile.vue')
          },
          {
            path: 'books',
            name: 'Библиотека',
            component: () => import('../views/Library/Library.vue')
        },
      ],
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes,
})
router.beforeEach((to, from, next) => {
  document.title = to.name;
  next();
});
export default router