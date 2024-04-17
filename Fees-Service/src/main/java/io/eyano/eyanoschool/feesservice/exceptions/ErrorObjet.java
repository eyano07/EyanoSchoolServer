package io.eyano.eyanoschool.feesservice.exceptions;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
@Data @Builder
public class ErrorObjet {
    private Integer statusCode;
    private Integer errorCode;
    private String message;
    private LocalDate date;

}
