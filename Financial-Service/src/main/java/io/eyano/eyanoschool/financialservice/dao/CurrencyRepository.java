package io.eyano.eyanoschool.financialservice.dao;

import io.eyano.eyanoschool.financialservice.entities.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
public Currency findBySymbolAndRemoveFalse(String symbol);
public Optional<Currency> findByIdAndRemoveFalse(Long id);
public Optional<Currency> findByIdAndRemoveTrue(Long id);
public List<Currency> findByDesignationContainsIgnoreCaseAndRemoveFalse(String designation);
public List<Currency> findByDesignationContainsIgnoreCaseAndRemoveTrue(String designation);
}
