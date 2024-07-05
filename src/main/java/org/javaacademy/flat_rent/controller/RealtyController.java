package org.javaacademy.flat_rent.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Flat Controller", description = "Методы управления квартирами.")
public class RealtyController {
    private final RealtyService realtyService;

    @PostMapping
    @ResponseStatus(CREATED)
    @Operation(summary = "Метод создания квартиры.")
    public void createRealty(@RequestBody RealtyDto realtyDto) {
        realtyService.createRealty(realtyDto);
    }

    @GetMapping
    @Operation(summary = "Метод получения скиска квартир по: городу и типу.")
    public Set<RealtyDto> getRealtiesByCityAndRealtyType(@RequestParam Integer startPosition,
                                                         @RequestParam Integer endPosition,
                                                         @RequestBody RealtyDtoFilterRq realtyDtoFilterRq) {
        return realtyService.getPagerealtiesByCityAndRealtyType(startPosition, endPosition, realtyDtoFilterRq);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Метод получения квартиры по id.")
    public RealtyDto getRealtyById(@PathVariable Long id) {
        return realtyService.getRealtyDtoById(id);
    }
}
