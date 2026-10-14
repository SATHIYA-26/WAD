import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import FoodLayout from '../views/FoodLayout.vue'
import MenuView from '../views/MenuView.vue'
import CartView from '../views/CartView.vue'

const routes = [
  { path: '/', component: HomeView },
  {
    path: '/food',
    component: FoodLayout,
    children: [
      { path: 'menu', component: MenuView },
      { path: 'cart', component: CartView }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router