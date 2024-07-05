package org.javaacademy.flat_rent.service;

import lombok.RequiredArgsConstructor;
import org.javaacademy.flat_rent.dto.realty.RealtyDto;
import org.javaacademy.flat_rent.dto.realty.RealtyDtoFilterRq;
import org.javaacademy.flat_rent.entity.Realty;
import org.javaacademy.flat_rent.mapper.RealtyMapper;
import org.javaacademy.flat_rent.repository.RealtyRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class RealtyService {
    private final RealtyRepository realtyRepository;
    private final RealtyMapper realtyMapper;

    public void createRealty(RealtyDto realtyDto) {
        Realty realty = realtyMapper.convertToEntity(realtyDto);
        realtyRepository.save(realty);
    }

    public Set<RealtyDto> getPagerealtiesByCityAndRealtyType(Integer startPosition,
                                                             Integer endPosition,
                                                             RealtyDtoFilterRq realtyDtoFilterRq) {
        Pageable pageRequest = PageRequest.of(startPosition, endPosition);
        return realtyRepository.findAllByCityAndRealtyType(
                        realtyDtoFilterRq.getCity(),
                        realtyDtoFilterRq.getRealtyType(),
                        pageRequest)
                .map(realtyMapper::convertToDto)
                .toSet();
    }

    public RealtyDto getRealtyDtoById(Long id) {
        Realty realty = realtyRepository.findById(id).orElseThrow();
        return realtyMapper.convertToDto(realty);
    }

    public Realty getRealtyById(Long id) {
        return realtyRepository.findById(id).orElseThrow();
    }

}
