package io.eyano.eyanoschool.financialservice.entitiesExt;


import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data @Builder @ToString
public class Fees {
    private Long id;

    private String designation;

    private double amount;

    //From the utility module
    private SchoolYear schoolYear;

    //From the class module
    private ClassFees classFees;

    //From the fees module
    private TypeFees typeFees;

    //From the fees module
    private SliceFees sliceFees;

}
