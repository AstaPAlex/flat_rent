package org.javaacademy.flat_rent.dto.booking;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class DatesFreeFromBookingDtoRq {
    private Long idRealty;
    private Integer year;
    private Short month;
}
