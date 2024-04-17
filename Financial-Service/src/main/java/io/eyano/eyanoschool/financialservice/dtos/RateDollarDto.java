package io.eyano.eyanoschool.financialservice.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDate;

@Data @Builder @ToString @NoArgsConstructor @AllArgsConstructor
public class RateDollarDto {
    private Long id;

    private String observation;

    private LocalDate date;

    private boolean isCurrentRate;

    private CurrencyDto currency;

    private double amount;

    @JsonIgnore
    private boolean remove;

}
