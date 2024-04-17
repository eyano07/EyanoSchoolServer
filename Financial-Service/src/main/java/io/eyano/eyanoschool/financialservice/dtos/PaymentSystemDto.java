package io.eyano.eyanoschool.financialservice.dtos;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.eyano.eyanoschool.financialservice.enums.PaymentMethode;
import lombok.*;

import java.time.LocalDate;

@Data @Builder @ToString @NoArgsConstructor @AllArgsConstructor
public class PaymentSystemDto {
    private Long id;

    private PaymentMethode paymentMethod;

    private String institutionName;

    private String designation;

    private String accountNumberOrPhoneNumber;

    private String transactionNumber;

    private String clientName;

    private String clientPhoneNumber;

    private boolean paid;

    private LocalDate paymentDate;

    @JsonIgnore
    private boolean isRemove;
}
