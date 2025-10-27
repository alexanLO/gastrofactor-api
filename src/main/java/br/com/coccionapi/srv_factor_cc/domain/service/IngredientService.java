package br.com.coccionapi.srv_factor_cc.domain.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.coccionapi.srv_factor_cc.domain.model.CorrectionFactor;
import br.com.coccionapi.srv_factor_cc.domain.model.Ingredient;
import br.com.coccionapi.srv_factor_cc.port.input.CorrectionFactorUseCase;
import br.com.coccionapi.srv_factor_cc.port.input.IngredientUseCase;
import br.com.coccionapi.srv_factor_cc.port.output.ConsultIngredientPort;
import br.com.coccionapi.srv_factor_cc.port.output.CorrectionFactorPort;
import br.com.coccionapi.srv_factor_cc.port.output.IngredientPort;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IngredientService implements IngredientUseCase, CorrectionFactorUseCase {

    private static final String ERROR_VALUE_ZERO = "Peso bruto e líquido deve ser maior que zero.";

    private final IngredientPort ingredientPort;
    private final CorrectionFactorPort correctionFactorPort;
    private final ConsultIngredientPort consultIngredientPort;

    @Override
    public Ingredient register(Ingredient modelRequest) {
        return ingredientPort.registerIngredient(modelRequest);
    }

    @Override
    public CorrectionFactor calculateCorrectionFactor(UUID id) {

        Ingredient ingredient = consultIngredientPort.searchingById(id);

        if (ingredient.getGrossWeight().compareTo(BigDecimal.ZERO) <= 0
                || ingredient.getNetWeight().compareTo(BigDecimal.ZERO) <= 0) {
            // TODO criar exceptions personalizada invez de usar RunTime.
            throw new RuntimeException(ERROR_VALUE_ZERO);
        }

        // * Calculo do fator de correção */
        BigDecimal resultCalcFC = ingredient.getGrossWeight().divide(ingredient.getNetWeight());

        // * Salva o calculo no BD para consultas futuras e retorna o resultado */
        return correctionFactorPort.registerCF(
                CorrectionFactor.builder()
                        .ingredientId(ingredient.getId())
                        .grossWeight(ingredient.getGrossWeight())
                        .netWeight(ingredient.getNetWeight())
                        .correctionFactor(resultCalcFC)
                        .calculatedAt(LocalDateTime.now())
                        .build());
    }
}
