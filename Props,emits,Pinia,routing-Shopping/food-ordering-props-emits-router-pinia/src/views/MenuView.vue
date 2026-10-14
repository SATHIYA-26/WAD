<template>
  <section>
    <h2>Food Menu</h2>

    <FoodCard
      v-for="food in foods"
      :key="food.id"
      :food="food"
      @add="cart.add"
    />

    <p v-if="cart.totalItems > 0">
      Items in cart: {{ cart.totalItems }}
    </p>
  </section>
</template>

<script setup>
import FoodCard from '../components/FoodCard.vue'
import { useCartStore } from '../stores/cart'

const cart = useCartStore()

const foods = [
  { id: 1, name: 'Pizza', price: 200 },
  { id: 2, name: 'Burger', price: 120 },
  { id: 3, name: 'Dosa', price: 80 }
]
</script>