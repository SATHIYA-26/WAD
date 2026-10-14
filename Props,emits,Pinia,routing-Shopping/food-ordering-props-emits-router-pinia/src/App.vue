<template>
  <div class="app">
    <header>
      <h1>Online Food Ordering</h1>
      <nav>
        <router-link to="/">Home</router-link>
        <router-link to="/food/menu">Food Section</router-link>
      </nav>
    </header>

    <main>
      <router-view />
    </main>

  </div>
</template>

<style>
* { box-sizing: border-box; }
body {
  margin: 0;
  font-family: Arial, sans-serif;
  background: #f5f7fb;
  color: #202431;
}
.app { max-width: 850px; margin: 0 auto; padding: 24px; }
header, main, footer {
  background: white;
  padding: 22px;
  margin-bottom: 16px;
  border-radius: 12px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, .06);
}
nav { display: flex; gap: 18px; flex-wrap: wrap; }
nav a { color: #175cd3; text-decoration: none; }
nav a.router-link-active { font-weight: bold; text-decoration: underline; }
button {
  padding: 8px 12px; margin: 4px 4px 4px 0;
  border: 0; border-radius: 6px; background: #175cd3;
  color: white; cursor: pointer;
}
button.secondary { background: #667085; }
button.danger { background: #b42318; }
.food-card, .cart-card {
  border: 1px solid #e4e7ec; border-radius: 8px;
  padding: 12px; margin: 12px 0;
}
.subnav { margin: 12px 0; }
footer { text-align: center; color: #667085; font-size: 13px; }
</style>