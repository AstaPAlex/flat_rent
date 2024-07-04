package org.javaacademy.flat_rent.mapper;

import org.javaacademy.flat_rent.dto.realty.RealtyDto;
import org.javaacademy.flat_rent.entity.Realty;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RealtyMapper {

    Realty convertToEntity(RealtyDto realtyDto);

    RealtyDto convertToDto(Realty realty);
}
