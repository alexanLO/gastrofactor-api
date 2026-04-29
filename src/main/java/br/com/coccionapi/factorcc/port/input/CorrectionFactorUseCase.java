package br.com.coccionapi.factorcc.port.input;

import java.util.UUID;

import br.com.coccionapi.factorcc.domain.model.CorrectionFactor;

public interface CorrectionFactorUseCase {

    CorrectionFactor calculateCorrectionFactor(UUID id);

}
