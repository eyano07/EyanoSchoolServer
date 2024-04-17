package io.eyano.eyanoschool.financialservice.web;

import io.eyano.eyanoschool.financialservice.dtos.RateDollarDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import io.eyano.eyanoschool.financialservice.entitiesService.RateDollarService;
import io.eyano.eyanoschool.financialservice.exceptions.IdNotFoundException;
import io.eyano.eyanoschool.financialservice.exceptions.IdIsNullException;
import io.eyano.eyanoschool.financialservice.exceptions.IdNotNullException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller class for RateDollarController
 * @author : Pascal Tshingila
 * @version : 1.0
 * @since : 2021-04-19
 * @see RateDollarService
 */
@RestController
@AllArgsConstructor
@Slf4j
public class RateDollarController {
    private RateDollarService rateDollarService;

    /**
     * @param entity : Entity to be saved
     * @return RateDollarDto : Saved entity
     * @throws IdNotNullException : Exception thrown when the id is not null
     * @throws IdIsNullException : Exception thrown when the id is null
     * @throws IdNotFoundException : Exception thrown when the id is not found
     */
    @PostMapping("/rateDollars")
    @Tag(name = "RateDollarController", description = "Save a rate dollar")
    public RateDollarDto save(@RequestBody @Valid RateDollarDto entity) throws IdNotNullException, IdIsNullException, IdNotFoundException {
        log.info("execution of the method : save(RateDollarDto entity) in the class : RateDollarController");
        if (entity.getId() != null) {
            log.error("execution of the method : The id is not null");
            throw new IdNotNullException("The id is not null");
        }
        RateDollarDto result = rateDollarService.save(entity);
        log.info("the creation of the entity : {" + result + "} is done successfully");
        return result;
    }
    /**
     * @param entity : Entity to be updated
     * @return RateDollarDto : Updated entity
     * @throws IdNotNullException : Exception thrown when the id is not null
     * @throws IdIsNullException : Exception thrown when the id is null
     * @throws IdNotFoundException : Exception thrown when the id is not found
     */
    @PutMapping("/rateDollars")
    @Tag(name = "RateDollarController", description = "Update a rate dollar")
    public RateDollarDto update(@RequestBody @Valid RateDollarDto entity) throws IdNotFoundException, IdIsNullException, IdNotNullException {
        log.info("execution of the method : update(RateDollarDto entity) in the class : RateDollarController");
        if (entity.getId() == null) {
            log.error("execution of the method : The id is null");
            throw new IdIsNullException("The id is null");
        }
        rateDollarService.update(entity);
        log.info("the update of the entity : {" + entity + "} is done successfully");
        return entity;
    }
    /**
     * @param id : Identifier of the entity to be removed
     * @return Map<String, Object> : Response of the removed entity
     * @throws IdNotNullException : Exception thrown when the id is not null
     * @throws IdNotFoundException : Exception thrown when the id is not found
     * @throws IdIsNullException : Exception thrown when the id is null
     */
    @DeleteMapping("/rateDollars/{id}")
    @Tag(name = "RateDollarController", description = "Delete a rate dollar")
    public Map<String, Object> deleteById(@PathVariable Long id) throws IdIsNullException, IdNotFoundException, IdNotNullException {
        log.info("execution of the method : deleteById(Long id) in the class : RateDollarController");
        Map<String, Object> map = new HashMap<>();
        RateDollarDto rateDollarDto = rateDollarService.findById(id);
        boolean isRemoved = rateDollarService.removeById(rateDollarDto.getId());
        map.put("isRemoved", isRemoved);
        map.put("rateDollarDto", rateDollarDto);
        map.put("Deleted entity", "RateDollar");
        log.info("the removal of the entity : {" + rateDollarDto + "} is done successfully");
        return map;
    }
    /**
     * @param entity : Entity to be removed
     * @return Map<String, Object> : Response of the removed entity
     * @throws IdNotNullException : Exception thrown when the id is not null
     * @throws IdNotFoundException : Exception thrown when the id is not found
     * @throws IdIsNullException : Exception thrown when the id is null
     */
    @DeleteMapping("/rateDollars")
    @Tag(name = "RateDollarController", description = "Delete a rate dollar")
    public Map<String, Object> remove(@RequestBody RateDollarDto entity) throws IdNotFoundException, IdIsNullException, IdNotNullException {
        log.info("execution of the method : remove(RateDollarDto entity) in the class : RateDollarController");
        Map<String, Object> map = new HashMap<>();
        boolean isRemoved = rateDollarService.remove(entity);
        map.put("isRemoved", isRemoved);
        map.put("Deleted entity", "RateDollar");
        log.info("the removal of the entity : {" + entity + "} is done successfully");
        return map;
    }
    /**
     * @param id : Identifier of the entity to be restored
     * @return Map<String, String> : Response of the restored entity
     * @throws IdNotNullException : Exception thrown when the id is not null
     * @throws IdNotFoundException : Exception thrown when the id is not found
     * @throws IdIsNullException : Exception thrown when the id is null
     */
    @GetMapping("/rateDollars/restore/{id}")
    @Tag(name = "RateDollarController", description = "Restore a rate dollar")
    public Map<String,String> restore(@PathVariable("id") Long id) throws IdNotFoundException, IdIsNullException, IdNotNullException {
        log.info("execution of the method : restore(Long id) in the class : RateDollarController");
        Map<String,String> map = new HashMap<>();
        RateDollarDto rateDollarDto = rateDollarService.findById(id);
        rateDollarDto.setRemove(false);
        rateDollarService.update(rateDollarDto);
        map.put("message","The entity is restored successfully");
        map.put("entity",rateDollarDto.toString());
        map.put("isRestore","true");
        log.info("the restoration of the entity : {"+rateDollarDto+"} is done successfully");
        return map;
    }

    /**
     * the methode to find all the entities
     * @return List<RateDollarDto> : List of the entities
     */
    @GetMapping("/rateDollars")
    @Tag(name = "RateDollarController", description = "Find all rate dollars")
    public List<RateDollarDto> findAll(){
        log.info("execution of the method : findAll() in the class : RateDollarController");
        List<RateDollarDto> rateDollarDtos = rateDollarService.findAll();
        log.info("the list of the entities : {"+rateDollarDtos+"} is returned successfully");
        return rateDollarDtos;
    }
    /**
     * @param id : Identifier of the entity to be found
     * @return RateDollarDto : Found entity
     * @throws IdNotFoundException : Exception thrown when the id is not found
     * @throws IdIsNullException : Exception thrown when the id is null
     * @throws IdNotNullException : Exception thrown when the id is not null
     */
    @GetMapping("/rateDollars/{id}")
    @Tag(name = "RateDollarController", description = "Find a rate dollar by id")
    public RateDollarDto findById(@PathVariable Long id) throws IdNotFoundException, IdIsNullException, IdNotNullException {
        log.info("execution of the method : findById(Long id) in the class : RateDollarController");
        if (id == null) {
            log.error("execution of the method : The id is null");
            throw new IdIsNullException("The id is null");
        }
        RateDollarDto rateDollarDto = rateDollarService.findById(id);
        log.info("the entity : {" + rateDollarDto + "} is returned successfully");
        return rateDollarDto;
    }

    /**
     * the method to find the current rate dollar
     * @return RateDollarDto : Found entity
     */
    @GetMapping("/rateDollars/current")
    @Tag(name = "RateDollarController", description = "Find the current rate dollar")
    public RateDollarDto findByCurrentTrueAndRemoveFalse() {
        log.info("execution of the method : findByCurrentTrueAndRemoveFalse() in the class : RateDollarController");
        RateDollarDto rateDollarDto = rateDollarService.findByCurrentTrueAndRemoveFalse();
        log.info("the entity : {" + rateDollarDto + "} is returned successfully");
        return rateDollarDto;
    }
    /**
     * the method to find the last rate dollar
     * @return List<RateDollarDto> : List of the entities
     */
    @GetMapping("/rateDollars/last")
    @Tag(name = "RateDollarController", description = "Find the last rate dollar")
    public List<RateDollarDto> findAllByOrderByDateDesc() {
        log.info("execution of the method : findAllByOrderByDateDesc() in the class : RateDollarController");
        List<RateDollarDto> rateDollarDtos = rateDollarService.findAllByOrderByDateDesc();
        log.info("the list of the entities : {" + rateDollarDtos + "} is returned successfully");
        return rateDollarDtos;
    }

}
