package com.example.roombooking.dto;

import com.example.roombooking.model.Booking;
import com.example.roombooking.model.Room;

public class BookingResponse {
    private Room room;
    private Booking booking;

    public BookingResponse(Room room, Booking booking) {
        this.room = room;
        this.booking = booking;
    }

    public Room getRoom() { return room; }
    public Booking getBooking() { return booking; }
}
