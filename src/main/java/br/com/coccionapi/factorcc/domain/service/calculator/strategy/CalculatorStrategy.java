package br.com.coccionapi.factorcc.domain.service.calculator.strategy;

import br.com.coccionapi.factorcc.domain.command.CalculatorCommand;
import br.com.coccionapi.factorcc.domain.enums.TypeWeightEnum;
import br.com.coccionapi.factorcc.domain.model.CalculatorVO;
import br.com.coccionapi.factorcc.domain.model.FoodYieldVO;

public interface CalculatorStrategy {

     boolean supports(TypeWeightEnum typeWeight);
    CalculatorVO calculate(CalculatorCommand command, FoodYieldVO foodYieldVO);
}
