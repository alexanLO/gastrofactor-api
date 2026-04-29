package br.com.coccionapi.factorcc.port.output;

import br.com.coccionapi.factorcc.domain.model.CookingFactor;

public interface CookingFactorPort {

    CookingFactor registerCookingFactor(CookingFactor cookingFactor);
    
}
