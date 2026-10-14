<template>
  <div>
    <h2>Food Ordering</h2>

    <nav>
      <router-link to="/food/menu">Menu</router-link> |
      <router-link to="/food/cart">Cart</router-link> |
      <router-link to="/food/orders">Orders</router-link>
    </nav>

    <hr />

    <router-view />
  </div>
</template>