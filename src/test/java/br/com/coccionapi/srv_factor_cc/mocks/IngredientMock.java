package br.com.coccionapi.srv_factor_cc.mocks;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import br.com.coccionapi.srv_factor_cc.adapter.input.dto.requests.IngredientRequest;
import br.com.coccionapi.srv_factor_cc.adapter.output.database.entity.IngredientEntity;
import br.com.coccionapi.srv_factor_cc.domain.model.Ingredient;

public class IngredientMock {

    public final String name = "Banana";
    public final UUID id = UUID.randomUUID();
    public final BigDecimal grossWeight = BigDecimal.valueOf(1000.01);
    public final BigDecimal netWeight = BigDecimal.valueOf(800.00);
    public final BigDecimal weightRaw = BigDecimal.valueOf(800.00);
    public final BigDecimal weightCooked = BigDecimal.valueOf(700.50);
    public final LocalDateTime registrationDate = LocalDateTime.now();

    public IngredientRequest createIngredientRequestFaker() {
        return IngredientRequest.builder()
                .name(name)
                .grossWeight(grossWeight)
                .netWeight(netWeight)
                .rawWeight(weightRaw)
                .cookedWeight(weightCooked)
                .build();
    }

    public Ingredient createIngredientFaker() {
        return Ingredient.builder()
                .name(name)
                .grossWeight(grossWeight)
                .netWeight(netWeight)
                .rawWeight(weightRaw)
                .cookedWeight(weightCooked)
                .registrationDate(registrationDate)
                .build();
    }

    public IngredientEntity createIngredientEntityFaker() {
        return new IngredientEntity(
                id,
                name,
                grossWeight,
                netWeight,
                grossWeight,
                weightCooked,
                registrationDate);
    }
}
