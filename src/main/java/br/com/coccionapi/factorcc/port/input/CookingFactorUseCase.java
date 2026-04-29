package br.com.coccionapi.factorcc.port.input;

import java.util.UUID;

import br.com.coccionapi.factorcc.domain.model.CookingFactor;

public interface CookingFactorUseCase {

    CookingFactor calculateCookingFactor(UUID id);
    
}
