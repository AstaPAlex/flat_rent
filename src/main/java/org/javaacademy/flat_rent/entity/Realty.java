package org.javaacademy.flat_rent.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Table(name = "realties")
@Builder
public class Realty {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @NotBlank
    private String region;

    @Column
    @NotBlank
    private String city;

    @Column
    @NotBlank
    private String house;

    @Column(name = "realty_type")
    @Enumerated(EnumType.STRING)
    private RealtyType realtyType;

    @OneToMany(mappedBy = "realty")
    private Announcement announcement;

}
