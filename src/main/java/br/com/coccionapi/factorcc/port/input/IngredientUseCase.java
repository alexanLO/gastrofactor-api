package br.com.coccionapi.factorcc.port.input;

import br.com.coccionapi.factorcc.domain.model.Ingredient;

public interface IngredientUseCase {

    Ingredient register(Ingredient modelRequest);
    
}
