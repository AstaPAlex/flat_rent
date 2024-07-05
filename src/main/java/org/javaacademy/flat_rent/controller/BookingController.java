package org.javaacademy.flat_rent.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Booking Controller", description = "Методы управления бронированием.")
public class BookingController {
    private final BookingService bookingService;

    @GetMapping("/{id}")
    @Operation(summary = "Метод получения свободных дат по конкретной квартире.")
    public BookingDto getBookingById(@PathVariable Long id) {
        return bookingService.getBookingById(id);
    }

    @GetMapping("/free")
    @Operation(summary = "Метод получения свободных дат по квартире на конкретный год и месяц.")
    public Set<LocalDate> getDatesFreeFromBooking(
            @RequestBody DatesFreeFromBookingDtoRq datesFreeFromBookingDtoRq) {
            return bookingService.getDatesFreeInMonth(datesFreeFromBookingDtoRq);
    }

    @PostMapping
    @Operation(summary = "Метод создания бронирования.")
    public ResponseEntity<?> createBooking(@RequestBody BookingDtoRq bookingDtoRq) {
        return bookingService.createBooking(bookingDtoRq)
                ? ResponseEntity.status(CREATED).build()
                : ResponseEntity.status(BAD_REQUEST).build();
    }

}
