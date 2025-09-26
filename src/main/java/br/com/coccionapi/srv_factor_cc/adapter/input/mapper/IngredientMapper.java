package br.com.coccionapi.srv_factor_cc.adapter.input.mapper;

import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

import br.com.coccionapi.srv_factor_cc.adapter.input.dto.requests.IngredientRequest;
import br.com.coccionapi.srv_factor_cc.adapter.input.dto.responses.IngredientResponse;
import br.com.coccionapi.srv_factor_cc.domain.model.Ingredient;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, builder = @Builder(disableBuilder = true), injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface IngredientMapper {

    Ingredient toModelRequest(IngredientRequest request);

    IngredientResponse toDTOResponse(Ingredient response);

}
