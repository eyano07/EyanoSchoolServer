package io.eyano.eyanoschool.financialservice.web;

import io.eyano.eyanoschool.financialservice.dtos.PaymentSystemDto;
import io.eyano.eyanoschool.financialservice.entitiesService.PaymentSystemService;
import io.eyano.eyanoschool.financialservice.exceptions.IdNotFoundException;
import io.eyano.eyanoschool.financialservice.exceptions.IdIsNullException;
import io.eyano.eyanoschool.financialservice.exceptions.IdNotNullException;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * PaymentSystemController is a class that allows you to define all the methods that allow you to manage payment systems
 * @version 1.0
 * @since 2021-04-19
 * @see PaymentSystemService
 * @author Pascal Tshingila

 */
@RestController
@AllArgsConstructor
@Slf4j
@Tag(name = "Payment System", description = "Operations pertaining to payment system")
@RequestMapping("/api/paymentSystem-Service")
public class PaymentSystemController {
    PaymentSystemService paymentSystemService;

    /**
     * Save a payment system
     * @param paymentSystemDto PaymentSystemDto
     * @return PaymentSystemDto saved payment system
     * @throws IdNotNullException : IdNotNullException
     * @throws IdIsNullException : IdIsNullException
     * @throws IdNotFoundException : IdNotFoundException
     */
    @PostMapping("/paymentSystems")
    @Tag(name = "Payment system", description = "Save a payment system")
    public PaymentSystemDto save(@RequestBody @Valid PaymentSystemDto paymentSystemDto) throws IdNotNullException, IdIsNullException, IdNotFoundException {
        log.info("execution of the method: save(PaymentSystemDto paymentSystemDto) parameter: paymentSystemDto:" + paymentSystemDto);
        if(paymentSystemDto.getId() != null)throw new IdNotNullException("Id must be null");
        paymentSystemDto.setRemove(false);
        PaymentSystemDto result = paymentSystemService.save(paymentSystemDto);
        log.info("execution of the method: save(PaymentSystemDto paymentSystemDto) result: result:" + result);
        return result;
    }
    /**
     * Update a payment system
     * @param paymentSystemDto PaymentSystemDto
     * @return PaymentSystemDto updated payment system
     * @throws IdIsNullException : IdIsNullException
     */
    @PutMapping("/paymentSystems")
    @Tag(name = "Payment system", description = "Update a payment system")
    public PaymentSystemDto update(
            @RequestBody @Valid PaymentSystemDto paymentSystemDto) throws IdIsNullException, IdNotNullException, IdNotFoundException {
        log.info("execution of the method: update(PaymentSystemDto paymentSystemDto) parameter: paymentSystemDto:" + paymentSystemDto);
        if(paymentSystemDto.getId() == null) {
            throw new IdIsNullException("Id is null");
        }
        PaymentSystemDto result = paymentSystemService.update(paymentSystemDto);
        log.info("execution of the method: update(PaymentSystemDto paymentSystemDto) result: result:" + result);
        return result;
    }

    /**
     * removeById is a method that allows you to delete a payment system by its id
     * @param id : the id of the payment system to delete
     * @return : a map containing the result of the deletion
     * @throws IdIsNullException : if the id is null
     * @throws IdNotFoundException : if the id is not found
     * @throws IdNotNullException : if the id is not null
     */
    @DeleteMapping("/paymentSystems/delete/{id}")
    @Tag(name = "Payment system", description = "Delete a payment system by its id")
    public Map<String, Object> removeById(@PathVariable("id") Long id) throws IdIsNullException, IdNotFoundException, IdNotNullException {
        log.info("execution of the method: removeById(Long id) parameter: id:" + id);
        if(id == null) {
            throw new IdIsNullException("Id is null");
        }
        Map<String, Object> map = new HashMap<>();
        PaymentSystemDto paymentSystemDto = paymentSystemService.findById(id);
        boolean isRemoved = paymentSystemService.removeById(id);
        map.put("is removed", isRemoved);
        map.put("Designation of the entity", paymentSystemDto.getDesignation());
        map.put("Id of the entity", paymentSystemDto.getId());
        map.put("Deleted entity", "paymentSystem");
        log.info("execution of the method: removeById(Long id) result: map:" + map);
        return map;
    }

    /**
     * remove is a method that allows you to delete a payment system
     * @param paymentSystemDto : the payment system to delete
     * @return : a map containing the result of the deletion
     * @throws IdIsNullException : if the id is null
     * @throws IdNotFoundException : if the id is not found
     * @throws IdNotNullException : if the id is not null
     */
    @DeleteMapping("/paymentSystems")
    @Tag(name = "Payment system", description = "Delete a payment system")
    public Map<String, Object> remove(@RequestBody PaymentSystemDto paymentSystemDto) throws IdIsNullException, IdNotFoundException, IdNotNullException {
        log.info("execution of the method: remove(PaymentSystemDto paymentSystemDto) parameter: paymentSystemDto:" + paymentSystemDto);
        if(paymentSystemDto == null) {
            throw new IdIsNullException("Id is null");
        }
        Map<String, Object> map = new HashMap<>();
        PaymentSystemDto paymentSystemDto1 = paymentSystemService.findById(paymentSystemDto.getId());
        boolean isRemoved = paymentSystemService.remove(paymentSystemDto);
        map.put("is removed", isRemoved);
        map.put("Designation of the entity", paymentSystemDto1.getDesignation());
        map.put("Id of the entity", paymentSystemDto1.getId());
        map.put("Deleted entity", "paymentSystem");
        log.info("execution of the method: remove(PaymentSystemDto paymentSystemDto) result: map:" + map);
        return map;
    }
    /**
     * restore is a method that allows you to restore a payment system
     * @param id : the id of the payment system to restore
     * @return : a map containing the result of the restoration
     * @throws IdIsNullException : if the id is null
     * @throws IdNotFoundException : if the id is not found
     * @throws IdNotNullException : if the id is not null
     */
    @GetMapping("/paymentSystems/restore/{id}")
    @Tag(name = "Payment system", description = "Restore a payment system")
    public Map<String,String> restore(@PathVariable("id") Long id) throws IdIsNullException, IdNotFoundException, IdNotNullException {
        log.info("execution of the method: restore(Long id) parameter: id:" + id);
        if(id == null) {
            throw new IdIsNullException("Id is null");
        }
        Map<String, String> map = new HashMap<>();
        PaymentSystemDto paymentSystemDto = paymentSystemService.findByIdAndRemoveTrue(id);
        paymentSystemService.restore(paymentSystemDto.getId());
        map.put("Designation of the entity", paymentSystemDto.getDesignation());
        map.put("Id of the entity", paymentSystemDto.getId().toString());
        map.put("Restored entity", "paymentSystem");
        map.put("is restore", "true");
        log.info("execution of the method: restore(Long id) result: map:" + map);
        return map;
    }

    /**
     * findAll is a method that allows you to find all payment systems
     * @return List<PaymentSystemDto> : a list of all payment systems
     */
    @GetMapping("/paymentSystems")
    @Tag(name = "Payment system", description = "Find all payment systems")
    public List<PaymentSystemDto> findAll() {
        log.info("execution of the method: findAll()");
        List<PaymentSystemDto> result = paymentSystemService.findAll();
        log.info("execution of the method: findAll() result: result:" + result);
        return result;
    }
    /**
     * findById is a method that allows you to find a payment system by its id
     * @param id : the id of the payment system to find
     * @return PaymentSystemDto : the payment system found
     * @throws IdIsNullException : if the id is null
     * @throws IdNotFoundException : if the id is not found
     */
    @GetMapping("/paymentSystems/{id}")
    @Tag(name = "Payment system", description = "Find a payment system by its id")
    public PaymentSystemDto findById(@PathVariable  Long id) throws IdIsNullException, IdNotFoundException {
        log.info("execution of the method: findById(Long id) parameter: id:" + id);
        PaymentSystemDto result = paymentSystemService.findById(id);
        log.info("execution of the method: findById(Long id) result: result:" + result);
        return result;
    }
    /**
     * findByDesignation is a method that allows you to find a payment system by its designation
     * @param designation : the designation of the payment system to find
     * @return List<PaymentSystemDto> : a list of payment systems found
     */
    @GetMapping("/paymentSystems/findDesignation")
    @Tag(name = "Payment system", description = "Find a payment system by its designation")
    public List<PaymentSystemDto> findByDesignationContainsIgnoreCaseAndIsRemoveFalse(
            @RequestParam(defaultValue = "") String designation) {
        log.info("execution of the method: findByDesignationContainsIgnoreCaseAndIsRemoveFalse(String designation) parameter: designation:" + designation);
        List<PaymentSystemDto> result = paymentSystemService.findByDesignationContainsIgnoreCaseAndIsRemoveFalse(designation);
        log.info("execution of the method: findByDesignationContainsIgnoreCaseAndIsRemoveFalse(String designation) result: result:" + result);
        return result;
    }

    /**
     * findByInstitutionName is a method that allows you to find a payment system by its institution name
     * @param institutionName : the institution name of the payment system to find
     * @return List<PaymentSystemDto> : a list of payment systems found
     */
    @GetMapping("/paymentSystems/findInstitutionName")
    @Tag(name = "Payment system", description = "Find a payment system by its institution name")
    public List<PaymentSystemDto> findByInstitutionNameContainsIgnoreCaseAndIsRemoveFalse(
            @RequestParam(defaultValue = "") String institutionName) {
        log.info("execution of the method: findByInstitutionNameContainsIgnoreCaseAndIsRemoveFalse(String institutionName) parameter: institutionName:" + institutionName);
        List<PaymentSystemDto> result = paymentSystemService.findByInstitutionNameContainsIgnoreCaseAndIsRemoveFalse(institutionName);
        log.info("execution of the method: findByInstitutionNameContainsIgnoreCaseAndIsRemoveFalse(String institutionName) result: result:" + result);
        return result;
    }

    /**
     * findByInstitutionNameAndStartDateAndEndDate is a method that allows you to find a payment system by its institution name and payment date
     * @param institutionName : the institution name of the payment system to find
     * @param startDate : the start date of the payment system to find
     * @param endDate : the end date of the payment system to find
     * @return List<PaymentSystemDto> : a list of payment systems found
     */
    @GetMapping("/paymentSystems/findInstitutionNameAndPaymentDate")
    @Tag(name = "Payment system", description = "Find a payment system by its institution name and payment date")
    public List<PaymentSystemDto> findByInstitutionNameContainsIgnoreCaseAndIsRemoveFalseAndPaymentDateBetween(
            @RequestParam(defaultValue = "") String institutionName,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        log.info("execution of the method: findByInstitutionNameContainsIgnoreCaseAndIsRemoveFalseAndPaymentDateBetween(String institutionName, LocalDate startDate, LocalDate endDate) parameter: institutionName:" + institutionName + ", startDate:" + startDate + ", endDate:" + endDate);
        List<PaymentSystemDto> result = paymentSystemService.findByInstitutionNameContainsIgnoreCaseAndIsRemoveFalseAndPaymentDateBetween(institutionName, startDate, endDate);
        log.info("execution of the method: findByInstitutionNameContainsIgnoreCaseAndIsRemoveFalseAndPaymentDateBetween(String institutionName, LocalDate startDate, LocalDate endDate) result: result:" + result);
        return result;
    }
    /**
     * This method finds an entity  deleted in the database
     * @param designation : the designation of the entity to find
     * @return List<PaymentSystemDto> : a list of payment systems found
     */
    @GetMapping("/paymentSystems/delete/findDesignation")
    @Tag(name = "Payment system", description = "Find a payment system deleted by its designation")
    public List<PaymentSystemDto> findByDesignationContainsIgnoreCaseAndIsRemoveTrue(
            @RequestParam(defaultValue = "") String designation) {
        log.info("execution of the method: findByDesignationContainsIgnoreCaseAndIsRemoveTrue(String designation) parameter: designation:" + designation);
        List<PaymentSystemDto> result = paymentSystemService.findByDesignationContainsIgnoreCaseAndIsRemoveTrue(designation);
        log.info("execution of the method: findByDesignationContainsIgnoreCaseAndIsRemoveTrue(String designation) result: result:" + result);
        return result;
    }
    /**
     * This method finds an entity  deleted in the database
     * @param institutionName : the institution name of the entity to find
     * @return List<PaymentSystemDto> : a list of payment systems found
     */
    @GetMapping("/paymentSystems/delete/findInstitutionName")
    @Tag(name = "Payment system", description = "Find a payment system deleted by its institution name")
    public List<PaymentSystemDto> findByInstitutionNameContainsAndIsRemoveTrue(
            @RequestParam(defaultValue = "") String institutionName) {
        log.info("execution of the method: findByInstitutionNameContainsAndIsRemoveTrue(String institutionName) parameter: institutionName:" + institutionName);
        List<PaymentSystemDto> result = paymentSystemService.findByInstitutionNameContainsAndIsRemoveTrue(institutionName);
        log.info("execution of the method: findByInstitutionNameContainsAndIsRemoveTrue(String institutionName) result: result:" + result);
        return result;
    }
    /**
     * This method finds an entity  deleted in the database
     * @param institutionName : the institution name of the entity to find
     * @param paymentDate : the payment date of the entity to find
     * @return List<PaymentSystemDto> : a list of payment systems found
     */
    @GetMapping("/paymentSystems/delete/findInstitutionNameAndPaymentDate")
    @Tag(name = "Payment system", description = "Find a payment system deleted by its institution name and payment date")
    public List<PaymentSystemDto> findByInstitutionNameContainsAndIsRemoveTrueAndPaymentDate(
            @RequestParam(defaultValue = "") String institutionName,
            @RequestParam LocalDate paymentDate) {
        log.info("execution of the method: findByInstitutionNameContainsAndIsRemoveTrueAndPaymentDate(String institutionName, LocalDate paymentDate) parameter: institutionName:" + institutionName + ", paymentDate:" + paymentDate);
        List<PaymentSystemDto> result = paymentSystemService.findByInstitutionNameContainsAndIsRemoveTrueAndPaymentDate(institutionName, paymentDate);
        log.info("execution of the method: findByInstitutionNameContainsAndIsRemoveTrueAndPaymentDate(String institutionName, LocalDate paymentDate) result: result:" + result);
        return result;
    }

    /**
     * This method findByIsRemoveFalseAndPaymentMethodAndPaymentDate an entity  in the database per page
     * @param paymentMethodString : the payment method of the entity to find
     * @param paymentDate : the payment date of the entity to find
     * @param page : the page of the entity to find
     * @param size : the size of the entity to find
     * @return Map<String, Object> : a map of payment systems found
     */
    @GetMapping("/paymentSystems/PaymentMethodAndPaymentDate")
    @Tag(name = "Payment system", description = "Find a payment system by its payment method and payment date")
    public Map<String, Object> findByIsRemoveFalseAndPaymentMethodAndPaymentDate(
            @RequestParam(defaultValue = "") String paymentMethodString,
            @RequestParam LocalDate paymentDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        log.info("execution of the method: findByIsRemoveFalseAndPaymentMethodAndPaymentDate(String paymentMethodString, LocalDate paymentDate, int page, int size) parameter: paymentMethodString:" + paymentMethodString + ", paymentDate:" + paymentDate + ", page:" + page + ", size:" + size);
        Map<String, Object> result = paymentSystemService.findByIsRemoveFalseAndPaymentMethodAndPaymentDate(paymentMethodString, paymentDate, page, size);
        log.info("execution of the method: findByIsRemoveFalseAndPaymentMethodAndPaymentDate(String paymentMethodString, LocalDate paymentDate, int page, int size) result: result:" + result);
        return result;
    }
    /**
     * This method findByIsRemoveFalseAndPaymentMethodAndPaymentDateBetween an entity  in the database per page
     * @param paymentMethodString : the payment method of the entity to find
     * @param startDate : the start date of the entity to find
     * @param endDate : the end date of the entity to find
     * @param page : the page of the entity to find
     * @param size : the size of the entity to find
     * @return Map<String, Object> : a map of payment systems found
     */
    @GetMapping("/paymentSystems/PaymentMethodAndPaymentDateBetween")
    @Tag(name = "Payment system", description = "Find a payment system by its payment method and payment date between")
    public Map<String, Object> findByIsRemoveFalseAndPaymentMethodAndPaymentDateBetween(
            @RequestParam(defaultValue = "") String paymentMethodString,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        log.info("execution of the method: findByIsRemoveFalseAndPaymentMethodAndPaymentDateBetween(String paymentMethodString, LocalDate startDate, LocalDate endDate, int page, int size) parameter: paymentMethodString:" + paymentMethodString + ", startDate:" + startDate + ", endDate:" + endDate + ", page:" + page + ", size:" + size);
        Map<String, Object> result = paymentSystemService.findByIsRemoveFalseAndPaymentMethodAndPaymentDateBetween(paymentMethodString, startDate, endDate, page, size);
        log.info("execution of the method: findByIsRemoveFalseAndPaymentMethodAndPaymentDateBetween(String paymentMethodString, LocalDate startDate, LocalDate endDate, int page, int size) result: result:" + result);
        return result;
    }
    /**
     * This method findByIsRemoveFalseAndInstitutionNameContainsIgnoreCaseAndPaymentDateBetween an entity  in the database per page
     * @param institutionName : the institution name of the entity to find
     * @param startDate : the start date of the entity to find
     * @param endDate : the end date of the entity to find
     * @param page : the page of the entity to find
     * @param size : the size of the entity to find
     * @return Map<String, Object> : a map of payment systems found
     */
    @GetMapping("/paymentSystems/institutionNameAndPaymentDateBetween")
    @Tag(name = "Payment system", description = "Find a payment system by its institution name and payment date between")
    public Map<String, Object> findByIsRemoveFalseAndInstitutionNameContainsIgnoreCaseAndPaymentDateBetween(
            @RequestParam(defaultValue = "") String institutionName,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        log.info("execution of the method: findByIsRemoveFalseAndInstitutionNameContainsIgnoreCaseAndPaymentDateBetween(String institutionName, LocalDate startDate, LocalDate endDate, int page, int size) parameter: institutionName:" + institutionName + ", startDate:" + startDate + ", endDate:" + endDate + ", page:" + page + ", size:" + size);
        Map<String, Object> result = paymentSystemService.findByIsRemoveFalseAndInstitutionNameContainsIgnoreCaseAndPaymentDateBetween(institutionName, startDate, endDate, page, size);
        log.info("execution of the method: findByIsRemoveFalseAndInstitutionNameContainsIgnoreCaseAndPaymentDateBetween(String institutionName, LocalDate startDate, LocalDate endDate, int page, int size) result: result:" + result);
        return result;
    }

    /**
     * this method allows you to find a payment system by its id and delete it
     * @param id : the id of the payment system to delete
     * @return PaymentSystemDto : the payment system found
     * @throws IdNotFoundException : if the id is not found
     */
    @GetMapping("/paymentSystems/delete/find/{id}")
    @Tag(name = "Payment system", description = "Find a payment system by its id and delete it")
    public PaymentSystemDto findByIdAndRemoveTrue(@PathVariable Long id) throws IdNotFoundException {
        log.info("execution of the method: findByIdAndRemoveTrue(Long id) parameter: id:" + id);
        PaymentSystemDto byIdAndRemoveTrue = paymentSystemService.findByIdAndRemoveTrue(id);
        log.info("execution of the method: findByIdAndRemoveTrue(Long id) result: byIdAndRemoveTrue:" + byIdAndRemoveTrue);
        return byIdAndRemoveTrue;
    }




}
