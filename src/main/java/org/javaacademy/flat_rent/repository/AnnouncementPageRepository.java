package org.javaacademy.flat_rent.repository;

import org.javaacademy.flat_rent.entity.Announcement;
import org.javaacademy.flat_rent.entity.RealtyType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

public interface AnnouncementPageRepository extends PagingAndSortingRepository<Announcement, Long> {

    @Transactional(readOnly = true)
    Page<Announcement> findAllByRealty_CityAndRealty_RealtyTypeAndPriceBetween(
                                                                String city, RealtyType realtyType,
                                                                BigDecimal startPrice, BigDecimal endPrice,
                                                                Pageable pageable);
}
