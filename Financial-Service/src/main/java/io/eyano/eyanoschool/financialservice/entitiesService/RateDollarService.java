package io.eyano.eyanoschool.financialservice.entitiesService;

import io.eyano.eyanoschool.financialservice.dao.RateDollarRepository;
import io.eyano.eyanoschool.financialservice.dtos.RateDollarDto;
import io.eyano.eyanoschool.financialservice.entities.RateDollar;
import io.eyano.eyanoschool.financialservice.mappers.RateDollarMapper;
import io.eyano.eyanoschool.financialservice.exceptions.IdNotFoundException;
import io.eyano.eyanoschool.financialservice.exceptions.IdIsNullException;
import io.eyano.eyanoschool.financialservice.exceptions.IdNotNullException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * Service class for RateDollarService
 * @author : Pascal Tshingila
 * @version : 1.0
 * @since : 2021-04-19
 */

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class RateDollarService implements CrudService<RateDollarDto, Long>{
    private RateDollarMapper rateDollarMapper;
    private RateDollarRepository rateDollarRepository;

    /**
     * @param entity : Entity to be saved
     * @return RateDollarDto : Saved entity
     * @throws IdNotFoundException : Exception thrown when the id is not found
     * @throws IdIsNullException : Exception thrown when the id is null
     * @throws IdNotNullException : Exception thrown when the id is not null
     */
    @Override
    public RateDollarDto save(RateDollarDto entity) throws IdNotFoundException, IdIsNullException, IdNotNullException {
        log.info("execution of the method : save(RateDollarDto entity) in the class : RateDollarService");
        RateDollar rateDollar = rateDollarMapper.dtoFromEntity(entity);
        RateDollarDto rateDollarDto = rateDollarMapper.entityFromDto(rateDollarRepository.save(rateDollar));
        log.info("the creation of the entity : {"+rateDollarDto+"} is done successfully");
        return rateDollarDto;
    }

    /**
     * @param entity  : Entity to be updated
     * @return RateDollarDto : Updated entity
     * @throws IdNotFoundException : Exception thrown when the id is not found
     * @throws IdIsNullException : Exception thrown when the id is null
     * @throws IdNotNullException : Exception thrown when the id is not null
     */
    @Override
    public RateDollarDto update(RateDollarDto entity) throws IdNotFoundException, IdIsNullException, IdNotNullException {
        log.info("execution of the method : update(RateDollarDto entity) in the class : RateDollarService");
        if(entity.getId() == null) throw new IdIsNullException("The id is null");
        rateDollarRepository.findById(entity.getId()).orElseThrow(()->new IdNotFoundException("The id is not found"));
        RateDollarDto rateDollarDto = save(entity);
        log.info("the update of the entity : {"+rateDollarDto+"} is done successfully");
        return rateDollarDto;
    }

    /**
     * @param entity : Entity to be removed
     * @return boolean : True if the entity is removed
     * @throws IdNotFoundException : Exception thrown when the id is not found
     * @throws IdIsNullException   : Exception thrown when the id is null
     * @throws IdNotNullException : Exception thrown when the id is not null
     */
    @Override
    public boolean remove(RateDollarDto entity) throws IdNotFoundException, IdIsNullException, IdNotNullException {
        log.info("execution of the method : remove(RateDollarDto entity) in the class : RateDollarService");
        if(entity.getId() == null) throw new IdIsNullException("The id is null");
        rateDollarRepository.findByIdAndRemoveFalse(entity.getId()).orElseThrow(()->new IdNotFoundException("The id is not found"));
        entity.setRemove(true);
        RateDollarDto rateDollarDto = update(entity);
        log.info("the removal of the entity : {"+rateDollarDto+"} is done successfully");
        return rateDollarDto.isRemove();
    }

    /**
     * @param id : identifier of the entity to be removed
     * @return boolean : True if the entity is removed
     * @throws IdNotFoundException : Exception thrown when the id is not found
     * @throws IdIsNullException : Exception thrown when the id is null
     * @throws IdNotNullException : Exception thrown when the id is not null
     */
    @Override
    public boolean removeById(Long id) throws IdNotFoundException, IdIsNullException, IdNotNullException {
        log.info("execution of the method : removeById(Long id) in the class : RateDollarService");
        if (id == null) throw new IdIsNullException("The id is null");
        RateDollarDto rateDollarDto = rateDollarMapper.entityFromDto(
                rateDollarRepository.findByIdAndRemoveFalse(id)
                .orElseThrow(()->new IdNotFoundException("The id is not found")));
        rateDollarDto.setRemove(true);
        rateDollarDto = update(rateDollarDto);
        log.info("the removal of the entity : {"+rateDollarDto+"} is done successfully");
        return rateDollarDto.isRemove();
    }

    /**
     * @param id : identifier of the entity to be restored
     * @return boolean : True if the entity is restored
     * @throws IdNotFoundException : Exception thrown when the id is not found
     * @throws IdIsNullException : Exception thrown when the id is null
     * @throws IdNotNullException : Exception thrown when the id is not null
     */
    @Override
    public boolean restore(Long id) throws IdNotFoundException, IdIsNullException, IdNotNullException {
        log.info("execution of the method : restore(Long id) in the class : RateDollarService");
        if (id == null) throw new IdIsNullException("The id is null");
        RateDollarDto rateDollarDto = rateDollarMapper.entityFromDto(
                rateDollarRepository.findByIdAndRemoveTrue(id)
                .orElseThrow(()->new IdNotFoundException("The id is not found")));
        rateDollarDto.setRemove(false);
        rateDollarDto = update(rateDollarDto);
        log.info("the restoration of the entity : {"+rateDollarDto+"} is done successfully");
        return rateDollarDto.isRemove();
    }

    /**
     * @param id : identifier of the entity to be checked
     * @return boolean : True if the entity is existed
     * @throws IdNotFoundException : Exception thrown when the id is not found
     * @throws IdIsNullException : Exception thrown when the id is null
     */
    @Override
    public boolean isExist(Long id) throws IdNotFoundException, IdIsNullException {
        log.info("execution of the method : isExist(Long id) in the class : RateDollarService");
        if (id == null) throw new IdIsNullException("The id is null");
        rateDollarRepository.findById(id).orElseThrow(IdNotFoundException::new);
        log.info("the entity with the id : {"+id+"} is existed");
        return true;
    }

    /**
     * @param id : identifier of the entity to be checked
     * @return boolean : True if the entity is removed
     * @throws IdNotFoundException : Exception thrown when the id is not found
     */
    @Override
    public boolean isRemove(Long id) throws IdNotFoundException, IdIsNullException {
        log.info("execution of the method : isRemove(Long id) in the class : RateDollarService");
        if(id==null) throw new IdIsNullException("The id is null");
        RateDollarDto rateDollarDto = rateDollarMapper.entityFromDto(
                rateDollarRepository.findByIdAndRemoveTrue(id)
                .orElseThrow(()->new IdNotFoundException("The id is not found")));
        boolean isRemove = rateDollarDto.isRemove();
        log.info("the entity with the id : {"+id+"} is removed");
        return isRemove;
    }

    /**
     * @param id : identifier of the entity to be found
     * @return RateDollarDto : Entity found
     * @throws IdNotFoundException : Exception thrown when the id is not found
     * @throws IdIsNullException : Exception thrown when the id is null
     */
    @Override
    public RateDollarDto findById(Long id) throws IdNotFoundException, IdIsNullException {
        log.info("execution of the method : findById(Long id) in the class : RateDollarService");
        if(id==null) throw new IdIsNullException("The id is null");
        RateDollarDto rateDollarDto = rateDollarMapper.entityFromDto(
                rateDollarRepository.findByIdAndRemoveFalse(id)
                .orElseThrow(()->new IdNotFoundException("The id is not found")));
        log.info("the entity with the id : {"+id+"} is found");
        return rateDollarDto;
    }

    /**
     * This method finds an entity deleted in the database per page
     * @return List<RateDollarDto> : List of entities
     */
    @Override
    public List<RateDollarDto> findAll() {
        log.info("execution of the method : findAll() in the class : RateDollarService");
        List<RateDollarDto> rateDollarDtos = rateDollarMapper.entitiesFromDtos(rateDollarRepository.findAll());
        log.info("the list of entities : {"+rateDollarDtos+"} is found");
        return rateDollarDtos;
    }

    public RateDollarDto findByCurrentTrueAndRemoveFalse() {
        log.info("execution of the method : findByCurrentTrueAndRemoveFalse() in the class : RateDollarService");
        RateDollarDto rateDollarDtos = rateDollarMapper.entityFromDto(
                rateDollarRepository.findByIsCurrentRateTrueAndRemoveFalse());
        log.info("the list of entities : {"+rateDollarDtos+"} is found");
        return rateDollarDtos;
    }

    public List<RateDollarDto> findAllByOrderByDateDesc() {
        log.info("execution of the method : findAllByOrderByDateDesc() in the class : RateDollarService");
        List<RateDollarDto> rateDollarDtos = rateDollarMapper.entitiesFromDtos(rateDollarRepository.findAllByOrderByDateDesc());
        log.info("the list of entities : {"+rateDollarDtos+"} is found");
        return rateDollarDtos;
    }
}
