package br.com.coccionapi.srv_factor_cc.port.output;

import br.com.coccionapi.srv_factor_cc.domain.model.CookingFactor;

public interface CookingFactorPort {

    CookingFactor registerCookingFactor(CookingFactor cookingFactor);
    
}
