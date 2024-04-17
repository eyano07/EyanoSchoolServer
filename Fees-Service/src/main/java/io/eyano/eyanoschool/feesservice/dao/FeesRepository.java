package io.eyano.eyanoschool.feesservice.dao;

import io.eyano.eyanoschool.feesservice.entities.Fees;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * FeesRepository is an interface that extends JpaRepository and has generic type Fees and Long
 * @author : Pascal Tshingila
 * @since : 02/02/2021
 * @version : 1.0
 */
public interface FeesRepository extends JpaRepository<Fees, Long> {
    //find all fees that are not removed all fees
    List<Fees> findFeesByRemoveIsFalse();
    //find all fees that are removed
    List<Fees> findFeesByRemoveIsTrue();
    //find all fees that are not removed and id fees is equal to id
    Optional<Fees> findFeesByIdAndRemoveIsFalse(Long id);
    //find all fees that are removed and id fees is equal to id
    Optional<Fees> findByIdAndRemoveIsTrue(Long id);
    //find all fees that are not removed and idClass is equal to idClasse and IdSchoolYear is equal to idSchoolYear
    List<Fees> findByIdClassFessAndIdSchoolYearAndRemoveIsFalse(long idClass, long idSchoolYear);
    //find all fees that are not removed and idClass is equal to idClasse and IdSchoolYear is equal to idSchoolYear
    List<Fees> findByDesignationContainsAndRemoveIsFalseAndIdClassFessAndIdSchoolYear(String designation,long idClass, long idSchoolYear);
    //find all fees that are not removed and idClass is equal to idClasse and IdSchoolYear is equal to idSchoolYear and idTypeFees is equal to idTypeFees
    List<Fees> findByIdClassFessAndIdSchoolYearAndTypeFeesIdAndRemoveIsTrue(long idClass, long idSchoolYear,long idtypeFees);
    //find All fees that are not removed and idSchoolYear is equal to idSchoolYear and designation contains designation
    List<Fees> findByIdSchoolYearAndRemoveIsFalseAndDesignationContains(Long idSchoolYear, String designation);
    //find All fees page that are not removed and idSchoolYear is equal to idSchoolYear and designation contains designation
    Page<Fees> findByIdSchoolYearAndRemoveIsFalseAndDesignationContains(Long idSchoolYearn,String designation, Pageable pageable);
    //find All fees that are not removed and idSchoolYear is equal to idSchoolYear and idTypeFees is equal to idTypeFees and idClass is equal to idClass
    List<Fees> findByIdSchoolYearAndTypeFeesIdAndRemoveIsFalseAndIdClassFess(long idClass,Long idSchoolYear, Long idTypeFees);

    Page<Fees> findFeesByRemoveIsFalseAndDesignationContains(String tag, Pageable pageable);

    Page<Fees> findFeesByRemoveIsTrueAndDesignationContains(String tag, Pageable pageable);

    List<Fees> findByIdClassFessAndIdSchoolYearAndSliceFeesIdAndRemoveIsFalse(Long idClass, long idSchoolYear, long sliceFeesId);
}
