package io.eyano.eyanoschool.financialservice.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter @Builder @ToString
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String designation;

    @Column(length = 5, nullable = false)
    private String symbol;

    //Attribute to allow removing an entity
    private boolean remove;
}
