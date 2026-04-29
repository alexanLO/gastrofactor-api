package br.com.coccionapi.factorcc.port.output;

import java.util.UUID;

import br.com.coccionapi.factorcc.domain.model.Ingredient;

public interface ConsultIngredientPort {

    Ingredient searchingById(UUID id);
    
}
