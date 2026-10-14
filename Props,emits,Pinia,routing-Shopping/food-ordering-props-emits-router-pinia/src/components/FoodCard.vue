<template>
  <article class="food-card">
    <h3>{{ food.name }}</h3>
    <p>Price: ₹{{ food.price }}</p>
    <button @click="$emit('add', food)">Add to Cart</button>
  </article>
</template>

<script setup>
defineProps({
  food: {
    type: Object,
    required: true
  }
})

defineEmits(['add'])
</script>