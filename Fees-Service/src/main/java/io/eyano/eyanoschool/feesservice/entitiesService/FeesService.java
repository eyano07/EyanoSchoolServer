package io.eyano.eyanoschool.feesservice.entitiesService;

import io.eyano.eyanoschool.feesservice.dao.FeesRepository;
import io.eyano.eyanoschool.feesservice.dao.SliceFeesRepository;
import io.eyano.eyanoschool.feesservice.dao.TypeFeesRepository;
import io.eyano.eyanoschool.feesservice.dtos.FeesDto;
import io.eyano.eyanoschool.feesservice.entities.Fees;
import io.eyano.eyanoschool.feesservice.entities.SliceFees;
import io.eyano.eyanoschool.feesservice.entities.TypeFees;
import io.eyano.eyanoschool.feesservice.exceptions.IdIsNullException;
import io.eyano.eyanoschool.feesservice.exceptions.IdNotFoundException;
import io.eyano.eyanoschool.feesservice.exceptions.IdNotFoundParamException;
import io.eyano.eyanoschool.feesservice.mappers.FeesMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * FeesService is a class that implements the CrudService interface and has generic type FeesDto and Long
 * @author : Pascal Tshingila
 * @since : 02/02/2021
 * @version : 1.0
 */
@Service @Transactional
@AllArgsConstructor
@Slf4j
public class FeesService implements CrudService<FeesDto, Long> {
    FeesMapper mapper;
    FeesRepository feesRepository;
    SliceFeesRepository sliceFeesRepository;
    TypeFeesRepository typeFeesRepository;

    /**
        * implementation of methode save from CrudService interface for saving FeesDto entity
        * @param entity : FeesDto
        * @throws IdNotFoundException (if the entity is not found)
        * @return : the FeesDto saved
     */
    @Override
    public FeesDto save(FeesDto entity) throws IdNotFoundException {
        log.info("execution of the method:save(FeesDto entity)");
        Fees fees = mapper.dtoFromEntity(entity);
        SliceFees sliceFees = sliceFeesRepository.findById(fees.getSliceFees().getId()).orElseThrow(IdNotFoundException::new);
        fees.setSliceFees(sliceFees);
        TypeFees typeFees = typeFeesRepository.findById(fees.getTypeFees().getId()).orElseThrow(IdNotFoundException::new);
        fees.setTypeFees(typeFees);
        FeesDto feesDto = mapper.entityFromDTO(feesRepository.save(fees));
        log.info("the creation of the entity : {"+ feesDto+"}");
        return feesDto;
    }
    /**
        * implementation of methode update from CrudService interface for updating FeesDto entity
        * @param entity : FeesDto
        * @throws IdNotFoundException (if the entity is not found)
        * @return : FeesDto
     */
    @Override
    public FeesDto update(FeesDto entity) throws IdNotFoundException {
        log.info("execution of the method:update(FeesDto entity)");
        feesRepository.findById(entity.getId()).orElseThrow(IdNotFoundException::new);
        FeesDto feesDto = save(entity);
        log.info("entity update : {" +feesDto+"}");
        return feesDto;
    }
    /**
        * implementation of methode remove from CrudService interface for removing FeesDto entity
        * @param entity : FeesDto
        * @throws IdNotFoundException (if the entity is not found)
        * @return : boolean
     */
    @Override
    public boolean remove(FeesDto entity) throws IdNotFoundException{
        log.info("execution of the method:remove(FeesDto entity)");
        Fees fees = feesRepository.findById(entity.getId()).orElseThrow(IdNotFoundException::new);
        FeesDto feesDto = mapper.entityFromDTO(fees);
        feesDto.setRemove(true);

        FeesDto result = update(feesDto);
        log.info("the entity : {"+ result+"} is deleted in the database");
        return result.isRemove();
    }
    /**
        * implementation of methode removeById from CrudService interface for removing FeesDto entity by id
        * @param id : Long
        * @throws IdNotFoundException (if the entity is not found)
        * @return : boolean
     */
    @Override
    public boolean removeById(Long id) throws IdNotFoundException, IdIsNullException {
        log.info("execution of the method:removeById(Long id)") ;
        if(id==null){
            throw new IdIsNullException("id is null");
        }
        Fees fees = feesRepository.findById(id).orElseThrow(IdNotFoundException::new);
        FeesDto feesDto = mapper.entityFromDTO(fees);
        feesDto.setRemove(true);

        boolean remove = update(feesDto).isRemove();
        log.info("the entity : {"+ feesDto+"} is deleted in the database from id");
        return remove;
    }
    /**
        * implementation of methode restore from CrudService interface for restoring FeesDto entity
        * @param id : Long
        * @throws IdNotFoundException (if the entity is not found)
        * @return : boolean
     */
    @Override
    public boolean restore(Long id) throws IdNotFoundException {
        log.info("execution of the method:restore(Long id)") ;
        Fees fees = feesRepository.findByIdAndRemoveIsTrue(id).orElseThrow(IdNotFoundException::new);
        FeesDto feesDto = mapper.entityFromDTO(fees);
        feesDto.setRemove(false);
        update(feesDto);
        log.info("the entity : {"+ feesDto+"} is restored in the database");
        return feesDto.isRemove();
    }

    /**
        * implementation of methode isExist from CrudService interface for checking if FeesDto entity exist
        * @param id : Long
        * @throws IdNotFoundException (if the entity is not found)
        * @return : boolean
     */
    @Override
    public boolean isExist(Long id) throws IdNotFoundException {
        log.info("execution of the method:isExist(Long id)") ;
        Fees fees = feesRepository.findById(id).orElseThrow(IdNotFoundException::new);
        log.info("the entity : {"+ fees+"} exists in the database");
        return true;
    }
    /**
        * implementation of methode isRemove from CrudService interface for checking if FeesDto entity is removed
        * @param id : Long
        * @throws IdNotFoundException (if the entity is not found)
        * @return : boolean
     */
    @Override
    public boolean isRemove(Long id) throws IdNotFoundException {
        log.info("execution of the method:isRemove(Long id)") ;
        Fees fees = feesRepository.findById(id).orElseThrow(IdNotFoundException::new);
        boolean remove = fees.isRemove();
        log.info("the remove attribute of the entity {"+ fees+"} is : "+fees.isRemove());
        return remove;
    }
    /**
        * implementation of methode findById from CrudService interface for finding FeesDto entity by id
        * @param id : Long
        * @throws IdNotFoundException (if the entity is not found)
        * @return : FeesDto
     */
    public FeesDto findById(Long id) throws IdNotFoundException {
        log.info("execution of the method:findById(Long id)") ;
        Fees fees = feesRepository.findFeesByIdAndRemoveIsFalse(id).orElseThrow(IdNotFoundException::new);
        FeesDto feesDto = mapper.entityFromDTO(fees);
        log.info("end of method execution:findById() Entity : "+feesDto) ;
        return feesDto;
    }
    /**
        * implementation of methode findByIdRemoveIsTrue for finding FeesDto entity by id and remove attribute is true
        * @param id : Long
        * @throws IdNotFoundException (if the entity is not found)
        * @return : FeesDto
     */
    public FeesDto findByIdRemoveIsTrue(Long id) throws IdNotFoundException {
        log.info("execution of the method:findByIdRemoveIsTrue(Long)") ;
        Fees fees = feesRepository.findByIdAndRemoveIsTrue(id).orElseThrow(IdNotFoundException::new);
        FeesDto feesDto = mapper.entityFromDTO(fees);
        log.info("end of method execution:findByIdRemoveIsTrue() Entity : "+feesDto) ;
        return feesDto;
    }
    /**
        * implementation of methode findByIdClassFessAndIdSchoolYear for finding FeesDto entity by idClass and idSchoolYear
        * @param idClass : Long
        * @param idSchoolYear : Long
        * @throws IdNotFoundParamException (if the entity is not found)
        * @return : List<FeesDto>
     */
    public List<FeesDto>  findByIdClassFessAndIdSchoolYear(Long idClass, long idSchoolYear) throws IdNotFoundParamException {
        log.info("execution of the method:findByIdClassFessAndIdSchoolYear(Long, Long)") ;
        List<Fees> feesList = feesRepository.findByIdClassFessAndIdSchoolYearAndRemoveIsFalse(idClass, idSchoolYear);
        if(feesList.isEmpty()){
            throw new IdNotFoundParamException("class id or schoolYear id not found");
        }
        List<FeesDto> feesDtos = mapper.entitiesFromDtos(feesList);
        log.info("end of method execution:findByIdClassFessAndIdSchoolYear() : "+feesDtos) ;
        return feesDtos;
    }
   /**
        * implementation of methode findByIdClassFessAndIdSchoolYearAndSliceFeesId for finding FeesDto entity by idClass, idSchoolYear and typeFeesId
        * @param idClass : Long
        * @param idSchoolYear : Long
        * @param sliceFeesId : Long
        * @throws IdNotFoundParamException (if the entity is not found)
        * @return : List<FeesDto>
     */
    public  List<FeesDto>  findByIdClassFessAndIdSchoolYearAndSliceFeesId(Long idClass, long idSchoolYear,long sliceFeesId) throws IdNotFoundParamException {
        log.info("execution of the method:findByIdClassFessAndIdSchoolYearAndSliceFeesId(Long, Long,Long)") ;
        List<Fees> feesList= feesRepository.findByIdClassFessAndIdSchoolYearAndSliceFeesIdAndRemoveIsFalse(idClass, idSchoolYear,sliceFeesId);
        if(feesList.isEmpty()){
            throw new IdNotFoundParamException("class id or schoolYear id or Type fees id not found");
        }
        List<FeesDto> feesDtoList = mapper.entitiesFromDtos(feesList);
        log.info("end of method execution:findByIdClassFessAndIdSchoolYearAndSliceFeesId() Entity : "+feesDtoList) ;
        return feesDtoList;
    }
    /**
        * implementation of methode findAll from CrudService interface for finding all FeesDto entity
        * @return List<FeesDto>
     */
    @Override
    public List<FeesDto> findAll() {
        log.info("execution of the method:findAll()") ;
        List<FeesDto> feesDtoList = mapper.entitiesFromDtos(feesRepository.findFeesByRemoveIsFalse());
        log.info("end of method execution:findAll()") ;
        return feesDtoList;
    }
    /**
        * implementation of methode findFeesByRemoveIsFalseAndDesignationContains for finding all FeesDto entity per page
        * @param page : int
        * @param size : int
        * @return Map<String,Object> : detail page
     */
    public Map<String,Object> findFeesByRemoveIsFalseAndDesignationContains(String tag, int page, int size){
        log.info("execution of the method:findFeesByRemoveIsFalseAndDesignationContains(int, int)") ;
        Map<String,Object> feesPage = mapper.entitiesFromDtosPage(feesRepository.findFeesByRemoveIsFalseAndDesignationContains(tag,PageRequest.of(page, size)));
        log.info("end of method execution:findFeesByRemoveIsFalseAndDesignationContains()") ;
        return feesPage;
    }
    /**
        * implementation of methode findFeesByRemoveIsTrueAndDesignationContains for finding all FeesDto entity that are removed and contain tag
        * @param page : int
        * @param size : int
        * @param tag : String
        * @return Map<String,Object> : detail page
     */
    public Map<String,Object> findFeesByRemoveIsTrueAndDesignationContains(String tag, int page, int size){
        log.info("execution of the method:findFeesByRemoveIsTrueAndDesignationContains(String,int, int)") ;
        Map<String,Object> feesPage = mapper.entitiesFromDtosPage(feesRepository.findFeesByRemoveIsTrueAndDesignationContains(tag,PageRequest.of(page, size)));
        log.info("end of method execution:findFeesByRemoveIsTrueAndDesignationContains()") ;
        return feesPage;
    }
    /**
        * implementation of methode findByIdSchoolYearAndRemoveIsFalseAndDesignationContains for finding all FeesDto entity by idSchoolYear and designation
        * @param idSchoolYear : Long
        * @param designation : String
        * @return List<FeesDto>
     */
    public List<FeesDto> findByIdSchoolYearAndRemoveIsFalseAndDesignationContains(Long idSchoolYear, String designation){
        log.info("execution of the method:findByIdSchoolYearAndRemoveIsFalseAndDesignationContains(Long, String)") ;
        List<Fees> feesList = feesRepository.findByIdSchoolYearAndRemoveIsFalseAndDesignationContains(idSchoolYear, designation);
        List<FeesDto> feesDtoList = mapper.entitiesFromDtos(feesList);
        log.info("end of method execution:findByIdSchoolYearAndRemoveIsFalseAndDesignationContains()") ;
        return feesDtoList;
    }
    /**
        * implementation of methode findByIdSchoolYearAndRemoveIsFalseAndDesignationContains for finding all FeesDto entity by idSchoolYear and designation per page
        * @param idSchoolYear : Long
        * @param designation : String
        * @param page : int
        * @param size : int
        * @return Map<String,Object> : detail page
     */
    public Map<String,Object> findByIdSchoolYearAndRemoveIsFalseAndDesignationContains(Long idSchoolYear, String designation, int page, int size){
        log.info("execution of the method:findByIdSchoolYearAndRemoveIsFalseAndDesignationContains(Long, String, int, int)") ;
        Map<String,Object> feesPage = mapper.entitiesFromDtosPage(feesRepository.findByIdSchoolYearAndRemoveIsFalseAndDesignationContains(idSchoolYear, designation, PageRequest.of(page, size)));
        log.info("end of method execution:findByIdSchoolYearAndRemoveIsFalseAndDesignationContains()") ;
        return feesPage;
    }
    /**
        * implementation of methode findByIdSchoolYearAndTypeFeesIdAndRemoveIsFalseAndIdClassFess for finding all FeesDto entity by idSchoolYear, idTypeFees and idClass
        * @param idClass : Long
        * @param idSchoolYear : Long
        * @param idTypeFees : Long
        * @return List<FeesDto>
     */
    public List<FeesDto> findByIdSchoolYearAndTypeFeesIdAndRemoveIsFalseAndIdClassFess(long idClass,Long idSchoolYear, Long idTypeFees){
        log.info("execution of the method:findByIdSchoolYearAndTypeFeesIdAndRemoveIsFalseAndIdClassFess(long, long, long)") ;
        List<Fees> feesList = feesRepository.findByIdSchoolYearAndTypeFeesIdAndRemoveIsFalseAndIdClassFess(idSchoolYear, idTypeFees, idClass);
        List<FeesDto> feesDtoList = mapper.entitiesFromDtos(feesList);
        log.info("end of method execution:findByIdSchoolYearAndTypeFeesIdAndRemoveIsFalseAndIdClassFess()") ;
        return feesDtoList;
    }
    /**
        * implementation of methode findAllRemoveIsTrue for finding all FeesDto entity that are removed
        * @return List<FeesDto>
     */
    public List<FeesDto> findAllRemoveIsTrue() {
        log.info("execution of the method:findAllRemoveIsTrue()") ;
        List<FeesDto> feesDtoList = mapper.entitiesFromDtos(feesRepository.findFeesByRemoveIsTrue());
        log.info("end of method execution:findAllRemoveIsTrue") ;
        return feesDtoList;
    }
    /**
        * implementation of methode findAllDelete for finding all FeesDto entity that are removed
        * @param designation : String
        * @param idClass : Long
        * @param idSchoolYear : Long
        * @return List<FeesDto>
     */
    public List<FeesDto> findByDesignationContainsAndRemoveIsFalseAndIdClassFessAndIdSchoolYear(String designation,long idClass, long idSchoolYear){
        log.info("execution of the method:findByDesignationContainsAndRemoveIsFalseAndIdClassFessAndIdSchoolYear(String,long, long)") ;
        List<Fees> feesList = feesRepository.findByDesignationContainsAndRemoveIsFalseAndIdClassFessAndIdSchoolYear(designation, idClass, idSchoolYear);
        List<FeesDto> feesDtoList = mapper.entitiesFromDtos(feesList);
        log.info("end of method execution:findByDesignationContainsAndRemoveIsFalseAndIdClassFessAndIdSchoolYear") ;
        return feesDtoList;
    }

    /**
        * implementation of methode findByIdClassFessAndIdSchoolYearAndTypeFeesIdAndRemoveIsTrue for finding all FeesDto entity by idClass, idSchoolYear and typeFeesId
        * @param idClass : Long
        * @param idSchoolYear : Long
        * @param idtypeFees : Long
        * @return List<FeesDto>
     */
    public List<FeesDto> findByIdClassFessAndIdSchoolYearAndTypeFeesIdAndRemoveIsTrue(long idClass, long idSchoolYear,long idtypeFees){
        log.info("execution of the method:findByIdClassFessAndIdSchoolYearAndTypeFeesIdAndRemoveIsTrue(long, long, long)") ;
        List<Fees> feesList = feesRepository.findByIdClassFessAndIdSchoolYearAndTypeFeesIdAndRemoveIsTrue(idClass, idSchoolYear, idtypeFees);
        List<FeesDto> feesDtoList = mapper.entitiesFromDtos(feesList);
        log.info("end of method execution:findByIdClassFessAndIdSchoolYearAndTypeFeesIdAndRemoveIsTrue") ;
        return feesDtoList;
    }


}
