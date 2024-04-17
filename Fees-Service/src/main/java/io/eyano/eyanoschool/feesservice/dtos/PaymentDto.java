package io.eyano.eyanoschool.feesservice.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.eyano.eyanoschool.feesservice.entitiesExt.Currency;
import io.eyano.eyanoschool.feesservice.entitiesExt.PaymentSystem;
import io.eyano.eyanoschool.feesservice.entitiesExt.Pupil;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data @NoArgsConstructor @ToString
public class PaymentDto  {
    private Long id;
    private double amount;
    private LocalDate date;

    private Pupil pupil;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long idPupil;

    private String nameFirstnameUser;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long idUser;

    private PaymentSystem paymentSystem;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long idPaymentSystem;

    private Currency currency;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long idCurrency;

    private FeesDto fees;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean remove;

}
