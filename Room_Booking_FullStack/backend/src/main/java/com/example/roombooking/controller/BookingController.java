package com.example.roombooking.controller;

import com.example.roombooking.dto.BookingRequest;
import com.example.roombooking.dto.BookingResponse;
import com.example.roombooking.model.Booking;
import com.example.roombooking.model.Room;
import com.example.roombooking.repository.BookingRepository;
import com.example.roombooking.repository.RoomRepository;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class BookingController {
    private final RoomRepository roomRepository;
    private final BookingRepository bookingRepository;

    public BookingController(RoomRepository roomRepository,
                             BookingRepository bookingRepository) {
        this.roomRepository = roomRepository;
        this.bookingRepository = bookingRepository;
    }

    @PostMapping("/bookings")
    public ResponseEntity<?> createBooking(@RequestBody BookingRequest request) {
        if (request.getGuestName() == null || request.getGuestName().isBlank()
                || request.getRoomNumber() == null || request.getRoomNumber().isBlank()
                || request.getRoomType() == null || request.getRoomType().isBlank()
                || request.getCheckIn() == null || request.getCheckOut() == null) {
            return ResponseEntity.badRequest().body("Please provide all required fields.");
        }
        if (request.getPrice() < 0) {
            return ResponseEntity.badRequest().body("Price cannot be negative.");
        }

        Room room = new Room();
        room.setRoomNumber(request.getRoomNumber());
        room.setRoomType(request.getRoomType());
        room.setPrice(request.getPrice());
        room.setStatus("BOOKED");
        Room savedRoom = roomRepository.save(room);

        Booking booking = new Booking();
        booking.setGuestName(request.getGuestName());
        booking.setEmail(request.getEmail());
        booking.setRoomId(savedRoom.getId());
        booking.setRoomNumber(savedRoom.getRoomNumber());
        booking.setCheckIn(request.getCheckIn());
        booking.setCheckOut(request.getCheckOut());
        Booking savedBooking = bookingRepository.save(booking);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new BookingResponse(savedRoom, savedBooking));
    }

    @GetMapping("/rooms")
    public List<Room> getRooms() {
        return roomRepository.findAll();
    }

    @GetMapping("/bookings")
    public List<Booking> getBookings() {
        return bookingRepository.findAll();
    }
}
