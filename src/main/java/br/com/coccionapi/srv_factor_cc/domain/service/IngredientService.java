package br.com.coccionapi.srv_factor_cc.domain.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.coccionapi.srv_factor_cc.domain.model.CookingFactor;
import br.com.coccionapi.srv_factor_cc.domain.model.CorrectionFactor;
import br.com.coccionapi.srv_factor_cc.domain.model.Ingredient;
import br.com.coccionapi.srv_factor_cc.exceptions.BusinessException;
import br.com.coccionapi.srv_factor_cc.port.input.CookingFactorUseCase;
import br.com.coccionapi.srv_factor_cc.port.input.CorrectionFactorUseCase;
import br.com.coccionapi.srv_factor_cc.port.input.IngredientUseCase;
import br.com.coccionapi.srv_factor_cc.port.output.ConsultIngredientPort;
import br.com.coccionapi.srv_factor_cc.port.output.CookingFactorPort;
import br.com.coccionapi.srv_factor_cc.port.output.CorrectionFactorPort;
import br.com.coccionapi.srv_factor_cc.port.output.IngredientPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class IngredientService implements IngredientUseCase, CorrectionFactorUseCase, CookingFactorUseCase {

    private static final String ERROR_GROSS_VALUE_ZERO = "Peso bruto e líquido deve ser maior que zero.";
    private static final String ERROR_RAW_VALUE_ZERO = "Peso bruto e líquido deve ser maior que zero.";

    private final IngredientPort ingredientPort;
    private final CorrectionFactorPort correctionFactorPort;
    private final ConsultIngredientPort consultIngredientPort;
    private final CookingFactorPort cookingFactorPort;

    @Override
    public Ingredient register(Ingredient modelRequest) {
        return ingredientPort.registerIngredient(modelRequest);
    }

    @Override
    public CorrectionFactor calculateCorrectionFactor(UUID id) {

        Ingredient ingredient = consultIngredientPort.searchingById(id);

        if (ingredient.getGrossWeight().compareTo(BigDecimal.ZERO) <= 0
                || ingredient.getNetWeight().compareTo(BigDecimal.ZERO) <= 0) {
           throw new BusinessException(HttpStatus.UNPROCESSABLE_ENTITY.value(), ERROR_GROSS_VALUE_ZERO);
        }

        // * Calculo do fator de correção */
        BigDecimal resultCalcFC = ingredient.getGrossWeight().divide(ingredient.getNetWeight(), 2,  RoundingMode.HALF_UP);

        // * Salva o calculo no BD para consultas futuras e retorna o resultado */
        return correctionFactorPort.registerCorrectionFactor(
                CorrectionFactor.builder()
                        .ingredientId(ingredient.getId())
                        .grossWeight(ingredient.getGrossWeight())
                        .netWeight(ingredient.getNetWeight())
                        .correctionFactor(resultCalcFC)
                        .calculatedAt(LocalDateTime.now())
                        .build());
    }

    @Override
    public CookingFactor calculateCookingFactor(UUID id) {

        Ingredient ingredient = consultIngredientPort.searchingById(id);

        if (ingredient.getRawWeight().compareTo(BigDecimal.ZERO) <= 0
                || ingredient.getCookedWeight().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessException(HttpStatus.UNPROCESSABLE_ENTITY.value(), ERROR_RAW_VALUE_ZERO);
        }

        BigDecimal resultCalcFC = ingredient.getCookedWeight().divide(ingredient.getRawWeight(), 2,  RoundingMode.HALF_UP);

        return cookingFactorPort.registerCookingFactor(CookingFactor.builder()
                .ingredientId(ingredient.getId())
                .cookedWeight(ingredient.getCookedWeight())
                .rawWeight(ingredient.getRawWeight())
                .cookingFactor(resultCalcFC)
                .calculatedAt(LocalDateTime.now())
                .build());
    }
}
