package br.com.coccionapi.srv_factor_cc.port.input;

import java.util.UUID;

import br.com.coccionapi.srv_factor_cc.domain.model.CookingFactor;

public interface CookingFactorUseCase {

    CookingFactor calculateCookingFactor(UUID id);
    
}
