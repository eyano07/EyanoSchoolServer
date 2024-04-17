package io.eyano.eyanoschool.feesservice.entities;

import io.eyano.eyanoschool.feesservice.entitiesExt.SchoolYear;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder @ToString
public class FeesAllocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150, nullable = false)
    private String designation;

    @Column(nullable = false)
    private double percentage;

    //From the utility module
    @Transient
    private SchoolYear schoolYear;
    private Long idSchoolYear;

    @ManyToOne
    private TypeFees typeFees;

    //Attribute to allow removing an entity
    private boolean remove;
}
