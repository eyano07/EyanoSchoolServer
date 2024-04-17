package io.eyano.eyanoschool.financialservice.entitiesExt;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;


@Data @Builder @ToString
public class TypeFees {
    private Long id;

    private String designation;
}
