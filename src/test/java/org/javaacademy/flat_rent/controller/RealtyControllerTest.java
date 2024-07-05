package org.javaacademy.flat_rent.controller;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import org.javaacademy.flat_rent.dto.realty.RealtyDto;
import org.javaacademy.flat_rent.dto.realty.RealtyDtoFilterRq;
import org.javaacademy.flat_rent.entity.RealtyType;
import org.javaacademy.flat_rent.repository.RealtyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class RealtyControllerTest {
    @Autowired
    private RealtyRepository realtyRepository;
    private final static String REGION_TEST = "Московская область";
    private final static String CITY_TEST = "Подольск";
    private final static String HOUSE_TEST = "50";
    private final static String URL_TEST = "/flat";

    @Test
    public void createRealtySuccess() {
        RealtyDto realtyDto = RealtyDto.builder()
                .realtyType(RealtyType.ROOM)
                .region(REGION_TEST)
                .city(CITY_TEST)
                .house(HOUSE_TEST)
                .build();
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(realtyDto)
                .log().all()
                .post(URL_TEST)
                .then()
                .log().all()
                .statusCode(201);
    }

    @Test
    public void getRealtySuccess() {
        RealtyDto realtyDto = RestAssured.given()
                .log().all()
                .get("/flat/1")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .body().as(RealtyDto.class);
        Assertions.assertEquals(REGION_TEST, realtyDto.getRegion());
        Assertions.assertEquals(CITY_TEST, realtyDto.getCity());
        Assertions.assertEquals(HOUSE_TEST, realtyDto.getHouse());
    }

    @Test
    public void getRealtiesByCityAndRealtyTypeSuccess() {
        RealtyDtoFilterRq dtoFilterRq = new RealtyDtoFilterRq("Подольск", RealtyType.ROOM);
        TypeRef<Set<RealtyDto>> typeRef = new TypeRef<>(){};
        Set<RealtyDto> realtyDtos = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(dtoFilterRq)
                .log().all()
                .get("/flat?startPosition=0&endPosition=3")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .body().as(typeRef);
        Assertions.assertEquals(3, realtyDtos.size());
    }

}