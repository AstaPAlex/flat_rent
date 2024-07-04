package org.javaacademy.flat_rent.dto.booking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDtoRq {
    private LocalDate dateStart;
    private Integer countDayBooking;
    private String client;
    private String email;
    private Long announcement_id;
}
