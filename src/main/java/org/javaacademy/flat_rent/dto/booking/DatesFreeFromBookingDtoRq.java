package org.javaacademy.flat_rent.dto.booking;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DatesFreeFromBookingDtoRq {
    private Long idRealty;
    private Integer year;
    private Short month;
}
