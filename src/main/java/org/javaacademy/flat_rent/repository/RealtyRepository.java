package org.javaacademy.flat_rent.repository;

import org.javaacademy.flat_rent.entity.Realty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RealtyRepository extends JpaRepository<Realty, Long> {
}
