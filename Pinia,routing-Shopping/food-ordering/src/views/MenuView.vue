<template>
  <section>
    <h2>Food Menu</h2>
    <div v-for="food in foods" :key="food.id" class="food-card">
      <h3>{{ food.name }}</h3>
      <p class="price">₹{{ food.price }}</p>
      <button @click="cart.addToCart(food)">Add to Cart</button>
    </div>
    <p v-if="cart.totalItems">Items in cart: {{ cart.totalItems }}</p>
  </section>
</template>

<script setup>
import { useCartStore } from '../stores/cart'

const cart = useCartStore()
const foods = [
  { id: 1, name: 'Pizza', price: 200 },
  { id: 2, name: 'Burger', price: 120 },
  { id: 3, name: 'Dosa', price: 80 }
]
</script>