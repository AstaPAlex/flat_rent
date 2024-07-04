package org.javaacademy.flat_rent.service;

import lombok.RequiredArgsConstructor;
import org.javaacademy.flat_rent.dto.announcement.AnnouncementDto;
import org.javaacademy.flat_rent.dto.announcement.AnnouncementDtoRs;
import org.javaacademy.flat_rent.dto.announcement.AnnouncementFilterDtoRq;
import org.javaacademy.flat_rent.entity.Announcement;
import org.javaacademy.flat_rent.entity.Realty;
import org.javaacademy.flat_rent.mapper.AnnouncementMapper;
import org.javaacademy.flat_rent.repository.AnnouncementPageRepository;
import org.javaacademy.flat_rent.repository.AnnouncementRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnnouncementService {
    private final AnnouncementRepository announcementRepository;
    private final RealtyService realtyService;
    private final AnnouncementPageRepository announcementPageRepository;
    private final AnnouncementMapper announcementMapper;

    public void createAnnouncement(AnnouncementDto announcementDto) {
        Realty realtyById = realtyService.getRealtyById(announcementDto.getRealtyId());
        Announcement announcement = announcementMapper.convertToEntity(announcementDto);
        announcement.setRealty(realtyById);
        announcementRepository.save(announcement);
    }

    public Set<AnnouncementDtoRs> getAnnouncementByCityAndRoomsAndPriceRange(AnnouncementFilterDtoRq announcementFilterDtoRq,
                                                                             Integer startPosition,
                                                                             Integer endPosition) {
        Pageable pageRequest = PageRequest.of(startPosition, endPosition);
        return announcementPageRepository.findAllByRealty_CityAndRealty_RealtyTypeAndPriceBetween(
                        announcementFilterDtoRq.getCity(),
                        announcementFilterDtoRq.getRealtyType(),
                        announcementFilterDtoRq.getStartPrice(),
                        announcementFilterDtoRq.getEndPrice(),
                        pageRequest
                )
                .stream()
                .map(announcementMapper::convertToDtoRs)
                .collect(Collectors.toSet());
    }

    @Transactional
    public void changeActive(Long id) {
        Announcement announcement = announcementRepository.findById(id).orElseThrow();
        announcement.setIsActive(false);
        announcementRepository.save(announcement);
    }

    public Announcement getAnnouncementById(Long id) {
        return announcementRepository.findById(id).orElseThrow();
    }

}
