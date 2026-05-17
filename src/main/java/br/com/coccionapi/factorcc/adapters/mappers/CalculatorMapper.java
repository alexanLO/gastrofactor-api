package br.com.coccionapi.factorcc.adapters.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import br.com.coccionapi.factorcc.adapters.input.api.calculator.dto.request.CalculatorRequest;
import br.com.coccionapi.factorcc.adapters.input.api.calculator.dto.response.CalculatorResponse;
import br.com.coccionapi.factorcc.adapters.output.persistence.entity.FoodYieldEntity;
import br.com.coccionapi.factorcc.domain.command.CalculatorCommand;
import br.com.coccionapi.factorcc.domain.enums.TypeWeightEnum;
import br.com.coccionapi.factorcc.domain.model.CalculatorVO;
import br.com.coccionapi.factorcc.domain.model.FoodYieldVO;
import br.com.coccionapi.factorcc.shared.utils.EnumUtils;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CalculatorMapper {

    @Mapping(target = "typeWeight", expression = "java(mapTypeWeight(request.getTypeWeight()))")
    CalculatorCommand toRequestModel(CalculatorRequest request);

    CalculatorResponse toResponseDTO(CalculatorVO requestModel);

    FoodYieldVO fromEntityToModel(FoodYieldEntity eFoodYieldEntity);

    default TypeWeightEnum mapTypeWeight(String typeWeight) {
        return EnumUtils.getEnumFromStringOrThrow(TypeWeightEnum.class, typeWeight);
    }

}