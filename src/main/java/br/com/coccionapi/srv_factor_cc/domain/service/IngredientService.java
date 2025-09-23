package br.com.coccionapi.srv_factor_cc.domain.service;

import org.springframework.stereotype.Service;

import br.com.coccionapi.srv_factor_cc.domain.model.Ingredient;
import br.com.coccionapi.srv_factor_cc.port.input.IngredientUseCase;
import br.com.coccionapi.srv_factor_cc.port.output.IngredientPort;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IngredientService implements IngredientUseCase {

    private final IngredientPort ingredientPort;

    @Override
    public Ingredient register(Ingredient modelRequest) {
        return ingredientPort.register(modelRequest);
    }
}
