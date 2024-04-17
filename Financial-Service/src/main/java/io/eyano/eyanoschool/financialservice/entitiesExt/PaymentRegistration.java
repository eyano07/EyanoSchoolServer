package io.eyano.eyanoschool.financialservice.entitiesExt;


import io.eyano.eyanoschool.financialservice.dtos.PaymentSystemDto;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
@Data @Builder @ToString
public class PaymentRegistration {
    private Long id;

    private LocalDate date;

    private double amount;

    private PaymentSystemDto paymentSystem;


    //From the User module
    private String nameFirstnameUser;
    private Long idUser;

    //From the Registration module
    private String nameFirstnameCandidate;
    private Long idCandidate;

    //From the fees module
    private Fees fees;

}
