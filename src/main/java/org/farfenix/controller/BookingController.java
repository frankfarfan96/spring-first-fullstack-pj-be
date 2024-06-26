package org.farfenix.controller;


import lombok.RequiredArgsConstructor;
import org.farfenix.exception.InvalidBookingRequestException;
import org.farfenix.exception.ResourceNotFoundException;
import org.farfenix.model.BookedRoom;
import org.farfenix.model.Room;
import org.farfenix.response.BookingResponse;
import org.farfenix.response.RoomResponse;
import org.farfenix.service.IBookingService;
import org.farfenix.service.IRoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/bookings")
public class BookingController {
    private final IBookingService bookingService;
    private final IRoomService roomService;

    @GetMapping("/all-bookings")
    public ResponseEntity<List<BookingResponse>> getAllBookings() {
        List<BookedRoom> bookings = bookingService.getAllBookings();
        List<BookingResponse> bookingResponses = new ArrayList<>();

        for(BookedRoom booking : bookings) {
           BookingResponse bookingResponse = getBookingResponse(booking);
           bookingResponses.add(bookingResponse);
        }

        return ResponseEntity.ok(bookingResponses);
    }

    @GetMapping("/confirmation/{confirmationCode}")
    public ResponseEntity<?> getBookingByConfirmationCode(@PathVariable String confirmationCode) {
        try {
            BookedRoom booking = bookingService.findByBookingConfirmationCode(confirmationCode);
            BookingResponse bookingResponse = getBookingResponse(booking);

            return ResponseEntity.ok(bookingResponse);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PostMapping("/room/{roomId}/booking")
    public ResponseEntity<?> saveBooking(@PathVariable Long roomId, @RequestBody BookedRoom bookingRequest) {
        try {
            String confirmationCode = bookingService.saveBooking(roomId, bookingRequest);

            return ResponseEntity.ok("Room boked successfully!! You booking confirmation code is :" + confirmationCode);
        } catch (InvalidBookingRequestException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/booking/{bookingId}/delete")
    public void cancelBooking(@PathVariable Long bookingId) {
        bookingService.cancelBooking(bookingId);
    }

    private BookingResponse getBookingResponse(BookedRoom booking) {
        Room theRoom = roomService.getRoomById(booking.getRoom().getId()).get();
        RoomResponse room = new RoomResponse(
                theRoom.getId(),
                theRoom.getRoomType(),
                theRoom.getRoomPrice()
        );

        return new BookingResponse(
                booking.getBookingId(), booking.getCheckInDate(),
                booking.getCheckOutDate(), booking.getGuestFullName(),
                booking.getGuestEmail(), booking.getNumOfAdults(),
                booking.getNumOfChildren(), booking.getTotalNumGuest(),
                booking.getBookingConfirmationCode(), room
        );
    }
}
