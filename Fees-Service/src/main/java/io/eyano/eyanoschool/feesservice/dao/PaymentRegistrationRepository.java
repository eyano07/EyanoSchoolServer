package io.eyano.eyanoschool.feesservice.dao;

import io.eyano.eyanoschool.feesservice.entities.PaymentRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PaymentRegistrationRepository extends JpaRepository<PaymentRegistration, Long> {
    public Optional<PaymentRegistration> findByRemoveIsFalseAndId(Long id);
    public List<PaymentRegistration> findByRemoveIsFalseAndIdCandidate(Long idCandidate);
    public List<PaymentRegistration> findByRemoveIsFalseAndIdCandidateAndFeesIdSchoolYear(Long idCandidate, Long idSchoolYear);
    public List<PaymentRegistration> findByRemoveIsTrueAndIdCandidateAndFeesIdSchoolYear(Long idCandidate, Long idSchoolYear);
    public List<PaymentRegistration> findByRemoveIsFalseAndIdUserAndFeesIdSchoolYear(Long idUser, Long idSchoolYear);
    public List<PaymentRegistration> findByRemoveIsFalseAndDate(LocalDate date);
    public List<PaymentRegistration> findByRemoveIsFalseAndIdPaymentSystemAndFeesIdSchoolYear(Long idPaymentSystem, Long idSchoolYear);
}
