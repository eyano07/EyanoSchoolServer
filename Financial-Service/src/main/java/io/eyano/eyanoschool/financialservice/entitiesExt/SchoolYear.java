package io.eyano.eyanoschool.financialservice.entitiesExt;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
@Data @Builder @ToString
public class SchoolYear {
    private Long id;
    private String designation;
    private LocalDate startDate;
    private LocalDate endDAte;
    private boolean isCurrent;
}
