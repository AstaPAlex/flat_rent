package org.javaacademy.flat_rent.controller;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import org.javaacademy.flat_rent.dto.booking.BookingDtoRq;
import org.javaacademy.flat_rent.dto.booking.DatesFreeFromBookingDtoRq;
import org.javaacademy.flat_rent.repository.BookingRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Set;
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class BookingControllerTest {
    @Autowired
    private BookingRepository bookingRepository;

    @Test
    public void createBookSuccess() {
            BookingDtoRq bookingDtoRq = BookingDtoRq.builder()
                    .email("test@mail.ru")
                    .countDayBooking(10)
                    .announcementId(1L)
                    .dateStart(LocalDate.of(2024, 7, 20))
                    .client("Иван Иванов")
                    .build();
            RestAssured.given()
                    .contentType(ContentType.JSON)
                    .body(bookingDtoRq)
                    .log().all()
                    .post("/booking")
                    .then()
                    .log().all()
                    .statusCode(201);
    }

    @Test
    public void createBookFail() {
        BookingDtoRq bookingDtoRq = BookingDtoRq.builder()
                .email("test@mail.ru")
                .countDayBooking(10)
                .announcementId(15L)
                .dateStart(LocalDate.of(2024, 9, 20))
                .client("Иван Иванов")
                .build();
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(bookingDtoRq)
                .log().all()
                .post("/booking")
                .then()
                .log().all()
                .statusCode(406);
    }

    @Test
    public void findFreeDate() {
        TypeRef<Set<LocalDate>> typeRef = new TypeRef<>(){};
        DatesFreeFromBookingDtoRq dtoRq = DatesFreeFromBookingDtoRq.builder()
                .year(2024)
                .month(Short.valueOf("7"))
                .idRealty(2L)
                .build();
        Set<LocalDate> localDates = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(dtoRq)
                .log().all()
                .get("/booking/free")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .body().as(typeRef);
        Assertions.assertEquals(31, localDates.size());
    }
  
}