package io.eyano.eyanoschool.feesservice.entitiesExt;

import io.eyano.eyanoschool.feesservice.enums.PaymentMethode;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
@Data @Builder @ToString
public class PaymentSystem {
    private Long id;
    private PaymentMethode paymentMethod;
    private String institutionName;
    private String designation;
    private String accountNumberOrPhoneNumber;
    private String transactionNumber;
    private String clientName;
    private String clientPhoneNumber;
}
