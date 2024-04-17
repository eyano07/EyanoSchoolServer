package io.eyano.eyanoschool.feesservice.entities;

import jakarta.persistence.*;
import lombok.*;


@Entity
@AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder @ToString
public class TypeFees {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(length = 30, nullable = false)
    private String designation;
    private boolean remove;

}
