package io.eyano.eyanoschool.feesservice.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.eyano.eyanoschool.feesservice.entitiesExt.PaymentSystem;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
@Data @NoArgsConstructor @ToString
public class PaymentRegistrationDto  {
    private Long id;
    private LocalDate date;
    private double amount;

    private PaymentSystem paymentSystem;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long idPaymentSystem;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long idUser;
    private String nameFirstnameUser;

    private String nameFirstnameCandidate;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long idCandidate;

    private FeesDto fees;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean remove;
}
