package io.eyano.eyanoschool.feesservice.entitiesService;

import io.eyano.eyanoschool.feesservice.dao.FeesAllocationRepository;
import io.eyano.eyanoschool.feesservice.dtos.FeesAllocationDto;
import io.eyano.eyanoschool.feesservice.entities.FeesAllocation;
import io.eyano.eyanoschool.feesservice.exceptions.IdIsNullException;
import io.eyano.eyanoschool.feesservice.exceptions.IdNotFoundException;
import io.eyano.eyanoschool.feesservice.exceptions.IdNotNullException;
import io.eyano.eyanoschool.feesservice.mappers.FeesAllocationMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * FeesAllocationService is a class that implements the CrudService interface
 * @author : Pascal Tshingila
 * @since : 2021-04-19
 * @version : 1.0
 * @see CrudService
 * @see FeesAllocationDto
 * @see FeesAllocation
 */
@Service @Transactional
@AllArgsConstructor @Slf4j
public class FeesAllocationService implements CrudService<FeesAllocationDto, Long> {
    FeesAllocationRepository feesAllocationRepository;
    FeesAllocationMapper mapper;

    /**
        * This method finds an entity in the database
        * @param id : the id of the entity to find
        * @return FeesAllocationDto : the entity found
        * @throws IdNotFoundException : if the entity is not found
        * @throws IdIsNullException : if the id is null
     */
    public FeesAllocationDto findById(Long id) throws IdNotFoundException, IdIsNullException {
        log.info("execution of the method:findById(Long id)") ;
        if(id==null){
            throw new IdIsNullException("The id is null");
        }
        FeesAllocation byId = feesAllocationRepository.findById(id).orElseThrow(IdNotFoundException::new);
        FeesAllocationDto feesAllocationDto = mapper.entityFromDTO(byId);
        log.info("end of method execution:findById(Long id)") ;
        return feesAllocationDto;
    }

    /**
        * This method saves an entity in the database
        * @param entity : the entity to save
        * @return the entity saved
     */
    @Override
    public FeesAllocationDto save(FeesAllocationDto entity){
        log.info("execution of the method:save(FeesAllocationDto entity) : {"+entity+"}") ;
        FeesAllocation feesAllocation = mapper.dtoFromEntity(entity);
        FeesAllocation feesAllocationSave = feesAllocationRepository.save(feesAllocation);
        FeesAllocationDto feesAllocationDto = mapper.entityFromDTO(feesAllocationSave);
        log.info("the creation of the entity : {"+ feesAllocationDto.toString()+"}");
        return feesAllocationDto ;
    }
    /**
        * This method updates an entity in the database
        * @param entity : the entity to update
        * @return the entity updated
        * @throws IdNotFoundException : if the entity is not found
        * @throws IdIsNullException : if the id is null
     */
    @Override
    public FeesAllocationDto update(FeesAllocationDto entity) throws IdIsNullException, IdNotFoundException {
        log.info("execution of the method:update(FeesAllocationDto entity) : {"+entity+"}" ) ;
        if(entity.getId()==null){
            throw new IdIsNullException("The id is null");
        }
        feesAllocationRepository.findById(entity.getId()).orElseThrow(IdNotFoundException::new);
        FeesAllocationDto feesAllocationDto = save(entity);
        log.info("entity update : {" +feesAllocationDto.toString()+"}");
        return feesAllocationDto;
    }


    /**
        * This method deletes an entity in the database
        * @param entity : the entity to delete
        * @return the entity deleted
        * @throws IdNotFoundException : if the entity is not found
        * @throws IdIsNullException : if the id is null
        * @throws IdNotNullException : if the id is not null
     */
    @Override
    public boolean remove(FeesAllocationDto entity) throws IdNotFoundException, IdIsNullException, IdNotNullException {
        log.info("execution of the method:remove(FeesAllocationDto entity)") ;
        if (entity.getId()==null){
            throw new IdIsNullException("The id is null");
        }
        FeesAllocation feesAllocation = feesAllocationRepository.findById(entity.getId()).orElseThrow(IdNotFoundException::new);
        FeesAllocationDto feesAllocationDto = mapper.entityFromDTO(feesAllocation);
        feesAllocationDto.setRemove(true);
        boolean remove = update(feesAllocationDto).isRemove();
        log.info("the entity : {"+ feesAllocationDto+"} is deleted in the database");
        return remove;
    }

    /**
        * This method deletes an entity in the database
        * @param id : the id of the entity to delete
        * @return boolean : true if the entity is deleted
        * @throws IdNotFoundException : if the entity is not found
        * @throws IdIsNullException : if the id is null
        * @throws IdNotNullException : if the id is not null
     */
    @Override
    public boolean removeById(Long id) throws IdNotFoundException, IdIsNullException, IdNotNullException {
        log.info("execution of the method:removeById(Long id)") ;
        if(id==null){
            throw new IdIsNullException("The id is null");
        }
        FeesAllocation feesAllocation = feesAllocationRepository.findById(id).orElseThrow(IdNotFoundException::new);
        FeesAllocationDto feesAllocationDto = mapper.entityFromDTO(feesAllocation);
        feesAllocationDto.setRemove(true);
        boolean remove = update(feesAllocationDto).isRemove();
        log.info("the entity : {"+ feesAllocationDto+"} is deleted in the database from id");
        return remove;
    }

    /**
        * This method restores an entity in the database
        * @param id : the id of the entity to restore
        * @return boolean : true if the entity is restored
        * @throws IdNotFoundException : if the entity is not found
     */
    @Override
    public boolean restore(Long id) throws IdNotFoundException, IdIsNullException, IdNotNullException {
        log.info("execution of the method:restore(Long id)") ;
        if(id==null){
            throw new IdIsNullException("The id is null");
        }
        FeesAllocation feesAllocation = feesAllocationRepository.findById(id).orElseThrow(IdNotFoundException::new);
        FeesAllocationDto feesAllocationDto = mapper.entityFromDTO(feesAllocation);
        feesAllocationDto.setRemove(false);
        update(feesAllocationDto);
        log.info("the entity : {"+ feesAllocationDto+"} is restored in the database from id");
        return true;
    }
    /**
        * This method finds an entity in the database
        * @param id : the id of the entity to find
        * @return FeesAllocationDto : the entity found
        * @throws IdNotFoundException : if the entity is not found
     */
    public FeesAllocationDto findByIdAndRemoveIsFalse(Long id) throws IdNotFoundException, IdIsNullException {
        log.info("execution of the method:findById(Long id)") ;
        if(id==null){
            throw new IdIsNullException("The id is null");
        }
        FeesAllocation feesAllocation = feesAllocationRepository.findByIdAndRemoveIsFalse(id).orElseThrow(IdNotFoundException::new);
        FeesAllocationDto feesAllocationDto = mapper.entityFromDTO(feesAllocation);
        log.info("the entity : {"+ feesAllocationDto+"} is found in the database from id");
        return feesAllocationDto;
    }

    /**
        * This method checks if an entity exists in the database
        * @param id : the id of the entity to check
        * @return boolean : true if the entity exists
        * @throws IdNotFoundException : if the entity is not found
     */
    @Override
    public boolean isExist(Long id) throws IdNotFoundException, IdIsNullException {
        log.info("execution of the method:isExist(Long id)") ;
        if(id==null){
            throw new IdIsNullException("The id is null");
        }
        FeesAllocation feesAllocation = feesAllocationRepository.findById(id).orElseThrow(IdNotFoundException::new);
        log.info("the entity : {"+ feesAllocation+"} exists in the database from id");
        return true;
    }
    /**
        * This method checks if an entity is deleted in the database
        * @param id : the id of the entity to check
        * @return boolean : true if the entity is deleted
        * @throws IdNotFoundException : if the entity is not found
     */
    @Override
    public boolean isRemove(Long id) throws IdNotFoundException {
        log.info("execution of the method:isRemove(Long id)") ;
        FeesAllocation feesAllocation = feesAllocationRepository.findById(id).orElseThrow(IdNotFoundException::new);
        boolean remove = feesAllocation.isRemove();
        log.info("the remove attribute of the entity {"+ feesAllocation+"} is : "+feesAllocation.isRemove());
        return remove;
    }

    /**
        * This method finds an entity in the database
        * @return List<FeesAllocationDto> : the list of entities found
     */
    @Override
    public List<FeesAllocationDto> findAll() {
        log.info("execution of the method:findAll()") ;
        List<FeesAllocation> feesAllocations = feesAllocationRepository.findByRemoveIsFalse();
        List<FeesAllocationDto> feesAllocationDtos = mapper.entitiesFromDtos(feesAllocations);
        log.info("end of method execution:findAll()") ;
        return feesAllocationDtos;
    }

    /**
        * This method finds all entities in the database
        * @param tag : designation of the entity
        * @param idSchoolYear : the id of the school year
        * @return List<FeesAllocationDto> : the list of entities found
     */
    public List<FeesAllocationDto> findByRemoveFalseAndDesignationContainsAndIdSchoolYear(String tag, Long idSchoolYear) throws IdIsNullException {
        log.info("execution of the method:findByRemoveFalseAndDesignationContainsAndIdSchoolYear(String tag, Long idSchoolYear)") ;
        if(idSchoolYear==null){
            throw new IdIsNullException("The id school year is null");
        }
        //todo: add test for school year id is exist
        List<FeesAllocationDto> byRemoveFalseAndDesignationContainsAndIdSchoolYear = mapper.entitiesFromDtos(feesAllocationRepository.findByRemoveFalseAndDesignationIgnoreCaseContainsAndIdSchoolYear(tag, idSchoolYear));
        log.info("end of method execution:findByRemoveFalseAndDesignationContainsAndIdSchoolYear(String tag, Long idSchoolYear)") ;
        return byRemoveFalseAndDesignationContainsAndIdSchoolYear;
    }
    /**
        * This method finds all entities deleted in the database
        * @param tag : designation of the entity
        * @param idSchoolYear : the id of the school year
        * @return List<FeesAllocationDto> : the list of entities found
     */
    public List<FeesAllocationDto> findByRemoveTrueAndDesignationContainsAndIdSchoolYear(String tag, Long idSchoolYear) throws IdIsNullException {
        log.info("execution of the method:findByRemoveTrueAndDesignationContainsAndIdSchoolYear(String tag, Long idSchoolYear)") ;
        if(idSchoolYear==null){
            throw new IdIsNullException("The id school year is null");
        }
        //todo: add test for school year id is exist
        List<FeesAllocationDto> byRemoveTrueAndDesignationContainsAndIdSchoolYear = mapper.entitiesFromDtos(feesAllocationRepository.findByRemoveTrueAndDesignationIgnoreCaseContainsAndIdSchoolYear(tag, idSchoolYear));
        log.info("end of method execution:findByRemoveTrueAndDesignationContainsAndIdSchoolYear(String tag, Long idSchoolYear)") ;
        return byRemoveTrueAndDesignationContainsAndIdSchoolYear;
    }

    /**
        * This method finds an entity in the database
        * @param id : the id of the entity
        * @return Optional<FeesAllocationDto> : the entity found
        * @throws IdNotFoundException : if the entity is not found
     */

    /**
        * This method finds all entities deleted in the database
        * @param tag : designation of the entity
        * @param idSchoolYear : the id of the school year
        * @param idTypeFees : the id of the type fees
        * @return List<FeesAllocationDto> : the list of entities found
     */
    public List<FeesAllocation> findByRemoveTrueAndDesignationContainsAndIdSchoolYearAndTypeFeesId(String tag, Long idSchoolYear, Long idTypeFees){
        log.info("execution of the method:findByRemoveTrueAndDesignationContainsAndIdSchoolYearAndTypeFeesId(String tag, Long idSchoolYear, Long idTypeFees)") ;
        List<FeesAllocation> byRemoveTrueAndDesignationContainsAndIdSchoolYearAndTypeFeesId = feesAllocationRepository.findByRemoveTrueAndDesignationContainsAndIdSchoolYearAndTypeFeesId(tag, idSchoolYear, idTypeFees);
        log.info("end of method execution:findByRemoveTrueAndDesignationContainsAndIdSchoolYearAndTypeFeesId(String tag, Long idSchoolYear, Long idTypeFees)") ;
        return byRemoveTrueAndDesignationContainsAndIdSchoolYearAndTypeFeesId;
    }
    /**
        * This method finds all entities in the database
        * @param tag : designation of the entity
        * @param idSchoolYear : the id of the school year
        * @param idTypeFees : the id of the type fees
        * @return List<FeesAllocationDto> : the list of entities found
     */
    public List<FeesAllocationDto> findByRemoveFalseAndDesignationContainsAndIdSchoolYearAndTypeFeesId(String tag, Long idSchoolYear, Long idTypeFees) throws IdIsNullException {
        log.info("execution of the method:findByRemoveFalseAndDesignationContainsAndIdSchoolYearAndTypeFeesId(String tag, Long idSchoolYear, Long idTypeFees)") ;
        if(idSchoolYear==null){
            throw new IdIsNullException("The id School Year is null");
        }else if(idTypeFees==null){
            throw new IdIsNullException("The id School Year is null");
        }

        //todo: add test for school year id is exist
        List<FeesAllocationDto> byRemoveFalseAndDesignationContainsAndIdSchoolYearAndTypeFeesId = mapper.entitiesFromDtos(feesAllocationRepository.findByRemoveFalseAndDesignationContainsAndIdSchoolYearAndTypeFeesId(tag, idSchoolYear, idTypeFees));
        log.info("end of method execution:findByRemoveFalseAndDesignationContainsAndIdSchoolYearAndTypeFeesId(String tag, Long idSchoolYear, Long idTypeFees)") ;
        return byRemoveFalseAndDesignationContainsAndIdSchoolYearAndTypeFeesId;
    }
    /**
        * This method finds an entity deleted in the database
        * @param id : the id of the entity
        * @return FeesAllocationDto : the entity found
        * @throws IdNotFoundException : if the entity is not found
     */
    public FeesAllocationDto findByIdAndRemoveIsTrue(Long id) throws IdNotFoundException, IdIsNullException {
        log.info("execution of the method:findByIdAndRemoveIsTrue(Long aLong)") ;
        if(id==null){
            throw new IdIsNullException("The id is null");
        }
        FeesAllocationDto byIdAndRemoveIsTrue = mapper.entityFromDTO(feesAllocationRepository.findByIdAndRemoveIsTrue(id).orElseThrow(IdNotFoundException::new));
        log.info("end of method execution:findByIdAndRemoveIsTrue(Long aLong)") ;
        return byIdAndRemoveIsTrue;
    }


}
