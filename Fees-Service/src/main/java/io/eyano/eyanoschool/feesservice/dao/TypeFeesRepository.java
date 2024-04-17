package io.eyano.eyanoschool.feesservice.dao;

import io.eyano.eyanoschool.feesservice.entities.TypeFees;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TypeFeesRepository  extends JpaRepository<TypeFees, Long> {
    Optional<TypeFees> findByIdAndRemoveIsTrue(Long id);
    Optional<TypeFees> findByIdAndRemoveIsFalse(Long id);
    List<TypeFees> findTypeFeesByDesignationIgnoreCaseContainsAndRemoveIsTrue(String tag);
    List<TypeFees> findTypeFeesByDesignationIgnoreCaseContainsAndRemoveIsFalse(String tag);
    Page<TypeFees> findTypeFeesByDesignationIgnoreCaseContainsAndRemoveIsFalse(String tag, Pageable pageable);
    Page<TypeFees> findTypeFeesByDesignationIgnoreCaseContainsAndRemoveIsTrue(String tag, Pageable pageable);
    List<TypeFees> findTypeFeesByRemoveIsFalse();
    List<TypeFees> findTypeFeesByRemoveIsTrue();
}
