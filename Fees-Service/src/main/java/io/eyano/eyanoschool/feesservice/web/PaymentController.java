package io.eyano.eyanoschool.feesservice.web;

import io.eyano.eyanoschool.feesservice.dtos.PaymentDto;
import io.eyano.eyanoschool.feesservice.entitiesService.PaymentService;
import io.eyano.eyanoschool.feesservice.exceptions.IdIsNullException;
import io.eyano.eyanoschool.feesservice.exceptions.IdNotFoundException;
import io.eyano.eyanoschool.feesservice.exceptions.IdNotNullException;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
    * PaymentController is class exposes the services of the payment in the form of Restful services
    * @author : Pascal Tshingila
    * @since : 02/02/2021
    * @version : 1.0
 */
@RestController
@AllArgsConstructor
@Slf4j
@Tag(name = "PaymentController", description = "Controller for managing payments")
@RequestMapping("/api/fees-service")
public class PaymentController {
    PaymentService paymentService;
    /**
        * save is a method that allows you to save a paymentDto entity in the database
        * @param paymentDto : the payment to save
        * @throws IdNotNullException : if the paymentDto id is not null
        * @return the payment saved
     */
    @PostMapping("/payments")
    @ResponseStatus(HttpStatus.CREATED)
    @Tag(name = "save", description = "Method to save a payment")
    public PaymentDto save(@RequestBody @Valid PaymentDto paymentDto) throws IdNotNullException {
        log.info("execution of the method : save(PaymentDto) parameter : "+paymentDto);
        if(paymentDto.getId()!=null){
            throw new IdNotNullException("The id of the payment must be null");
        }
        paymentDto.setRemove(false);
        PaymentDto result = paymentService.save(paymentDto);
        log.info("End of the method : save(PaymentDto) result : "+result);
        return result;
    }
    /**
        * update is a method that allows you to update a paymentDto entity in the database
        * @param paymentDto : the paymentDto to update
        * @return the payment updated
        * @throws IdNotFoundException : if the paymentDto id is not found
        * @throws IdIsNullException : if the paymentDto id is null
     *
     */
    @PutMapping("/payments")
    @Tag(name = "update", description = "Method to update a payment")
    public PaymentDto update(@RequestBody @Valid PaymentDto paymentDto) throws IdNotFoundException, IdIsNullException {
        log.info("execution of the method : update(PaymentDto) parameter : "+paymentDto);
        if(paymentDto.getId()==null){
            throw new IdIsNullException("The id of the payment is null");
        }
        PaymentDto result = paymentService.update(paymentDto);
        log.info("End of the method : update(PaymentDto) result : "+result);
        return result;
    }
    /**
        * removeById is a method that allows you to remove a paymentDto entity in the database
        * @param id : the id of the paymentDto to remove
        * @return a map containing the result of the operation
        * @throws IdNotFoundException : if the paymentDto id is not found
     */
    @DeleteMapping("/payments/{id}")
    @Tag(name = "removeById", description = "Method to remove a payment by id")
    public Map<String, Object> removeById(@PathVariable("id") Long id) throws IdNotFoundException {
        log.info("execution of the method : removeById(Long) parameter : "+id);
        Map<String,Object> result = new HashMap<>();
        PaymentDto paymentDto = paymentService.findById(id);
        boolean isRemove = paymentService.removeById(id);
        result.put("Deleted entity","Payment");
        result.put("Designation Fees",paymentDto.getFees().getDesignation());
        result.put("Id of the entity",paymentDto.getId());
        result.put("is removed",isRemove?"true":"false");
        log.info("End of the method : removeById(Long) result : "+result);
        return result;
    }

    /**
        * remove is a method that allows you to remove a paymentDto entity in the database
        * @param paymentDto : the paymentDto to remove
        * @return a map containing the result of the operation
        * @throws IdNotFoundException : if the paymentDto id is not found
     */
    @DeleteMapping("/payments")
    public Map<String, String> remove(@RequestBody PaymentDto paymentDto) throws IdNotFoundException {
        log.info("execution of the method : remove(PaymentDto) parameter : "+paymentDto);
        Map<String,String> result = new HashMap<>();
        boolean isRemove = paymentService.remove(paymentDto);
        result.put("Deleted entity","Payment");
        result.put("Designation Fees",paymentDto.getFees().getDesignation());
        result.put("Id of the entity",paymentDto.getId().toString());
        result.put("is removed",isRemove?"true":"false");
        log.info("End of the method : remove(PaymentDto) result : "+result);
        return result;
    }

    /**
        * restoreById is a method that allows you to restore a paymentDto entity in the database
        * @param id : the id of the paymentDto to restore
        * @return a map containing the result of the operation
     */
    @GetMapping("/payments/restore/{id}")
    @Tag(name = "restoreById", description = "Method to restore a payment by id")
    public Map<String,String> restoreById(@PathVariable Long id) throws IdNotFoundException {
        log.info("execution of the method : restoreById(Long) parameter : "+id);
        Map<String,String> result = new HashMap<>();
        PaymentDto paymentDto = paymentService.findById(id);
        boolean isRestore = paymentService.restore(id);
        result.put("Restored entity","Payment");
        result.put("Designation Fees",paymentDto.getFees().getDesignation());
        result.put("Id of the entity",paymentDto.getId().toString());
        result.put("is restored",isRestore?"true":"false");
        log.info("End of the method : restoreById(Long) result : "+result);
        return result;
    }
    /**
        * findByDate is a method that allows you to find a paymentDto entity by date in the database
        * @param date : the date of the paymentDto to find
        * @return a list of paymentDto
     */
    @GetMapping("/payments/findByDate/{date}")
    @Tag(name = "findByDate", description = "Method to find a payment by date")
    public List<PaymentDto> findByDate(@PathVariable String date){
        log.info("execution of the method : findByDate(String) parameter : "+date);
        LocalDate date1 = LocalDate.parse(date);
        List<PaymentDto> result = paymentService.findByDate(date1);
        log.info("End of the method : findByDate(String) result : "+result);
        return result;
    }
    /**
        * findByDate is a method that allows you to find a paymentDto entity per page by date in the database
        * @param date : the date of the paymentDto to find
        * @param page : the page number
        * @param size : the size of the page
        * @return a map containing the result of the operation
     */
    @GetMapping("/payments/date")
    @Tag(name = "findByDate", description = "Method to find a payment by date")
    public Map<String, Object> findByDate(
            @RequestParam String date,
            @RequestParam (defaultValue = "0") int page,
            @RequestParam (defaultValue = "5")int size){
        log.info("execution of the method : findByDate(String, int, int) parameter : "+date+" "+page+" "+size);
        LocalDate date1 = LocalDate.parse(date);
        Map<String,Object> result = paymentService.findByDate(date1, page, size);
        log.info("End of the method : findByDate(String, int, int) result : "+result);
        return result;
    }
    /**
        * findByIdPupilAndFeesIdSchoolYear is a method that allows you to find a paymentDto entity by pupil id and School year id in the database
        * @param idPupil : the id of the pupil
        * @param idSchoolYear : the id of the school year
        * @return a list of paymentDto
     */
    @GetMapping("/payments/findByIdPupilAndFeesIdSchoolYear")
    @Tag(name = "findByIdPupilAndFeesIdSchoolYear", description = "Method to find a payment by pupil id and school year id")
    public List<PaymentDto> findByIdPupilAndFeesIdSchoolYear(
            @RequestParam Long idPupil,
            @RequestParam Long idSchoolYear){
        log.info("execution of the method : findByIdPupilAndFeesIdSchoolYear(Long, Long) parameter : "+idPupil+" "+idSchoolYear);
        List<PaymentDto> result = paymentService.findByIdPupilAndFeesIdSchoolYear(idPupil, idSchoolYear);
        log.info("End of the method : findByIdPupilAndFeesIdSchoolYear(Long, Long) result : "+result);
        return result;
    }
    /**
        * findByIdUserAndFeesIdSchoolYear is a method that allows you to find a paymentDto entity by user id and School year id in the database
        * @param idUser : the id of the user
        * @param idSchoolYear : the id of the school year
        * @return a list of paymentDto
     */
    @GetMapping("/payments/findByIdUserAndFeesIdSchoolYear")
    @Tag(name = "findByIdUserAndFeesIdSchoolYear", description = "Method to find a payment by user id and school year id")
    public List<PaymentDto> findByIdUserAndFeesIdSchoolYear(
            @RequestParam Long idUser,
            @RequestParam Long idSchoolYear){
        log.info("execution of the method : findByIdUserAndFeesIdSchoolYear(Long, Long) parameter : "+idUser+" "+idSchoolYear);
        List<PaymentDto> result = paymentService.findByIdUserAndFeesIdSchoolYear(idUser, idSchoolYear);
        log.info("End of the method : findByIdUserAndFeesIdSchoolYear(Long, Long) result : "+result);
        return result;
    }
    /**
        * findByIdUserAndFeesIdSchoolYearPage is a method that allows you to find a paymentDto entity by user id and School year id in the database per page
        * @param idUser : the id of the user
        * @param idSchoolYear : the id of the school year
        * @param page : the page number
        * @param size : the size of the page
        * @return a map containing the result of the operation
     */
    @GetMapping("/payments/findByIdUserAndFeesIdSchoolYearPage")
    @Tag(name = "findByIdUserAndFeesIdSchoolYearPage", description = "Method to find a payment by user id and school year id per page")
    public Map<String,Object> findByIdUserAndFeesIdSchoolYearPage(
            @RequestParam Long idUser,
            @RequestParam Long idSchoolYear,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size){
        log.info("execution of the method : findByIdUserAndFeesIdSchoolYearPage(Long, Long, int, int) parameter : "+idUser+" "+idSchoolYear+" "+page+" "+size);
        Map<String,Object> result = paymentService.findByIdUserAndFeesIdSchoolYear(idUser, idSchoolYear, page, size);
        log.info("End of the method : findByIdUserAndFeesIdSchoolYearPage(Long, Long, int, int) result : "+result);
        return result;
    }
    /**
        * findByIdUserAndDate is a method that allows you to find a paymentDto entity by user id and date in the database
        * @param idUser : the id of the user
        * @param date : the date of the payment
        * @return a list of paymentDto
     */
    @GetMapping("/payments/findByIdUserAndDate")
    @Tag(name = "findByIdUserAndDate", description = "Method to find a payment by user id and date")
    public List<PaymentDto> findByIdUserAndDate(
            @RequestParam Long idUser,
            @RequestParam String date){
        log.info("execution of the method : findByIdUserAndDate(Long, String) parameter : "+idUser+" "+date);
        LocalDate date1 = LocalDate.parse(date);
        List<PaymentDto> result = paymentService.findByIdUserAndDate(idUser, date1);
        log.info("End of the method : findByIdUserAndDate(Long, String) result : "+result);
        return result;
    }
    /**
        * findByIdPaymentSystemAndFeesIdSchoolYear is a method that allows you to find a paymentDto entity by PaymentSystem id and School year id in the database
        * @param idPaymentSystem : the id of the PaymentSystem
        * @param idSchoolYear : the id of the school year
        * @return a list of paymentDto
     */
    @GetMapping("/payments/findByIdPaymentSystemAndFeesIdSchoolYear")
    public List<PaymentDto> findByIdPaymentSystemAndFeesIdSchoolYear(
            @RequestParam Long idPaymentSystem,
            @RequestParam Long idSchoolYear){
        log.info("execution of the method : findByIdPaymentSystemAndFeesIdSchoolYear(Long, Long) parameter : "+idPaymentSystem+" "+idSchoolYear);
        List<PaymentDto> result = paymentService.findByIdPaymentSystemAndFeesIdSchoolYear(idPaymentSystem, idSchoolYear);
        log.info("End of the method : findByIdPaymentSystemAndFeesIdSchoolYear(Long, Long) result : "+result);
        return result;
    }
    /**
        * findByIdPaymentSystemAndFeesIdSchoolYearPage is a method that allows you to find a paymentDto entity by PaymentSystem id and School year id in the database per page
        * @param idPaymentSystem : the id of the PaymentSystem
        * @param idSchoolYear : the id of the school year
        * @param page : the page number
        * @param size : the size of the page
        * @return a map containing the result of the operation
     */
    @GetMapping("/payments/findByIdPaymentSystemAndFeesIdSchoolYearPage")
    @Tag(name = "findByIdPaymentSystemAndFeesIdSchoolYearPage", description = "Method to find a payment by PaymentSystem id and school year id per page")
    public Map<String, Object> findByIdPaymentSystemAndFeesIdSchoolYearPage(
            @RequestParam Long idPaymentSystem,
            @RequestParam Long idSchoolYear,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size){
        log.info("execution of the method : findByIdPaymentSystemAndFeesIdSchoolYearPage(Long, Long, int, int) parameter : "+idPaymentSystem+" "+idSchoolYear+" "+page+" "+size);
        Map<String,Object> result = paymentService.findByIdPaymentSystemAndFeesIdSchoolYear(idPaymentSystem, idSchoolYear, page, size);
        log.info("End of the method : findByIdPaymentSystemAndFeesIdSchoolYearPage(Long, Long, int, int) result : "+result);
        return result;
    }
    /**
        * findAllByRemoveIsFalse is a method that allows you to find all paymentDto entities in the database
        * @return a list of paymentDto
     */
    @GetMapping("/payments")
    @Tag(name = "findAll", description = "Method to find all payments")
    public List<PaymentDto> findAllByRemoveIsFalse(){
        log.info("execution of the method : findAllByRemoveIsFalse()");
        List<PaymentDto> result = paymentService.findAllByRemoveIsFalse();
        log.info("End of the method : findAllByRemoveIsFalse() result : "+result);
        return result;
    }
    /**
        * findAllByRemoveIsFalsePage is a method that allows you to find all paymentDto entities in the database per page
        * @param page : the page number
        * @param size : the size of the page
        * @return a map containing the result of the operation
     */
    @GetMapping("/payments/page")
    @Tag(name = "findAllPage", description = "Method to find all payments per page")
    public Map<String, Object> findAllPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size){
        log.info("execution of the method : findAllPage(int, int) parameter : "+page+" "+size);
        Map<String,Object> result = paymentService.findAllPage(page, size);
        log.info("End of the method : findAllPage(int, int) result : "+result);
        return result;
    }

    /**
        * findAllByRemoveIsTrue is a method that allows you to find all paymentDto entities that are removed in the database
        * @return a list of paymentDto
     */
    @GetMapping("/payments/removed/{date}")
    @Tag(name = "findAllRemoved", description = "Method to find all removed payments")
    public List<PaymentDto> findByDateAndRemoveIsTrue(
            @PathVariable String date
    ){
        log.info("execution of the method : findByDateAndRemoveIsTrue(String date) parameter : "+date);
        LocalDate date1 = LocalDate.parse(date);
        List<PaymentDto> result = paymentService.findByDateAndRemoveIsTrue(date1);
        log.info("End of the method : findByDateAndRemoveIsTrue(String) result : "+result);
        return result;
    }


}
