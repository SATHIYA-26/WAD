ONLINE FOOD ORDERING: PROPS + EMITS + VUE ROUTER + PINIA

Requirements: Node.js and npm

Run:
1. Extract the ZIP.
2. Open the food-ordering-props-emits-router-pinia folder in VS Code.
3. Open a terminal in this folder.
4. Run:
   npm install
   npm run dev
5. Open the localhost URL printed by Vite, usually http://localhost:5173.

Routes:
- /            Home
- /food/menu   Food menu (nested route)
- /food/cart   Cart (nested route)

Concepts:
- Props: MenuView passes each food object to FoodCard through :food="food".
- Emits: FoodCard emits the 'add' event; MenuView handles it with @add="cart.add".
- Vue Router: switches between Home, Menu and Cart.
- Pinia: stores shared cart items and calculates item count and total price.

Frontend-only demo; no backend or database is required.
