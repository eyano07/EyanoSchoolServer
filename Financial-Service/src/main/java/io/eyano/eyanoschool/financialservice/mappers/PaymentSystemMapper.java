package io.eyano.eyanoschool.financialservice.mappers;

import io.eyano.eyanoschool.financialservice.dtos.PaymentSystemDto;
import io.eyano.eyanoschool.financialservice.entities.PaymentSystem;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Mapper class for PaymentSystem
 * @author : Pascal Tshingila
 * @version : 1.0
 * @since : 2021-04-19
 */
@Service
@Transactional
@AllArgsConstructor
public class PaymentSystemMapper implements Mapper<PaymentSystemDto, PaymentSystem>{
    ModelMapper modelMapper;
    /**
     * @param paymentSystem : Entity to be converted to DTO
     * @return PaymentSystemDto
     */
    @Override
    public PaymentSystemDto entityFromDto(PaymentSystem paymentSystem) {
        return modelMapper.map(paymentSystem, PaymentSystemDto.class);
    }

    /**
     * @param paymentSystemDto : Entity to be converted to DTO
     * @return PaymentSystem
     */
    @Override
    public PaymentSystem dtoFromEntity(PaymentSystemDto paymentSystemDto) {
        return modelMapper.map(paymentSystemDto, PaymentSystem.class);
    }

    /**
     * @param paymentSystemDtos  : List of DTOs to be converted to Entities
     * @return List<PaymentSystem>
     */
    @Override
    public List<PaymentSystem> dtosFromEntities(List<PaymentSystemDto> paymentSystemDtos) {
        List<PaymentSystem> paymentSystems =  new ArrayList<>();
        for(PaymentSystemDto paymentSystemDto : paymentSystemDtos) {
            paymentSystems.add(dtoFromEntity(paymentSystemDto));
        }
        return paymentSystems;
    }

    /**
     * @param paymentSystems  : List of Entities to be converted to DTOs
     * @return List<PaymentSystemDto>
     */
    @Override
    public List<PaymentSystemDto> entitiesFromDtos(List<PaymentSystem> paymentSystems) {
        List<PaymentSystemDto> paymentSystemDtos = new ArrayList<>();
        for(PaymentSystem paymentSystem : paymentSystems) {
            paymentSystemDtos.add(entityFromDto(paymentSystem));
        }
        return paymentSystemDtos;
    }

    public Map<String,Object> entitiesFromDtosPage(Page<PaymentSystem> paymentSystems) {
        Map<String, Object> content = new HashMap<>();
        content.put("content", entitiesFromDtos(paymentSystems.getContent()));
        content.put("totalPages", paymentSystems.getTotalPages());
        content.put("totalElements", paymentSystems.getTotalElements());
        content.put("size", paymentSystems.getSize());
        content.put("number", paymentSystems.getNumber());
        content.put("numberOfElements", paymentSystems.getNumberOfElements());
        content.put("isLast", paymentSystems.isLast());
        content.put("isEmpty", paymentSystems.isEmpty());
        content.put("hasContent", paymentSystems.hasContent());
        content.put("hasNext", paymentSystems.hasNext());
        content.put("hasPrevious", paymentSystems.hasPrevious());
        content.put("isFirst", paymentSystems.isFirst());
        content.put("toString", paymentSystems.toString());
        return content;
    }
}
