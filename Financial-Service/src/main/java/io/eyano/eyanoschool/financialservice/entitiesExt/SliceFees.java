package io.eyano.eyanoschool.financialservice.entitiesExt;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Data @Builder @ToString
public class SliceFees {
    private Long id;

    private String designation;

    private double percentage;

    private LocalDate date;

    private LocalDate datePayment;

    private LocalDate endDatePayment;

}
