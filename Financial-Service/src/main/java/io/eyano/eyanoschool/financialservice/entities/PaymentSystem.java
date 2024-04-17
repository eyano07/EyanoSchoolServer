package io.eyano.eyanoschool.financialservice.entities;


import io.eyano.eyanoschool.financialservice.enums.PaymentMethode;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter @Builder @ToString
public class PaymentSystem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentMethode paymentMethod;

    @Column(length = 50, nullable = false)
    private String institutionName;

    @Column(length = 50, nullable = false)
    private String designation;

    @Column(length = 50)
    private String accountNumberOrPhoneNumber;

    @Column(length = 50)
    private String transactionNumber;

    @Column(length = 50, nullable = false)
    private String clientName;

    @Column(length = 15, nullable = false)
    private String clientPhoneNumber;

    private boolean paid;
    private LocalDate paymentDate;
    //Attribute to allow removing an entity
    private boolean isRemove;
}
