package org.javaacademy.flat_rent.dto.announcement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.javaacademy.flat_rent.entity.RealtyType;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnnouncementFilterDtoRq {
    private String city;
    private RealtyType realtyType;
    private BigDecimal startPrice;
    private BigDecimal endPrice;
}
