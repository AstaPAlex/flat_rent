package org.javaacademy.flat_rent.repository;

import org.javaacademy.flat_rent.entity.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
}
