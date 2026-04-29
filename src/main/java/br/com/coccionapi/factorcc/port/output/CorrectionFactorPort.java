package br.com.coccionapi.factorcc.port.output;

import br.com.coccionapi.factorcc.domain.model.CorrectionFactor;

public interface CorrectionFactorPort {

    CorrectionFactor registerCorrectionFactor(CorrectionFactor correctionFactor);
    
}
