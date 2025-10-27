package br.com.coccionapi.srv_factor_cc.port.output;

import br.com.coccionapi.srv_factor_cc.domain.model.Ingredient;

public interface IngredientPort {

    Ingredient registerIngredient(Ingredient modelRequest);
    
}
