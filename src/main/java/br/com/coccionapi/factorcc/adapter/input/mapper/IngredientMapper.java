package br.com.coccionapi.factorcc.adapter.input.mapper;

import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

import br.com.coccionapi.factorcc.adapter.input.dto.requests.IngredientRequest;
import br.com.coccionapi.factorcc.adapter.input.dto.responses.CookingFactorResponse;
import br.com.coccionapi.factorcc.adapter.input.dto.responses.CorrectionFactorResponse;
import br.com.coccionapi.factorcc.adapter.input.dto.responses.IngredientResponse;
import br.com.coccionapi.factorcc.domain.model.CookingFactor;
import br.com.coccionapi.factorcc.domain.model.CorrectionFactor;
import br.com.coccionapi.factorcc.domain.model.Ingredient;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, builder = @Builder(disableBuilder = true), injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface IngredientMapper {

    Ingredient toModelRequest(IngredientRequest request);

    IngredientResponse toDTOResponse(Ingredient response);

    CorrectionFactorResponse toCorrectionFactorResponse(CorrectionFactor calculateCorrectionFactor);

    CookingFactorResponse toCookingFactorResponse(CookingFactor calculateCorrectionFactor);

}
