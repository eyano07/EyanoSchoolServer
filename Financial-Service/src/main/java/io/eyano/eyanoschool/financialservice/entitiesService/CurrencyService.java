package io.eyano.eyanoschool.financialservice.entitiesService;

import io.eyano.eyanoschool.financialservice.dao.CurrencyRepository;
import io.eyano.eyanoschool.financialservice.dtos.CurrencyDto;
import io.eyano.eyanoschool.financialservice.entities.Currency;
import io.eyano.eyanoschool.financialservice.mappers.CurrencyMapper;
import io.eyano.eyanoschool.financialservice.exceptions.IdNotFoundException;
import io.eyano.eyanoschool.financialservice.exceptions.IdIsNullException;
import io.eyano.eyanoschool.financialservice.exceptions.IdNotNullException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * Service class for Currency
 * @author : Pascal Tshingila
 * @version : 1.0
 * @since : 2021-04-19
 */
@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class CurrencyService implements CrudService<CurrencyDto, Long>{
    private CurrencyMapper currencyMapper;
    private CurrencyRepository currencyRepository;

    /**
     * @param entity : Entity to be saved
     * @return CurrencyDto : Saved entity
     * @throws IdNotFoundException : Exception thrown when the id is not found
     * @throws IdIsNullException : Exception thrown when the id is null
     * @throws IdNotNullException : Exception thrown when the id is not null
     */
    @Override
    public CurrencyDto save(CurrencyDto entity) throws IdNotFoundException, IdIsNullException, IdNotNullException {
        log.info("execution of the method : save(CurrencyDto entity)");
        Currency currency = currencyMapper.dtoFromEntity(entity);
        CurrencyDto currencyDto = currencyMapper.entityFromDto(currencyRepository.save(currency));
        log.info("the creation of the entity : {"+ currencyDto+"}");
        return currencyDto;
    }

    /**
     * @param entity  : Entity to be updated
     * @return CurrencyDto : Updated entity
     * @throws IdNotFoundException : Exception thrown when the id is not found
     * @throws IdIsNullException : Exception thrown when the id is null
     * @throws IdNotNullException : Exception thrown when the id is not null
     */
    @Override
    public CurrencyDto update(CurrencyDto entity) throws IdNotFoundException, IdIsNullException, IdNotNullException {
        log.info("execution of the method : update(CurrencyDto entity)");
        if(entity.getId() == null){
            throw new IdIsNullException("id is null");
        }
        CurrencyDto currencyDto = save(entity);
        log.info("entity update : {"+currencyDto+"}");
        return currencyDto;
    }


    /**
     * @param entity  : Entity to be removed
     * @return boolean : True if the entity is removed
     * @throws IdNotFoundException : Exception thrown when the id is not found
     * @throws IdIsNullException : Exception thrown when the id is null
     * @throws IdNotNullException : Exception thrown when the id is not null
     */
    @Override
    public boolean remove(CurrencyDto entity) throws IdNotFoundException, IdIsNullException, IdNotNullException {
        log.info("execution of the method : remove(CurrencyDto entity)");
        if(entity.getId() == null){
            throw new IdIsNullException("id is null");
        }
        currencyRepository.findByIdAndRemoveFalse(entity.getId()).orElseThrow(IdNotFoundException::new);
        entity.setRemove(true);
        CurrencyDto result = update(entity);
        log.info("entity removed : {"+result+"}");
        return result.isRemove();
    }

    /**
     * @param id : Identifier of the entity to be removed
     * @return boolean : True if the entity is removed
     * @throws IdNotFoundException : Exception thrown when the id is not found
     * @throws IdIsNullException : Exception thrown when the id is null
     * @throws IdNotNullException : Exception thrown when the id is not null
     */
    @Override
    public boolean removeById(Long id) throws IdNotFoundException, IdIsNullException, IdNotNullException {
        log.info("execution of the method : removeById(Long id)");
        if(id == null){
            throw new IdIsNullException("id is null");
        }
        CurrencyDto currencyDto = currencyMapper.entityFromDto(
                currencyRepository.findByIdAndRemoveFalse(id).orElseThrow(IdNotFoundException::new));
        currencyDto.setRemove(true);
        update(currencyDto);
        log.info("entity removed : {"+currencyDto+"}");
        return currencyDto.isRemove();
    }

    /**
     * @param id : Identifier of the entity to be restored
     * @return boolean : isRemove False if the entity is restored
     * @throws IdNotFoundException : Exception thrown when the id is not found
     * @throws IdIsNullException : Exception thrown when the id is null
     * @throws IdNotNullException : Exception thrown when the id is not null
     */
    @Override
    public boolean restore(Long id) throws IdNotFoundException, IdIsNullException, IdNotNullException {
        log.info("execution of the method : restore(Long id)");

        if (id == null) throw new IdIsNullException("id is null");
        System.err.println("id : "+id);
        CurrencyDto currencyDto = currencyMapper.entityFromDto(
                currencyRepository.findByIdAndRemoveTrue(id).orElseThrow(IdNotFoundException::new));
        currencyDto.setRemove(false);
        System.err.println("currencyDto : "+currencyDto);
        CurrencyDto result = update(currencyDto);
        log.info("entity restored : {"+result+"}");
        return result.isRemove();
    }

    /**
     * @param id : Identifier of the entity to be checked
     * @return boolean : True if the entity exists
     * @throws IdNotFoundException : Exception thrown when the id is not found
     * @throws IdIsNullException : Exception thrown when the id is null
     */
    @Override
    public boolean isExist(Long id) throws IdNotFoundException, IdIsNullException {
        log.info("execution of the method : isExist(Long id)");
        if(id == null){
            throw new IdIsNullException("id is null");
        }
        Currency currency = currencyRepository.findById(id).orElseThrow(IdNotFoundException::new);
        log.info("entity found : {"+currency+"}");
        return true;
    }

    /**
     * @param id : Identifier of the entity to be checked
     * @return boolean : True if the entity is removed
     * @throws IdNotFoundException : Exception thrown when the id is not found
     */
    @Override
    public boolean isRemove(Long id) throws IdNotFoundException, IdIsNullException {
        log.info("execution of the method : isRemove(Long id)");
        if(id == null){
            throw new IdIsNullException("id is null");
        }
        Currency currency = currencyRepository.findByIdAndRemoveTrue(id).orElseThrow(IdNotFoundException::new);
        log.info("entity found : {"+currency+"}");
        return true;
    }

    /**
     * @param id : Identifier of the entity to be found
     * @return CurrencyDto : Entity found
     * @throws IdNotFoundException : Exception thrown when the id is not found
     */
    @Override
    public CurrencyDto findById(Long id) throws IdNotFoundException, IdIsNullException {
        log.info("execution of the method : findById(Long id)");
        if(id == null){
            throw new IdIsNullException("id is null");
        }
        Currency currency = currencyRepository.findByIdAndRemoveFalse(id).orElseThrow(IdNotFoundException::new);
        log.info("entity found : {"+currency+"}");
        return currencyMapper.entityFromDto(currency);
    }

    /**
     * This method finds an entity deleted in the database per page
     * @return  List<CurrencyDto> : List of all entities
     */
    @Override
    public List<CurrencyDto> findAll() {
        log.info("execution of the method : findAll()");
        List<Currency> currencies = currencyRepository.findByDesignationContainsIgnoreCaseAndRemoveFalse("");
        log.info("entities found : {"+currencies+"}");
        return currencyMapper.entitiesFromDtos(currencies);
    }

    /**
     * This method findBySymbolAndRemoveFalse in the database
     * @return  CurrencyDto : entity found
     * @param symbol : symbol of the entity to be found
     */
    public CurrencyDto findBySymbolAndRemoveFalse(String symbol) {
        log.info("execution of the method : findBySymbolAndRemoveFalse(String symbol)");
        Currency currency = currencyRepository.findBySymbolAndRemoveFalse(symbol);
        log.info("entity found : {"+currency+"}");
        return currencyMapper.entityFromDto(currency);
    }

    /**
     * This method findByIdAndRemoveFalse in the database
     * @return  CurrencyDto : entity found
     * @param id : id of the entity to be found
     */
    public CurrencyDto findByIdAndRemoveTrue(Long id) throws IdIsNullException, IdNotFoundException {
        log.info("execution of the method : findByIdAndRemoveTrue(Long id)");
        if(id == null){
            throw new IdIsNullException("id is null");
        }
        Currency currency = currencyRepository.findByIdAndRemoveTrue(id).orElseThrow(IdNotFoundException::new);
        log.info("entity found : {"+currency+"}");
        return currencyMapper.entityFromDto(currency);
    }
    /**
     * This method findByDesignationContainsIgnoreCaseAndRemoveFalse in the database
     * @return  List<CurrencyDto> : List of all entities found
     * @param designation : designation of the entity to be found
     */
    public List<CurrencyDto> findByDesignationContainsIgnoreCaseAndRemoveFalse(String designation) {
        log.info("execution of the method : findByDesignationContainsIgnoreCaseAndRemoveFalse(String designation)");
        List<Currency> currencies = currencyRepository.findByDesignationContainsIgnoreCaseAndRemoveFalse(designation);
        log.info("entities found : {"+currencies+"}");
        return currencyMapper.entitiesFromDtos(currencies);
    }

    /**
     * This method findByDesignationContainsIgnoreCaseAndRemoveTrue in the database
     * @return  List<CurrencyDto> : List of all entities found
     * @param designation : designation of the entity to be found
     */
    public List<CurrencyDto> findByDesignationContainsIgnoreCaseAndRemoveTrue(String designation) {
        log.info("execution of the method : findByDesignationContainsIgnoreCaseAndRemoveTrue(String designation)");
        List<Currency> currencies = currencyRepository.findByDesignationContainsIgnoreCaseAndRemoveTrue(designation);
        log.info("entities found : {"+currencies+"}");
        return currencyMapper.entitiesFromDtos(currencies);
    }


}
