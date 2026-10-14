<template>
  <section>
    <h2>Food Section</h2>
    <nav class="subnav">
      <router-link to="/food/menu">Menu</router-link>
      <router-link to="/food/cart">Cart ({{ cart.totalItems }})</router-link>
    </nav>
    <hr />
    <router-view />
  </section>
</template>

<script setup>
import { useCartStore } from '../stores/cart'
const cart = useCartStore()
</script>