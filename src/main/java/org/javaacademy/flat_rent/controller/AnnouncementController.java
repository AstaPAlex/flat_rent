package org.javaacademy.flat_rent.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Advert Controller", description = "Методы управления объявлениями.")
public class AnnouncementController {
    private final AnnouncementService announcementService;

    @PostMapping
    @ResponseStatus(CREATED)
    @Operation(summary = "Метод создания объявления.")
    public void createAnnouncement(@RequestBody AnnouncementDto announcementDto) {
        announcementService.createAnnouncement(announcementDto);
    }

    @Operation(summary = "Метод получения объявлений по: городу, типу и диапазону цены.")
    @GetMapping
    public Set<AnnouncementDtoRs> getAnnouncements(@RequestParam Integer startPosition,
                                                   @RequestParam Integer endPosition,
                                                   @RequestBody AnnouncementFilterDtoRq announcementFilterDtoRq) {
        return announcementService.getAnnouncementByCityAndRoomsAndPriceRange(announcementFilterDtoRq,
                startPosition, endPosition);
    }


    @PatchMapping("/{id}")
    @ResponseStatus(ACCEPTED)
    @Operation(summary = "Метод отправки объявления в архив.")
    public void changeActive(@PathVariable Long id) {
        announcementService.changeActive(id);
    }

}