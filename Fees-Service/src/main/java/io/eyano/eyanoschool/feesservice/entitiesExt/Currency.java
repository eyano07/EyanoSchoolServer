package io.eyano.eyanoschool.feesservice.entitiesExt;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
@Data @Builder @ToString
public class Currency {
    private Long id;
    private String designation;
    private String symbol;
    private RateDollar rate;
}
