package org.javaacademy.flat_rent.controller;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import org.javaacademy.flat_rent.dto.announcement.AnnouncementDto;
import org.javaacademy.flat_rent.dto.announcement.AnnouncementDtoRs;
import org.javaacademy.flat_rent.dto.announcement.AnnouncementFilterDtoRq;
import org.javaacademy.flat_rent.entity.RealtyType;
import org.javaacademy.flat_rent.repository.AnnouncementRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Set;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class AnnouncementControllerTest {
    @Autowired
    private AnnouncementRepository announcementRepository;
    private final static String REGION_TEST = "Московская область";
    private final static String CITY_TEST = "Подольск";
    private final static String HOUSE_TEST = "50";

    @Test
    public void createAnnouncementSuccess() {
        AnnouncementDto announcementDto = AnnouncementDto.builder()
                .price(BigDecimal.valueOf(10_000))
                .isActive(true)
                .realtyId(1L)
                .build();
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(announcementDto)
                .log().all()
                .post("/advert")
                .then()
                .log().all()
                .statusCode(201);
    }

    @Test
    public void createAnnouncementFail() {
        AnnouncementDto announcementDto = AnnouncementDto.builder()
                .price(BigDecimal.valueOf(10_000))
                .isActive(true)
                .realtyId(15000L)
                .build();
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(announcementDto)
                .log().all()
                .post("/advert")
                .then()
                .log().all()
                .statusCode(406);
    }

    @Test
    public void getAnnouncementByCityAndRoomsAndPriceRange() {
        AnnouncementFilterDtoRq filterDtoRq = AnnouncementFilterDtoRq.builder()
                .city(CITY_TEST)
                .startPrice(BigDecimal.ZERO)
                .endPrice(BigDecimal.valueOf(15_000))
                .realtyType(RealtyType.ROOM)
                .build();
        TypeRef<Set<AnnouncementDtoRs>> typeRef = new TypeRef<>(){};
        Set<AnnouncementDtoRs> announcementDtoRs = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(filterDtoRq)
                .log().all()
                .get("/advert?startPosition=0&endPosition=1")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .body().as(typeRef);
        Assertions.assertEquals(1, announcementDtoRs.size());
    }

}