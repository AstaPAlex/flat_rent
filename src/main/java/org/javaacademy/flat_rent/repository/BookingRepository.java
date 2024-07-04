package org.javaacademy.flat_rent.repository;

import org.javaacademy.flat_rent.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Transactional(readOnly = true)
    Set<Booking> findAllByAnnouncement_Realty_Id(Long id);

    @Transactional(readOnly = true)
    Set<Booking> findAllByAnnouncement_Id(Long id);

}
