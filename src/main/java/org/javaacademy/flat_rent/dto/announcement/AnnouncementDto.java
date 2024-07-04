package org.javaacademy.flat_rent.dto.announcement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.javaacademy.flat_rent.entity.Realty;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnnouncementDto {
    private Long id;
    private BigDecimal price;
    private Boolean isActive;
    private Long realtyId;
    private Realty realty;
}
