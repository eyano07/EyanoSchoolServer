package io.eyano.eyanoschool.feesservice.dao;

import io.eyano.eyanoschool.feesservice.entities.FeesAllocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
/**
 * The FeesAllocationRepository interface is a repository that extends JpaRepository
 * for CRUD methods
 * @version 1.0
 * @since 1.0
 * @author : Pascal Tshingila
 */
public interface FeesAllocationRepository  extends JpaRepository<FeesAllocation, Long> {
    /**
     * This method is used to find all fee allocations by id
     * @return List of fees allocations
     */
    Optional<FeesAllocation> findByIdAndRemoveIsFalse(Long id);
    /**
     * This method is used to find all fee allocations that are removed
     * @return List of fees allocations
     */
    Optional<FeesAllocation> findByIdAndRemoveIsTrue(Long id);

    /**
     * This method is used to find all fee allocations
     * @return List of fees allocations
     */
    List<FeesAllocation> findByRemoveIsFalse();
    /**
     * This method is used to find all fee allocations
     * @return List of fees allocations
     */
    List<FeesAllocation> findByRemoveIsTrue();
//-----------------------------------------------------------------------------------------------------------------
    /**
     * This method is used to find all fee allocations by tag and school year
     * @return List of fees allocations
     */
    List<FeesAllocation> findByRemoveFalseAndDesignationContainsAndIdSchoolYear(String tag, Long idSchoolYear);
    /**
     * This method is used to find all fee allocations by tag and school year that are removed
     * @return List of fees allocations
     */
    List<FeesAllocation> findByRemoveTrueAndDesignationContainsAndIdSchoolYear(String tag, Long idSchoolYear);

    /**
     * This method is used to find all fee allocations by tag, school year and id type of fees
     * @return List of fees allocations
     */
    List<FeesAllocation> findByRemoveFalseAndDesignationContainsAndIdSchoolYearAndTypeFeesId(String tag, Long idSchoolYear, Long idTypeFees);
    /**
     * This method is used to find all fee allocations by tag, school year and id type of fees that are removed
     * @return List of fees allocations
     */
    List<FeesAllocation> findByRemoveTrueAndDesignationContainsAndIdSchoolYearAndTypeFeesId(String tag, Long idSchoolYear, Long idTypeFees);

}
