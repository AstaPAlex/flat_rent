package org.javaacademy.flat_rent.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Builder
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "date_start")
    @NotBlank
    private LocalDate dateStart;

    @Column(name = "count_day_booking")
    @Min(1)
    private Integer countDayBooking;

    @Column
    @NotBlank
    private String client;

    @Column
    @Email
    private String email;

    @ManyToOne
    @JoinColumn(name = "id_announcement")
    @NotBlank
    private Announcement announcement;

    @Column(name = "price_booking")
    @DecimalMin(value = "0.01")
    private BigDecimal priceBooking;

}
