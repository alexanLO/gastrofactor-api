package br.com.coccionapi.factorcc.port.output;

import br.com.coccionapi.factorcc.domain.model.Ingredient;

public interface IngredientPort {

    Ingredient registerIngredient(Ingredient modelRequest);
    
}
