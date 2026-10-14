<template>
  <section>
    <h2>Your Cart</h2>
    <p v-if="cart.cartItems.length === 0">Your cart is empty. Visit the Menu to add food.</p>

    <div v-for="item in cart.cartItems" :key="item.id" class="cart-card">
      <h3>{{ item.name }}</h3>
      <p>Price: ₹{{ item.price }}</p>
      <p>Quantity: {{ item.quantity }}</p>
      <p>Subtotal: ₹{{ item.price * item.quantity }}</p>
      <button @click="cart.increaseQuantity(item.id)">+</button>
      <button class="secondary" @click="cart.decreaseQuantity(item.id)">−</button>
      <button class="secondary" @click="cart.removeFromCart(item.id)">Remove</button>
    </div>

    <div v-if="cart.cartItems.length">
      <h3>Total Items: {{ cart.totalItems }}</h3>
      <h3>Total Price: ₹{{ cart.totalPrice }}</h3>
      <button class="secondary" @click="cart.clearCart()">Clear Cart</button>
    </div>
  </section>
</template>

<script setup>
import { useCartStore } from '../stores/cart'
const cart = useCartStore()
</script>