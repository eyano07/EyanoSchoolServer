package io.eyano.eyanoschool.feesservice.web;

import io.eyano.eyanoschool.feesservice.dtos.SliceFeesDto;
import io.eyano.eyanoschool.feesservice.entitiesService.SliceFeesService;
import io.eyano.eyanoschool.feesservice.exceptions.IdIsNullException;
import io.eyano.eyanoschool.feesservice.exceptions.IdNotFoundException;
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
 * SliceFeesController is a class that manages the slices
 * @version : 1.0
 * @since : 2021-04-19
 * @author : Pascal Tshingila

 */

@RestController
@AllArgsConstructor
@Slf4j
@Tag(name = "SliceFeesController",description = "Controller that manages the slices")
@RequestMapping("/api/fees-service")
public class SliceFeesController {
    SliceFeesService sliceFeesService;

    /**
     * This method saves an entity in the database
     * @param sliceFeesDto : the entity to save
     * @return the entity saved
     * @throws IdNotNullException : if the id of the entity is not null
     */
    @PostMapping("/sliceFees")
    @Tag(name = "SliceFeesController : Register an entity")
    public SliceFeesDto save(@RequestBody @Valid SliceFeesDto sliceFeesDto) throws IdNotNullException {
        log.info("execution of the method:save(SliceFeesDto) parameter : " +sliceFeesDto) ;
        if(sliceFeesDto.getId() != null){
            throw new IdNotNullException("The id of the payment must be null");
        }
        sliceFeesDto.setRemove(false);
        SliceFeesDto result = sliceFeesService.save(sliceFeesDto);
        log.info("end of execution of the method:save(SliceFeesDto) parameter : " +result) ;
        return result;
    }

    /**
     * This method updates an entity in the database
     * @param sliceFeesDto : the entity to update
     * @return the entity updated
     * @throws IdNotFoundException : if the entity is not found
     * @throws IdIsNullException : if the entity is null
     */
    @PutMapping("/sliceFees")
    @Tag(name = "SliceFeesController : Modify an entity")
    public SliceFeesDto update(@RequestBody @Valid SliceFeesDto sliceFeesDto) throws IdNotFoundException, IdIsNullException {
        log.info("execution of the method:update(SliceFeesDto) parameter : " +sliceFeesDto) ;
        if(sliceFeesDto.getId() == null){
            throw new IdIsNullException("The id of the payment is null");
        }
        sliceFeesDto.setRemove(false);
        SliceFeesDto result = sliceFeesService.update(sliceFeesDto);
        log.info("end of execution of the method:update(SliceFeesDto) parameter : " +result) ;
        return result;
    }

    /**
     * This method deletes an entity in the database
     * @param id : the entity to delete
     * @return the entity deleted
     * @throws IdNotFoundException : if the entity is not found
     */
    @DeleteMapping("/sliceFees/{id}")
    @Tag(name = "SliceFeesController : Delete an entity using its identifier (ID)")
    public Map<String,String> removeById(@PathVariable("id") Long id) throws IdNotFoundException {
        log.info("execution of the method:removeById(Long) parameter : " +id) ;
        Map<String,String> map = new HashMap<>();
        SliceFeesDto sliceFeesDto = sliceFeesService.findByIdAndRemoveIsFalse(id);
        boolean result = sliceFeesService.removeById(id);
        map.put("Deleted entity","SliceFees");
        map.put("Designation of the entity",sliceFeesDto.getDesignation());
        map.put("Id of the entity",sliceFeesDto.getId().toString());
        map.put("is remove", String.valueOf(result));
        log.info("end of execution of the method:removeById(FeesDto) parameter : " +result) ;
        return map;
    }

    /**
     * This method deletes an entity in the database
     * @param sliceFeesDto : the entity to delete
     * @return the entity deleted
     * @throws IdNotFoundException : if the entity is not found
     */
    @DeleteMapping("/sliceFees")
    @Tag(name = "SliceFeesController : Delete an entity")
    public Map<String,String> remove(@RequestBody @Valid SliceFeesDto sliceFeesDto) throws IdNotFoundException {
        log.info("execution of the method:remove(SliceFeesDto) parameter : " +sliceFeesDto) ;
        Map<String,String> map = new HashMap<>();
        boolean result = sliceFeesService.remove(sliceFeesDto);
        map.put("Deleted entity","SliceFees");
        map.put("Designation of the entity",sliceFeesDto.getDesignation());
        map.put("Id of the entity",sliceFeesDto.getId().toString());
        map.put("is remove", String.valueOf(result));
        log.info("end of execution of the method:remove(FeesDto) parameter : " +result) ;
        return map;
    }
    /**
     * This method lists all entities in the database
     * @return the list of entities
     */
    @GetMapping("/sliceFees")
    @Tag(name = "SliceFeesController : List all entities")
    public List<SliceFeesDto> findAll(){
        log.info("execution of the method:findAll() ") ;
        List<SliceFeesDto> sliceFeesDtoList = sliceFeesService.findAll();
        log.info("end of execution of the method:findAll() ") ;
        return sliceFeesDtoList;
    }
    /**
     * This method lists all entities in the database
     * @return the list of entities
     * @param tag : the designation of the entity
     */
    @GetMapping("/sliceFees/designationContains/delete")
    @Tag(name = "SliceFeesController : Search for entities using their designation (Delete)")
    public List<SliceFeesDto> findSliceFeesByDesignationContainsAndRemoveIsTrue(@RequestParam(defaultValue = "") String tag){
        log.info("execution of the method:findSliceFeesByDesignationContainsAndRemoveIsTrue(String) ") ;
        List<SliceFeesDto> sliceFeesDtoList = sliceFeesService.findSliceFeesByDesignationContainsAndRemoveIsTrue(tag);
        log.info("end of execution of the method:findSliceFeesByDesignationContainsAndRemoveIsTrue(String) ") ;
        return sliceFeesDtoList;
    }

    /**
     * This method lists all entities in the database
     * @return the list of entities
     * @param tag : the designation of the entity
     */
    @GetMapping("/sliceFees/designationContains")
    @Tag(name = "SliceFeesController : Search for entities using their designation")
    public List<SliceFeesDto> findSliceFeesByDesignationContainsAndRemoveIsFalse(@RequestParam(defaultValue = "") String tag){
        log.info("execution of the method:findSliceFeesByDesignationContainsAndRemoveIsFalse(String) ") ;
        List<SliceFeesDto> sliceFeesDtoList = sliceFeesService.findSliceFeesByDesignationContainsAndRemoveIsFalse(tag);
        log.info("end of execution of the method:findSliceFeesByDesignationContainsAndRemoveIsFalse(String) ") ;
        return sliceFeesDtoList;
    }

    /**
     * This method lists all entities in the database
     * @return the entities
     * @param tag : the designation of the entity
     * @param page : the page number
     * @param Size : the number of entities per page
     */
    @GetMapping("/sliceFees/designationContainsPage")
    @Tag(name = "SliceFeesController : Search for entities using their designation Page")
    public Map<String,Object> findSliceFeesByDesignationContainsAndRemoveIsFalsePage(
            @RequestParam(defaultValue = "") String tag,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int Size
    ){
        log.info("execution of the method:findSliceFeesByDesignationContainsAndRemoveIsFalsePage(String,int,int)") ;
        Map<String,Object> sliceFeesDtoPage = sliceFeesService.findSliceFeesByDesignationContainsAndRemoveIsFalsePage(tag,page,Size);
        log.info("end of execution of the method:findSliceFeesByDesignationContainsAndRemoveIsFalsePage(String,int,int)") ;
        return sliceFeesDtoPage;
    }
    /**
     * This method lists all entities deleted in the database
     * @return the entities
     * @param tag : the designation of the entity
     * @param page : the page number
     * @param Size : the number of entities per page
     */
    @GetMapping("/sliceFees/designationContainsPage/delete")
    @Tag(name = "SliceFeesController : Search for entities using their designation (Delete) Page")
    public Map<String,Object> findSliceFeesByDesignationContainsAndRemoveIsTruePage(
            @RequestParam(defaultValue = "") String tag,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int Size
    ){
        log.info("execution of the method:findSliceFeesByDesignationContainsAndRemoveIsDeletePage(String,int,int)") ;
        Map<String,Object> sliceFeesDtoPage = sliceFeesService.findSliceFeesByDesignationContainsAndRemoveIsTruePage(tag,page,Size);
        log.info("end of execution of the method:findSliceFeesByDesignationContainsAndRemoveIsTruePage(String,int,int)") ;
        return sliceFeesDtoPage;
    }
    /**
     * This method lists all entities deleted in the database
     * @return the entities
     */
    @GetMapping(value="/sliceFees/delete")
    @Tag(name = "SliceFeesController : List all deleted entities")
    public List<SliceFeesDto> findAllDelete(){
        log.info("execution of the method:findAllDelete() ") ;
        List<SliceFeesDto> sliceFeesDtoList = sliceFeesService.findAllDelete();
        log.info("end of execution of the method:findAllDelete() ") ;
        return sliceFeesDtoList;
    }

    /**
     * This method finds an entity in the database
     * @param id : the id of the entity to find
     * @return the entity found
     * @throws IdNotFoundException : if the entity is not found
     */

    @GetMapping(value="/sliceFees/{id}")
    @Tag(name = "SliceFeesController : Find an entity using its identifier (ID)")
    public SliceFeesDto findById(@PathVariable("id") Long id) throws IdNotFoundException {
        log.info("execution of the method:findById(Long) ") ;
        SliceFeesDto sliceFeesDto = sliceFeesService.findById(id);
        log.info("end of execution of the method:findById(Long) ") ;
        return sliceFeesDto;
    }

    /**
     * This method restores an entity in the database
     * @param id : the id of the entity to restore
     * @return Map<String,String> details of the entity restored
     * @throws IdNotFoundException : if the entity is not found
     */
    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping(value="/sliceFees/restore/{id}")
    @Tag(name = "SliceFeesController : Restore the entity using its identifier (ID)")
    public Map<String,String> restore(@PathVariable("id") Long id) throws IdNotFoundException {
        log.info("execution of the method:restore(Long) ") ;
        Map<String,String> map = new HashMap<>();
        SliceFeesDto sliceFeesDto = sliceFeesService.findByIdAndRemoveIsTrue(id);
        boolean result = sliceFeesService.restore(id);
        map.put("Deleted entity","SliceFees");
        map.put("Designation of the entity",sliceFeesDto.getDesignation());
        map.put("Id of the entity",sliceFeesDto.getId().toString());
        map.put("is remove", String.valueOf(result));
        log.info("end of execution of the method:restore(Long) ") ;
        return map;
    }

    /**
     * This method checks if an entity exists in the database
     * @param id : the id of the entity to find
     * @return Map<String,String> details of the entity found
     * @throws IdNotFoundException : if the entity is not found
     */
    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping(value="/sliceFees/isExist/{id}")
    @Tag(name = "SliceFeesController : Check if the entity exists in the database (ID)")
    public Map<String,String> isExist(@PathVariable("id") Long id) throws IdNotFoundException {
        log.info("execution of the method:isExist(Long) ") ;
        Map<String,String> map = new HashMap<>();
        boolean result = sliceFeesService.isExist(id);
        try {
            sliceFeesService.findById(id);
            map.put("Entity","SliceFees");
            map.put("Id of the entity", String.valueOf(id));
            map.put("is exist", String.valueOf(result));
            map.put("is deleted", "false");
        }catch (IdNotFoundException e){
            map.put("Entity","SliceFees");
            map.put("Id of the entity", String.valueOf(id));
            map.put("is exist", String.valueOf(result));
            map.put("is deleted", "true");
        }

        log.info("end of execution of the method:isExist(Long) ") ;
        return map;
    }

}
