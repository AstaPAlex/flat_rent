package org.javaacademy.flat_rent.dto.realty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.javaacademy.flat_rent.entity.RealtyType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RealtyDtoFilterRq {
    private String city;
    private RealtyType realtyType;
}
