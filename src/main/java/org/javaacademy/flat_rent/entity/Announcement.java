package org.javaacademy.flat_rent.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Data
@Builder
@Table(name = "announcements")
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    @DecimalMin(value = "0.01")
    private BigDecimal price;

    @Column(name = "is_active")
    private Boolean isActive;

    @OneToMany
    @JoinColumn(name = "realty_id")
    private Realty realty;

    @OneToMany(mappedBy = "announcement")
    private Set<Booking> bookingList;

}
