package io.eyano.eyanoschool.feesservice.entitiesService;

import io.eyano.eyanoschool.feesservice.exceptions.IdIsNullException;
import io.eyano.eyanoschool.feesservice.exceptions.IdNotFoundException;
import io.eyano.eyanoschool.feesservice.exceptions.IdNotNullException;

import java.util.List;

public interface CrudService<E,ID> {
    E save(E entity) throws IdNotFoundException, IdIsNullException, IdNotNullException;
    E update(E entity) throws IdNotFoundException, IdIsNullException, IdNotNullException;
    boolean remove(E entity) throws IdNotFoundException, IdIsNullException, IdNotNullException;
    boolean removeById(ID id) throws IdNotFoundException, IdIsNullException, IdNotNullException;
    boolean restore(ID id) throws IdNotFoundException, IdIsNullException, IdNotNullException;
    boolean isExist(ID id) throws IdNotFoundException, IdIsNullException;
    boolean isRemove(ID id) throws IdNotFoundException;

    List<E> findAll();


}
