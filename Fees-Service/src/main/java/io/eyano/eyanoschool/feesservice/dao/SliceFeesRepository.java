package io.eyano.eyanoschool.feesservice.dao;

import io.eyano.eyanoschool.feesservice.entities.SliceFees;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SliceFeesRepository extends JpaRepository<SliceFees, Long> {
    List<SliceFees> findSliceFeesByRemoveIsFalse();
    List<SliceFees> findSliceFeesByRemoveIsTrue();
    List<SliceFees> findSliceFeesByDesignationIgnoreCaseContainsAndRemoveIsTrue(String designation);
    List<SliceFees> findSliceFeesByDesignationIgnoreCaseContainsAndRemoveIsFalse(String designation);
    Page<SliceFees> findSliceFeesByDesignationIgnoreCaseContainsAndRemoveIsFalse(String designation, Pageable pageable);
    Page<SliceFees> findSliceFeesByDesignationIgnoreCaseContainsAndRemoveIsTrue(String designation, Pageable pageable);
    Optional<SliceFees> findByIdAndRemoveIsFalse(Long id);
    Optional<SliceFees> findByIdAndRemoveIsTrue(Long id);

}
