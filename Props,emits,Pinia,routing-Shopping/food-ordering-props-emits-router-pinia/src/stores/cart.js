import { defineStore } from 'pinia'

export const useCartStore = defineStore('cart', {
  state: () => ({
    items: []
  }),

  getters: {
    totalItems: (state) =>
      state.items.reduce((sum, item) => sum + item.quantity, 0),

    total: (state) =>
      state.items.reduce(
        (sum, item) => sum + item.price * item.quantity, 0
      )
  },

  actions: {
    add(food) {
      const item = this.items.find(i => i.id === food.id)

      if (item) {
        item.quantity++
      } else {
        this.items.push({ ...food, quantity: 1 })
      }
    },

    increase(id) {
      const item = this.items.find(i => i.id === id)
      if (item) item.quantity++
    },

    decrease(id) {
      const item = this.items.find(i => i.id === id)
      if (!item) return

      if (item.quantity > 1) {
        item.quantity--
      } else {
        this.remove(id)
      }
    },

    remove(id) {
      this.items = this.items.filter(i => i.id !== id)
    },

    clear() {
      this.items = []
    }
  }
})