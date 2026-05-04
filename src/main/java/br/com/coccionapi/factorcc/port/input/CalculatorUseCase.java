package br.com.coccionapi.factorcc.port.input;

import br.com.coccionapi.factorcc.domain.command.CalculatorCommand;
import br.com.coccionapi.factorcc.domain.model.CalculatorVO;

public interface CalculatorUseCase {

    CalculatorVO calculator(CalculatorCommand requestModel);
}
