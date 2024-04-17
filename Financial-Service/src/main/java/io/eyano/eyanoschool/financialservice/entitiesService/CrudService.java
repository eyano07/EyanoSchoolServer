package io.eyano.eyanoschool.financialservice.entitiesService;

import io.eyano.eyanoschool.financialservice.exceptions.IdIsNullException;
import io.eyano.eyanoschool.financialservice.exceptions.IdNotFoundException;
import io.eyano.eyanoschool.financialservice.exceptions.IdNotNullException;

import java.util.List;

public interface CrudService<E,ID> {
    E save(E entity) throws IdNotFoundException, IdIsNullException, IdNotNullException;
    E update(E entity) throws IdNotFoundException, IdIsNullException, IdNotNullException;
    boolean remove(E entity) throws IdNotFoundException, IdIsNullException, IdNotNullException;
    boolean removeById(ID id) throws IdNotFoundException, IdIsNullException, IdNotNullException;
    boolean restore(ID id) throws IdNotFoundException, IdIsNullException, IdNotNullException;
    boolean isExist(ID id) throws IdNotFoundException, IdIsNullException;
    boolean isRemove(ID id) throws IdNotFoundException, IdIsNullException;
    E findById(ID id) throws IdNotFoundException, IdIsNullException;


    List<E> findAll();


}
