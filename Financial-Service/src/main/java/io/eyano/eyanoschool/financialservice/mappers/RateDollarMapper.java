package io.eyano.eyanoschool.financialservice.mappers;

import io.eyano.eyanoschool.financialservice.dtos.RateDollarDto;
import io.eyano.eyanoschool.financialservice.entities.RateDollar;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
/**
 * Mapper class for RateDollarService
 * @author : Pascal Tshingila
 * @version : 1.0
 * @since : 2021-04-19
 */
@Service
@Transactional
@AllArgsConstructor
public class RateDollarMapper implements Mapper<RateDollarDto, RateDollar>{
    ModelMapper modelMapper;
    /**
     * @param rateDollar : Entity rateDollar to be converted to rateDollar DTO
     * @return RateDollarDto
     */
    @Override
    public RateDollarDto entityFromDto(RateDollar rateDollar) {

        return modelMapper.map(rateDollar, RateDollarDto.class);
    }

    /**
     * @param rateDollarDto : DTO rateDollar to be converted to rateDollar Entity
     * @return RateDollarService
     */
    @Override
    public RateDollar dtoFromEntity(RateDollarDto rateDollarDto) {

        return modelMapper.map(rateDollarDto, RateDollar.class);
    }

    /**
     * @param rateDollarDtos  : List of DTOs to be converted to Entities
     * @return List<RateDollarService>
     */
    @Override
    public List<RateDollar> dtosFromEntities(List<RateDollarDto> rateDollarDtos) {
        List<RateDollar> rateDollars = new ArrayList<>();
        for(RateDollarDto rateDollarDto : rateDollarDtos) {
            rateDollars.add(dtoFromEntity(rateDollarDto));
        }
        return rateDollars;
    }

    /**
     * @param rateDollars  : List of Entities to be converted to DTOs
     * @return List<RateDollarDto>
     */
    @Override
    public List<RateDollarDto> entitiesFromDtos(List<RateDollar> rateDollars) {
        List<RateDollarDto> rateDollarDtos = new ArrayList<>();
        for(RateDollar rateDollar : rateDollars) {
            rateDollarDtos.add(entityFromDto(rateDollar));
        }
        return rateDollarDtos;
    }
}
