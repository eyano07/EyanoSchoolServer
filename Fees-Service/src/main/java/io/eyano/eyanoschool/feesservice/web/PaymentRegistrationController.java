package io.eyano.eyanoschool.feesservice.web;

import io.eyano.eyanoschool.feesservice.dtos.PaymentRegistrationDto;
import io.eyano.eyanoschool.feesservice.entitiesService.PaymentRegistrationService;
import io.eyano.eyanoschool.feesservice.exceptions.IdIsNullException;
import io.eyano.eyanoschool.feesservice.exceptions.IdNotFoundException;
import io.eyano.eyanoschool.feesservice.exceptions.IdNotNullException;
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
 * PaymentRegistrationController is a class that exposes the services of the payment registration
 * @author : Pascal Tshingila
 * @version : 1.0
 * @since : 2021-04-20
 */
@RestController
@AllArgsConstructor
@Slf4j
@Tag(name = "Payment Registration", description = "Controller that manages the registration of payments")
@RequestMapping("/api/fees-service")
public class PaymentRegistrationController {
    PaymentRegistrationService paymentRegistrationService;
    /**
     * This method allows you to save a payment registration
     * @param paymentRegistrationDto : the payment registration to save
     * @return the payment registration saved
     * @throws IdNotNullException : if the id of the payment is not null
     * @throws IdNotFoundException : if the id of the payment is not found
     */
    @PostMapping("/paymentRegistrations")
    @Tag(name = "Save a payment registration", description = "This method allows you to save a payment registration")
    public PaymentRegistrationDto save(@RequestBody @Valid PaymentRegistrationDto paymentRegistrationDto) throws IdNotNullException, IdNotFoundException {
        log.info("execution of the method:save(PaymentRegistrationDto) parameter : " +paymentRegistrationDto) ;
        if(paymentRegistrationDto.getId() != null){
            throw new IdNotNullException("The id of the payment must be null");
        }
        paymentRegistrationDto.setRemove(false);
        PaymentRegistrationDto result = paymentRegistrationService.save(paymentRegistrationDto);
        log.info("execution of the method:save(PaymentRegistrationDto) result : " +result) ;
        return result;
    }
    /**
     * This method allows you to update a payment registration
     * @param paymentRegistrationDto : the payment registration to update
     * @return the payment registration updated
     * @throws IdIsNullException : if the id of the payment is null
     * @throws IdNotFoundException : if the id of the payment is not found
     */
    @PutMapping("/paymentRegistrations")
    @Tag(name = "Update a payment registration", description = "This method allows you to update a payment registration")
    public PaymentRegistrationDto update(@RequestBody @Valid PaymentRegistrationDto paymentRegistrationDto) throws IdIsNullException, IdNotFoundException {
        log.info("execution of the method:update(PaymentRegistrationDto) parameter : " +paymentRegistrationDto) ;
        if(paymentRegistrationDto.getId() == null){
            throw new IdIsNullException("The id of the payment is null");
        }
        paymentRegistrationDto.setRemove(false);
        PaymentRegistrationDto result = paymentRegistrationService.update(paymentRegistrationDto);
        log.info("execution of the method:update(PaymentRegistrationDto) result : " +result) ;
        return result;
    }

    /**
     * removeById is a method that allows you to remove a payment registration by its id
     * @param id : the identifier of the entity to delete
     * @return Map<String,String> : detail on the entity deleted
     * @throws IdNotFoundException : if the entity is not found
     */
    @DeleteMapping("/paymentRegistrations/{id}")
    @Tag(name = "Remove a payment registration by its id", description = "This method allows you to remove a payment registration by its id")
    public Map<String,String> removeById(@PathVariable("id") Long id) throws IdNotFoundException {
        log.info("execution of the method:removeById(Long) parameter : " +id) ;
        Map<String,String> map = new HashMap<>();
        PaymentRegistrationDto paymentRegistrationDto = paymentRegistrationService.findByRemoveFalseAndId(id);
        boolean result = paymentRegistrationService.removeById(id);
        map.put("Deleted entity","PaymentRegistration");
        map.put("Designation of the entity",paymentRegistrationDto.getFees().getDesignation());
        map.put("Id of the entity",paymentRegistrationDto.getId().toString());
        map.put("is remove", String.valueOf(result));
        log.info("execution of the method:removeById(Long) result : " +result) ;
        return map;
    }
    /**
     * remove is a method that allows you to remove a payment registration
     * @param paymentRegistrationDto : the payment registration to delete
     * @return Map<String,String> : detail on the entity deleted
     * @throws IdNotFoundException : if the entity is not found
     */
    @DeleteMapping("/paymentRegistrations")
    @Tag(name = "Remove a payment registration", description = "This method allows you to remove a payment registration")
    public Map<String,String> remove(@RequestBody @Valid PaymentRegistrationDto paymentRegistrationDto) throws IdNotFoundException {
        log.info("execution of the method:remove(PaymentRegistrationDto) parameter : " +paymentRegistrationDto) ;
        Map<String,String> map = new HashMap<>();
        PaymentRegistrationDto paymentRegistrationDto1 = paymentRegistrationService.findByRemoveFalseAndId(paymentRegistrationDto.getId());
        boolean result = paymentRegistrationService.remove(paymentRegistrationDto);
        map.put("Deleted entity","PaymentRegistration");
        map.put("Designation of the entity",paymentRegistrationDto1.getFees().getDesignation());
        map.put("Id of the entity",paymentRegistrationDto1.getId().toString());
        map.put("is remove", String.valueOf(result));
        log.info("execution of the method:remove(PaymentRegistrationDto) result : " +result) ;
        return map;
    }
    /**
     * isExist is a method that allows you to check if a payment registration exists
     * @param id : the identifier of the entity to check
     * @return Map<String,String> : detail on the entity checked
     * @throws IdNotFoundException : if the entity is not found
     */
    @GetMapping("/paymentRegistrations/isExist/{id}")
    @Tag(name = "Check if a payment registration exists", description = "This method allows you to check if a payment registration exists")
    public Map<String, String> isExist(@PathVariable("id") Long id) throws IdNotFoundException {
        log.info("execution of the method:isExist(Long) parameter : " +id) ;
        Map<String,String> map = new HashMap<>();
        boolean result = paymentRegistrationService.isExist(id);
        try {
            PaymentRegistrationDto paymentRegistrationDto = paymentRegistrationService.findByRemoveFalseAndId(id);
            map.put("Designation of the entity",paymentRegistrationDto.getFees().getDesignation());
            map.put("Id of the entity",paymentRegistrationDto.getId().toString());
            map.put("is exist", String.valueOf(result));
            map.put("is deleted", "false");
        } catch (IdNotFoundException e) {
            map.put("Entity","PaymentRegistrationDto");
            map.put("Id of the entity", String.valueOf(id));
            map.put("is exist", String.valueOf(result));
            map.put("is deleted", "true");

        }
        map.put("Exist", String.valueOf(result));
        log.info("execution of the method:isExist(Long) result : " +result) ;
        return map;
    }
    /**
     * isRemove is a method that allows you to check if a payment registration is removed
     * @param id : the identifier of the entity to check
     * @return Map<String,String> : detail on the entity checked
     * @throws IdNotFoundException : if the entity is not found
     */
    @GetMapping("/paymentRegistrations/isRemove/{id}")
    @Tag(name = "Check if a payment registration is removed", description = "This method allows you to check if a payment registration is removed")
    public  Map<String, String> isRemove(@PathVariable("id") Long id) throws IdNotFoundException {
        log.info("execution of the method:isRemove(Long) parameter : " +id) ;
        Map<String,String> map = new HashMap<>();
        boolean result = paymentRegistrationService.isRemove(id);
        try {
            PaymentRegistrationDto paymentRegistrationDto = paymentRegistrationService.findByRemoveFalseAndId(id);
            map.put("Designation of the entity",paymentRegistrationDto.getFees().getDesignation());
            map.put("Id of the entity",paymentRegistrationDto.getId().toString());
            map.put("is remove", String.valueOf(result));
            map.put("is deleted", "false");
        } catch (IdNotFoundException e) {
            map.put("Entity","PaymentRegistrationDto");
            map.put("Id of the entity", String.valueOf(id));
            map.put("is remove", String.valueOf(result));
            map.put("is deleted", "true");

        }
        map.put("Remove", String.valueOf(result));
        log.info("execution of the method:isRemove(Long) result : " +result) ;
        return map;
    }
    /**
     * findById is a method that allows you to find a payment registration by its id
     * @param id : the identifier of the entity to find
     * @return PaymentRegistrationDto : the entity found
     * @throws IdNotFoundException : if the entity is not found
     */
    @GetMapping("/paymentRegistrations/{id}")
    @Tag(name = "Find a payment registration by its id", description = "This method allows you to find a payment registration by its id")
    public PaymentRegistrationDto findById(@PathVariable("id") Long id) throws IdNotFoundException {
        log.info("execution of the method:findById(Long) parameter : " +id) ;
        PaymentRegistrationDto result = paymentRegistrationService.findByRemoveFalseAndId(id);
        log.info("execution of the method:findById(Long) result : " +result) ;
        return result;
    }
    /**
     * findAll is a method that allows you to find all payment registrations
     * @param idCandidate : the identifier of the candidate
     * @return List<PaymentRegistrationDto> : the list of entities found
     */
    @GetMapping("/paymentRegistrations/findByRemoveIsFalseAndIdCandidate/{idCandidate}")
    @Tag(name = "Find all payment registrations by candidate", description = "This method allows you to find all payment registrations by candidate")
    public List<PaymentRegistrationDto> findByRemoveIsFalseAndIdCandidate(@PathVariable Long idCandidate){
        log.info("execution of the method:findByRemoveIsFalseAndIdCandidate(Long) parameter : " +idCandidate) ;
        List<PaymentRegistrationDto> byRemoveIsFalseAndIdCandidate = paymentRegistrationService.findByRemoveIsFalseAndIdCandidate(idCandidate);
        log.info("execution of the method:findByRemoveIsFalseAndIdCandidate(Long) result : " +byRemoveIsFalseAndIdCandidate) ;
        return byRemoveIsFalseAndIdCandidate;
    }
    /**
     * findAll is a method that allows you to find all payment registrations
     * @param idCandidate : the identifier of the candidate
     * @param idSchoolYear : the identifier of the school year
     * @return List<PaymentRegistrationDto> : the list of entities found
     */
    @GetMapping("/paymentRegistrations/findByRemoveIsFalseAndIdCandidateAndFeesIdSchoolYear")
    @Tag(name = "Find all payment registrations by candidate and school year", description = "This method allows you to find all payment registrations by candidate and school year")
    public List<PaymentRegistrationDto> findByRemoveIsFalseAndIdCandidateAndFeesIdSchoolYear(
            @RequestParam Long idCandidate,
            @RequestParam Long idSchoolYear){
        log.info("execution of the method:findByRemoveIsFalseAndIdCandidateAndFeesIdSchoolYear(Long, Long) parameter : " +idCandidate + " " + idSchoolYear) ;
        List<PaymentRegistrationDto> byRemoveIsFalseAndIdCandidateAndFeesIdSchoolYear = paymentRegistrationService.findByRemoveIsFalseAndIdCandidateAndFeesIdSchoolYear(idCandidate, idSchoolYear);
        log.info("execution of the method:findByRemoveIsFalseAndIdCandidateAndFeesIdSchoolYear(Long, Long) result : " +byRemoveIsFalseAndIdCandidateAndFeesIdSchoolYear) ;
        return byRemoveIsFalseAndIdCandidateAndFeesIdSchoolYear;
    }
    /**
     * findAll is a method that allows you to find all payment registrations
     * @param idCandidate : the identifier of the candidate
     * @param idSchoolYear : the identifier of the school year
     * @return List<PaymentRegistrationDto> : the list of entities found
     */
    @GetMapping("/paymentRegistrations/findByRemoveIsTrueAndIdCandidateAndFeesIdSchoolYear")
    @Tag(name = "Find all payment registrations by candidate and school year", description = "This method allows you to find all payment registrations by candidate and school year")
    public List<PaymentRegistrationDto> findByRemoveIsTrueAndIdCandidateAndFeesIdSchoolYear(
            @RequestParam Long idCandidate,
            @RequestParam Long idSchoolYear){
        log.info("execution of the method:findByRemoveIsTrueAndIdCandidateAndFeesIdSchoolYear(Long, Long) parameter : " +idCandidate + " " + idSchoolYear) ;
        List<PaymentRegistrationDto> byRemoveIsTrueAndIdCandidateAndFeesIdSchoolYear = paymentRegistrationService.findByRemoveIsTrueAndIdCandidateAndFeesIdSchoolYear(idCandidate, idSchoolYear);
        log.info("execution of the method:findByRemoveIsTrueAndIdCandidateAndFeesIdSchoolYear(Long, Long) result : " +byRemoveIsTrueAndIdCandidateAndFeesIdSchoolYear) ;
        return byRemoveIsTrueAndIdCandidateAndFeesIdSchoolYear;
    }
    /**
     * findAll is a method that allows you to find all payment registrations
     * @param idUser : the identifier of the user
     * @param idSchoolYear : the identifier of the school year
     * @return List<PaymentRegistrationDto> : the list of entities found
     */
    @GetMapping("/paymentRegistrations/findByRemoveIsFalseAndIdUserAndFeesIdSchoolYear")
    @Tag(name = "Find all payment registrations by user and school year", description = "This method allows you to find all payment registrations by user and school year")
    public List<PaymentRegistrationDto> findByRemoveIsFalseAndIdUserAndFeesIdSchoolYear(
            @RequestParam Long idUser,
            @RequestParam Long idSchoolYear){
        log.info("execution of the method:findByRemoveIsFalseAndIdUserAndFeesIdSchoolYear(Long, Long) parameter : " +idUser + " " + idSchoolYear) ;
        List<PaymentRegistrationDto> byRemoveIsFalseAndIdUserAndFeesIdSchoolYear = paymentRegistrationService.findByRemoveIsFalseAndIdUserAndFeesIdSchoolYear(idUser, idSchoolYear);
        log.info("execution of the method:findByRemoveIsFalseAndIdUserAndFeesIdSchoolYear(Long, Long) result : " +byRemoveIsFalseAndIdUserAndFeesIdSchoolYear) ;
        return byRemoveIsFalseAndIdUserAndFeesIdSchoolYear;
    }
    /**
     * findAll is a method that allows you to find all payment registrations
     * @param date : the date of the payment
     * @return List<PaymentRegistrationDto> : the list of entities found
     */
    @GetMapping("/paymentRegistrations/findByRemoveIsFalseAndDate")
    @Tag(name = "Find all payment registrations by date", description = "This method allows you to find all payment registrations by date")
    public List<PaymentRegistrationDto> findByRemoveIsFalseAndDate(@RequestParam String date){
        log.info("execution of the method:findByRemoveIsFalseAndDate(LocalDate) parameter : " +date) ;
        LocalDate date1 = LocalDate.parse(date);
        List<PaymentRegistrationDto> byRemoveIsFalseAndDate = paymentRegistrationService.findByRemoveIsFalseAndDate(date1);
        log.info("execution of the method:findByRemoveIsFalseAndDate(LocalDate) result : " +byRemoveIsFalseAndDate) ;
        return byRemoveIsFalseAndDate;
    }
    /**
     * findAll is a method that allows you to find all payment registrations
     * @param idPaymentSystem : the identifier of the payment system
     * @param idSchoolYear : the identifier of the school year
     * @return List<PaymentRegistrationDto> : the list of entities found
     */
    @GetMapping("/paymentRegistrations/findByRemoveIsFalseAndIdPaymentSystemAndFeesIdSchoolYear")
    @Tag(name = "Find all payment registrations by payment system and school year", description = "This method allows you to find all payment registrations by payment system and school year")
    public List<PaymentRegistrationDto> findByRemoveIsFalseAndIdPaymentSystemAndFeesIdSchoolYear(
            @RequestParam Long idPaymentSystem,
            @RequestParam Long idSchoolYear){
        log.info("execution of the method:findByRemoveIsFalseAndIdPaymentSystemAndFeesIdSchoolYear(Long, Long) parameter : " +idPaymentSystem + " " + idSchoolYear) ;
        List<PaymentRegistrationDto> byRemoveIsFalseAndIdPaymentSystemAndFeesIdSchoolYear = paymentRegistrationService.findByRemoveIsFalseAndIdPaymentSystemAndFeesIdSchoolYear(idPaymentSystem, idSchoolYear);
        log.info("execution of the method:findByRemoveIsFalseAndIdPaymentSystemAndFeesIdSchoolYear(Long, Long) result : " +byRemoveIsFalseAndIdPaymentSystemAndFeesIdSchoolYear) ;
        return byRemoveIsFalseAndIdPaymentSystemAndFeesIdSchoolYear;
    }
}
