package org.javaacademy.flat_rent.dto.realty;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.javaacademy.flat_rent.entity.RealtyType;

@Data
@Builder
public class RealtyDto {
    private Long id;
    private String region;
    private String city;
    private String house;
    @JsonProperty(value = "realtytype")
    private RealtyType realtyType;

}
