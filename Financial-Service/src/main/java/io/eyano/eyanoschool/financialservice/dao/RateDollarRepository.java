package io.eyano.eyanoschool.financialservice.dao;


import io.eyano.eyanoschool.financialservice.entities.RateDollar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RateDollarRepository extends JpaRepository<RateDollar, Long> {
    public RateDollar findByIsCurrentRateTrueAndRemoveFalse();
    public List<RateDollar> findAllByOrderByDateDesc();

    Optional<RateDollar> findByIdAndRemoveFalse(Long id);

    Optional<RateDollar> findByIdAndRemoveTrue(Long id);
}
