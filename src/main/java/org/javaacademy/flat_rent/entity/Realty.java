package org.javaacademy.flat_rent.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@Table(name = "realties")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Realty {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "realties_id_seq")
    @SequenceGenerator(name = "realties_id_seq", allocationSize = 1)
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
    private Set<Announcement> announcement;

}