package io.eyano.eyanoschool.financialservice.entitiesExt;


import io.eyano.eyanoschool.financialservice.dtos.CurrencyDto;
import io.eyano.eyanoschool.financialservice.dtos.PaymentSystemDto;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Data @Builder @ToString
public class Payment {
    private Long id;

    private double amount;

    private LocalDate date;

    //From the pupil module
    private Pupil pupil;

    //From the User module
    private String nameFirstnameUser;
    private Long idUser;

    private PaymentSystemDto paymentSystem;

    //From the Finance module
    private CurrencyDto currency;

    private Fees fees;

}
