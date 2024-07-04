package org.javaacademy.flat_rent.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "booking_id_seq")
    @SequenceGenerator(name = "booking_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "date_start")
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

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_announcement")
    private Announcement announcement;

    @Column(name = "price_booking")
    @DecimalMin(value = "0.01")
    private BigDecimal priceBooking;

}
