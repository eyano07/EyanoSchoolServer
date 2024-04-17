package io.eyano.eyanoschool.feesservice.entitiesExt;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data @Builder @ToString
public class ClassFees {
    private Long id;
    private String designation;
    private int ability;
    private String cycle;
    private String local;
    private String nameFirstnameFullProfessor;
    private long idFullProfessor;
}
