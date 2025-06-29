package com.example.antitouch.controller;

import com.example.antitouch.dto.BookingRequest;
import com.example.antitouch.dto.BookingResponse;
import com.example.antitouch.dto.BookingTimeDto;
import com.example.antitouch.entity.Booking;
import com.example.antitouch.repository.BookingRepository;
import com.example.antitouch.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingRepository bookingRepository;
    private final BookingService bookingService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookingResponse createBooking(@RequestBody BookingRequest request) {
        return bookingService.createBooking(request);
    }

    @DeleteMapping("/{id}/cancel")
    public ResponseEntity<Void> cancelBooking(@PathVariable String id) {
        if (bookingRepository.existsById(id)) {
            bookingRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/bookings-by-day")
    public ResponseEntity<List<BookingTimeDto>> getBookingsByPcAndDate(
            @PathVariable("id") String pcId,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        List<BookingTimeDto> bookings = bookingService.getBookingsByPcAndDate(pcId, date);
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/user")
    public List<Booking> getBookingsByEmail(@RequestParam String email) {
        return bookingService.getBookingsByEmail(email);
    }
}
