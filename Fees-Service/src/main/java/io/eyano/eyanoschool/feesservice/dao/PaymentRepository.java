package io.eyano.eyanoschool.feesservice.dao;

import io.eyano.eyanoschool.feesservice.entities.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PaymentRepository  extends JpaRepository<Payment, Long> {
    List<Payment> findByDateAndRemoveIsFalse(LocalDate date);
    Page<Payment> findByDateAndRemoveIsFalse(LocalDate date, Pageable pageable);
    List<Payment> findByIdPupilAndFeesIdSchoolYearAndRemoveIsFalse(Long idPupil, Long idSchoolYear);
    List<Payment> findByIdUserAndFeesIdSchoolYearAndRemoveIsFalse(Long idUser, Long idSchoolYear);
    Page<Payment> findByIdUserAndFeesIdSchoolYearAndRemoveIsFalse(Long idUser, Long idSchoolYear,Pageable pageable);
    List<Payment> findByIdPaymentSystemAndFeesIdSchoolYearAndRemoveIsFalse(Long idPaymentSystem, Long idSchoolYear);
    Page<Payment> findByIdPaymentSystemAndFeesIdSchoolYearAndRemoveIsFalse(Long idPaymentSystem, Long idSchoolYear,Pageable pageable);
    List<Payment> findAllByRemoveIsFalse();

    Page<Payment> findByRemoveIsFalse(Pageable pageable);
    List<Payment> findByRemoveIsFalse();
    List<Payment> findByDateAndRemoveIsTrue(LocalDate date);

    List<Payment> findByIdUserAndDateAndRemoveIsFalse(Long idUser, LocalDate date);
}
