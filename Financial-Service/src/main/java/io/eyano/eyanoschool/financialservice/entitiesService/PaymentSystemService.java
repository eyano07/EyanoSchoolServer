package io.eyano.eyanoschool.financialservice.entitiesService;

import io.eyano.eyanoschool.financialservice.dao.PaymentSystemRepository;
import io.eyano.eyanoschool.financialservice.dtos.PaymentSystemDto;
import io.eyano.eyanoschool.financialservice.entities.PaymentSystem;
import io.eyano.eyanoschool.financialservice.enums.PaymentMethode;
import io.eyano.eyanoschool.financialservice.mappers.PaymentSystemMapper;
import io.eyano.eyanoschool.financialservice.exceptions.IdNotFoundException;
import io.eyano.eyanoschool.financialservice.exceptions.IdIsNullException;
import io.eyano.eyanoschool.financialservice.exceptions.IdNotNullException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Service class for PaymentSystem
 * @author : Pascal Tshingila
 * @version : 1.0
 * @since : 2021-04-19
 */
@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class PaymentSystemService implements CrudService<PaymentSystemDto, Long>{

    private PaymentSystemMapper paymentSystemMapper;
    private PaymentSystemRepository paymentSystemRepository;

    /**
     * @param entity : Entity to be saved
     * @return PaymentSystemDto : Saved entity
     * @throws IdNotFoundException : Exception thrown when the id is not found
     * @throws IdIsNullException : Exception thrown when the id is null
     * @throws IdNotNullException : Exception thrown when the id is not null
     */
    @Override
    public PaymentSystemDto save(PaymentSystemDto entity) throws IdNotFoundException, IdIsNullException, IdNotNullException {
        log.info("execution of the method: save(PaymentSystemDto entity) in the class: PaymentSystemService");
        PaymentSystem paymentSystem = paymentSystemMapper.dtoFromEntity(entity);
        PaymentSystemDto paymentSystemDto = paymentSystemMapper.entityFromDto(paymentSystemRepository.save(paymentSystem));
        log.info("the creation of the entity : {"+ paymentSystemDto+"}");
        return paymentSystemDto;
    }

    /**
     * @param entity : Entity to be updated
     * @return PaymentSystemDto : Updated entity
     * @throws IdNotFoundException  : Exception thrown when the id is not found
     * @throws IdIsNullException : Exception thrown when the id is null
     * @throws IdNotNullException   : Exception thrown when the id is not null
     */
    @Override
    public PaymentSystemDto update(PaymentSystemDto entity) throws IdNotFoundException, IdIsNullException, IdNotNullException {
        log.info("execution of the method: update(PaymentSystemDto entity) in the class: PaymentSystemService");
        if(entity.getId() == null) throw new IdIsNullException("The id is null");
        paymentSystemRepository.findById(entity.getId()).orElseThrow(IdNotFoundException::new);
        PaymentSystemDto paymentSystemDto = save(entity);
        log.info("the update of the entity : {"+ paymentSystemDto+"}");
        return paymentSystemDto;
    }

    /**
     * @param entity : Entity to be removed
     * @return boolean : True if the entity is removed
     * @throws IdNotFoundException : Exception thrown when the id is not found
     * @throws IdIsNullException    : Exception thrown when the id is null
     * @throws IdNotNullException   : Exception thrown when the id is not null
     */
    @Override
    public boolean remove(PaymentSystemDto entity) throws IdNotFoundException, IdIsNullException, IdNotNullException {
        log.info("execution of the method: remove(PaymentSystemDto entity) in the class: PaymentSystemService");
        if(entity.getId() == null) throw new IdIsNullException("The id is null");
        paymentSystemRepository.findByIdAndIsRemoveFalse(entity.getId()).orElseThrow(IdNotFoundException::new);
        entity.setRemove(true);
        PaymentSystemDto paymentSystemDto = update(entity);
        log.info("the deletion of the entity : {"+ paymentSystemDto+"}");
        return paymentSystemDto.isRemove();
    }

    /**
     * @param id : Identifier of the entity to be removed
     * @return boolean : True if the entity is removed
     * @throws IdNotFoundException : Exception thrown when the id is not found
     * @throws IdIsNullException   : Exception thrown when the id is null
     * @throws IdNotNullException : Exception thrown when the id is not null
     */
    @Override
    public boolean removeById(Long id) throws IdNotFoundException, IdIsNullException, IdNotNullException {
        log.info("execution of the method: removeById(Long id) in the class: PaymentSystemService");
        if (id == null) throw new IdIsNullException("The id is null");
        PaymentSystemDto paymentSystemDto = paymentSystemMapper.entityFromDto(
                paymentSystemRepository.findByIdAndIsRemoveFalse(id).
                        orElseThrow(IdNotFoundException::new));
        paymentSystemDto.setRemove(true);
        paymentSystemDto = update(paymentSystemDto);
        log.info("the deletion of the entity : {"+ paymentSystemDto+"}");
        return paymentSystemDto.isRemove();
    }

    /**
     * @param id : Identifier of the entity to be restored
     * @return  boolean : True if the entity is restored
     * @throws IdNotFoundException : Exception thrown when the id is not found
     * @throws IdIsNullException  : Exception thrown when the id is null
     * @throws IdNotNullException : Exception thrown when the id is not null
     */
    @Override
    public boolean restore(Long id) throws IdNotFoundException, IdIsNullException, IdNotNullException {
        log.info("execution of the method: restore(Long id) in the class: PaymentSystemService");
        if (id == null) throw new IdIsNullException("The id is null");
        PaymentSystemDto paymentSystemDto = paymentSystemMapper.entityFromDto(
                paymentSystemRepository.findByIdAndIsRemoveTrue(id).
                        orElseThrow(IdNotFoundException::new));
        paymentSystemDto.setRemove(false);
        paymentSystemDto = update(paymentSystemDto);
        log.info("the restoration of the entity : {"+ paymentSystemDto+"}");
        return paymentSystemDto.isRemove();
    }

    /**
     * @param id : Identifier of the entity to be checked
     * @return boolean : True if the entity exists
     * @throws IdNotFoundException : Exception thrown when the id is not found
     * @throws IdIsNullException : Exception thrown when the id is null
     */
    @Override
    public boolean isExist(Long id) throws IdNotFoundException, IdIsNullException {
        log.info("execution of the method: isExist(Long id) in the class: PaymentSystemService");
        if (id == null) throw new IdIsNullException("The id is null");
        paymentSystemRepository.findById(id).orElseThrow(IdNotFoundException::new);
        log.info("the existence of the entity : {"+ true+"}");
        return true;
    }

    /**
     * @param id : Identifier of the entity to be checked
     * @return boolean : True if the entity is removed
     * @throws IdNotFoundException : Exception thrown when the id is not found
     */
    @Override
    public boolean isRemove(Long id) throws IdNotFoundException, IdIsNullException {
        log.info("execution of the method: isRemove(Long id) in the class: PaymentSystemService");
        if (id == null) throw new IdIsNullException("The id is null");
        PaymentSystem paymentSystem =  paymentSystemRepository.findByIdAndIsRemoveTrue(id).orElseThrow(IdNotFoundException::new);
        boolean isRemove = paymentSystem.isRemove();
        log.info("the entity is removed : {"+ isRemove+"}");
        return isRemove;
    }

    /**
     * @param id : Identifier of the entity to be found
     * @return PaymentSystemDto : Entity found
     * @throws IdNotFoundException : Exception thrown when the id is not found
     * @throws IdIsNullException : Exception thrown when the id is null
     */
    @Override
    public PaymentSystemDto findById(Long id) throws IdNotFoundException, IdIsNullException {
        log.info("execution of the method: findById(Long id) in the class: PaymentSystemService");
        if (id == null) throw new IdIsNullException("The id is null");
        PaymentSystemDto paymentSystemDto = paymentSystemMapper.entityFromDto(
                paymentSystemRepository.findByIdAndIsRemoveFalse(id).
                        orElseThrow(IdNotFoundException::new));
        log.info("the entity found : {"+ paymentSystemDto+"}");
        return paymentSystemDto;
    }

    /**
     * This method finds an entity deleted in the database per page
     * @return List<PaymentSystemDto> : List of entities
     */
    @Override
    public List<PaymentSystemDto> findAll() {
        log.info("execution of the method: findAll() in the class: PaymentSystemService");
        List<PaymentSystemDto> paymentSystemDtos = paymentSystemMapper.entitiesFromDtos(paymentSystemRepository.findByIsRemoveFalse());
        log.info("the list of entities found : {"+ paymentSystemDtos+"}");
        return paymentSystemDtos;
    }

    /**
     * This method finds an entity  in the database per page
     * @param designation : Designation of the entity to be found
     * @return List<PaymentSystemDto> : List of entities
     */
    public List<PaymentSystemDto> findByDesignationContainsIgnoreCaseAndIsRemoveFalse(String designation) {
        log.info("execution of the method: findByDesignationContainsIgnoreCaseAndIsRemoveFalse(String designation) in the class: PaymentSystemService");
        List<PaymentSystemDto> paymentSystemDtos = paymentSystemMapper.entitiesFromDtos(
                paymentSystemRepository.findByDesignationContainsIgnoreCaseAndIsRemoveFalse(designation));
        log.info("the list of entities found : {"+ paymentSystemDtos+"}");
        return paymentSystemDtos;
    }

    /**
     * This method finds an entity  in the database per page
     * @param institutionName : Institution name of the entity to be found
     * @return List<PaymentSystemDto> : List of entities
     */
    public List<PaymentSystemDto> findByInstitutionNameContainsIgnoreCaseAndIsRemoveFalse(String institutionName) {
        log.info("execution of the method: findByInstitutionNameContainsIgnoreCaseAndIsRemoveFalse(String institutionName) in the class: PaymentSystemService");
        List<PaymentSystemDto> paymentSystemDtos = paymentSystemMapper.entitiesFromDtos(
                paymentSystemRepository.findByInstitutionNameContainsIgnoreCaseAndIsRemoveFalse(institutionName));
        log.info("the list of entities found : {"+ paymentSystemDtos+"}");
        return paymentSystemDtos;
    }

    /**
     * This method finds an entity  in the database per page
     * @param institutionName : Institution name of the entity to be found
     * @param startDate : Start date of the entity to be found
     * @param endDate : End date of the entity to be found
     * @return List<PaymentSystemDto> : List of entities
     */
    public List<PaymentSystemDto> findByInstitutionNameContainsIgnoreCaseAndIsRemoveFalseAndPaymentDateBetween(String institutionName, LocalDate startDate, LocalDate endDate) {
        log.info("execution of the method: findByInstitutionNameContainsIgnoreCaseAndIsRemoveFalseAndPaymentDateBetween(String institutionName, LocalDate startDate, LocalDate endDate) in the class: PaymentSystemService");
        List<PaymentSystemDto> paymentSystemDtos = paymentSystemMapper.entitiesFromDtos(
                paymentSystemRepository.findByInstitutionNameContainsIgnoreCaseAndIsRemoveFalseAndPaymentDateBetween(institutionName, startDate, endDate));
        log.info("the list of entities found : {"+ paymentSystemDtos+"}");
        return paymentSystemDtos;
    }
    /**
     * This method finds an entity  deleted in the database
     * @param designation : Designation of the entity to be found
     * @return List<PaymentSystemDto> : List of entities
     */
    public List<PaymentSystemDto> findByDesignationContainsIgnoreCaseAndIsRemoveTrue(String designation) {
        log.info("execution of the method: findByDesignationContainsIgnoreCaseAndIsRemoveTrue(String designation) in the class: PaymentSystemService");
        List<PaymentSystemDto> paymentSystemDtos = paymentSystemMapper.entitiesFromDtos(
                paymentSystemRepository.findByDesignationContainsIgnoreCaseAndIsRemoveTrue(designation));
        log.info("the list of entities found : {"+ paymentSystemDtos+"}");
        return paymentSystemDtos;
    }
    /**
     * This method finds an entity  deleted in the database per page
     * @param institutionName : Institution name of the entity to be found
     * @return List<PaymentSystemDto> : List of entities
     */
    public List<PaymentSystemDto> findByInstitutionNameContainsAndIsRemoveTrue(String institutionName) {
        log.info("execution of the method: findByInstitutionNameContainsAndIsRemoveTrue(String institutionName) in the class: PaymentSystemService");
        List<PaymentSystemDto> paymentSystemDtos = paymentSystemMapper.entitiesFromDtos(
                paymentSystemRepository.findByInstitutionNameContainsIgnoreCaseAndIsRemoveTrue(institutionName));

        log.info("the list of entities found : {"+ paymentSystemDtos+"}");
        return paymentSystemDtos;
    }

    /**
     * This method finds an entity  deleted in the database per page
     * @param institutionName : Institution name of the entity to be found
     * @param paymentDate : Payment date of the entity to be found
     * @return List<PaymentSystemDto> : List of entities
     */
    public List<PaymentSystemDto> findByInstitutionNameContainsAndIsRemoveTrueAndPaymentDate(String institutionName, LocalDate paymentDate) {
        log.info("execution of the method: findByInstitutionNameContainsAndIsRemoveTrueAndPaymentDate(String institutionName, LocalDate paymentDate) in the class: PaymentSystemService");
        List<PaymentSystemDto> paymentSystemDtos = paymentSystemMapper.entitiesFromDtos(
                paymentSystemRepository.findByInstitutionNameContainsIgnoreCaseAndIsRemoveTrueAndPaymentDate(institutionName, paymentDate));
        log.info("the list of entities found : {"+ paymentSystemDtos+"}");
        return paymentSystemDtos;
    }
    /**
     * This method findByIsRemoveFalseAndPaymentMethodAndPaymentDate an entity  in the database per page
     * @param paymentMethodString : Payment method of the entity to be found
     * @param paymentDate : Payment date of the entity to be found
     * @return Map<String, String> : Map of entities
     */
    public Map<String, Object> findByIsRemoveFalseAndPaymentMethodAndPaymentDate(String paymentMethodString, LocalDate paymentDate, int page, int size) {
        log.info("execution of the method: findByIsRemoveFalseAndPaymentMethodContainsIgnoreCaseAndPaymentDate(String paymentMethod, LocalDate paymentDate, int page, int size) in the class: PaymentSystemService");
        PaymentMethode paymentMethod = PaymentMethode.valueOf(paymentMethodString);
        Map<String, Object> paymentSystemDtos = paymentSystemMapper.entitiesFromDtosPage(
                paymentSystemRepository.findByIsRemoveFalseAndPaymentMethodAndPaymentDate(paymentMethod, paymentDate, PageRequest.of(page, size)));
        log.info("the list of entities found : {"+ paymentSystemDtos+"}");
        return paymentSystemDtos;
    }

    /**
     * This method findByIsRemoveFalseAndPaymentMethodAndPaymentDateBetween an entity  in the database per page
     * @param paymentMethodString : Payment method of the entity to be found
     * @param startDate : Start date of the entity to be found
     * @param endDate : End date of the entity to be found
     * @return Map<String, String> : Map of entities
     */
    public Map<String, Object> findByIsRemoveFalseAndPaymentMethodAndPaymentDateBetween(String paymentMethodString, LocalDate startDate, LocalDate endDate, int page, int size) {
        log.info("execution of the method: findByIsRemoveFalseAndPaymentMethodContainsIgnoreCaseAndPaymentDateBetween(String paymentMethod, LocalDate startDate, LocalDate endDate) in the class: PaymentSystemService");
        PaymentMethode paymentMethod = PaymentMethode.valueOf(paymentMethodString);
        Map<String, Object> paymentSystemDtos = paymentSystemMapper.entitiesFromDtosPage(
                paymentSystemRepository.findByIsRemoveFalseAndPaymentMethodAndPaymentDateBetween(paymentMethod, startDate, endDate, PageRequest.of(page, size)));
        log.info("the list of entities found : {"+ paymentSystemDtos+"}");
        return paymentSystemDtos;
    }

    /**
     * This method finds an entity  in the database per page
     * @param institutionName : Institution name of the entity to be found
     * @param startDate : Start date of the entity to be found
     * @param endDate : End date of the entity to be found
     * @return Map<String, String> : Map of entities
     */
    public Map<String, Object> findByIsRemoveFalseAndInstitutionNameContainsIgnoreCaseAndPaymentDateBetween(String institutionName, LocalDate startDate, LocalDate endDate, int page, int size) {
        log.info("execution of the method: findByIsRemoveFalseAndInstitutionNameContainsIgnoreCaseAndPaymentDateBetween(String institutionName, LocalDate startDate, LocalDate endDate) in the class: PaymentSystemService");
        Map<String, Object> paymentSystemDtos = paymentSystemMapper.entitiesFromDtosPage(
                paymentSystemRepository.findByIsRemoveFalseAndInstitutionNameContainsIgnoreCaseAndPaymentDateBetween(institutionName, startDate, endDate, PageRequest.of(page, size)));
        log.info("the list of entities found : {"+ paymentSystemDtos+"}");
        return paymentSystemDtos;
    }

    /**
     * this the method findByIdAndRemoveTrue(Long id) in the class PaymentSystemService
     * @param id : the id of the entity to find
     * @return PaymentSystemDto : the entity found
     * @throws IdNotFoundException : if the id is not found
     */
    public PaymentSystemDto findByIdAndRemoveTrue(Long id) throws IdNotFoundException {
        log.info("execution of the method: findByIdAndRemoveTrue(Long id) in the class: PaymentSystemService");
        PaymentSystemDto paymentSystemDto = paymentSystemMapper.entityFromDto(
                paymentSystemRepository.findByIdAndIsRemoveTrue(id).
                        orElseThrow(IdNotFoundException::new));
        log.info("the entity found : {"+ paymentSystemDto+"}");
        return paymentSystemDto;
    }
}
