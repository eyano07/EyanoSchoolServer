package io.eyano.eyanoschool.feesservice.entitiesExt;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Data @Builder @ToString
public class RateDollar {
    private Long id;
    private String observation;
    private LocalDate date;
    private boolean isCurrentRate;
    private double amount;

}
