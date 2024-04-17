package io.eyano.eyanoschool.feesservice.web;

import io.eyano.eyanoschool.feesservice.dtos.FeesAllocationDto;
import io.eyano.eyanoschool.feesservice.entities.FeesAllocation;
import io.eyano.eyanoschool.feesservice.exceptions.IdIsNullException;
import io.eyano.eyanoschool.feesservice.exceptions.IdNotFoundException;
import io.eyano.eyanoschool.feesservice.exceptions.IdNotNullException;
import io.eyano.eyanoschool.feesservice.entitiesService.FeesAllocationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * This class is a controller that manages the allocation of fees to a school year.
 * @version 1.0
 * @since 1.0
 * @see FeesAllocationService
 * @author : Pascal Tshingila
 */
@RestController
@AllArgsConstructor
@Slf4j
@Tag(name = "Controller that manages the allocation of fees to a school year.")
@RequestMapping("/api/fees-service")
public class FeesAllocationController {

    FeesAllocationService feesAllocationService;

    /**
     * This method saves an entity in the database
     * @param feesAllocationDto : the entity to save
     * @return the entity saved
     * @throws IdNotNullException : if the id of the entity is not null
     */
    @PostMapping("/feesAllocations")
    @Tag(name = "Fees Allocation", description = "Register an entity in the database.")
    public FeesAllocationDto save(@RequestBody @Valid FeesAllocationDto feesAllocationDto) throws IdNotNullException, IdIsNullException {
        log.info("execution of the method:save(FeesAllocationDto) parameter : "+feesAllocationDto);
        if(feesAllocationDto.getId() != null){
            throw new IdNotNullException("The id of the payment is not null");
        }
        feesAllocationDto.setRemove(false);
        FeesAllocationDto result = feesAllocationService.save(feesAllocationDto);
        log.info("end of execution of the method:save(FeesAllocationDto) parameter : "+result);
        return result;
    }
    /**
     * This method updates an entity in the database
     * @param feesAllocationDto : the entity to update
     * @return the entity updated
     * @throws IdNotFoundException : if the id of the entity is not found
     * @throws IdIsNullException : if the id of the entity is null
     */
    @PutMapping("/feesAllocations")
    @Tag(name = "Fees Allocation", description = "Update an entity in the database.")
    public FeesAllocationDto update(@RequestBody @Valid FeesAllocationDto feesAllocationDto) throws IdNotFoundException, IdIsNullException, IdNotNullException {
        log.info("execution of the method:update(FeesAllocationDto) parameter : "+feesAllocationDto);
        if(feesAllocationDto.getId() == null){
            throw new IdIsNullException("The id of the payment is null");
        }
        //todo: suppression getRemove
        FeesAllocationDto result = feesAllocationService.update(feesAllocationDto);
        log.info("end of execution of the method:update(FeesAllocationDto) parameter : "+result);
        return result;
    }
    /**
     * removeById is a method that allows you to delete a FeesAllocation entity from the database
     * @param id : the id of the entity to delete
     * @return a map containing the id of the entity deleted and the result of the operation
     * @throws IdNotFoundException : if the id of the entity is not found
     */
    @DeleteMapping("/feesAllocations/{id}")
    @Tag(name = "Fees Allocation", description = "Delete an entity using its identifier (ID)")
    public Map<String, String> removeById(@PathVariable("id") Long id) throws IdNotFoundException, IdIsNullException, IdNotNullException {
        log.info("execution of the method:removeById(Long) parameter : "+id);
        Map<String,String> map = new HashMap<>();
        FeesAllocationDto feesAllocationDto = feesAllocationService.findByIdAndRemoveIsFalse(id);
        boolean result = feesAllocationService.removeById(id);
        map.put("Deleted entity","FeesAllocation");
        map.put("Designation of the entity",feesAllocationDto.getDesignation());
        map.put("Id of the entity",feesAllocationDto.getId().toString());
        map.put("is remove", String.valueOf(result));
        log.info("end of execution of the method:removeById(Long) parameter : "+result);
        return map;
    }

    /**
     * remove is a method that allows you to delete a FeesAllocation entity from the database
     * @param feesAllocationDto : the entity to delete
     * @return a map containing the id of the entity deleted and the result of the operation
     * @throws IdNotFoundException : if the id of the entity is not found
     */
    @DeleteMapping("/feesAllocations")
    @Tag(name = "Fees Allocation", description = "Delete an entity")
    public Map<String, String> remove(@RequestBody @Valid FeesAllocationDto feesAllocationDto) throws IdNotFoundException, IdIsNullException, IdNotNullException {
        log.info("execution of the method:remove(FeesAllocationDto) parameter : "+feesAllocationDto);
        Map<String,String> map = new HashMap<>();
        FeesAllocationDto feesAllocationDto1 = feesAllocationService.findByIdAndRemoveIsFalse(feesAllocationDto.getId());
        boolean result = feesAllocationService.remove(feesAllocationDto);
        map.put("Deleted entity","FeesAllocation");
        map.put("Designation of the entity",feesAllocationDto1.getDesignation());
        map.put("Id of the entity",feesAllocationDto1.getId().toString());
        map.put("is remove", String.valueOf(result));
        log.info("end of execution of the method:remove(FeesAllocationDto) parameter : "+result);
        return map;
    }
    /**
     * restore is a method that allows you to restore a FeesAllocation entity in the database
     * @param id : the id of the entity to restore
     * @return a map containing the id of the entity restored and the result of the operation
     * @throws IdNotFoundException : if the id of the entity is not found
     */
    @GetMapping("/feesAllocations/restore/{id}")
    @Tag(name = "Fees Allocation", description = "Restore an entity using its identifier (ID)")
    public Map<String,String> restore(@PathVariable("id") Long id) throws IdNotFoundException, IdIsNullException, IdNotNullException {
        log.info("execution of the method:restore(Long) parameter : "+id);
        Map<String,String> map = new HashMap<>();
        FeesAllocationDto feesAllocationDto = feesAllocationService.findByIdAndRemoveIsTrue(id);
        boolean result = feesAllocationService.restore(id);
        map.put("Restored entity","FeesAllocation");
        map.put("Designation of the entity",feesAllocationDto.getDesignation());
        map.put("Id of the entity",feesAllocationDto.getId().toString());
        map.put("is restore", String.valueOf(result));
        log.info("end of execution of the method:restore(Long) parameter : "+result);
        return map;
    }
    /**
     * This method lists all entities in the database
     * @return the list of entities
     */
    @GetMapping("/feesAllocations")
    @Tag(name = "Fees Allocation", description = "List all entities")
    public List<FeesAllocationDto> findAll(){
        log.info("execution of the method:findAll()");
        List<FeesAllocationDto> result = feesAllocationService.findAll();
        log.info("end of execution of the method:findAll()");
        return result;
    }
    /**
     * This method lists all entities in the database
     * @param tag : Designation of the entity
     * @param idSchoolYear : the id of the school year
     * @return the list of entities
     */
    @GetMapping("/feesAllocations/findByDesignationAndIdSchoolYear")
    @Tag(name = "Fees Allocation", description = "List all entities by designation and school year")
    List<FeesAllocationDto> findByRemoveFalseAndDesignationContainsAndIdSchoolYear(
            @RequestParam(defaultValue = "") String tag,
            @RequestParam Long idSchoolYear) throws IdIsNullException {
        log.info("execution of the method:findByRemoveFalseAndDesignationContainsAndIdSchoolYear(String,Long) parameters : "+tag+" "+idSchoolYear);
        List<FeesAllocationDto> result = feesAllocationService.findByRemoveFalseAndDesignationContainsAndIdSchoolYear(tag, idSchoolYear);
        log.info("end of execution of the method:findByRemoveFalseAndDesignationContainsAndIdSchoolYear(String,Long) parameters : "+result);
        return result;
    }
    /**
     * This method lists all entities deleted in the database
     * @param tag : Designation of the entity
     * @param idSchoolYear : the id of the school year
     * @return the list of entities
     */
    @GetMapping("/feesAllocations/deleted/findByDesignationAndIdSchoolYear")
    @Tag(name = "Fees Allocation", description = "List all entities deleted by designation and school year")
    List<FeesAllocationDto> findByRemoveTrueAndDesignationContainsAndIdSchoolYear(
            @RequestParam(defaultValue = "") String tag,
            @RequestParam Long idSchoolYear) throws IdIsNullException {
        log.info("execution of the method:findByRemoveTrueAndDesignationContainsAndIdSchoolYear(String,Long) parameters : "+tag+" "+idSchoolYear);
        List<FeesAllocationDto> result = feesAllocationService.findByRemoveTrueAndDesignationContainsAndIdSchoolYear(tag, idSchoolYear);
        log.info("end of execution of the method:findByRemoveTrueAndDesignationContainsAndIdSchoolYear(String,Long) parameters : "+result);
        return result;
    }
    /**
     * This method lists all entities deleted in the database
     * @param tag : Designation of the entity
     * @param idSchoolYear : the id of the school year
     * @param idTypeFees : the id of the type fees
     * @return the list of entities
     */
    @GetMapping("/feesAllocations/deleted/findByDesignationAndIdSchoolYearAndTypeFeesId")
    @Tag(name = "Fees Allocation", description = "List all entities deleted by designation, school year and type fees")
    List<FeesAllocation> findByRemoveTrueAndDesignationContainsAndIdSchoolYearAndTypeFeesId(
            @RequestParam(defaultValue = "") String tag,
            @RequestParam Long idSchoolYear,
            @RequestParam Long idTypeFees){
        log.info("execution of the method:findByRemoveTrueAndDesignationContainsAndIdSchoolYearAndTypeFeesId(String,Long,Long) parameters : "+tag+" "+idSchoolYear+" "+idTypeFees);
        List<FeesAllocation> result = feesAllocationService.findByRemoveTrueAndDesignationContainsAndIdSchoolYearAndTypeFeesId(tag, idSchoolYear, idTypeFees);
        log.info("end of execution of the method:findByRemoveTrueAndDesignationContainsAndIdSchoolYearAndTypeFeesId(String,Long,Long) parameters : "+result);
        return result;
    }
    /**
     * This method lists all entities in the database
     * @param tag : Designation of the entity
     * @param idSchoolYear : the id of the school year
     * @param idTypeFees : the id of the type fees
     * @return the list of entities
     */
    @GetMapping("/feesAllocations/findByDesignationAndIdSchoolYearAndTypeFeesId")
    @Tag(name = "Fees Allocation", description = "List all entities by designation, school year and type fees")
    List<FeesAllocationDto> findByRemoveFalseAndDesignationContainsAndIdSchoolYearAndTypeFeesId(
            @RequestParam(defaultValue = "") String tag,
            @RequestParam Long idSchoolYear,
            @RequestParam Long idTypeFees) throws IdIsNullException {
        log.info("execution of the method:findByRemoveFalseAndDesignationContainsAndIdSchoolYearAndTypeFeesId(String,Long,Long) parameters : "+tag+" "+idSchoolYear+" "+idTypeFees);
        List<FeesAllocationDto> result = feesAllocationService.findByRemoveFalseAndDesignationContainsAndIdSchoolYearAndTypeFeesId(tag, idSchoolYear, idTypeFees);
        log.info("end of execution of the method:findByRemoveFalseAndDesignationContainsAndIdSchoolYearAndTypeFeesId(String,Long,Long) parameters : "+result);
        return result;
    }



}

