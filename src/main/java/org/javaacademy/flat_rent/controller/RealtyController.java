package org.javaacademy.flat_rent.controller;

import lombok.RequiredArgsConstructor;
import org.javaacademy.flat_rent.dto.realty.RealtyDto;
import org.javaacademy.flat_rent.dto.realty.RealtyDtoFilterRq;
import org.javaacademy.flat_rent.service.RealtyService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/flat")
public class RealtyController {
    private final RealtyService realtyService;

    @PostMapping
    @ResponseStatus(CREATED)
    public void createRealty(@RequestBody RealtyDto realtyDto) {
        realtyService.createRealty(realtyDto);
    }

    @GetMapping
    public Set<RealtyDto> getRealtiesByCityAndRealtyType(@RequestParam Integer startPosition,
                                                         @RequestParam Integer endPosition,
                                                         @RequestBody RealtyDtoFilterRq realtyDtoFilterRq) {
        return realtyService.getPagerealtiesByCityAndRealtyType(startPosition, endPosition, realtyDtoFilterRq);
    }

    @GetMapping("/{id}")
    public RealtyDto getRealtyById(@PathVariable Long id) {
        return realtyService.getRealtyDtoById(id);
    }
}
