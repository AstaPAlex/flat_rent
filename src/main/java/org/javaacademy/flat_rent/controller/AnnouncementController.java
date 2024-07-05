package org.javaacademy.flat_rent.controller;
import lombok.RequiredArgsConstructor;
import org.javaacademy.flat_rent.dto.announcement.AnnouncementDto;
import org.javaacademy.flat_rent.dto.announcement.AnnouncementDtoRs;
import org.javaacademy.flat_rent.dto.announcement.AnnouncementFilterDtoRq;
import org.javaacademy.flat_rent.service.AnnouncementService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/advert")
public class AnnouncementController {
    private final AnnouncementService announcementService;

    @PostMapping
    @ResponseStatus(CREATED)
    public void createAnnouncement(@RequestBody AnnouncementDto announcementDto) {
        announcementService.createAnnouncement(announcementDto);
    }

    @GetMapping
    public Set<AnnouncementDtoRs> getAnnouncements(@RequestParam Integer startPosition,
                                                   @RequestParam Integer endPosition,
                                                   @RequestBody AnnouncementFilterDtoRq announcementFilterDtoRq) {
        return announcementService.getAnnouncementByCityAndRoomsAndPriceRange(announcementFilterDtoRq,
                startPosition, endPosition);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(ACCEPTED)
    public void changeActive(@PathVariable Long id) {
        announcementService.changeActive(id);
    }

}