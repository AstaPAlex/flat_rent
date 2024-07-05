package org.javaacademy.flat_rent.repository;

import org.javaacademy.flat_rent.entity.Realty;
import org.javaacademy.flat_rent.entity.RealtyType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

public interface RealtyRepository extends JpaRepository<Realty, Long>, PagingAndSortingRepository<Realty, Long> {

    @Transactional(readOnly = true)
    Page<Realty> findAllByCityAndRealtyType(String city, RealtyType realtyType, Pageable pageable);
}
