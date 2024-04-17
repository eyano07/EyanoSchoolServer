package io.eyano.eyanoschool.financialservice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter @Builder @ToString
public class RateDollar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100, nullable = false)
    private String observation;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private boolean isCurrentRate;

    @Column(nullable = false)
    private double amount;

    @ManyToOne
    private Currency currency;

    //Attribute to allow removing an entity
    private boolean remove;

}
