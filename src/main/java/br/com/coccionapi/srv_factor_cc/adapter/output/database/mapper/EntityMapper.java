package br.com.coccionapi.srv_factor_cc.adapter.output.database.mapper;

import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

import br.com.coccionapi.srv_factor_cc.adapter.output.database.entity.CorrectionFactorEntity;
import br.com.coccionapi.srv_factor_cc.adapter.output.database.entity.IngredientEntity;
import br.com.coccionapi.srv_factor_cc.domain.model.CorrectionFactor;
import br.com.coccionapi.srv_factor_cc.domain.model.Ingredient;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, builder = @Builder(disableBuilder = true), injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface EntityMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "grossWgt", source = "grossWeight")
    @Mapping(target = "netWgt", source = "netWeight")
    @Mapping(target = "rawWgt", source = "rawWeight")
    @Mapping(target = "cookedWgt", source = "cookedWeight")
    @Mapping(target = "regisDate", expression = "java(java.time.LocalDateTime.now())")
    IngredientEntity toSaveEntity(Ingredient modelRequest);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "grossWeight", source = "grossWgt")
    @Mapping(target = "netWeight", source = "netWgt")
    @Mapping(target = "rawWeight", source = "rawWgt")
    @Mapping(target = "cookedWeight", source = "cookedWgt")
    @Mapping(target = "registrationDate", source = "regisDate")
    Ingredient toModelIngredient(IngredientEntity save);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "ingId.id", source = "ingredientId")
    @Mapping(target = "grossWgh", source = "grossWeight")
    @Mapping(target = "netWgh", source = "netWeight")
    @Mapping(target = "ctFactor", source = "correctionFactor")
    @Mapping(target = "calcdAt", source = "calculatedAt")
    CorrectionFactorEntity toSaveFCEntity(CorrectionFactor request);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "ingredientId", source = "ingId.id")
    @Mapping(target = "grossWeight", source = "grossWgh")
    @Mapping(target = "netWeight", source = "netWgh")
    @Mapping(target = "correctionFactor", source = "ctFactor")
    @Mapping(target = "calculatedAt", source = "calcdAt")
    CorrectionFactor toModelFC(CorrectionFactorEntity save);
}
