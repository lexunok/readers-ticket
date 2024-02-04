import router from '../router'
import { store } from '../store'

export function registerPlugins (app) {
  app
    .use(store)
    .use(router)
}