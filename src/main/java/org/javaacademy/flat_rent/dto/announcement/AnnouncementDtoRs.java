package org.javaacademy.flat_rent.dto.announcement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.javaacademy.flat_rent.dto.realty.RealtyDto;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnnouncementDtoRs {
    private BigDecimal price;
    private Boolean isActive;
    private RealtyDto realtydto;
}
