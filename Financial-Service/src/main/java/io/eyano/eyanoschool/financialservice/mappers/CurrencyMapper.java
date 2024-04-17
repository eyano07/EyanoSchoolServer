package io.eyano.eyanoschool.financialservice.mappers;

import io.eyano.eyanoschool.financialservice.dtos.CurrencyDto;
import io.eyano.eyanoschool.financialservice.entities.Currency;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
/**
 * Mapper class for Currency
 * @see CurrencyDto
 * @see Currency
 * @author : Pascal Tshingila
 * @version : 1.0
 * @since : 2021-04-19
 */

@Service
@Transactional
@AllArgsConstructor
public class CurrencyMapper implements Mapper<CurrencyDto, Currency>{
    ModelMapper modelMapper;
    /**
     * @param currency : Entity to be converted to DTO
     * @return CurrencyDto
     */
    @Override
    public CurrencyDto entityFromDto(Currency currency) {
        return  modelMapper.map(currency, CurrencyDto.class);
    }

    /**
     * @param currencyDto : Entity to be converted to DTO
     * @return Currency
     */
    @Override
    public Currency dtoFromEntity(CurrencyDto currencyDto) {

        return modelMapper.map(currencyDto, Currency.class);
    }

    /**
     * @param currencyDtos : List of DTOs to be converted to Entities
     * @return List<Currency>
     */
    @Override
    public List<Currency> dtosFromEntities(List<CurrencyDto> currencyDtos) {
        List<Currency> currencies = new ArrayList<>();
        for(CurrencyDto currencyDto : currencyDtos) {
            currencies.add(dtoFromEntity(currencyDto));
        }
        return currencies;
    }

    /**
     * @param currencies : List of Entities to be converted to DTOs
     * @return List<CurrencyDto>
     */
    @Override
    public List<CurrencyDto> entitiesFromDtos(List<Currency> currencies) {
        List<CurrencyDto> currencyDtos = new ArrayList<>();
        for(Currency currency : currencies) {
            currencyDtos.add(entityFromDto(currency));
        }
        return currencyDtos;
    }
}
