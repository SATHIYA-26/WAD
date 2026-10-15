<template>
  <main class="container">
    <h1>Hotel Room Booking</h1>
    <p class="intro">Enter guest and room details. One submission saves data to both MongoDB collections.</p>

    <form @submit.prevent="saveBooking" class="form">
      <h2>Guest Details</h2>
      <input v-model.trim="form.guestName" placeholder="Guest name" required>
      <input v-model.trim="form.email" type="email" placeholder="Email" required>

      <h2>Room Details</h2>
      <input v-model.trim="form.roomNumber" placeholder="Room number" required>
      <select v-model="form.roomType" required>
        <option disabled value="">Select room type</option>
        <option>Single</option>
        <option>Double</option>
        <option>Deluxe</option>
        <option>Suite</option>
      </select>
      <input v-model.number="form.price" type="number" min="0" step="0.01" placeholder="Price per night" required>

      <h2>Booking Dates</h2>
      <label>Check-in <input v-model="form.checkIn" type="date" required></label>
      <label>Check-out <input v-model="form.checkOut" type="date" required></label>

      <button type="submit" :disabled="loading">{{ loading ? 'Saving...' : 'Book Room' }}</button>
    </form>

    <p v-if="message" :class="['message', error ? 'error' : 'success']">{{ message }}</p>

    <section class="lists">
      <div>
        <h2>Saved Rooms ({{ rooms.length }})</h2>
        <button type="button" class="secondary" @click="loadData">Refresh Lists</button>
        <div v-for="room in rooms" :key="room.id" class="record">
          <strong>Room {{ room.roomNumber }}</strong>
          <span>{{ room.roomType }} · {{ room.price }} per night · {{ room.status }}</span>
        </div>
      </div>
      <div>
        <h2>Saved Bookings ({{ bookings.length }})</h2>
        <div v-for="booking in bookings" :key="booking.id" class="record">
          <strong>{{ booking.guestName }} — Room {{ booking.roomNumber }}</strong>
          <span>{{ booking.checkIn }} to {{ booking.checkOut }}</span>
          <small>{{ booking.email }}</small>
        </div>
      </div>
    </section>
  </main>
</template>

<script>
const API = 'http://localhost:8080/api';

export default {
  data() {
    return {
      form: {
        guestName: '',
        email: '',
        roomNumber: '',
        roomType: '',
        price: 0,
        checkIn: '',
        checkOut: ''
      },
      rooms: [],
      bookings: [],
      message: '',
      error: false,
      loading: false
    };
  },
  methods: {
    async loadData() {
      try {
        const [roomResponse, bookingResponse] = await Promise.all([
          fetch(`${API}/rooms`),
          fetch(`${API}/bookings`)
        ]);
        if (!roomResponse.ok || !bookingResponse.ok) throw new Error();
        this.rooms = await roomResponse.json();
        this.bookings = await bookingResponse.json();
      } catch {
        this.message = 'Could not load lists. Make sure Spring Boot and MongoDB are running.';
        this.error = true;
      }
    },
    async saveBooking() {
      if (this.form.checkOut <= this.form.checkIn) {
        this.message = 'Check-out date must be after check-in date.';
        this.error = true;
        return;
      }

      this.loading = true;
      this.message = '';
      try {
        const response = await fetch(`${API}/bookings`, {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify(this.form)
        });

        if (!response.ok) {
          const detail = await response.text();
          throw new Error(detail || 'Booking could not be saved.');
        }

        const result = await response.json();
        this.message = `Booking saved! Room ID: ${result.room.id}; Booking ID: ${result.booking.id}. Data was saved to both collections.`;
        this.error = false;
        this.form = {
          guestName: '',
          email: '',
          roomNumber: '',
          roomType: '',
          price: 0,
          checkIn: '',
          checkOut: ''
        };
        await this.loadData();
      } catch (e) {
        this.message = e.message || 'Could not connect to backend.';
        this.error = true;
      } finally {
        this.loading = false;
      }
    }
  },
  mounted() {
    this.loadData();
  }
};
</script>
