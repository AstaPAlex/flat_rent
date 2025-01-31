package org.javaacademy.flat_rent.dto.booking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.javaacademy.flat_rent.dto.announcement.AnnouncementDtoRs;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDto {
    private Long id;
    private LocalDate dateStart;
    private Integer countDayBooking;
    private String client;
    private String email;
    private AnnouncementDtoRs announcementDtoRs;
    private BigDecimal priceBooking;

}
