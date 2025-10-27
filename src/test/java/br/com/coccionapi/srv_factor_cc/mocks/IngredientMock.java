package br.com.coccionapi.srv_factor_cc.mocks;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import br.com.coccionapi.srv_factor_cc.adapter.input.dto.requests.IngredientRequest;
import br.com.coccionapi.srv_factor_cc.adapter.input.dto.responses.CorrectionFactorResponse;
import br.com.coccionapi.srv_factor_cc.adapter.output.database.entity.CorrectionFactorEntity;
import br.com.coccionapi.srv_factor_cc.adapter.output.database.entity.IngredientEntity;
import br.com.coccionapi.srv_factor_cc.domain.model.CorrectionFactor;
import br.com.coccionapi.srv_factor_cc.domain.model.Ingredient;

public class IngredientMock {

    public final String name = "Banana";
    public final UUID ID = UUID.randomUUID();
    public final BigDecimal GROSS_WEIGHT = BigDecimal.valueOf(1000.01);
    public final BigDecimal NET_WEIGHT = BigDecimal.valueOf(800.00);
    public final BigDecimal RAW_WEIGHT = BigDecimal.valueOf(800.00);
    public final BigDecimal COOKED_WEIGHT = BigDecimal.valueOf(700.50);
    public final BigDecimal CORRECTION_FACTOR = BigDecimal.valueOf(0.875);
    public final LocalDateTime DATA_TIME_AT = LocalDateTime.now();

    public IngredientRequest createIngredientRequestFaker() {
        return IngredientRequest.builder()
                .name(name)
                .grossWeight(GROSS_WEIGHT)
                .netWeight(NET_WEIGHT)
                .rawWeight(RAW_WEIGHT)
                .cookedWeight(COOKED_WEIGHT)
                .build();
    }

    public Ingredient createIngredientFaker() {
        return Ingredient.builder()
                .name(name)
                .grossWeight(GROSS_WEIGHT)
                .netWeight(NET_WEIGHT)
                .rawWeight(RAW_WEIGHT)
                .cookedWeight(COOKED_WEIGHT)
                .registrationDate(DATA_TIME_AT)
                .build();
    }

    public IngredientEntity createIngredientEntityFaker() {
        return new IngredientEntity(
                ID,
                name,
                GROSS_WEIGHT,
                NET_WEIGHT,
                GROSS_WEIGHT,
                COOKED_WEIGHT,
                DATA_TIME_AT);
    }

    public CorrectionFactor createCorrectionFactorFaker() {
        return CorrectionFactor.builder()
                .id(ID)
                .ingredientId(ID)
                .grossWeight(GROSS_WEIGHT)
                .netWeight(NET_WEIGHT)
                .correctionFactor(CORRECTION_FACTOR)
                .calculatedAt(DATA_TIME_AT)
                .build();
    }

    public CorrectionFactorResponse createCorrectionFactorResponseFaker() {
        return CorrectionFactorResponse.builder()
                .id(ID)
                .ingredientId(ID)
                .grossWeight(GROSS_WEIGHT)
                .netWeight(NET_WEIGHT)
                .correctionFactor(CORRECTION_FACTOR)
                .calculatedAt(DATA_TIME_AT)
                .build();
    }

    public CorrectionFactorEntity createCorrectionFactorEntityFaker() {
        return new CorrectionFactorEntity(
                ID,
                createIngredientEntityFaker(),
                GROSS_WEIGHT,
                NET_WEIGHT,
                CORRECTION_FACTOR,
                DATA_TIME_AT);
    }
}
