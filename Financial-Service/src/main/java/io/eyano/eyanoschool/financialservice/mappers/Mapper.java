package io.eyano.eyanoschool.financialservice.mappers;

import java.util.List;

public interface Mapper <DTO, E>{
    DTO entityFromDto(E e);
    E dtoFromEntity(DTO dto);
    List<E> dtosFromEntities(List<DTO> dtos);
    List<DTO> entitiesFromDtos(List<E> eList);
}
