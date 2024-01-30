import { createRouter, createWebHashHistory, createWebHistory } from 'vue-router'

const routes = [
    {
      path: '/',
      name: 'Добро пожаловать!',
      component: () => import( '../views/Start.vue'),
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