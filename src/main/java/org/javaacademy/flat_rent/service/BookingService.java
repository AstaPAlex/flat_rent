package org.javaacademy.flat_rent.service;

import lombok.RequiredArgsConstructor;
import org.javaacademy.flat_rent.dto.booking.BookingDto;
import org.javaacademy.flat_rent.dto.booking.BookingDtoRq;
import org.javaacademy.flat_rent.dto.booking.DatesFreeFromBookingDtoRq;
import org.javaacademy.flat_rent.entity.Announcement;
import org.javaacademy.flat_rent.entity.Booking;
import org.javaacademy.flat_rent.exception.NotFoundAnnouncementException;
import org.javaacademy.flat_rent.mapper.BookingMapper;
import org.javaacademy.flat_rent.repository.BookingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;
    private final AnnouncementService announcementService;

    public BookingDto getBookingById(Long id) {
        Booking booking = bookingRepository.findById(id).orElseThrow();
        return bookingMapper.convertToDto(booking);
    }

    @Transactional
    public Set<LocalDate> getDatesFreeInMonth(DatesFreeFromBookingDtoRq datesFreeDto) {
        try {
            Set<Booking> bookings = bookingRepository.findAllByAnnouncement_Realty_Id(datesFreeDto.getIdRealty());
            int countDaysOfYearMonth = YearMonth.of(datesFreeDto.getYear(), Integer.valueOf(datesFreeDto.getMonth()))
                    .lengthOfMonth();

            Set<LocalDate> desiredDates = getDates(
                    LocalDate.of(datesFreeDto.getYear(), datesFreeDto.getMonth(), 1),
                    countDaysOfYearMonth);
            Set<LocalDate> bookingDates = getDatesFromBookings(bookings);

            return  getFreeDates(desiredDates, bookingDates);
        } catch (Exception e) {
            throw new NotFoundAnnouncementException();
        }

    }

    private Set<LocalDate> getDates(LocalDate dateStart, Integer countDay) {
        return IntStream.range(0, countDay)
                .mapToObj(dateStart::plusDays)
                .collect(Collectors.toSet());
    }

    private Set<LocalDate> getDatesFromBookings(Set<Booking> bookings) {
        return bookings.stream()
                .flatMap(booking -> getDates(booking.getDateStart(), booking.getCountDayBooking()).stream())
                .collect(Collectors.toSet());
    }

    private Set<LocalDate> getFreeDates(Set<LocalDate> desiredDates, Set<LocalDate> bookingDates) {
        Set<LocalDate> datesFree = new HashSet<>(desiredDates);
        datesFree.removeAll(bookingDates);
        return  datesFree;
    }

    @Transactional
    public boolean createBooking(BookingDtoRq bookingDto) {
        Announcement announcementById;
        try {
            announcementById = announcementService.getAnnouncementById(bookingDto.getAnnouncementId());
        } catch (Exception e) {
            throw new NotFoundAnnouncementException();
        }
        Set<Booking> bookings = bookingRepository.findAllByAnnouncement_Id(announcementById.getId());
        if (isPossibilityBooking(bookingDto, bookings)) {
            Booking booking = Booking.builder()
                    .announcement(announcementById)
                    .dateStart(bookingDto.getDateStart())
                    .countDayBooking(bookingDto.getCountDayBooking())
                    .client(bookingDto.getClient())
                    .email(bookingDto.getEmail())
                    .priceBooking(announcementById.getPrice()
                            .multiply(BigDecimal.valueOf(bookingDto.getCountDayBooking())))
                    .build();
            bookingRepository.save(booking);
            return true;
        }
        return false;
    }

    private boolean isPossibilityBooking(BookingDtoRq bookingDto, Set<Booking> bookings) {
        Set<LocalDate> datesFromBookings = getDatesFromBookings(bookings);
        Set<LocalDate> desiredDates = getDates(bookingDto.getDateStart(), bookingDto.getCountDayBooking());
        Set<LocalDate> freeDates = getFreeDates(desiredDates, datesFromBookings);
        return freeDates.size() == desiredDates.size();
    }
}
