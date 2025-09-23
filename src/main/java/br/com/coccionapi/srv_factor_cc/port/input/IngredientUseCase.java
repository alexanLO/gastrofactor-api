package br.com.coccionapi.srv_factor_cc.port.input;

import br.com.coccionapi.srv_factor_cc.domain.model.Ingredient;

public interface IngredientUseCase {

    Ingredient register(Ingredient modelRequest);
    
}
