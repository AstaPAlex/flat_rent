package org.javaacademy.flat_rent.controller;

import lombok.RequiredArgsConstructor;
import org.javaacademy.flat_rent.dto.booking.BookingDto;
import org.javaacademy.flat_rent.dto.booking.BookingDtoRq;
import org.javaacademy.flat_rent.dto.booking.DatesFreeFromBookingDtoRq;
import org.javaacademy.flat_rent.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Set;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;

@RequestMapping("/booking")
@RequiredArgsConstructor
@RestController
public class BookingController {
    private final BookingService bookingService;

    @GetMapping("/{id}")
    public BookingDto getBookingById(@PathVariable Long id) {
        return bookingService.getBookingById(id);
    }

    @GetMapping("/free")
    public Set<LocalDate> getDatesFreeFromBooking(
            @RequestBody DatesFreeFromBookingDtoRq datesFreeFromBookingDtoRq) {
            return bookingService.getDatesFreeInMonth(datesFreeFromBookingDtoRq);
    }

    @PostMapping
    public ResponseEntity<?> createBooking(@RequestBody BookingDtoRq bookingDtoRq) {
        return bookingService.createBooking(bookingDtoRq)
                ? ResponseEntity.status(CREATED).build()
                : ResponseEntity.status(BAD_REQUEST).build();
    }

}
