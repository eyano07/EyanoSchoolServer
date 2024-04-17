package io.eyano.eyanoschool.feesservice.web;

import io.eyano.eyanoschool.feesservice.dtos.TypeFeesDto;
import io.eyano.eyanoschool.feesservice.entitiesService.TypeFeesService;
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
 * TypeFeesController is a class that manages the fees type
 * @version : 1.0
 * @since : 2021-04-19
 * @author : Pascal Tshingila
 */
@RestController
@AllArgsConstructor
@Slf4j
@Tag(name = "TypeFeesController",description = "Controller that manages the fees type")
@RequestMapping("/api/fees-service")
public class TypeFeesController {
    TypeFeesService typeFeesService;

    /**
     * This method saves an entity in the database
     * @param typeFeesDto : the entity to save
     * @return the entity saved
     * @throws IdNotNullException : if the entity is not null
     */
    @PostMapping("/typeFees")
    @Tag(name = "TypeFeesController : Register an entity")
    public TypeFeesDto save(@RequestBody @Valid TypeFeesDto typeFeesDto) throws IdNotNullException {
        log.info("execution of the method:save(TypeFeesDto) parameter : " +typeFeesDto) ;
        if(typeFeesDto.getId() != null){
            throw new IdNotNullException("The id of the payment must be null");
        }
        typeFeesDto.setRemove(false);
        TypeFeesDto result = typeFeesService.save(typeFeesDto);
        log.info("end of execution of the method:save(TypeFeesDto) parameter : " +result) ;
        return result;
    }

    /**
     * This method updates an entity in the database
     * @param typeFeesDto : the entity to update
     * @return the entity updated
     * @throws IdNotFoundException : if the entity is not found
     * @throws IdIsNullException : if the entity is null
     */
    @PutMapping("/typeFees")
    @Tag(name = "TypeFeesController : Modify an entity")
    public TypeFeesDto update(@RequestBody @Valid TypeFeesDto typeFeesDto) throws IdNotFoundException, IdIsNullException {
        log.info("execution of the method:update(TypeFeesDto) parameter : " +typeFeesDto) ;
        if (typeFeesDto.getId() == null){
            throw new IdIsNullException("The id of the payment is null");
        }
        typeFeesDto.setRemove(false);
        TypeFeesDto result = typeFeesService.update(typeFeesDto);
        log.info("end of execution of the method:update(TypeFeesDto) parameter : " +result) ;
        return result;
    }

    /**
     * This method deletes an entity in the database
     * @param id : the entity to delete
     * @return the entity deleted
     * @throws IdNotFoundException : if the entity is not found
     */
    @DeleteMapping("/typeFees/{id}")
    @Tag(name = "TypeFeesController : Delete an entity using its identifier (ID)")
    public Map<String,String> removeById(@PathVariable("id") Long id) throws IdNotFoundException {
        log.info("execution of the method:removeById(Long) parameter : " +id) ;
        Map<String,String> map = new HashMap<>();
        TypeFeesDto typeFeesDto = typeFeesService.findByIdAndRemoveIsFalse(id);
        boolean result = typeFeesService.removeById(id);
        map.put("Deleted entity","TypeFees");
        map.put("Designation of the entity",typeFeesDto.getDesignation());
        map.put("Id of the entity",typeFeesDto.getId().toString());
        map.put("is remove", String.valueOf(result));
        log.info("end of execution of the method:removeById(FeesDto) parameter : " +result) ;
        return map;
    }

    /**
     * This method deletes an entity in the database
     * @param typeFeesDto : the entity to delete
     * @return the entity deleted
     * @throws IdNotFoundException : if the entity is not found
     */
    @DeleteMapping("/typeFees")
    @Tag(name = "TypeFeesController : Delete an entity")
    public Map<String,String> remove(@RequestBody @Valid TypeFeesDto typeFeesDto) throws IdNotFoundException {
        log.info("execution of the method:remove(TypeFeesDto) parameter : " +typeFeesDto) ;
        Map<String,String> map = new HashMap<>();
        boolean result = typeFeesService.remove(typeFeesDto);
        map.put("Deleted entity","TypeFees");
        map.put("Designation of the entity",typeFeesDto.getDesignation());
        map.put("Id of the entity",typeFeesDto.getId().toString());
        map.put("is remove", String.valueOf(result));
        log.info("end of execution of the method:remove(TypeFeesDto) parameter : " +result) ;
        return map;
    }
    /**
     * This method find all entities in the database
     * @return the list of entities
     */
    @GetMapping("/typeFees")
    @Tag(name = "TypeFeesController : List all entities")
    public List<TypeFeesDto> findAll(){
        log.info("execution of the method:findAll() ") ;
        List<TypeFeesDto> typeFeesDtoList = typeFeesService.findAll();
        log.info("end of execution of the method:findAll() ") ;
        return typeFeesDtoList;
    }

    /**
     * This method find all entities deleted in the database
     * @param tag : the designation of the entity
     * @return the list of entities
     */
    @GetMapping("/typeFees/removed/designationContains")
    @Tag(name = "TypeFeesController : Search for entities using their designation (Delete)")
    public List<TypeFeesDto> findTypeFeesByDesignationContainsAndRemoveIsTrue(@RequestParam(defaultValue = "") String tag){
        log.info("execution of the method:findTypeFeesByDesignationContainsAndRemoveIsTrue(String) ") ;
        List<TypeFeesDto> typeFeesDtoList = typeFeesService.findTypeFeesByDesignationContainsAndRemoveIsTrue(tag);
        log.info("end of execution of the method:findTypeFeesByDesignationContainsAndRemoveIsTrue(String) ") ;
        return typeFeesDtoList;
    }

    /**
     * This method find all entities in the database
     * @param tag : the designation of the entity
     * @return the list of entities
     */
    @GetMapping("/typeFees/designationContains")
    @Tag(name = "TypeFeesController : Search for entities using their designation")
    public List<TypeFeesDto> findTypeFeesByDesignationContainsAndRemoveIsFalse(@RequestParam(defaultValue = "") String tag){
        log.info("execution of the method:findTypeFeesByDesignationContainsAndRemoveIsFalse(String) ") ;
        List<TypeFeesDto> typeFeesDtoList = typeFeesService.findTypeFeesByDesignationContainsAndRemoveIsFalse(tag);
        log.info("end of execution of the method:findTypeFeesByDesignationContainsAndRemoveIsFalse(String) ") ;
        return typeFeesDtoList;
    }

    /**
     * This method find all entities in the database
     * @param tag : the designation of the entity
     * @param page : the page number
     * @param Size : the number of entities per page
     * @return Map<String,Object> : the list of entities
     */
    @GetMapping("/typeFees/designationContainsPage")
    @Tag(name = "TypeFeesController : Search for entities using their designation Page")
    public Map<String,Object> findTypeFeesByDesignationContainsAndRemoveIsFalsePage(
            @RequestParam(defaultValue = "") String tag,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int Size
    ){
        log.info("execution of the method:findTypeFeesByDesignationContainsAndRemoveIsFalsePage(String,int,int)") ;
        Map<String,Object> typeFeesDtoPage = typeFeesService.findTypeFeesByDesignationContainsAndRemoveIsFalsePage(tag,page,Size);
        log.info("end of execution of the method:findTypeFeesByDesignationContainsAndRemoveIsFalsePage(String,int,int)") ;
        return typeFeesDtoPage;
    }

    /**
     * This method find all entities deleted in the database
     * @param tag : the designation of the entity
     * @param page : the page number
     * @param Size : the number of entities per page
     * @return Map<String,Object> : the list of entities
     */
    @GetMapping("/typeFees/removed/designationContainsPage")
    @Tag(name = "TypeFeesController : Search for entities using their designation (Delete) Page")
    public Map<String,Object> findTypeFeesByDesignationContainsAndRemoveIsTruePage(
            @RequestParam(defaultValue = "") String tag,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int Size
    ){
        log.info("execution of the method:findTypeFeesByDesignationContainsAndRemoveIsDeletePage(String,int,int)") ;
        Map<String,Object> typeFeesDtoPage = typeFeesService.findTypeFeesByDesignationContainsAndRemoveIsTruePage(tag,page,Size);
        log.info("end of execution of the method:findTypeFeesByDesignationContainsAndRemoveIsTruePage(String,int,int)") ;
        return typeFeesDtoPage;
    }

    /**
     * This method find all entities deleted in the database
     * @return the list of entities
     */
    @GetMapping(value="/typeFees/delete")
    @Tag(name = "TypeFeesController : List all deleted entities")
    public List<TypeFeesDto> findAllDelete(){
        log.info("execution of the method:findAllDelete() ") ;
        List<TypeFeesDto> typeFeesDtoList = typeFeesService.findAllDelete();
        log.info("end of execution of the method:findAllDelete() ") ;
        return typeFeesDtoList;
    }

    /**
     * This method find an entity in the database
     * @param id : the id of the entity
     * @return the entity found
     * @throws IdNotFoundException : if the entity is not found
     */
    @GetMapping(value="/typeFees/{id}")
    @Tag(name = "TypeFeesController : Find an entity using its identifier (ID)")
    public TypeFeesDto findById(@PathVariable("id") Long id) throws IdNotFoundException {
        log.info("execution of the method:findById(Long) ") ;
        TypeFeesDto typeFeesDto = typeFeesService.findById(id);
        log.info("end of execution of the method:findById(Long) ") ;
        return typeFeesDto;
    }
    /**
     * This method restore an entity in the database
     * @param id : the id of the entity
     * @return Map<String,String> : the entity restored
     * @throws IdNotFoundException : if the entity is not found
     */
    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping(value="/typeFees/restore/{id}")
    @Tag(name = "TypeFeesController : Restore the entity using its identifier (ID)")
    public Map<String,String> restore(@PathVariable("id") Long id) throws IdNotFoundException {
        log.info("execution of the method:restore(Long) ") ;
        Map<String,String> map = new HashMap<>();
        TypeFeesDto typeFeesDto = typeFeesService.findByIdAndRemoveIsTrue(id);
        boolean result = typeFeesService.restore(id);
        map.put("Deleted entity","TypeFees");
        map.put("Designation of the entity",typeFeesDto.getDesignation());
        map.put("Id of the entity",typeFeesDto.getId().toString());
        map.put("is remove", String.valueOf(result));
        log.info("end of execution of the method:restore(Long) ") ;
        return map;
    }

    /**
     * This method checks if an entity exists in the database
     * @param id : the id of the entity
     * @return Map<String,String> : the entity found
     * @throws IdNotFoundException : if the entity is not found
     */
    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping(value="/typeFees/isExist/{id}")
    @Tag(name = "TypeFeesController : Check if the entity exists in the database (ID)")
    public Map<String,String> isExist(@PathVariable("id") Long id) throws IdNotFoundException {
        log.info("execution of the method:isExist(Long) ") ;
        Map<String,String> map = new HashMap<>();
        boolean result = typeFeesService.isExist(id);
        try {
            typeFeesService.findById(id);
            map.put("Entity","TypeFees");
            map.put("Id of the entity", String.valueOf(id));
            map.put("is exist", String.valueOf(result));
            map.put("is deleted", "false");
        }catch (IdNotFoundException e){
            map.put("Entity","TypeFees");
            map.put("Id of the entity", String.valueOf(id));
            map.put("is exist", String.valueOf(result));
            map.put("is deleted", "true");
        }

        log.info("end of execution of the method:isExist(Long) ") ;
        return map;
    }

}
