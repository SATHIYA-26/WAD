# Room + Booking Full-Stack Application

Stack:
- Frontend: Vue 3 + Vite
- Backend: Spring Boot REST API
- Database: MongoDB
- Java compiler target: Java 17 (can run using JDK 21)

## What happens when the user submits the form?
The frontend sends one request to `POST /api/bookings`.
The backend:
1. Saves room information into MongoDB collection `room`.
2. Saves guest/booking information into collection `booking`, including the saved room ID.
3. Returns both saved records to the frontend.

## Requirements
- JDK 21 (JDK 17 also works)
- Maven
- Node.js and npm
- MongoDB running on `localhost:27017`

## Run backend
Open a terminal in `backend`:
```bash
java -version
mvn -version
mvn spring-boot:run
```
Backend runs at `http://localhost:8080`.

## Run frontend
Open another terminal in `frontend`:
```bash
npm install
npm run dev
```
Open the URL shown by Vite, normally `http://localhost:5173`.

## MongoDB
Database: `hotel_db`
Collections: `room`, `booking`
Collections are created automatically when the first documents are saved.

## API endpoints
- `POST /api/bookings` — save room and booking records together
- `GET /api/bookings` — list booking records
- `GET /api/rooms` — list room records

Note: This sample creates a new room document for each submitted booking, as requested for a form submission that writes to both collections. A production hotel system would normally select an existing room and check availability before creating a booking.
