package org.javaacademy.flat_rent.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "announcements")
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "announcements_id_seq")
    @SequenceGenerator(name = "announcements_id_seq", allocationSize = 1)
    private Long id;

    @Column
    @DecimalMin(value = "0.01")
    private BigDecimal price;

    @Column(name = "is_active")
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "realty_id")
    private Realty realty;

    @OneToMany(mappedBy = "announcement")
    private Set<Booking> bookingList;

}
