package io.eyano.eyanoschool.feesservice.dao;

import io.eyano.eyanoschool.feesservice.entities.FeesAllocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FeesAllocationRepository  extends JpaRepository<FeesAllocation, Long> {

    List<FeesAllocation> findByRemoveFalseAndDesignationContainsAndIdSchoolYear(String tag, Long idSchoolYear);
    List<FeesAllocation> findByRemoveTrueAndDesignationContainsAndIdSchoolYear(String tag, Long idSchoolYear);
    List<FeesAllocation> findByRemoveIsFalse();
    Optional<FeesAllocation> findByIdAndRemoveIsFalse(Long id);
    List<FeesAllocation> findByRemoveFalseAndDesignationContainsAndIdSchoolYearAndTypeFeesId(String tag, Long idSchoolYear, Long idTypeFees);
    List<FeesAllocation> findByRemoveTrueAndDesignationContainsAndIdSchoolYearAndTypeFeesId(String tag, Long idSchoolYear, Long idTypeFees);

    Optional<FeesAllocation> findByIdAndRemoveIsTrue(Long id);
}
