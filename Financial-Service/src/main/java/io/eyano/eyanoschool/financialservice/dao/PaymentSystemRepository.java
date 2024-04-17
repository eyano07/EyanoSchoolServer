package io.eyano.eyanoschool.financialservice.dao;

import io.eyano.eyanoschool.financialservice.entities.PaymentSystem;
import io.eyano.eyanoschool.financialservice.enums.PaymentMethode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PaymentSystemRepository extends JpaRepository<PaymentSystem, Long> {
    public List<PaymentSystem> findByDesignationContainsIgnoreCaseAndIsRemoveFalse(String designation);
    public List<PaymentSystem> findByInstitutionNameContainsIgnoreCaseAndIsRemoveFalse(String institutionName);
    public List<PaymentSystem> findByInstitutionNameContainsIgnoreCaseAndIsRemoveFalseAndPaymentDateBetween(String institutionName, LocalDate startDate, LocalDate endDate);
    public List<PaymentSystem> findByDesignationContainsIgnoreCaseAndIsRemoveTrue(String designation);
    public List<PaymentSystem> findByInstitutionNameContainsIgnoreCaseAndIsRemoveTrue(String institutionName);
    public List<PaymentSystem> findByInstitutionNameContainsIgnoreCaseAndIsRemoveTrueAndPaymentDate(String institutionName, LocalDate paymentDate);
    public Page<PaymentSystem> findByIsRemoveFalseAndPaymentMethodAndPaymentDate(PaymentMethode paymentMethod, LocalDate paymentDate, Pageable pageable);
    public Page<PaymentSystem> findByIsRemoveFalseAndPaymentMethodAndPaymentDateBetween(PaymentMethode paymentMethod, LocalDate startDate, LocalDate endDate, Pageable pageable);
    public Page<PaymentSystem> findByIsRemoveFalseAndInstitutionNameContainsIgnoreCaseAndPaymentDateBetween(String institutionName, LocalDate startDate, LocalDate endDate, Pageable pageable);


    Optional<PaymentSystem> findByIdAndIsRemoveTrue(Long id);

    Optional<PaymentSystem> findByIdAndIsRemoveFalse(Long id);

    List<PaymentSystem> findByIsRemoveFalse();
}
