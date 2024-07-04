package org.javaacademy.flat_rent.mapper;

import org.javaacademy.flat_rent.dto.announcement.AnnouncementDto;
import org.javaacademy.flat_rent.dto.announcement.AnnouncementDtoRs;
import org.javaacademy.flat_rent.entity.Announcement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {RealtyMapper.class})
public interface AnnouncementMapper {

    Announcement convertToEntity(AnnouncementDto dto);

    @Mapping(target = "realtyId", ignore = true)
    AnnouncementDto convertToDto(Announcement entity);

    @Mapping(target = "realtydto", source = "realty")
    AnnouncementDtoRs convertToDtoRs(Announcement entity);
}
