package br.com.coccionapi.srv_factor_cc.port.output;

import br.com.coccionapi.srv_factor_cc.domain.model.CorrectionFactor;

public interface CorrectionFactorPort {

    CorrectionFactor register(CorrectionFactor correctionFactor);
    
}
