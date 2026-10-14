import { defineStore } from 'pinia'

export const useCartStore = defineStore('cart', {
  state: () => ({
    cartItems: []
  }),

  getters: {
    totalItems: (state) =>
      state.cartItems.reduce((sum, item) => sum + item.quantity, 0),

    totalPrice: (state) =>
      state.cartItems.reduce(
        (sum, item) => sum + item.price * item.quantity, 0
      )
  },

  actions: {
    addToCart(food) {
      const item = this.cartItems.find(i => i.id === food.id)

      if (item) {
        item.quantity++
      } else {
        this.cartItems.push({ ...food, quantity: 1 })
      }
    },

    increaseQuantity(id) {
      const item = this.cartItems.find(i => i.id === id)
      if (item) item.quantity++
    },

    decreaseQuantity(id) {
      const item = this.cartItems.find(i => i.id === id)

      if (item) {
        if (item.quantity > 1) {
          item.quantity--
        } else {
          this.removeFromCart(id)
        }
      }
    },

    removeFromCart(id) {
      this.cartItems = this.cartItems.filter(i => i.id !== id)
    },

    clearCart() {
      this.cartItems = []
    }
  }
})