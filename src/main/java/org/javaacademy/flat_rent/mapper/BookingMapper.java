package org.javaacademy.flat_rent.mapper;

import org.javaacademy.flat_rent.dto.booking.BookingDto;
import org.javaacademy.flat_rent.entity.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {AnnouncementMapper.class})
public interface BookingMapper {

    Booking convertToEntity(BookingDto bookingDto);

    @Mapping(target = "announcementDtoRs", source = "announcement")
    BookingDto convertToDto(Booking booking);
}
