package org.javaacademy.flat_rent.controller;

import org.javaacademy.flat_rent.exception.NotFoundAnnouncementException;
import org.javaacademy.flat_rent.exception.NotFoundRealtyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(NotFoundRealtyException.class)
    public ResponseEntity<?> handleRealtyNotFound(Exception e) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                .body("Не удалось найти квартиру!");
    }

    @ExceptionHandler(NotFoundAnnouncementException.class)
    public ResponseEntity<?> handleAnnouncementNotFound(Exception e) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                .body("Не удалось найти объявление!");
    }
}
