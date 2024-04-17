package io.eyano.eyanoschool.feesservice.web;

import io.eyano.eyanoschool.feesservice.dtos.FeesDto;
import io.eyano.eyanoschool.feesservice.entitiesService.FeesService;
import io.eyano.eyanoschool.feesservice.exceptions.IdIsNullException;
import io.eyano.eyanoschool.feesservice.exceptions.IdNotFoundException;
import io.eyano.eyanoschool.feesservice.exceptions.IdNotFoundParamException;
import io.eyano.eyanoschool.feesservice.exceptions.IdNotNullException;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
    * FeesController is a class that exposes the services of the Fees in the form of Restful Web Services
    * @author : Pascal Tshingila
    * @since : 02/02/2021
    * @version : 1.0
    * @see FeesService

 */
@RestController
@AllArgsConstructor
@Slf4j
@Tag(name = "FeesController",description = "Controller that manages the Fees")
@RequestMapping("/api/fees-service")
public class FeesController {
    FeesService feesService;

    /**
        * save is a method that allows you to save a FeesDto entity in the database
        * @param feesDto : the entity to save
        * @return FeesDto : the entity saved
        * @throws IdNotNullException : if the paymentDto id is not null
        * @throws IdNotFoundException : if the entity is not found
     */
    @PostMapping("/fees")
    @Tag(name = "FeesController : Register an entity")
    public FeesDto save(@RequestBody @Valid FeesDto feesDto) throws IdNotNullException, IdNotFoundException {
        log.info("execution of the method:save(FeesDto) parameter : " +feesDto) ;
        if(feesDto.getId() != null){
            throw new IdNotNullException("The id of the payment must be null");
        }
        feesDto.setRemove(false);
        FeesDto result = feesService.save(feesDto);
        log.info("end of execution of the method:save(FeesDto) result : " +result) ;
        return result;
    }
    /**
        * update is a method that allows you to update a FeesDto entity in the database
        * @param feesDto : the entity to update
        * @return FeesDto : the entity updated
        * @throws IdIsNullException : if the paymentDto id is null
        * @throws IdNotFoundException : if the entity is not found
     */
    @PutMapping("/fees")
    @Tag(name = "FeesController : Modify an entity")
    public FeesDto update(@RequestBody @Valid FeesDto feesDto) throws IdIsNullException, IdNotFoundException {
        log.info("execution of the method:update(FeesDto) parameter : " +feesDto) ;
        if(feesDto.getId() == null){
            throw new IdIsNullException("The id of the payment is null");
        }

        feesDto.setRemove(false);
        FeesDto result = feesService.update(feesDto);
        log.info("end of execution of the method:update(FeesDto) parameter : " +result) ;
        return result;
    }
    /**
        * removeById is a method that allows you to delete a FeesDto entity in the database
        * @param id : the identifier of the entity to delete
        * @return Map<String,String> : detail on the entity deleted
        * @throws IdNotFoundException : if the entity is not found
     */
    @DeleteMapping("/fees/{id}")
    @Tag(name = "FeesController : Delete an entity using its identifier (ID)")
    public Map<String,String> removeById(@PathVariable("id") Long id) throws IdNotFoundException, IdIsNullException {
        log.info("execution of the method:removeById(Long) parameter : " +id) ;
        Map<String,String> map = new HashMap<>();
        FeesDto feesDto = feesService.findById(id);
        boolean result = feesService.removeById(id);
        map.put("Deleted entity","Fees");
        map.put("Designation of the entity",feesDto.getDesignation());
        map.put("Id of the entity",feesDto.getId().toString());
        map.put("is remove", String.valueOf(result));
        log.info("end of execution of the method:removeById(FeesDto) parameter : " +result) ;
        return map;
    }
    /**
        * remove is a method that allows you to delete a FeesDto entity in the database
        * @param feesDto : the entity to delete
        * @return Map<String,String> : detail on the entity deleted
        * @throws IdNotFoundException : if the entity is not found
     */
    @DeleteMapping("/fees")
    @Tag(name = "FeesController : Delete an entity")
    public Map<String,String> remove(@RequestBody @Valid FeesDto feesDto) throws IdNotFoundException {
        log.info("execution of the method:remove(FeesDto) parameter : " +feesDto) ;
        Map<String,String> map = new HashMap<>();
        boolean result = feesService.remove(feesDto);
        map.put("Deleted entity","Fees");
        map.put("Designation of the entity",feesDto.getDesignation());
        map.put("Id of the entity",feesDto.getId().toString());
        map.put("is remove", String.valueOf(result));
        log.info("end of execution of the method:remove(FeesDto) parameter : " +result) ;
        return map;
    }

    /**
        * findAll is a method that allows you to list all FeesDto entities in the database
        * @return List<FeesDto> : the list of entities
     */
    @GetMapping("/fees")
    @Tag(name = "FeesController : List all entities")
    public List<FeesDto> findAll(){
        log.info("execution of the method:findAll() ") ;
        List<FeesDto> feesDtoList = feesService.findAll();
        log.info("end of execution of the method:findAll() ") ;
        return feesDtoList;
    }
    /**
        * restoreById is a method that allows you to restore a FeesDto entity in the database
        * @param id : the identifier of the entity to restore
        * @return Map<String,String> : detail on the entity restored
        * @throws IdNotFoundException : if the entity is not found
     */
    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("/fees/restore/{id}")
    @Tag(name = "FeesController : Restore an entity using its identifier (ID)")
    public Map<String, String> restoreById(@PathVariable("id") Long id) throws IdNotFoundException{
        log.info("execution of the method:restoreById(Long) parameter : " +id) ;
        Map<String,String> map = new HashMap<>();
        FeesDto feesDto = feesService.findByIdRemoveIsTrue(id);
        boolean result = feesService.restore(id);
        map.put("Restored entity","Fees");
        map.put("Designation of the entity",feesDto.getDesignation());
        map.put("Id of the entity",feesDto.getId().toString());
        map.put("is remove", String.valueOf(result));
        log.info("end of execution of the method:restoreById(FeesDto) parameter : " +result) ;
        return map;
    }
    /**
        * isExist is a method that allows you to check if a FeesDto entity exists in the database
        * @param id : the identifier of the entity to check
        * @return Map<String,String> : detail on the entity checked
        * @throws IdNotFoundException : if the entity is not found
     */
    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("/fees/isExist/{id}")
    @Tag(name = "FeesController : Check if an entity exists using its identifier (ID)")
    public Map<String, String> isExist(@PathVariable("id") Long id) throws IdNotFoundException{
        log.info("execution of the method:isExist(Long) parameter : " +id) ;
        Map<String,String> map = new HashMap<>();
        boolean result = feesService.isExist(id);
        try {
            FeesDto feesDto = feesService.findById(id);
            map.put("Entity","Fees");
            map.put("Designation of the entity",feesDto.getDesignation());
            map.put("Id of the entity", String.valueOf(id));
            map.put("is exist", String.valueOf(result));
            map.put("is deleted", "false");
        }catch (IdNotFoundException e){
            map.put("Entity","Fees");
            map.put("Id of the entity", String.valueOf(id));
            map.put("is exist", String.valueOf(result));
            map.put("is deleted", "true");
        }
        log.info("end of execution of the method:isExist(FeesDto) parameter : " +result) ;
        return map;
    }
    /**
        * isRemove is a method that allows you to check if a FeesDto entity is deleted in the database
        * @param id : the identifier of the entity to check
        * @return Map<String,String> : detail on the entity checked
        * @throws IdNotFoundException : if the entity is not found
     */
    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("/fees/isRemove/{id}")
    @Tag(name = "FeesController : Check if an entity is deleted using its identifier (ID)")
    public  Map<String, String> isRemove(@PathVariable("id") Long id) throws IdNotFoundException{
        log.info("execution of the method:isRemove(Long) parameter : " +id) ;
        Map<String,String> map = new HashMap<>();
        boolean result = feesService.isRemove(id);
        try {
            FeesDto feesDto = feesService.findById(id);
            map.put("Entity","Fees");
            map.put("Designation of the entity",feesDto.getDesignation());
            map.put("Id of the entity", String.valueOf(id));
            map.put("is deleted", "false");
        }catch (IdNotFoundException e){
            map.put("Entity","Fees");
            map.put("Id of the entity", String.valueOf(id));
            map.put("is remove", String.valueOf(result));
            map.put("is deleted", "true");
        }
        log.info("end of execution of the method:isRemove(FeesDto) parameter : " +result) ;
        return map;
    }
    /**
        * findById is a method that allows you to find a FeesDto entity in the database
        * @param id : the identifier of the entity to find
        * @return FeesDto : the entity found
        * @throws IdNotFoundException : if the entity is not found
     */
    @GetMapping("/fees/{id}")
    @Tag(name = "FeesController : Find an entity using its identifier (ID)")
    public FeesDto findById(@PathVariable("id") Long id) throws IdNotFoundException {
        log.info("execution of the method:findById(Long) ") ;
        FeesDto feesDto = feesService.findById(id);
        log.info("end of execution of the method:findById(Long) ") ;
        return feesDto;
    }
    /**
        * findByIdAndRemoveIsTrue is a method that allows you to find a FeesDto entity in the database
        * @param id : the identifier of the entity to find
        * @return FeesDto : the entity found
        * @throws IdNotFoundException : if the entity is not found
     */
    @GetMapping("/fees/removed/{id}")
    @Tag(name = "FeesController : Find an entity using its identifier (ID) and is deleted")
    public FeesDto findByIdAndRemoveIsTrue(@PathVariable("id")Long id) throws IdNotFoundException {
        log.info("execution of the method:findByIdAndRemoveIsTrue(Long) ") ;
        FeesDto feesDto = feesService.findByIdRemoveIsTrue(id);
        log.info("end of execution of the method:findByIdAndRemoveIsTrue(Long) ") ;
        return feesDto;
    }
    /**
        * findByIdClassFessAndIdSchoolYear is a method that allows you to find a FeesDto entity in the database
        * @param idClass : the identifier of the class
        * @param idSchoolYear : the identifier of the school year
        * @return List<FeesDto> : the list of entities found
        * @throws IdNotFoundParamException : if the entity is not found
     */
    @GetMapping("/fees/idClassAndIdSchoolYear")
    @Tag(name = "FeesController : Find all entities using the identifier of the class and the identifier of the school year")
    public List<FeesDto>  findByIdClassFessAndIdSchoolYear(
            @RequestParam long idClass,
            @RequestParam long idSchoolYear) throws IdNotFoundParamException {
        log.info("execution of the method:findByIdClassFessAndIdSchoolYear(long,long) ") ;
        List<FeesDto> feesDtoList = feesService.findByIdClassFessAndIdSchoolYear(idClass, idSchoolYear);
        log.info("end of execution of the method:findByIdClassFessAndIdSchoolYear(long,long) ") ;
        return feesDtoList;
    }
    /**
        * findByIdSchoolYearAndDesignationContains is a method that allows you to find a FeesDto entity in the database
        * @param idClass : the identifier of the class
        * @param idSchoolYear : the identifier of the school year
        * @param idSliceFees : the identifier of the slice fees
        * @return List<FeesDto> : the list of entities found
        * @throws IdNotFoundParamException : if the entity is not found
     */
    @GetMapping("/fees/findByIdClassFessAndIdSchoolYearAndSliceFeesId")
    @Tag(name = "FeesController : Find all entities using the identifier of the  id class and the school year and id slice of the entity")
    public  List<FeesDto>  findByIdClassFessAndIdSchoolYearAndSliceFeesId(
            @RequestParam long idClass,
            @RequestParam long idSchoolYear,
            @RequestParam long idSliceFees) throws IdNotFoundParamException {
        log.info("execution of the method:findByIdClassFessAndIdSchoolYearAndSliceFeesId(long,long,long) ") ;
        List<FeesDto> feesDtoList = feesService.findByIdClassFessAndIdSchoolYearAndSliceFeesId(idClass, idSchoolYear, idSliceFees);
        log.info("end of execution of the method:findByIdClassFessAndIdSchoolYearAndSliceFeesId(long,long,long) ") ;
        return feesDtoList;
    }
    /**
        * findAllPage is a method that allows you to list all FeesDto entities per page in the database
        * @param page : the page number
        * @param size : the size of the page
        * @param tag : the designation of the entity
        * @return Map<String,Object> : the list of entities per page
        *
     */
    @GetMapping("/fees/findAllPage")
    @Tag(name = "FeesController : List all entities per page")
    public Map<String, Object> findFeesByRemoveIsFalseAndDesignationContains(
            @RequestParam(defaultValue = "") String tag,
            @RequestParam (defaultValue = "0") int page,
            @RequestParam (defaultValue = "5")int size) {
        log.info("execution of the method:findAllPage(String,int,int) ") ;
        Map<String,Object> feesPage = feesService.findFeesByRemoveIsFalseAndDesignationContains(tag,page,size);
        log.info("end of execution of the method:findAllPage(String,int,int) ") ;
        return feesPage;
    }
    /**
        * findAllPage is a method that allows you to list all FeesDto entities per page in the database
        * @param page : the page number
        * @param size : the size of the page
        * @param tag : the designation of the entity
        * @return Map<String,Object> : the list of entities per page
        *
     */
    @GetMapping("/fees/removed/findAllPage")
    @Tag(name = "FeesController : List all deleted entities per page")
    public Map<String,Object> findFeesByRemoveIsTrueAndDesignationContains(
            @RequestParam(defaultValue = "") String tag,
            @RequestParam (defaultValue = "0") int page,
            @RequestParam (defaultValue = "5")int size) {
        log.info("execution of the method:findAllPage(String,int,int) ") ;
        Map<String,Object> feesPage = feesService.findFeesByRemoveIsTrueAndDesignationContains(tag,page,size);
        log.info("end of execution of the method:findAllPage(String,int,int) ") ;
        return feesPage;
    }
    /**
        * findByIdSchoolYearAndRemoveIsFalseAndDesignationContains is a method that allows you to find a FeesDto entity in the database
        * @param idSchoolYear : the identifier of the school year
        * @param designation : the designation of the entity
        * @return List<FeesDto> : the list of entities found
     */
    @GetMapping("/fees/idSchoolYearAndDesignationContains")
    @Tag(name = "FeesController : Find all entities using the identifier of the school year and the designation of the entity")
    public List<FeesDto> findByIdSchoolYearAndRemoveIsFalseAndDesignationContains(
            @RequestParam Long idSchoolYear,
            @RequestParam (defaultValue = "")String designation) {
        log.info("execution of the method:findByIdSchoolYearAndRemoveIsFalseAndDesignationContains(Long,String) ") ;
        List<FeesDto> feesDtoList = feesService.findByIdSchoolYearAndRemoveIsFalseAndDesignationContains(idSchoolYear, designation);
        log.info("end of execution of the method:findByIdSchoolYearAndRemoveIsFalseAndDesignationContains(Long,String) ") ;
        return feesDtoList;
    }
    /**
        * findByIdSchoolYearAndRemoveIsFalseAndDesignationContainsPage is a method that allows you to find a FeesDto entity in the database
        * @param idSchoolYear : the identifier of the school year
        * @param designation : the designation of the entity
        * @param page : the page number
        * @param size : the size of the page
        * @return Map<String,Object> : the list of entities per page
        *
     */
    @GetMapping("/fees/idSchoolYearAndDesignationContainsPage")
    @Tag(name = "FeesController : Find all entities using the identifier of the school year and the designation of the entity per page")
    public Map<String,Object> findByIdSchoolYearAndRemoveIsFalseAndDesignationContainsPage(
            @RequestParam Long idSchoolYear,
            @RequestParam (defaultValue = "")String designation,
            @RequestParam (defaultValue = "0") int page,
            @RequestParam (defaultValue = "5")int size) {
        log.info("execution of the method:findByIdSchoolYearAndRemoveIsFalseAndDesignationContainsPage(Long,String,int,int) ") ;
        Map<String,Object> feesPage = feesService.findByIdSchoolYearAndRemoveIsFalseAndDesignationContains(idSchoolYear, designation, page, size);
        log.info("end of execution of the method:findByIdSchoolYearAndRemoveIsFalseAndDesignationContainsPage(Long,String,int,int) ") ;
        return feesPage;
    }
    /**
       * findByIdSchoolYearAndTypeFeesIdAndRemoveIsFalseAndIdClassFess is a method that allows you to find a FeesDto entity in the database
       * @param idClass : the identifier of the class
       * @param idSchoolYear : the identifier of the school year
       * @param idTypeFees : the identifier of the type fees
       * @return List<FeesDto> : the list of entities found
     */
    @GetMapping("/fees/idSchoolYearAndTypeFeesIdAndIdClassFess")
    @Tag(name = "FeesController : Find all entities using the identifier of the school year and the type fees and the class")
    public List<FeesDto> findByIdSchoolYearAndTypeFeesIdAndRemoveIsFalseAndIdClassFess(
            @RequestParam Long idClass,
            @RequestParam Long idSchoolYear,
            @RequestParam Long idTypeFees) {
        log.info("execution of the method:findByIdSchoolYearAndTypeFeesIdAndRemoveIsFalseAndIdClassFess(Long,Long,Long) ") ;
        List<FeesDto> feesDtoList = feesService.findByIdSchoolYearAndTypeFeesIdAndRemoveIsFalseAndIdClassFess(idClass, idSchoolYear, idTypeFees);
        log.info("end of execution of the method:findByIdSchoolYearAndTypeFeesIdAndRemoveIsFalseAndIdClassFess(Long,Long,Long) ") ;
        return feesDtoList;
    }
    /**
        * findAllRemoveIsTrue is a method that allows you to list all FeesDto entities that are deleted in the database
        * @return List<FeesDto> : the list of entities
     */
    @GetMapping("/fees/removed/findAll")
    @Tag(name = "FeesController : List all deleted entities")
    public List<FeesDto> findAllRemoveIsTrue(){
        log.info("execution of the method:findAllRemoveIsTrue() ") ;
        List<FeesDto> feesDtoList = feesService.findAllRemoveIsTrue();
        log.info("end of execution of the method:findAllRemoveIsTrue() ") ;
        return feesDtoList;
    }

    /**
        * findByDesignationContainsAndRemoveIsFalseAndIdClassFessAndIdSchoolYear is a method that allows you to find a FeesDto entity in the database
        * @param designation : the designation of the entity
        * @param idClass : the identifier of the class
        * @param idSchoolYear : the identifier of the school year
        * @return List<FeesDto> : the list of entities found
     */
    @GetMapping("/fees/findByDesignationContainsAndAndIdClassFessAndIdSchoolYear")
    @Tag(name = "FeesController : Find all entities using the designation of the entity and the class and the school year")
    public List<FeesDto> findByDesignationContainsAndRemoveIsFalseAndIdClassFessAndIdSchoolYear(
            @RequestParam String designation,
            @RequestParam long idClass,
            @RequestParam long idSchoolYear) {
        log.info("execution of the method:findByDesignationContainsAndRemoveIsFalseAndIdClassFessAndIdSchoolYear(String,long,long) ") ;
        List<FeesDto> feesDtoList = feesService.findByDesignationContainsAndRemoveIsFalseAndIdClassFessAndIdSchoolYear(designation, idClass, idSchoolYear);
        log.info("end of execution of the method:findByDesignationContainsAndRemoveIsFalseAndIdClassFessAndIdSchoolYear(String,long,long) ") ;
        return feesDtoList;
    }
    /**
        * findByIdClassFessAndIdSchoolYearAndTypeFeesIdAndRemoveIsTrue is a method that allows you to find a FeesDto entity in the database
        * @param idClass : the identifier of the class
        * @param idSchoolYear : the identifier of the school year
        * @param idtypeFees : the identifier of the type fees
        * @return List<FeesDto> : the list of entities found
     */
    @GetMapping("/fees/removed/findByIdClassFessAndIdSchoolYearAndTypeFeesId")
    @Tag(name = "FeesController : Find all deleted entities using the identifier of the class and the school year and the type fees")
    public List<FeesDto> findByIdClassFessAndIdSchoolYearAndTypeFeesIdAndRemoveIsTrue(
            @RequestParam long idClass,
            @RequestParam long idSchoolYear,
            @RequestParam long idtypeFees) {
        log.info("execution of the method:findByIdClassFessAndIdSchoolYearAndTypeFeesIdAndRemoveIsTrue(long,long,long) ") ;
        List<FeesDto> feesDtoList = feesService.findByIdClassFessAndIdSchoolYearAndTypeFeesIdAndRemoveIsTrue(idClass, idSchoolYear, idtypeFees);
        log.info("end of execution of the method:findByIdClassFessAndIdSchoolYearAndTypeFeesIdAndRemoveIsTrue(long,long,long) ") ;
        return feesDtoList;
    }
}
