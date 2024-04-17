package io.eyano.eyanoschool.financialservice.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Data @Builder @ToString @NoArgsConstructor @AllArgsConstructor
public class CurrencyDto {
    private Long id;

    private String designation;

    private String symbol;

    @JsonIgnore
    private boolean remove;

}
