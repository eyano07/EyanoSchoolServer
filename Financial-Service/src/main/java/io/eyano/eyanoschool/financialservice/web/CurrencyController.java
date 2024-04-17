package io.eyano.eyanoschool.financialservice.web;

import io.eyano.eyanoschool.financialservice.dtos.CurrencyDto;
import io.eyano.eyanoschool.financialservice.exceptions.IdNotFoundException;
import io.eyano.eyanoschool.financialservice.exceptions.IdIsNullException;
import io.eyano.eyanoschool.financialservice.exceptions.IdNotNullException;
import io.eyano.eyanoschool.financialservice.entitiesService.CurrencyService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is a controller that manages currency operations
 * @version 1.0
 * @since 1.0
 * @see CurrencyService
 * @author : Pascal Tshingila
 */
@RestController
@AllArgsConstructor
@Slf4j
@Tag(name = "Controller that manages currency operations")
@RequestMapping("/api/currency-service")
public class CurrencyController {
    CurrencyService currencyService;

    /**
     * This method is used to save a currency
     * @param currencyDto : the currency to save
     * @return CurrencyDto
     * @throws IdIsNullException : if the id is null
     * @throws IdNotNullException : if the id is not null
     * @throws IdNotFoundException : if the id is not found
     */
    @PostMapping("/currencies")
    @Tag(name="Currency" , description = "This method is used to save a currency")
    public CurrencyDto save(
            @RequestBody @Valid CurrencyDto currencyDto) throws IdIsNullException, IdNotNullException, IdNotFoundException {
        log.info("execution of the method: save(CurrencyDto currencyDto) parameter :" + currencyDto );
        if(currencyDto.getId() != null) throw new IdNotNullException("The id must be null");
        currencyDto.setRemove(false);
        CurrencyDto result = currencyService.save(currencyDto);
        log.info("end of the method: save(CurrencyDto currencyDto) result :" + result );
        return result;
    }

    /**
     * This method is used to update a currency
     * @param currencyDto : the currency to update
     * @return CurrencyDto
     * @throws IdIsNullException : if the id is null
     * @throws IdNotNullException : if the id is not null
     * @throws IdNotFoundException : if the id is not found
     */
    @PutMapping("/currencies")
    @Tag(name="Currency" , description = "This method is used to update a currency")
    public CurrencyDto update(
            @RequestBody @Valid CurrencyDto currencyDto) throws IdIsNullException, IdNotNullException, IdNotFoundException {
        log.info("execution of the method: update(CurrencyDto currencyDto) parameter :" + currencyDto );
        if(currencyDto.getId() == null) throw new IdIsNullException("The id must not be null");
        CurrencyDto result = currencyService.update(currencyDto);
        log.info("end of the method: update(CurrencyDto currencyDto) result :" + result );
        return result;
    }
    /**
     * removeById is a method that allows you to delete a Currency entity from the database
     * @param id : the id of the currency to delete
     * @return CurrencyDto
     * @throws IdNotFoundException : if the id is not found
     * @throws IdNotNullException : if the id is not null
     * @throws IdIsNullException : if the id is null
     */
    @DeleteMapping("/currencies/delete/{id}")
    @Tag(name="Currency" , description = "This method is used to remove a currency")
    public Map<String, Object> removeById(@PathVariable("id") Long id) throws IdIsNullException, IdNotNullException, IdNotFoundException {
        log.info("execution of the method: removeById(Long id) parameter :" + id );
        if (id == null) throw new IdIsNullException("The id must not be null");
        Map<String, Object> map = new HashMap<>();
        CurrencyDto currencyDto = currencyService.findById(id);
        boolean isRemove = currencyService.removeById(currencyDto.getId());
        map.put("Deleted entity","Currency");
        map.put("Designation of the entity",currencyDto.getDesignation());
        map.put("Id of the entity",currencyDto.getId().toString());
        map.put("is remove", String.valueOf(isRemove));
        log.info("end of the method: removeById(Long id) result :" + map );
        return map;
    }

    /**
     * remove is a method that allows you to delete a Currency entity from the database
     * @param currencyDto : the currency to delete
     * @return CurrencyDto
     * @throws IdNotFoundException : if the id is not found
     * @throws IdNotNullException : if the id is not null
     * @throws IdIsNullException : if the id is null
     */
    @DeleteMapping("/currencies")
    @Tag(name="Currency" , description = "This method is used to remove a currency")
    public Map<String, Object> remove(@RequestBody CurrencyDto currencyDto) throws IdIsNullException, IdNotNullException, IdNotFoundException {
        log.info("execution of the method: remove(CurrencyDto currencyDto) parameter :" + currencyDto );
        Map<String, Object> map = new HashMap<>();
        boolean isRemove = currencyService.remove(currencyDto);
        map.put("Deleted entity","Currency");
        map.put("Designation of the entity",currencyDto.getDesignation());
        map.put("Id of the entity",currencyDto.getId().toString());
        map.put("is remove", String.valueOf(isRemove));
        log.info("end of the method: remove(CurrencyDto currencyDto) result :" + map );
        return map;
    }
    /**
     * findById is a method that allows you to find a Currency entity from the database
     * @param id : the id of the currency to find
     * @return CurrencyDto
     * @throws IdNotFoundException : if the id is not found
     * @throws IdNotNullException : if the id is not null
     * @throws IdIsNullException : if the id is null
     */
    @GetMapping("/currencies/restore/{id}")
    @Tag(name="Currency" , description = "This method is used to restore a currency")
    public Map<String,String> restore(@PathVariable("id") Long id) throws IdIsNullException, IdNotNullException, IdNotFoundException {
        log.info("execution of the method: restore(Long id) parameter :" + id );
        Map<String, String> map = new HashMap<>();
        if (id == null) throw new IdIsNullException("The id must not be null");
        CurrencyDto currencyDto = currencyService.findByIdAndRemoveTrue(id);
        currencyService.restore(currencyDto.getId());
        map.put("Restored entity","Currency");
        map.put("Designation of the entity",currencyDto.getDesignation());
        map.put("Id of the entity",currencyDto.getId().toString());
        map.put("is restore", "true");
        log.info("end of the method: restore(Long id) result :" + map );
        return map;
    }
    /**
     * This method List all the currencies
     * @return List<CurrencyDto> : the list of all currencies
     */
    @GetMapping("/currencies")
    @Tag(name="Currency" , description = "This method is used to list all currencies")
    public List<CurrencyDto> findAll(){
        log.info("execution of the method: findAll() parameter : void" );
        List<CurrencyDto> result = currencyService.findAll();
        log.info("end of the method: findAll() result :" + result );
        return result;
    }

    /**
     * the methode findBySimbolAndRemoveFalse is used to find a currency by its symbol
     * @param symbol : the symbol of the currency
     * @return CurrencyDto : the currency found
     */
    @GetMapping("/currencies/{symbol}")
    @Tag(name="Currency" , description = "This method is used to find a currency by its symbol")
    public CurrencyDto findBySymbolAndRemoveFalse(@PathVariable("symbol") String symbol) {
        log.info("execution of the method: findBySymbolAndRemoveFalse(String symbol) parameter :" + symbol );
        CurrencyDto result = currencyService.findBySymbolAndRemoveFalse(symbol);
        log.info("end of the method: findBySymbolAndRemoveFalse(String symbol) result :" + result );
        return result;
    }
    /**
     * the methode findByIdAndRemoveTrue is used to find a currency by its id
     * @param id : the id of the currency
     * @return CurrencyDto : the currency found
     * @throws IdIsNullException : if the id is null
     * @throws IdNotFoundException : if the id is not found
     */
    @GetMapping("/currencies/delete/{id}")
    @Tag(name="Currency" , description = "This method is used to find a currency by its id")
    public CurrencyDto findByIdAndRemoveTrue(@PathVariable("id") Long id) throws IdIsNullException, IdNotFoundException {
        log.info("execution of the method: findByIdAndRemoveTrue(Long id) parameter :" + id );
        CurrencyDto result = currencyService.findByIdAndRemoveTrue(id);
        log.info("end of the method: findByIdAndRemoveTrue(Long id) result :" + result );
        return result;
    }

    /**
     * the method findByDesignationContainsIgnoreCaseAndRemoveFalse is used to find a currency by its designation
     * @param designation : the designation of the currency
     * @return List<CurrencyDto> : the list of currencies found
     */
    @GetMapping("/currencies/designation")
    @Tag(name="Currency" , description = "This method is used to find a currency by its designation")
    List<CurrencyDto> findByDesignationContainsIgnoreCaseAndRemoveFalse(@RequestParam(defaultValue = "") String designation) {
        log.info("execution of the method: findByDesignationContainsIgnoreCaseAndRemoveFalse(String designation) parameter :" + designation );
        List<CurrencyDto> result = currencyService.findByDesignationContainsIgnoreCaseAndRemoveFalse(designation);
        log.info("end of the method: findByDesignationContainsIgnoreCaseAndRemoveFalse(String designation) result :" + result );
        return result;
    }
    /**
     * the method findByDesignationContainsIgnoreCaseAndRemoveTrue is used to find a currency by its designation
     * @param designation : the designation of the currency
     * @return List<CurrencyDto> : the list of currencies found
     */
    @GetMapping("/currencies/delete")
    @Tag(name="Currency" , description = "This method is used to find a currency by its designation")
    public List<CurrencyDto> findByDesignationContainsIgnoreCaseAndRemoveTrue(@RequestParam(defaultValue = "") String designation) {
        log.info("execution of the method: findByDesignationContainsIgnoreCaseAndRemoveTrue(String designation) parameter :" + designation );
        List<CurrencyDto> result = currencyService.findByDesignationContainsIgnoreCaseAndRemoveTrue(designation);
        log.info("end of the method: findByDesignationContainsIgnoreCaseAndRemoveTrue(String designation) result :" + result );
        return result;
    }
}
