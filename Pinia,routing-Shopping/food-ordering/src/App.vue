<template>
  <div>
    <h1>Online Food Ordering</h1>

    <nav>
      <router-link to="/">Home</router-link> |
      <router-link to="/food/menu">Food Section</router-link>
    </nav>

    <hr />

    <router-view />
  </div>
</template>

<style>
body {
  font-family: Arial, sans-serif;
  margin: 30px;
}

nav a {
  margin: 10px;
}
</style>