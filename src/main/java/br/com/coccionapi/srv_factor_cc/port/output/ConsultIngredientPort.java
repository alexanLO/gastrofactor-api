package br.com.coccionapi.srv_factor_cc.port.output;

import java.util.UUID;

import br.com.coccionapi.srv_factor_cc.domain.model.Ingredient;

public interface ConsultIngredientPort {

    Ingredient searchingById(UUID id);
    
}
