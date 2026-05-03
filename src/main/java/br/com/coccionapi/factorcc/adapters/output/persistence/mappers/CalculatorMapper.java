package br.com.coccionapi.factorcc.adapters.output.persistence.mappers;

import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

import br.com.coccionapi.factorcc.adapters.input.controllers.calculator.dto.request.CalculatorRequest;
import br.com.coccionapi.factorcc.adapters.input.controllers.calculator.dto.response.CalculatorResponse;
import br.com.coccionapi.factorcc.adapters.output.persistence.entity.FoodYieldEntity;
import br.com.coccionapi.factorcc.domain.command.CalculatorCommand;
import br.com.coccionapi.factorcc.domain.model.CalculatorVO;
import br.com.coccionapi.factorcc.domain.model.FoodYieldVO;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, builder = @Builder(disableBuilder = true), injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CalculatorMapper {

    CalculatorCommand toRequestModel(CalculatorRequest request);

    CalculatorResponse toResponseDTO(CalculatorVO requestModel);

    FoodYieldVO fromEntityToModel(FoodYieldEntity eFoodYieldEntity);

    
}