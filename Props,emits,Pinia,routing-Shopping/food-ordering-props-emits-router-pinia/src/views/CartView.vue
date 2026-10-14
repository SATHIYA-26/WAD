<template>
  <section>
    <h2>Shopping Cart</h2>

    <p v-if="cart.items.length === 0">Your cart is empty.</p>

    <article v-for="item in cart.items" :key="item.id" class="cart-card">
      <h3>{{ item.name }}</h3>
      <p>Price: ₹{{ item.price }}</p>
      <p>Quantity: {{ item.quantity }}</p>
      <p>Subtotal: ₹{{ item.price * item.quantity }}</p>

      <button @click="cart.increase(item.id)">+</button>
      <button class="secondary" @click="cart.decrease(item.id)">-</button>
      <button class="danger" @click="cart.remove(item.id)">Remove</button>
    </article>

    <div v-if="cart.items.length > 0">
      <h3>Total Items: {{ cart.totalItems }}</h3>
      <h3>Total Price: ₹{{ cart.total }}</h3>
      <button class="secondary" @click="cart.clear()">Clear Cart</button>
    </div>
  </section>
</template>

<script setup>
import { useCartStore } from '../stores/cart'
const cart = useCartStore()
</script>